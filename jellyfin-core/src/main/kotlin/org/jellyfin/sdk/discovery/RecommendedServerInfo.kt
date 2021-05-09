package org.jellyfin.sdk.discovery

import org.jellyfin.sdk.model.api.PublicSystemInfo

public data class RecommendedServerInfo(
	val address: String,
	val responseTime: Long,
	val score: RecommendedServerInfoScore,
	val systemInfo: PublicSystemInfo?,
)
