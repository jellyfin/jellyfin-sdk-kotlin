package org.jellyfin.sdk.api.client.util

internal fun String.encodeURLPart(): String = buildString {
	this@encodeURLPart.forEach { char ->
		when {
			char.isUnreserved() -> append(char)
			char == ' ' -> append('+')
			else -> char
				.toString()
				.encodeToByteArray()
				.forEach { byte -> append("%", byte.toUByte().toString(16).uppercase().padStart(2, '0')) }
		}
	}
}

internal fun Char.isUnreserved() =
	this in 'A'..'Z' ||
		this in 'a'..'z' ||
		this in '0'..'9' ||
		this in arrayOf('-', '_', '.', '~')

internal fun String.extractQuerystring(): Pair<String, String?> {
	val path = substringBefore('?')
	val querystring = if (path.length < length) substring(path.length + 1) else null

	return path to querystring
}
