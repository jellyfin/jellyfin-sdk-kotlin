package org.jellyfin.sdk.discovery

import io.ktor.http.URLBuilder
import io.ktor.http.URLParserException
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.http.takeFrom
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

/**
 * Parses the given [input] and allows to fix common mistakes.
 */
public actual class AddressCandidateHelper actual constructor(
	private val input: String,
) {
	public actual companion object {
		/**
		 * Default HTTP port for Jellyfin
		 */
		public const actual val JF_HTTP_PORT: Int = 8096

		/**
		 * Default HTTPS port for Jellyfin
		 */
		public const actual val JF_HTTPS_PORT: Int = 8920

		/**
		 * HTTP protocol prefix: http://
		 */
		private const val PROTOCOL_HTTP: String = "http://"

		/**
		 * HTTPS protocol prefix: https://
		 */
		private const val PROTOCOL_HTTPS: String = "https://"
	}

	private val candidates = mutableSetOf<Url>()
	private val prioritizeComparator = Comparator<Url> { a, b -> b.score() - a.score() }

	init {
		try {
			logger.debug { "Input is $input" }

			// Add the input as initial candidate
			if (input.isNotBlank()) {
				candidates.add(URLBuilder().apply {
					// Ktor doesn't like urls not starting with a protocol
					// so we default to a http prefix
					if (!input.startsWith(PROTOCOL_HTTP) && !input.startsWith(PROTOCOL_HTTPS))
						takeFrom(PROTOCOL_HTTP + input)
					else
						takeFrom(input)
				}.build())
			}
		} catch (error: URLParserException) {
			// Input can't be parsed
			logger.error(error) { "Input $input could not be parsed" }
		} catch (error: IllegalArgumentException) {
			// Input can't be parsed
			logger.error(error) { "Input $input could not be parsed" }
		}
	}

	/**
	 * Add an HTTPS candidate for each HTTP candidate
	 */
	public actual fun addProtocolCandidates() {
		logger.debug { "Adding protocol candidates" }

		// Add HTTPS candidate for each HTTP candidate
		candidates
			.filter { it.protocol == URLProtocol.Companion.HTTP }
			.forEach {
				candidates.add(URLBuilder(it).apply {
					protocol = URLProtocol.Companion.HTTPS
					if (port == URLProtocol.Companion.HTTP.defaultPort) port = URLProtocol.Companion.HTTPS.defaultPort
				}.build())
			}
	}

	/**
	 * Add a candidate using Jellyfin ports for each candidate without a specified port or
	 * protocol-default port.
	 */
	public actual fun addPortCandidates() {
		logger.debug { "Adding port candidates" }

		// Add HTTPS candidate for each HTTP candidate
		candidates
			.filter { it.port == it.protocol.defaultPort }
			.forEach {
				when (it.protocol) {
					URLProtocol.Companion.HTTP -> {
						candidates.add(URLBuilder(it).apply { port = JF_HTTP_PORT }.build())
					}

					URLProtocol.Companion.HTTPS -> {
						candidates.add(URLBuilder(it).apply { port = JF_HTTP_PORT }.build())
						candidates.add(URLBuilder(it).apply { port = JF_HTTPS_PORT }.build())
					}
				}
			}
	}

	/**
	 * Applies all fixes to the [input].
	 */
	public actual fun addCommonCandidates() {
		logger.debug { "Adding common candidates" }

		addProtocolCandidates()
		addPortCandidates()
	}

	@Suppress("MagicNumber")
	private fun Url.score(): Int {
		// Start out with a score of 0
		var score = 0

		// Score based on protocol
		when (protocol) {
			URLProtocol.Companion.HTTPS -> score += 5
			else -> score -= 5 // HTTP is less secure, try a secure option first
		}

		// Score based on port
		when (port) {
			JF_HTTP_PORT -> score += 2
			JF_HTTPS_PORT -> score -= 1 // Using the Jellyfin HTTPS port is not common
		}

		// Prefer default port for used protocol
		if (protocol == URLProtocol.Companion.HTTPS && port == 443) score += 3
		if (protocol == URLProtocol.Companion.HTTP && port == 80) score += 3

		return score
	}

	/**
	 * Returns all unique candidates sorted by priority.
	 *
	 * The priority is based on a few rules:
	 * - HTTPS before HTTP
	 * - Jellyfin ports before protocol default ports
	 */
	public actual fun getCandidates(): Collection<String> = candidates
		.sortedWith(prioritizeComparator)
		.map { it.toString() }
}
