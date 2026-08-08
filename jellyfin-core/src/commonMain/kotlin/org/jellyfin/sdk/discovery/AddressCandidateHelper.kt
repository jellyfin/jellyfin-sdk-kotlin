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
	 * - Trailing-slash form matching the user input when a subpath was supplied
	 *
	 * Non-root paths are returned both with and without a trailing slash so reverse
	 * proxies that treat the two forms differently can still be discovered.
	 */
	public fun getCandidates(): Collection<String>
}
