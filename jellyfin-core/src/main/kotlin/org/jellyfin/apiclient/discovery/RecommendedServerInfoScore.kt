package org.jellyfin.apiclient.discovery

public enum class RecommendedServerInfoScore(
	internal val score: Int
) {
	GOOD(1),
	OK(0),
	BAD(-1)
}
