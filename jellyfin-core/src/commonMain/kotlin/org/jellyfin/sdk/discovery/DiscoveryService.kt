package org.jellyfin.sdk.discovery

import kotlinx.coroutines.flow.Flow
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo

/**
 * Service for discovery related functionality.
 */
public class DiscoveryService(
	private val jellyfin: Jellyfin,
) {
	private val localServerDiscovery by lazy {
		LocalServerDiscovery(jellyfin.options)
	}

	private val recommendedServerDiscovery by lazy {
		RecommendedServerDiscovery(jellyfin)
	}

	/**
	 * Parses the given [input] and tries to fix common mistakes. The returned candidates are in order
	 * of most secure followed by most likely to work.
	 *
	 * See [AddressCandidateHelper] for more information.
	 */
	public fun getAddressCandidates(input: String): Collection<String> = AddressCandidateHelper(input).run {
		addCommonCandidates()
		getCandidates()
	}

	/**
	 * Connects to the [servers] and returns a flow of scored servers in the same order as the input.
	 * Uses the [RecommendedServerDiscovery] rules to determine the recommendation.
	 *
	 * If the input in [servers] is the output from [getAddressCandidates] the optimal server to use
	 * will be the first returned server with a [RecommendedServerInfoScore.GREAT] score. If no server
	 * with [RecommendedServerInfoScore.GREAT] is returned the best option is the first server with a
	 * [RecommendedServerInfoScore.GOOD] score. The [RecommendedServerInfoScore.OK] and
	 * [RecommendedServerInfoScore.BAD] scored are not recommended for connection.
	 *
	 * Output is not sorted, a BAD match could be returned before a GOOD match.
	 * **Never just use the first result**.
	 */
	public suspend fun getRecommendedServers(
		servers: Collection<String>,
		minimumScore: RecommendedServerInfoScore = RecommendedServerInfoScore.BAD,
	): Collection<RecommendedServerInfo> = recommendedServerDiscovery.discover(
		servers = servers,
		minimumScore = minimumScore
	)

	/**
	 * Utility function that calls [getRecommendedServers] with the output of [getAddressCandidates].
	 */
	public suspend fun getRecommendedServers(
		input: String,
		minimumScore: RecommendedServerInfoScore = RecommendedServerInfoScore.BAD,
	): Collection<RecommendedServerInfo> = getRecommendedServers(
		servers = getAddressCandidates(input),
		minimumScore = minimumScore
	)

	/**
	 * Discover servers on the local network.
	 */
	public fun discoverLocalServers(
		timeout: Int = LocalServerDiscovery.DISCOVERY_TIMEOUT,
		maxServers: Int = LocalServerDiscovery.DISCOVERY_MAX_SERVERS,
	): Flow<ServerDiscoveryInfo> = localServerDiscovery.discover(
		timeout = timeout,
		maxServers = maxServers
	)
}
