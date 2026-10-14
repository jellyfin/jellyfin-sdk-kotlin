package org.jellyfin.sdk.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.websocket.WebSockets
import kotlinx.coroutines.CoroutineScope
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.ApiClientFactory
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.sockets.SocketConnection
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

/**
 * Create a new factory that creates Ktor ApiClient instances and SocketConnections. All instances will be built on
 * top of the [base] client.
 */
public class KtorFactory(
	public val base: HttpClient = HttpClient(),
) : ApiClientFactory, SocketConnectionFactory {
	override fun create(
		baseUrl: String?,
		accessToken: String?,
		clientInfo: ClientInfo,
		deviceInfo: DeviceInfo,
		httpClientOptions: HttpClientOptions,
		socketConnectionFactory: SocketConnectionFactory,
	): ApiClient = KtorClient(
		client = createClient(httpClientOptions),
		initialBaseUrl = baseUrl,
		initialAccessToken = accessToken,
		initialClientInfo = clientInfo,
		initialDeviceInfo = deviceInfo,
		httpClientOptions = httpClientOptions,
		socketConnectionFactory = socketConnectionFactory,
	)

	override fun create(
		clientOptions: HttpClientOptions,
		scope: CoroutineScope,
	): SocketConnection = KtorSocketConnection(
		client = createClient(clientOptions),
		scope = scope,
	)

	/**
	 * Create a new [HttpClient] instance with the [HttpClientOptions] applied to it.
	 */
	public fun createClient(httpClientOptions: HttpClientOptions): HttpClient = base.config {
		expectSuccess = false
		followRedirects = httpClientOptions.followRedirects

		install(HttpTimeout) {
			connectTimeoutMillis = httpClientOptions.connectTimeout.inWholeMilliseconds
			requestTimeoutMillis = httpClientOptions.requestTimeout.inWholeMilliseconds
			socketTimeoutMillis = httpClientOptions.socketTimeout.inWholeMilliseconds
		}

		install(WebSockets)
	}
}
