package org.jellyfin.apiclient.discovery

enum class RecommendedServerInfoScore(
	val score: Int
) {
	GOOD(1),
	OK(0),
	BAD(-1)
}
