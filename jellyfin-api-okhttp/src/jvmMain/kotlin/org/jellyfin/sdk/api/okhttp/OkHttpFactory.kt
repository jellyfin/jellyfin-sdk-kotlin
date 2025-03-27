package org.jellyfin.sdk.api.okhttp

import kotlinx.coroutines.CoroutineScope
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.ApiClientFactory
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.sockets.SocketConnection
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import kotlin.time.toJavaDuration

/**
 * Create a new factory that creates OkHttp ApiClient instances and SocketConnections on a shared connection pool. All
 * instances will be built on top of the [base] okHttpClient.
 */
public class OkHttpFactory(
	public val base: okhttp3.OkHttpClient = okhttp3.OkHttpClient.Builder().build(),
) : ApiClientFactory, SocketConnectionFactory {
	override fun create(
		baseUrl: String?,
		accessToken: String?,
		clientInfo: ClientInfo,
		deviceInfo: DeviceInfo,
		httpClientOptions: HttpClientOptions,
		socketConnectionFactory: SocketConnectionFactory
	): ApiClient = OkHttpClient(
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
		scope: CoroutineScope
	): SocketConnection = OkHttpSocketConnection(
		client = createClient(clientOptions),
		scope = scope,
	)

	/**
	 * Create a new [okhttp3.OkHttpClient] instance with the [HttpClientOptions] applied to it.
	 */
	public fun createClient(httpClientOptions: HttpClientOptions): okhttp3.OkHttpClient = base.newBuilder()
		.followRedirects(httpClientOptions.followRedirects)
		.connectTimeout(httpClientOptions.connectTimeout.toJavaDuration())
		.callTimeout(httpClientOptions.requestTimeout.toJavaDuration())
		.readTimeout(httpClientOptions.socketTimeout.toJavaDuration())
		.writeTimeout(httpClientOptions.socketTimeout.toJavaDuration())
		.build()
}
