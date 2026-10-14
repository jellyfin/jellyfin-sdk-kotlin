package org.jellyfin.sdk.api.ktor

// Darwin wraps NSErrors in an exception with the error code in the message: cannot find host (-1003), cannot connect
// (-1004), DNS lookup failed (-1006) and the secure connection errors (-1200 to -1206)
private val UNREACHABLE_CODES = listOf("-1003", "-1004", "-1006")
private val SECURE_CODES = (1200..1206).map { "-$it" }

private fun Throwable.messages(): Sequence<String> =
	generateSequence(this) { it.cause }.mapNotNull { it.message }

private fun String.hasErrorCode(codes: List<String>): Boolean = codes.any {
	contains("Code=$it") || contains("code $it") || contains("($it)")
}

internal actual fun Throwable.isUnreachableHostError(): Boolean =
	messages().any { it.hasErrorCode(UNREACHABLE_CODES) || it.contains("host", ignoreCase = true) && it.contains("not found", ignoreCase = true) }

internal actual fun Throwable.isSecureConnectionError(): Boolean =
	messages().any {
		it.hasErrorCode(SECURE_CODES) ||
			it.contains("ssl", ignoreCase = true) ||
			it.contains("certificate", ignoreCase = true) ||
			it.contains("secure connection", ignoreCase = true)
	}
