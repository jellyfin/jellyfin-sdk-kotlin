package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.UUID

public expect open class KtorClient(
	baseUrl: String? = null,
	accessToken: String? = null,
	userId: UUID? = null,
	clientInfo: ClientInfo,
	deviceInfo: DeviceInfo,
	httpClientOptions: HttpClientOptions,
) : ApiClient {
	public override suspend fun request(
		method: HttpMethod,
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		requestBody: Any?,
	): RawResponse
}
