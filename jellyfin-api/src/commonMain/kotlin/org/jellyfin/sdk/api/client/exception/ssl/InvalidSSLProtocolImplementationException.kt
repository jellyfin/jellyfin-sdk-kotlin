package org.jellyfin.sdk.api.client.exception.ssl

import org.jellyfin.sdk.api.client.exception.SecureConnectionException

/**
 * An error occurred while attempting to establish a secure connection.
 * Normally this indicates a flaw in one of the protocol implementations.
 */
public class InvalidSSLProtocolImplementationException(
	message: String,
	exception: Throwable,
) : SecureConnectionException(message, exception)
