package org.jellyfin.apiclient.discovery

/**
 * Service for discovery related functionality
 */
class DiscoveryService(
	private val discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider
) {
	private val localServerDiscovery by lazy {
		// Create instance
		LocalServerDiscovery(discoveryBroadcastAddressesProvider)
	}

	/**
	 * Parses the given [input] and tries to fix common mistakes.
	 *
	 *   - Add protocol (https and http)
	 *   - Add port (8096 for http and 8920 for https)
	 *   - Add baseurl (/jellyfin)
	 *
	 * The returned candidates are in order of most secure followed by most likely to work.
	 * See [AddressCandidateHelper] for more information.
	 */
	fun getAddressCandidates(input: String) = AddressCandidateHelper(input).run {
		addCommonCandidates()
		prioritize()
		getCandidates()
	}

	/**
	 * Discover servers on the local network. Uses the [discoveryBroadcastAddressesProvider] to
	 * find local devices to query.
	 */
	fun discoverLocalServers(
		timeout: Int = LocalServerDiscovery.DISCOVERY_TIMEOUT,
		maxServers: Int = LocalServerDiscovery.DISCOVERY_MAX_SERVERS
	) = localServerDiscovery.discover(
		timeout,
		maxServers
	)
}
