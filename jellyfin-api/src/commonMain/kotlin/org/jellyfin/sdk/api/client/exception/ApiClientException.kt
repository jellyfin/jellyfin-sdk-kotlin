package org.jellyfin.sdk.api.client.exception

/**
 * Base exception for the ApiClient.
 */
public open class ApiClientException(
	message: String? = null,
	cause: Throwable? = null,
) : Exception(message, cause)
