package org.jellyfin.apiclient.api.client

interface ApiClient {
	var baseUrl: String?

	/**
	 * Replaces the variables in a path with the values in the map.
	 * Will also remove trailing and repeated slashes
	 */
	fun createPath(
		path: String,
		pathParameters: Map<String, Any?> = emptyMap()
	): String

	/**
	 * Create a complete url based on the [baseUrl] and given parameters.
	 * Uses [createPath] to create the path
	 */
	fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
	): String

	fun createAuthorizationHeader(): String?
}
