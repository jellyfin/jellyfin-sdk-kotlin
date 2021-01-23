package org.jellyfin.apiclient.discovery

import io.ktor.http.*
import org.slf4j.LoggerFactory

/**
 * Parses the given [input] and allows to fix common mistakes.
 */
public class AddressCandidateHelper(
	private val input: String
) {
	public companion object {
		/**
		 * Default HTTP port for Jellyfin
		 */
		public const val JF_HTTP_PORT: Int = 8096

		/**
		 * Default HTTPS port for Jellyfin
		 */
		public const val JF_HTTPS_PORT: Int = 8920

		/**
		 * Default base url for Jellyfin
		 */
		public const val JF_BASE_URL: String = "/jellyfin/"
	}

	private val candidates = mutableListOf<Url>()
	private val prioritizeComparator = Comparator<Url> { a, b -> b.score() - a.score() }
	private val logger = LoggerFactory.getLogger("AddressCandidateHelper")

	init {
		try {
			logger.debug("Input is $input")

			// Add the input as initial candidate
			candidates.add(URLBuilder().apply {
				// Ktor doesn't like urls not starting with a protocol
				// so we default to a http prefix
				if (!input.startsWith("http://") && !input.startsWith("https://"))
					takeFrom("http://$input")
				else
					takeFrom(input)
			}.build())
		} catch (error: URLParserException) {
			// Input can't be parsed
			logger.error("Input $input could not be parsed", error)
		}
	}

	/**
	 * Add a HTTPS candidate for each HTTP candidate
	 */
	public fun addProtocolCandidates() {
		logger.debug("Adding protocol candidates")

		// Add HTTPS candidate for each HTTP candidate
		candidates
			.filter { it.protocol == URLProtocol.HTTP }
			.forEach {
				candidates.add(it.copy(protocol = URLProtocol.HTTPS))
			}
	}

	/**
	 * Add a candidate using Jellyfin ports for each candidate without a specified port or
	 * protocol-default port.
	 */
	public fun addPortCandidates() {
		logger.debug("Adding port candidates")

		// Add HTTPS candidate for each HTTP candidate
		candidates
			.filter { it.port == it.protocol.defaultPort }
			.forEach {
				when (it.protocol) {
					URLProtocol.HTTP -> {
						candidates.add(it.copy(specifiedPort = JF_HTTP_PORT))
					}
					URLProtocol.HTTPS -> {
						candidates.add(it.copy(specifiedPort = JF_HTTP_PORT))
						candidates.add(it.copy(specifiedPort = JF_HTTPS_PORT))
					}
				}
			}
	}

	/**
	 * Add a base url ([JF_BASE_URL]) for each candidate with a root or no path.
	 */
	public fun addBaseUrlCandidates() {
		logger.debug("Adding base url candidates")

		candidates
			.filter { it.encodedPath.isEmpty() || it.encodedPath == "/" }
			.forEach {
				candidates.add(it.copy(encodedPath = JF_BASE_URL))
			}
	}

	/**
	 * Execute the fixes for common mistakes:
	 *
	 *   - Protocol
	 *   - Port
	 *
	 * Not included:
	 *
	 *   - BaseUrl
	 */
	public fun addCommonCandidates() {
		logger.debug("Adding common candidates")

		addProtocolCandidates()
		addPortCandidates()
		// Disabled by default since it doesn't affect most users
		// addBaseUrlCandidates()
	}

	private fun Url.score(): Int {
		// Start out with a score of 0
		var score = 0

		// Score based on protocol
		when (protocol) {
			URLProtocol.HTTPS -> score += 5
			else -> score -= 5 // HTTP is less secure, try a secure option first
		}

		// Score based on port
		when (port) {
			JF_HTTP_PORT -> score += 2
			JF_HTTPS_PORT -> score -= 1 // Using the Jellyfin HTTPS port is not common
		}

		// Prefer default port for used protocol
		if (protocol == URLProtocol.HTTPS && port == 443) score += 3
		if (protocol == URLProtocol.HTTP && port == 80) score += 3

		return score
	}

	/**
	 * Reorder all candidates based on priority.
	 *
	 *   - HTTPS above HTTP
	 *   - Jellyfin ports above default ports
	 */
	public fun prioritize() {
		logger.debug("Prioritizing candidates")
		candidates.sortWith(prioritizeComparator)
	}

	/**
	 * Get all deduplicated candidate URLs as strings.
	 * Call [prioritize] before if a sorted list is desired.
	 */
	public fun getCandidates(): List<String> = candidates
		.map { it.toString() }
		.distinct()
}
