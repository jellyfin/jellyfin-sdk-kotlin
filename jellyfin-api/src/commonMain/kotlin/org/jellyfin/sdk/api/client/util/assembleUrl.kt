package org.jellyfin.sdk.api.client.util

/**
 * Join a [baseUrl] with a [path] and query. [pathQuerystring] is already encoded, [query] is encoded here.
 */
internal expect fun assembleUrl(
	baseUrl: String,
	path: String,
	pathQuerystring: String?,
	query: List<Pair<String, String>>,
): String
