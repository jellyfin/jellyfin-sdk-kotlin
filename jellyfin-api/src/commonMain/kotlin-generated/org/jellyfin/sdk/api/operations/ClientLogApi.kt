// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.String
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.post
import org.jellyfin.sdk.model.api.ClientLogDocumentResponseDto

public class ClientLogApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Upload a document.
	 */
	public suspend fun logFile(`data`: String): Response<ClientLogDocumentResponseDto> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<ClientLogDocumentResponseDto>("/ClientLog/Document", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Upload a document.
	 */
	public fun logFileUrl(): String {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/ClientLog/Document", pathParameters, queryParameters)
	}
}
