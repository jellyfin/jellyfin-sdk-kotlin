package org.jellyfin.sdk.api.client.exception

/**
 * The connection timed out. This can happen when the host is unreachable or the network is offline.
 */
public class TimeoutException(
	message: String? = null,
	cause: Throwable? = null,
) : ApiClientException(message, cause)
