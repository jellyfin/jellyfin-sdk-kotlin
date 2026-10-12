package org.jellyfin.sdk.api.client.util

import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath

/**
 * Implementation of [assembleUrl] for platforms without OkHttp, matching its output.
 */
internal fun assembleUrlKtor(
	baseUrl: String,
	path: String,
	pathQuerystring: String?,
	query: List<Pair<String, String>>,
): String {
	val builder = URLBuilder(baseUrl)

	val basePath = builder.encodedPath.trimEnd('/')
	builder.encodedPath = canonicalizePath("$basePath/${path.trimStart('/')}")

	builder.encodedParameters.clear()
	if (!pathQuerystring.isNullOrEmpty()) {
		for (pair in pathQuerystring.split('&')) {
			if (pair.isEmpty()) continue
			val name = pair.substringBefore('=')
			val value = if ('=' in pair) pair.substringAfter('=') else null
			builder.encodedParameters.append(name, value ?: "")
		}
	}
	for ((name, value) in query) {
		builder.encodedParameters.append(encodeQueryComponent(name), encodeQueryComponent(value))
	}

	return builder.buildString()
}

private const val HEX = "0123456789ABCDEF"
private const val PATH_ENCODE_SET = " \"<>^`{}|\\?#"
private const val ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~+%"

/**
 * Percent-encode using the same rules as OkHttp's `HttpUrl.Builder.addQueryParameter`.
 */
private fun encodeQueryComponent(value: String): String = buildString {
	for (byte in value.encodeToByteArray()) {
		val int = byte.toInt() and 0xFF
		if (int < 0x20 || int >= 0x7F || ENCODE_SET.indexOf(int.toChar()) >= 0) {
			append('%')
			append(HEX[int shr 4])
			append(HEX[int and 0xF])
		} else {
			append(int.toChar())
		}
	}
}

/**
 * Canonicalize an already encoded path like OkHttp's `HttpUrl.Builder.encodedPath`: percent-encode characters that are
 * not allowed in a path, keep percent signs and resolve `.` and `..` segments.
 */
private fun canonicalizePath(path: String): String {
	val segments = mutableListOf<String>()

	for (segment in path.split('/')) {
		when (segment.lowercase()) {
			".", "%2e" -> Unit
			"..", ".%2e", "%2e.", "%2e%2e" -> if (segments.size > 1) segments.removeAt(segments.lastIndex)
			else -> segments.add(encodePathSegment(segment))
		}
	}

	// A trailing dot segment results in a trailing slash
	val last = path.substringAfterLast('/').lowercase()
	if (last in setOf(".", "%2e", "..", ".%2e", "%2e.", "%2e%2e")) segments.add("")

	return segments.joinToString("/").ifEmpty { "/" }
}

private fun encodePathSegment(segment: String): String = buildString {
	for (byte in segment.encodeToByteArray()) {
		val int = byte.toInt() and 0xFF
		val char = int.toChar()

		// OkHttp keeps percent signs as is, valid or not, because the path is considered already encoded
		if (int in 0x20 until 0x7F && PATH_ENCODE_SET.indexOf(char) < 0) {
			append(char)
		} else {
			append('%')
			append(HEX[int shr 4])
			append(HEX[int and 0xF])
		}
	}
}
