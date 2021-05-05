// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import java.util.UUID
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.jellyfin.sdk.api.client.KtorClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemDtoQueryResult
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemFilter

public class PersonsApi(
	private val api: KtorClient
) {
	/**
	 * Get person by name.
	 *
	 * @param name Person name.
	 * @param userId Optional. Filter by user id, and attach user data.
	 */
	public suspend fun getPerson(name: String, userId: UUID? = null): Response<BaseItemDto> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["name"] = name
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		val data = null
		val response = api.`get`<BaseItemDto>("/Persons/{name}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets all persons.
	 *
	 * @param limit Optional. The maximum number of records to return.
	 * @param searchTerm The search term.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param filters Optional. Specify additional filters to apply.
	 * @param isFavorite Optional filter by items that are marked as favorite, or not. userId is
	 * required.
	 * @param enableUserData Optional, include user data.
	 * @param imageTypeLimit Optional, the max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 * @param excludePersonTypes Optional. If specified results will be filtered to exclude those
	 * containing the specified PersonType. Allows multiple, comma-delimited.
	 * @param personTypes Optional. If specified results will be filtered to include only those
	 * containing the specified PersonType. Allows multiple, comma-delimited.
	 * @param appearsInItemId Optional. If specified, person results will be filtered on items related
	 * to said persons.
	 * @param userId User id.
	 * @param enableImages Optional, include image information in output.
	 */
	public suspend fun getPersons(
		limit: Int? = null,
		searchTerm: String? = null,
		fields: List<ItemFields>? = emptyList(),
		filters: List<ItemFilter>? = emptyList(),
		isFavorite: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: List<ImageType>? = emptyList(),
		excludePersonTypes: List<String>? = emptyList(),
		personTypes: List<String>? = emptyList(),
		appearsInItemId: UUID? = null,
		userId: UUID? = null,
		enableImages: Boolean? = true
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["limit"] = limit
		queryParameters["searchTerm"] = searchTerm
		queryParameters["fields"] = fields
		queryParameters["filters"] = filters
		queryParameters["isFavorite"] = isFavorite
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		queryParameters["excludePersonTypes"] = excludePersonTypes
		queryParameters["personTypes"] = personTypes
		queryParameters["appearsInItemId"] = appearsInItemId
		queryParameters["userId"] = userId
		queryParameters["enableImages"] = enableImages
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Persons", pathParameters, queryParameters,
				data)
		return response
	}
}
