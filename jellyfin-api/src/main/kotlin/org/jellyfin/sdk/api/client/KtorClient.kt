package org.jellyfin.sdk.api.client

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

public expect open class KtorClient : ApiClient {
	/**
	 * Internal HTTP client. Should not be used directly. Use [request] instead.
	 * Exposed publicly to allow inline functions to work.
	 */
	public val client: HttpClient

	@Suppress("ThrowsCount")
	public suspend inline fun <reified T> request(
		method: HttpMethod = HttpMethod.Get,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T>

	public suspend inline fun <reified T> get(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T>

	public suspend inline fun <reified T> post(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T>

	public suspend inline fun <reified T> delete(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T>
}
