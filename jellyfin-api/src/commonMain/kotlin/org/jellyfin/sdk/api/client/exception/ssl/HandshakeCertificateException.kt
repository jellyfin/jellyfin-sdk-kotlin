package org.jellyfin.sdk.api.client.exception.ssl

import org.jellyfin.sdk.api.client.exception.SecureConnectionException

/***
 * An error occurred while attempting to establish a secure connection.
 * Indicates that the client and server could not negotiate the desired level of security or the certificate was
 * revoked.
 */
public class HandshakeCertificateException(
	message: String, exception: Throwable
): SecureConnectionException(message, exception)
