package org.jellyfin.apiclient.discovery

import kotlinx.coroutines.flow.takeWhile
import org.jellyfin.apiclient.Jellyfin

/**
 * Service for discovery related functionality
 */
class DiscoveryService(
	private val jellyfin: Jellyfin,
	private val discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider
) {
	private val localServerDiscovery by lazy {
		LocalServerDiscovery(discoveryBroadcastAddressesProvider)
	}

	private val recommendedServerDiscovery by lazy {
		RecommendedServerDiscovery(jellyfin)
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
	 * Connects to the [servers] and returns a list of responding servers ordered by input order.
	 * Uses multiple rules to determine the recommendation like:
	 *
	 *   - HTTPS above HTTP
	 *   - Higher Jellyfin version
	 *   - Respond time
	 *
	 * Also adds the addresses reported by the server into the mix when visiting the public server
	 * information. Reported addresses are inserted before their parent server.
	 *
	 * If the input in [servers] is the output from [getAddressCandidates] the optimal server to use
	 * will be the first returned server with a [RecommendedServerInfoScore] of GOOD.
	 *
	 * Optionally use [getRecommendedServer] to make this selection automatically.
	 */
	suspend fun getRecommendedServers(
		servers: List<String>,
		includeAppendedServers: Boolean = true,
		minimumScore: RecommendedServerInfoScore = RecommendedServerInfoScore.BAD
	) = recommendedServerDiscovery.discover(
		servers = servers,
		includeAppendedServers = includeAppendedServers,
		minimumScore = minimumScore
	)

	/**
	 * Utility function that calls [getRecommendedServers] with the output of [getAddressCandidates].
	 */
	suspend fun getRecommendedServers(
		input: String,
		includeAppendedServers: Boolean = true,
		minimumScore: RecommendedServerInfoScore = RecommendedServerInfoScore.BAD
	) = getRecommendedServers(
		servers = getAddressCandidates(input),
		includeAppendedServers = includeAppendedServers,
		minimumScore = minimumScore
	)

	/**
	 * Utility function that calls [getRecommendedServers] for inputted [servers] and returns the
	 * best candidate. Returns when a server with a score of [RecommendedServerInfoScore.GOOD] is
	 * found or otherwise collects all servers and returns the best one based on order and score.
	 */
	suspend fun getRecommendedServer(
		servers: List<String>,
		includeAppendedServers: Boolean = true
	): RecommendedServerInfo? {
		var best: RecommendedServerInfo? = null

		getRecommendedServers(servers, includeAppendedServers).takeWhile {
			// Select if it's better than current
			if (best == null || it.score.score > best!!.score.score)
				best = it

			// Take while score is not GOOD (highest possible value)
			best!!.score != RecommendedServerInfoScore.GOOD
		}

		return best
	}

	/**
	 * Discover servers on the local network. Uses the [discoveryBroadcastAddressesProvider] to
	 * find local devices to query.
	 */
	fun discoverLocalServers(
		timeout: Int = LocalServerDiscovery.DISCOVERY_TIMEOUT,
		maxServers: Int = LocalServerDiscovery.DISCOVERY_MAX_SERVERS
	) = localServerDiscovery.discover(
		timeout = timeout,
		maxServers = maxServers
	)
}
