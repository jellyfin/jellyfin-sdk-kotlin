package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.api.client.exception.MissingBaseUrlException
import org.jellyfin.sdk.api.client.util.UrlBuilder
import org.jellyfin.sdk.api.operations.Api
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.UUID
import kotlin.reflect.KClass

public abstract class ApiClient {
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
	public abstract var baseUrl: String?

	/**
	 * Access token to use for requests. Appended to all requests if set.
	 */
	public abstract var accessToken: String?

	/**
	 * User identifier that will automatically be used in user-specific API operations.
	 * Should correspond to the same user as [accessToken].
	 */
	public abstract var userId: UUID?

	/**
	 * Information about the client / application send in all API requests.
	 */
	public abstract var clientInfo: ClientInfo

	/**
	 * Information about the device send in all API requests. Only a single session is allowed per
	 * device id.
	 */
	public abstract var deviceInfo: DeviceInfo

	/**
	 * HTTP Options for this ApiClient.
	 */
	public abstract val httpClientOptions: HttpClientOptions

	/**
	 * Create a complete url based on the [baseUrl] and given parameters.
	 * Uses [UrlBuilder] to create the path from the [pathTemplate] and [pathParameters].
	 *
	 * When [includeCredentials] is true it will add the access token as query parameter using [QUERY_ACCESS_TOKEN]
	 * to the url to make an authenticated request.
	 */
	public open fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		includeCredentials: Boolean = false,
	): String = UrlBuilder.buildUrl(
		baseUrl ?: throw MissingBaseUrlException(),
		pathTemplate,
		pathParameters,
		queryParameters.run {
			if (includeCredentials) plus(QUERY_ACCESS_TOKEN to checkNotNull(accessToken))
			else this
		}
	)

	public abstract suspend fun request(
		method: HttpMethod = HttpMethod.GET,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): RawResponse

	private val apiInstances = mutableMapOf<KClass<out Api>, Api>()

	@Suppress("UNCHECKED_CAST")
	public fun <T : Api> getOrCreateApi(kclass: KClass<T>, create: (apiClient: ApiClient) -> T): T =
		apiInstances.getOrPut(kclass) {
			create(this)
		} as T

	public inline fun <reified T : Api> getOrCreateApi(noinline create: (apiClient: ApiClient) -> T): T =
		getOrCreateApi(T::class, create)
}
