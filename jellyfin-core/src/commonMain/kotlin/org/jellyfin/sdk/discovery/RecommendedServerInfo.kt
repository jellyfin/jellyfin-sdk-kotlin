package org.jellyfin.sdk.discovery

import org.jellyfin.sdk.model.api.PublicSystemInfo

public data class RecommendedServerInfo(
	val address: String,
	val responseTime: Long,
	val score: RecommendedServerInfoScore,
	val issues: Collection<RecommendedServerIssue>,
	val systemInfo: Result<PublicSystemInfo>,
	val originalAddress: String? = null,
) {
	/**
	 * The issues are ordered by importance. When showing a single issue to an end user you
	 * normally want to show the first one.
	 */
	public fun firstIssueOrNull(): RecommendedServerIssue? = issues.firstOrNull()

	public fun isRedirect(): Boolean = originalAddress != null && originalAddress != address
}
