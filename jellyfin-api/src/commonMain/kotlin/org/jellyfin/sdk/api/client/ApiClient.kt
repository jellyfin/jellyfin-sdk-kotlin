package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.api.client.exception.MissingBaseUrlException
import org.jellyfin.sdk.api.client.util.UrlBuilder
import org.jellyfin.sdk.api.operations.Api
import org.jellyfin.sdk.api.sockets.SocketApi
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import kotlin.reflect.KClass

private const val UPDATE_DEPRECATION_MESSAGE =
	"This property should not be set directly as changes are not propagated to the WebSocket connection." +
		" Migrate to using the update() function instead."

public abstract class ApiClient {
	public companion object {
		/**
		 * The query parameter to use for access tokens.
		 */
		public const val QUERY_ACCESS_TOKEN: String = "ApiKey"

		/**
		 * The recommended value for the accept header. It prefers JSON followed by octet stream and finally
		 * everything. The "any MIME type" (* / *) is required for some endpoints in the server.
		 */
		public const val HEADER_ACCEPT: String = "application/json, application/octet-stream;q=0.9, */*;q=0.8"
	}

	/**
	 * URL to use as base for API endpoints. Should include the protocol and may contain a path.
	 */
	public abstract var baseUrl: String?
		@Deprecated(UPDATE_DEPRECATION_MESSAGE) set

	/**
	 * Access token to use for requests. Appended to all requests if set.
	 */
	public abstract var accessToken: String?
		@Deprecated(UPDATE_DEPRECATION_MESSAGE) set

	/**
	 * Information about the client / application send in all API requests.
	 */
	public abstract var clientInfo: ClientInfo
		@Deprecated(UPDATE_DEPRECATION_MESSAGE) set

	/**
	 * Information about the device send in all API requests. Only a single session is allowed per
	 * device id.
	 */
	public abstract var deviceInfo: DeviceInfo
		@Deprecated(UPDATE_DEPRECATION_MESSAGE) set

	/**
	 * HTTP Options for this ApiClient.
	 */
	public abstract val httpClientOptions: HttpClientOptions

	/**
	 * Change the authorization values used in this ApiClient instance.
	 */
	public abstract fun update(
		baseUrl: String? = this.baseUrl,
		accessToken: String? = this.accessToken,
		clientInfo: ClientInfo = this.clientInfo,
		deviceInfo: DeviceInfo = this.deviceInfo,
	)

	/**
	 * Create a complete url based on the [baseUrl] and given parameters.
	 * Uses [UrlBuilder] to create the path from the [pathTemplate] and [pathParameters].
	 *
	 * When [ignorePathParameters] is true, the [pathParameters] will be ignored and [pathTemplate] will not be parsed.
	 */
	public open fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		ignorePathParameters: Boolean = false,
	): String = UrlBuilder.buildUrl(
		baseUrl ?: throw MissingBaseUrlException(),
		pathTemplate,
		pathParameters,
		queryParameters,
		ignorePathParameters,
	)

	public abstract suspend fun request(
		method: HttpMethod = HttpMethod.GET,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
		expectedResponse: IntRange = 200 until 300,
	): RawResponse

	/**
	 * Get the instance of the SocketApi for this ApiClient.
	 * @see SocketApi
	 */
	public abstract val webSocket: SocketApi

	private val apiInstances = mutableMapOf<KClass<out Api>, Api>()

	@Suppress("UNCHECKED_CAST")
	public fun <T : Api> getOrCreateApi(kclass: KClass<T>, create: (apiClient: ApiClient) -> T): T =
		apiInstances.getOrPut(kclass) {
			create(this)
		} as T

	public inline fun <reified T : Api> getOrCreateApi(noinline create: (apiClient: ApiClient) -> T): T =
		getOrCreateApi(T::class, create)
}
