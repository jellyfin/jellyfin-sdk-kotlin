package org.jellyfin.sdk.api.client.util

import okhttp3.HttpUrl.Companion.toHttpUrl

internal actual fun assembleUrl(
	baseUrl: String,
	path: String,
	pathQuerystring: String?,
	query: List<Pair<String, String>>,
): String {
	val url = baseUrl.toHttpUrl()
	return url.newBuilder().apply {
		// Assign path making sure to remove duplicated slashes between the base and appended path
		encodedPath("${url.encodedPath.trimEnd('/')}/${path.trimStart('/')}")
		encodedQuery(pathQuerystring)

		// Append query parameters
		for ((name, value) in query) addQueryParameter(name, value)
	}.build().toString()
}
