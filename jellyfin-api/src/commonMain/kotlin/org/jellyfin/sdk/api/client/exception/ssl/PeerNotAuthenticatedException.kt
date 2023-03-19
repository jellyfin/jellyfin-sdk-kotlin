package org.jellyfin.sdk.api.client.exception.ssl

import org.jellyfin.sdk.api.client.exception.SecureConnectionException

/**
 * An error occurred while attempting to establish a secure connection.
 * Indicates that the peer's identity has not been verified.
 * When the peer was not able to identify itself (for example; no certificate, wrong host, the particular cipher
 * suite being used does not support authentication, no peer authentication was established during SSL handshaking)
 * this exception is thrown.
 */
public class PeerNotAuthenticatedException(
	message: String, exception: Throwable
): SecureConnectionException(message, exception)
