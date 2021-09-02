package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.api.client.util.UrlBuilder
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.UUID

public interface ApiClient {
	public companion object {
		/**
		 * The query parameter to use for access tokens. Used in the [createUrl] function when includeCredentials is
		 * true.
		 */
		public const val QUERY_ACCESS_TOKEN: String = "ApiKey"
	}

	/**
	 * URL to use as base for API endpoints. Should include the protocol and may contain a path.
	 */
	public var baseUrl: String?

	/**
	 * Access token to use for requests. Appended to all requests if set.
	 */
	public var accessToken: String?

	/**
	 * User identifier that will automatically be used in user-specific API operations.
	 * Should correspond to the same user as [accessToken].
	 */
	public var userId: UUID?

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
	 * HTTP Options for this ApiClient.
	 */
	public val httpClientOptions: HttpClientOptions

	/**
	 * Create a complete url based on the [baseUrl] and given parameters.
	 * Uses [UrlBuilder] to create the path from the [pathTemplate] and [pathParameters].
	 *
	 * When [includeCredentials] is true it will add the access token as query parameter using [QUERY_ACCESS_TOKEN]
	 * to the url to make an authenticated request.
	 */
	public fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		includeCredentials: Boolean = false,
	): String = UrlBuilder.buildUrl(
		requireNotNull(baseUrl),
		pathTemplate,
		pathParameters,
		queryParameters.run {
			if (includeCredentials) plus(QUERY_ACCESS_TOKEN to checkNotNull(accessToken))
			else this
		}
	)
}
