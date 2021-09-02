// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.String
import kotlin.Unit
import kotlin.collections.emptyMap
import kotlin.collections.mutableMapOf
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.api.client.extensions.delete
import org.jellyfin.sdk.api.client.extensions.post
import org.jellyfin.sdk.model.api.AuthenticationInfoQueryResult

public class ApiKeyApi(
	private val api: ApiClient
) {
	/**
	 * Create a new api key.
	 *
	 * @param app Name of the app using the authentication key.
	 */
	public suspend fun createKey(app: String): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["app"] = app
		val data = null
		val response = api.post<Unit>("/Auth/Keys", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Get all keys.
	 */
	public suspend fun getKeys(): Response<AuthenticationInfoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<AuthenticationInfoQueryResult>("/Auth/Keys", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Remove an api key.
	 *
	 * @param key The access token to delete.
	 */
	public suspend fun revokeKey(key: String): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["key"] = key
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.delete<Unit>("/Auth/Keys/{key}", pathParameters, queryParameters, data)
		return response
	}
}