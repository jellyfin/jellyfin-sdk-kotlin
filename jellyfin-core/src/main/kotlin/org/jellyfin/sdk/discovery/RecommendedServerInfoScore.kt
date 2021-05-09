package org.jellyfin.sdk.discovery

public enum class RecommendedServerInfoScore(
	internal val score: Int
) {
	/**
	 * Same as [GOOD] but none of the scoring tests failed.
	 */
	GREAT(2),

	/**
	 * Server responds properly and appears to be compatible with the SDK. The server might be slower to respond or use
	 * a slightly older api version.
	 */
	GOOD(1),

	/**
	 * The connection is not great (older server version, latency, not secure etc.) but the SDK should be able to
	 * communicate just fine.
	 */
	OK(0),

	/**
	 * Indicates a server was unable to reply or the reply was not valid. Generally not recommended to proceed with a
	 * connection.
	 */
	BAD(-1)
}
