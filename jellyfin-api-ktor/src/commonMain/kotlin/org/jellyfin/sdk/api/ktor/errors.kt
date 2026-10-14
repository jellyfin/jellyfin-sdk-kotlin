package org.jellyfin.sdk.api.ktor

/**
 * True when the host could not be resolved or reached.
 */
internal expect fun Throwable.isUnreachableHostError(): Boolean

/**
 * True when the failure is caused by TLS.
 */
internal expect fun Throwable.isSecureConnectionError(): Boolean
