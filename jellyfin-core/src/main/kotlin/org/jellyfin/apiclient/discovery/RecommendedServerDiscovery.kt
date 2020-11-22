package org.jellyfin.apiclient.discovery

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.operations.SystemApi
import org.jellyfin.apiclient.model.api.PublicSystemInfo
import org.jellyfin.apiclient.model.discovery.ServerVersion
import org.slf4j.LoggerFactory
import java.net.ConnectException

public class RecommendedServerDiscovery(
	private val jellyfin: Jellyfin
) {
	private val logger = LoggerFactory.getLogger("RecommendedServerDiscovery")

	private data class SystemInfoResult(
		val address: String,
		val systemInfo: PublicSystemInfo?,
		val responseTime: Long
	)

	private suspend fun getSystemInfoResult(address: String): SystemInfoResult? {
		logger.info("Requesting public system info for $address")

		val client = jellyfin.createApi(baseUrl = address)
		val api = SystemApi(client)

		val startTime = System.currentTimeMillis()
		@Suppress("TooGenericExceptionCaught")
		val info = try {
			api.getPublicSystemInfo()
		} catch (err: ConnectException) {
			logger.debug("Could not connect to $address")
			null
		} catch (err: Exception) {
			logger.error("Could not retrieve public system info for $address", err)
			null
		}
		val endTime = System.currentTimeMillis()

		return SystemInfoResult(
			address = address,
			systemInfo = if (info != null && info.status == 200) info.content else null,
			responseTime = endTime - startTime,
		)
	}

	private fun assignScore(result: SystemInfoResult, parentResult: SystemInfoResult? = null): RecommendedServerInfo {
		var points = 0

		// Security
		if (result.address.startsWith("https://")) points += 3

		// Speed
		when {
			result.responseTime < 500 -> points += 3
			result.responseTime < 1500 -> points += 2
			result.responseTime < 5000 -> points += 1
		}

		// Compatibility
		val version = result.systemInfo?.version?.let(ServerVersion::fromString)
		if (version != null) {
			if (version >= Jellyfin.apiVersion) points += 1

			if (version == Jellyfin.recommendedVersion) points += 2
			else if (version > Jellyfin.recommendedVersion) points += 1
		}

		// Minimum amount of points: 0
		// Maximum amount of points: 9
		val score = when {
			points < 3 -> RecommendedServerInfoScore.BAD
			points < 6 -> RecommendedServerInfoScore.OK
			else -> RecommendedServerInfoScore.GOOD
		}

		return RecommendedServerInfo(result.address, result.responseTime, score, result.systemInfo, parentResult?.address)
	}

	public suspend fun discover(
		servers: List<String>,
		includeAppendedServers: Boolean,
		minimumScore: RecommendedServerInfoScore
	): Flow<RecommendedServerInfo> = discover(
		servers = servers.asFlow(),
		includeAppendedServers = includeAppendedServers,
		minimumScore = minimumScore
	)

	public suspend fun discover(
		servers: Flow<String>,
		includeAppendedServers: Boolean,
		minimumScore: RecommendedServerInfoScore
	): Flow<RecommendedServerInfo> = withContext(Dispatchers.IO) {
		flow {
			servers.onEach parentEach@{ server ->
				val info = getSystemInfoResult(server) ?: return@parentEach

				if (includeAppendedServers) {
					// Check for child server before emitting parent server
					info.systemInfo?.localAddress?.let childLet@{ childServerAddress ->
						val childInfo = getSystemInfoResult(childServerAddress) ?: return@childLet

						// Emit info for child
						emit(assignScore(childInfo, info))
					}
				}

				// Emit info for server
				emit(assignScore(info))
			}.collect()
		}.filter {
			// Use [minimumScore] to filter out bad score matches
			it.score.score >= minimumScore.score
		}
	}
}
