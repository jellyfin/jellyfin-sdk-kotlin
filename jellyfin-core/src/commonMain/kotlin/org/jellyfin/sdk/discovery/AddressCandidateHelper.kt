package org.jellyfin.sdk.discovery

/**
 * Parses the given [input] and allows to fix common mistakes.
 */
public expect class AddressCandidateHelper(
	input: String,
) {
	public companion object {
		/**
		 * Default HTTP port for Jellyfin
		 */
		public val JF_HTTP_PORT: Int

		/**
		 * Default HTTPS port for Jellyfin
		 */
		public val JF_HTTPS_PORT: Int
	}

	/**
	 * Add an HTTPS candidate for each HTTP candidate
	 */
	public fun addProtocolCandidates()

	/**
	 * Add a candidate using Jellyfin ports for each candidate without a specified port or
	 * protocol-default port.
	 */
	public fun addPortCandidates()

	/**
	 * Applies all fixes to the [input].
	 */
	public fun addCommonCandidates()

	/**
	 * Returns all unique candidates sorted by priority.
	 *
	 * The priority is based on a few rules:
	 * - HTTPS before HTTP
	 * - Jellyfin ports before protocol default ports
	 */
	public fun getCandidates(): Collection<String>
}
