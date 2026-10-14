package org.jellyfin.sdk.discovery

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.URLBuilder
import io.ktor.http.URLParserException
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

private val logger = KotlinLogging.logger {}

/**
 * Implementation of [AddressCandidateHelper] for platforms without OkHttp.
 */
internal class AddressCandidates(private val input: String) {
	private companion object {
		const val JF_HTTP_PORT = 8096
		const val JF_HTTPS_PORT = 8920
		const val PROTOCOL_HTTP = "http://"
		const val PROTOCOL_HTTPS = "https://"
	}

	private val candidates = linkedSetOf<Url>()

	init {
		try {
			logger.debug { "Input is $input" }

			if (input.isNotBlank()) {
				val withProtocol = if (input.startsWith(PROTOCOL_HTTP, ignoreCase = true) ||
					input.startsWith(PROTOCOL_HTTPS, ignoreCase = true)
				) {
					input
				} else {
					PROTOCOL_HTTP + input
				}

				val url = Url(withProtocol)
				require(url.host.isNotBlank()) { "Missing host" }

				candidates.add(URLBuilder(url).apply { host = canonicalHost(host) }.build())
			}
		} catch (error: IllegalArgumentException) {
			// Input can't be parsed
			logger.error(error) { "Input $input could not be parsed" }
		} catch (error: URLParserException) {
			// Input can't be parsed
			logger.error(error) { "Input $input could not be parsed" }
		}
	}

	private val Url.isHttps get() = protocol == URLProtocol.HTTPS

	private fun Url.withPort(port: Int): Url = URLBuilder(this).apply { this.port = port }.build()

	fun addProtocolCandidates() {
		logger.debug { "Adding protocol candidates" }

		candidates
			.toList()
			.filterNot { it.isHttps }
			.forEach {
				candidates.add(URLBuilder(it).apply {
					protocol = URLProtocol.HTTPS
					if (it.port == URLProtocol.HTTP.defaultPort) port = URLProtocol.HTTPS.defaultPort
				}.build())
			}
	}

	fun addPortCandidates() {
		logger.debug { "Adding port candidates" }

		candidates
			.toList()
			.filter { it.port == it.protocol.defaultPort }
			.forEach {
				candidates.add(it.withPort(JF_HTTP_PORT))
				if (it.isHttps) candidates.add(it.withPort(JF_HTTPS_PORT))
			}
	}

	fun addCommonCandidates() {
		logger.debug { "Adding common candidates" }

		addProtocolCandidates()
		addPortCandidates()
	}

	@Suppress("MagicNumber")
	private fun Url.score(): Int {
		var score = 0

		// HTTP is less secure, try a secure option first
		score += if (isHttps) 5 else -5

		when (port) {
			JF_HTTP_PORT -> score += 2
			JF_HTTPS_PORT -> score -= 1 // Using the Jellyfin HTTPS port is not common
		}

		// Prefer default port for used protocol
		if (isHttps && port == 443) score += 3
		if (!isHttps && port == 80) score += 3

		return score
	}

	fun getCandidates(): Collection<String> = candidates
		.sortedByDescending { it.score() }
		.map { it.toString().trimEnd('/') + if (it.encodedPath.trimEnd('/').isNotEmpty()) '/' else "" }
}

private const val IPV6_GROUPS = 8

/**
 * Normalize the host the same way OkHttp does: lowercase hostnames and RFC 5952 IPv6 addresses.
 */
private fun canonicalHost(host: String): String {
	if (':' !in host) return host.lowercase()

	val bracketed = host.startsWith('[') && host.endsWith(']')
	val canonical = canonicalIpv6(if (bracketed) host.substring(1, host.length - 1) else host)
	return if (bracketed && ':' in canonical) "[$canonical]" else canonical
}

@Suppress("MagicNumber")
private fun canonicalIpv6(address: String): String {
	val groups = parseIpv6(address)

	// IPv4-mapped addresses are written as IPv4
	if ((0 until 5).all { groups[it] == 0 } && groups[5] == 0xffff) {
		return listOf(groups[6] shr 8, groups[6] and 0xff, groups[7] shr 8, groups[7] and 0xff).joinToString(".")
	}

	// Compress the first longest run of at least two zero groups
	var runStart = -1
	var runLength = 1
	var i = 0
	while (i < IPV6_GROUPS) {
		val start = i
		while (i < IPV6_GROUPS && groups[i] == 0) i++
		if (i - start > runLength) {
			runStart = start
			runLength = i - start
		}
		i++
	}

	if (runStart == -1) return groups.joinToString(":") { it.toString(16) }

	val head = groups.take(runStart).joinToString(":") { it.toString(16) }
	val tail = groups.drop(runStart + runLength).joinToString(":") { it.toString(16) }
	return "$head::$tail"
}

@Suppress("MagicNumber")
private fun parseIpv6(address: String): IntArray {
	val sides = address.split("::")
	require(sides.size <= 2) { "Multiple :: in $address" }

	val parsed = sides.mapIndexed { sideIndex, side ->
		if (side.isEmpty()) return@mapIndexed emptyList()

		val parts = side.split(':')
		parts.flatMapIndexed { index, part ->
			if ('.' in part) {
				require(index == parts.lastIndex && sideIndex == sides.lastIndex) { "Misplaced IPv4 in $address" }
				val bytes = part.split('.').map { byte ->
					require(byte.length in 1..3 && byte.all { it in '0'..'9' }) { "Invalid IPv4 in $address" }
					require(byte.length == 1 || byte[0] != '0') { "Invalid IPv4 in $address" }
					byte.toInt().also { require(it <= 255) { "Invalid IPv4 in $address" } }
				}
				require(bytes.size == 4) { "Invalid IPv4 in $address" }
				listOf((bytes[0] shl 8) or bytes[1], (bytes[2] shl 8) or bytes[3])
			} else {
				require(part.length in 1..4) { "Invalid group in $address" }
				listOf(part.toIntOrNull(16) ?: throw IllegalArgumentException("Invalid group in $address"))
			}
		}
	}

	val head = parsed[0]
	val tail = parsed.getOrElse(1) { emptyList() }
	val groups = IntArray(IPV6_GROUPS)

	if (sides.size == 1) {
		require(head.size == IPV6_GROUPS) { "Invalid group count in $address" }
		head.forEachIndexed { index, group -> groups[index] = group }
	} else {
		require(head.size + tail.size < IPV6_GROUPS) { "Invalid group count in $address" }
		head.forEachIndexed { index, group -> groups[index] = group }
		tail.forEachIndexed { index, group -> groups[IPV6_GROUPS - tail.size + index] = group }
	}

	return groups
}
