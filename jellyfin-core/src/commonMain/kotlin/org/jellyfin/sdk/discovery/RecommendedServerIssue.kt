package org.jellyfin.sdk.discovery

import org.jellyfin.sdk.model.ServerVersion

public sealed interface RecommendedServerIssue {
	/**
	 * No system information found. This could happen due too networking issues or an invalid server address.
	 */
	public data class MissingSystemInfo(public val throwable: Throwable?) : RecommendedServerIssue

	/**
	 * The product name in the system information is incorrect.
	 */
	public data class InvalidProductName(public val productName: String?) : RecommendedServerIssue

	/**
	 * No version found in system information.
	 */
	public object MissingVersion : RecommendedServerIssue {
		override fun toString(): String = this::class.simpleName!!
	}

	/**
	 * The SDK does not support the server version.
	 */
	public data class UnsupportedServerVersion(public val version: ServerVersion) : RecommendedServerIssue

	/**
	 * The SDK uses a newer version of the API compared to the server. While this normally shouldn't be a problem, it
	 * may potentially cause problems like crashes.
	 */
	public data class OutdatedServerVersion(public val version: ServerVersion) : RecommendedServerIssue

	/**
	 * The system information response was slow.
	 */
	public data class SlowResponse(public val responseTime: Long) : RecommendedServerIssue
}
