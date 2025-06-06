// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.ByteArray
import kotlin.String
import kotlin.Unit
import kotlin.collections.buildMap
import kotlin.collections.emptyMap
import kotlinx.serialization.json.JsonElement
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.api.client.extensions.post
import org.jellyfin.sdk.model.api.BrandingOptionsDto
import org.jellyfin.sdk.model.api.MetadataOptions
import org.jellyfin.sdk.model.api.ServerConfiguration

public class ConfigurationApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Gets application configuration.
	 */
	public suspend fun getConfiguration(): Response<ServerConfiguration> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ServerConfiguration>("/System/Configuration", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a default MetadataOptions object.
	 */
	public suspend fun getDefaultMetadataOptions(): Response<MetadataOptions> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<MetadataOptions>("/System/Configuration/MetadataOptions/Default", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a named configuration.
	 *
	 * @param key Configuration key.
	 */
	public suspend fun getNamedConfiguration(key: String): Response<ByteArray> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("key", key)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteArray>("/System/Configuration/{key}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a named configuration.
	 *
	 * @param key Configuration key.
	 */
	public fun getNamedConfigurationUrl(key: String): String {
		val pathParameters = buildMap<String, Any?>(1) {
			put("key", key)
		}
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/System/Configuration/{key}", pathParameters, queryParameters)
	}

	/**
	 * Updates branding configuration.
	 */
	public suspend fun updateBrandingConfiguration(`data`: BrandingOptionsDto): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/System/Configuration/Branding", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates application configuration.
	 */
	public suspend fun updateConfiguration(`data`: ServerConfiguration): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/System/Configuration", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates named configuration.
	 *
	 * @param key Configuration key.
	 */
	public suspend fun updateNamedConfiguration(key: String, `data`: JsonElement): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("key", key)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/System/Configuration/{key}", pathParameters, queryParameters, data)
		return response
	}
}
