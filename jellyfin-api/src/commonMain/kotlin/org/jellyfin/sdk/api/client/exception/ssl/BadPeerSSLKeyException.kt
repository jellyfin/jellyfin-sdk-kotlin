package org.jellyfin.sdk.api.client.exception.ssl

import org.jellyfin.sdk.api.client.exception.SecureConnectionException

/**
 * An error occurred while attempting to establish a secure connection.
 * This can happen when a bad key format is given.
 */
public class BadPeerSSLKeyException(
	message: String,
	exception: Throwable,
) : SecureConnectionException(message, exception)
