package org.jellyfin.sdk.api.client.exception

/**
 * Base exception for the ApiClient.
 */
public abstract class ApiClientException(
	message: String?,
) : Exception(message)
