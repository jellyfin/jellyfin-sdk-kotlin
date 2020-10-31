// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.api.operations

import kotlin.Any
import kotlin.String
import kotlin.collections.List
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.model.api.CountryInfo
import org.jellyfin.apiclient.model.api.CultureDto
import org.jellyfin.apiclient.model.api.LocalizationOption
import org.jellyfin.apiclient.model.api.ParentalRating

public class LocalizationApi(
	private val api: KtorClient
) {
	/**
	 * Gets known countries.
	 */
	public suspend fun getCountries(): Response<List<CountryInfo>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<CountryInfo>>("/Localization/Countries", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets known cultures.
	 */
	public suspend fun getCultures(): Response<List<CultureDto>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<CultureDto>>("/Localization/Cultures", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets localization options.
	 */
	public suspend fun getLocalizationOptions(): Response<List<LocalizationOption>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<LocalizationOption>>("/Localization/Options", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets known parental ratings.
	 */
	public suspend fun getParentalRatings(): Response<List<ParentalRating>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<ParentalRating>>("/Localization/ParentalRatings", pathParameters,
				queryParameters, data)
		return response
	}
}
