package org.jellyfin.sdk.discovery

import io.github.oshai.kotlinlogging.KotlinLogging
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

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
		public actual const val JF_HTTP_PORT: Int = 8096

		/**
		 * Default HTTPS port for Jellyfin
		 */
		public actual const val JF_HTTPS_PORT: Int = 8920

		/**
		 * HTTP protocol prefix: http://
		 */
		private const val PROTOCOL_HTTP: String = "http://"

		/**
		 * HTTPS protocol prefix: https://
		 */
		private const val PROTOCOL_HTTPS: String = "https://"
	}

	private val candidates = mutableSetOf<HttpUrl>()
	private val prioritizeComparator = Comparator<HttpUrl> { a, b -> b.score() - a.score() }

	init {
		try {
			logger.debug { "Input is $input" }

			// Add the input as initial candidate
			if (input.isNotBlank()) {
				if (input.startsWith(PROTOCOL_HTTP, ignoreCase = true) || input.startsWith(PROTOCOL_HTTPS, ignoreCase = true)) {
					candidates.add(input.toHttpUrl())
				} else {
					candidates.add((PROTOCOL_HTTP + input).toHttpUrl())
				}
			}
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
			.filterNot { it.isHttps }
			.forEach {
				candidates.add(it.newBuilder().apply {
					scheme("https")
					if (it.port == HttpUrl.defaultPort("http")) port(HttpUrl.defaultPort("https"))
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
			.filter { it.port == HttpUrl.defaultPort(it.scheme) }
			.forEach {
				when {
					it.isHttps -> {
						candidates.add(it.newBuilder().apply { port(JF_HTTP_PORT) }.build())
						candidates.add(it.newBuilder().apply { port(JF_HTTPS_PORT) }.build())
					}

					else -> {
						candidates.add(it.newBuilder().apply { port(JF_HTTP_PORT) }.build())
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
	private fun HttpUrl.score(): Int {
		// Start out with a score of 0
		var score = 0

		// Score based on protocol
		when {
			isHttps -> score += 5
			else -> score -= 5 // HTTP is less secure, try a secure option first
		}

		// Score based on port
		when (port) {
			JF_HTTP_PORT -> score += 2
			JF_HTTPS_PORT -> score -= 1 // Using the Jellyfin HTTPS port is not common
		}

		// Prefer default port for used protocol
		if (isHttps && port == 443) score += 3
		if (!isHttps && port == 80) score += 3

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
		.map { it.toString().trimEnd('/') }
}
