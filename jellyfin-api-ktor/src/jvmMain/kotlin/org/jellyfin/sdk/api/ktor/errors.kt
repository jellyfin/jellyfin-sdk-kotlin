package org.jellyfin.sdk.api.ktor

import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

internal actual fun Throwable.isUnreachableHostError(): Boolean =
	generateSequence(this) { it.cause }.any { it is UnknownHostException || it is ConnectException }

internal actual fun Throwable.isSecureConnectionError(): Boolean =
	generateSequence(this) { it.cause }.any { it is SSLException }
