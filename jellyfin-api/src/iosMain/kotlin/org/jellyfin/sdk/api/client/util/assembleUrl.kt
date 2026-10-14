package org.jellyfin.sdk.api.client.util

internal actual fun assembleUrl(
	baseUrl: String,
	path: String,
	pathQuerystring: String?,
	query: List<Pair<String, String>>,
): String = assembleUrlKtor(baseUrl, path, pathQuerystring, query)
