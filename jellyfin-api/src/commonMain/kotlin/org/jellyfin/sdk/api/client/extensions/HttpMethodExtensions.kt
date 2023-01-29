package org.jellyfin.sdk.api.client.extensions

import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HttpMethod
import org.jellyfin.sdk.api.client.Response

public suspend inline fun <reified T : Any> ApiClient.get(
	pathTemplate: String,
	pathParameters: Map<String, Any?> = emptyMap(),
	queryParameters: Map<String, Any?> = emptyMap(),
	requestBody: Any? = null,
): Response<T> = request(
	method = HttpMethod.GET,
	pathTemplate = pathTemplate,
	pathParameters = pathParameters,
	queryParameters = queryParameters,
	requestBody = requestBody
).createResponse()

public suspend inline fun <reified T : Any> ApiClient.post(
	pathTemplate: String,
	pathParameters: Map<String, Any?> = emptyMap(),
	queryParameters: Map<String, Any?> = emptyMap(),
	requestBody: Any? = null,
): Response<T> = request(
	method = HttpMethod.POST,
	pathTemplate = pathTemplate,
	pathParameters = pathParameters,
	queryParameters = queryParameters,
	requestBody = requestBody
).createResponse()

public suspend inline fun <reified T : Any> ApiClient.delete(
	pathTemplate: String,
	pathParameters: Map<String, Any?> = emptyMap(),
	queryParameters: Map<String, Any?> = emptyMap(),
	requestBody: Any? = null,
): Response<T> = request(
	method = HttpMethod.DELETE,
	pathTemplate = pathTemplate,
	pathParameters = pathParameters,
	queryParameters = queryParameters,
	requestBody = requestBody
).createResponse()
