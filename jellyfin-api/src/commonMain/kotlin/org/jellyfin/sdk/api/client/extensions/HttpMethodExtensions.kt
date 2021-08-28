package org.jellyfin.sdk.api.client.extensions

import io.ktor.http.*
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.KtorClient
import org.jellyfin.sdk.api.client.Response

public suspend inline fun <reified T> ApiClient.get(
	pathTemplate: String,
	pathParameters: Map<String, Any?> = emptyMap(),
	queryParameters: Map<String, Any?> = emptyMap(),
	requestBody: Any? = null,
): Response<T> = (this as KtorClient).request(
	method = HttpMethod.Get,
	pathTemplate = pathTemplate,
	pathParameters = pathParameters,
	queryParameters = queryParameters,
	requestBody = requestBody
)

public suspend inline fun <reified T> ApiClient.post(
	pathTemplate: String,
	pathParameters: Map<String, Any?> = emptyMap(),
	queryParameters: Map<String, Any?> = emptyMap(),
	requestBody: Any? = null,
): Response<T> = (this as KtorClient).request(
	method = HttpMethod.Post,
	pathTemplate = pathTemplate,
	pathParameters = pathParameters,
	queryParameters = queryParameters,
	requestBody = requestBody
)

public suspend inline fun <reified T> ApiClient.delete(
	pathTemplate: String,
	pathParameters: Map<String, Any?> = emptyMap(),
	queryParameters: Map<String, Any?> = emptyMap(),
	requestBody: Any? = null,
): Response<T> = (this as KtorClient).request(
	method = HttpMethod.Delete,
	pathTemplate = pathTemplate,
	pathParameters = pathParameters,
	queryParameters = queryParameters,
	requestBody = requestBody
)
