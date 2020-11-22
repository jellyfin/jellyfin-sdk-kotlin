package org.jellyfin.apiclient.discovery

import org.jellyfin.apiclient.model.api.PublicSystemInfo

public data class RecommendedServerInfo(
	val address: String,
	val responseTime: Long,
	val score: RecommendedServerInfoScore,
	val systemInfo: PublicSystemInfo?,
	val parent: String?
) {
	/**
	 * True when this server was not a part of the inputted addresses, false otherwise.
	 */
	val isAppended: Boolean = parent != null
}
