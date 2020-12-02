package org.jellyfin.apiclient.api.client

public interface ApiClient {
	public var baseUrl: String?

	/**
	 * Replaces the variables in a path with the values in the map.
	 * Will also remove trailing and repeated slashes
	 */
	public fun createPath(
		path: String,
		pathParameters: Map<String, Any?> = emptyMap()
	): String

	/**
	 * Create a complete url based on the [baseUrl] and given parameters.
	 * Uses [createPath] to create the path.
	 *
	 * When [includeCredentials] is true it will add the access token to the
	 * url to make an authenticated request.
	 */
	public fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		includeCredentials: Boolean = false
	): String

	public fun createAuthorizationHeader(): String?
}
