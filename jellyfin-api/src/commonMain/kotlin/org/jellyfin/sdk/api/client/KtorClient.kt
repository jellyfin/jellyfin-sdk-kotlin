package org.jellyfin.sdk.api.client

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
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
	/**
	 * Internal HTTP client. Should not be used directly. Use [request] instead.
	 * Exposed publicly to allow inline functions to work.
	 */
	public val client: HttpClient

	public suspend inline fun <reified T> request(
		method: HttpMethod = HttpMethod.Get,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T>
}
