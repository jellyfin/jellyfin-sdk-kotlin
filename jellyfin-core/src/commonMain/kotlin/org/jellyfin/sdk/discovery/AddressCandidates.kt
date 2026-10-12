package org.jellyfin.sdk.discovery

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.URLBuilder
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

				candidates.add(URLBuilder(url).apply { host = host.lowercase() }.build())
			}
		} catch (error: IllegalArgumentException) {
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
