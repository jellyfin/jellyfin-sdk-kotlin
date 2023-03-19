package org.jellyfin.sdk.api.client.exception

/**
 * An error occurred while attempting to establish a secure connection. This can happen when the server doesn't have
 * HTTPS enabled or the certificate is invalid.
 */
public open class SecureConnectionException(
	message: String? = null,
	cause: Throwable? = null,
) : ApiClientException(message, cause)
