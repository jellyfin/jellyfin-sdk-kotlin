package org.jellyfin.sdk.api.ktor

import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HeadResponse
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.HttpMethod
import org.jellyfin.sdk.api.client.RawResponse
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

@Suppress("LongParameterList")
public expect open class KtorClient(
	baseUrl: String? = null,
	accessToken: String? = null,
	clientInfo: ClientInfo,
	deviceInfo: DeviceInfo,
	httpClientOptions: HttpClientOptions,
	socketConnectionFactory: SocketConnectionFactory,
) : ApiClient {
	public override suspend fun request(
		method: HttpMethod,
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		requestBody: Any?,
		expectedResponseCodes: IntRange,
	): RawResponse

	public override suspend fun headRequest(
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		expectedResponseCodes: IntRange,
	): HeadResponse
}
