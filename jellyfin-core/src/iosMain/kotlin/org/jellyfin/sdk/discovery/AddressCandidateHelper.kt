package org.jellyfin.sdk.discovery

/**
 * Parses the given [input] and allows to fix common mistakes.
 */
public actual class AddressCandidateHelper actual constructor(
	input: String,
) {
	public actual companion object {
		/**
		 * Default HTTP port for Jellyfin
		 */
		public actual val JF_HTTP_PORT: Int = 8096

		/**
		 * Default HTTPS port for Jellyfin
		 */
		public actual val JF_HTTPS_PORT: Int = 8920
	}

	private val delegate = AddressCandidates(input)

	/**
	 * Add an HTTPS candidate for each HTTP candidate
	 */
	public actual fun addProtocolCandidates(): Unit = delegate.addProtocolCandidates()

	/**
	 * Add a candidate using Jellyfin ports for each candidate without a specified port or
	 * protocol-default port.
	 */
	public actual fun addPortCandidates(): Unit = delegate.addPortCandidates()

	/**
	 * Applies all fixes to the [input].
	 */
	public actual fun addCommonCandidates(): Unit = delegate.addCommonCandidates()

	/**
	 * Returns all unique candidates sorted by priority.
	 *
	 * The priority is based on a few rules:
	 * - HTTPS before HTTP
	 * - Jellyfin ports before protocol default ports
	 */
	public actual fun getCandidates(): Collection<String> = delegate.getCandidates()
}
