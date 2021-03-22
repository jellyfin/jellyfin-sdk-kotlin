package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

public interface ApiClient {
	/**
	 * URL to use as base for API endpoints. Should include the protocol and may contain a path.
	 */
	public var baseUrl: String?

	/**
	 * Access token to use for requests. Appended to all requests if set.
	 */
	public var accessToken: String?

	/**
	 * Information about the client / application send in all API requests.
	 */
	public var clientInfo: ClientInfo

	/**
	 * Information about the device send in all API requests. Only a single session is allowed per
	 * device id.
	 */
	public var deviceInfo: DeviceInfo

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

	/**
	 * Create the value of the "Authorization" header.
	 */
	public fun createAuthorizationHeader(): String?
}
