// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.String
import kotlin.collections.Collection
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlin.collections.mutableMapOf
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.QueryFilters
import org.jellyfin.sdk.model.api.QueryFiltersLegacy

public class FilterApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Gets query filters.
	 *
	 * @param userId Optional. User id.
	 * @param parentId Optional. Specify this to localize the search to a specific item or folder. Omit
	 * to use the root.
	 * @param includeItemTypes Optional. If specified, results will be filtered based on item type.
	 * This allows multiple, comma delimited.
	 * @param isAiring Optional. Is item airing.
	 * @param isMovie Optional. Is item movie.
	 * @param isSports Optional. Is item sports.
	 * @param isKids Optional. Is item kids.
	 * @param isNews Optional. Is item news.
	 * @param isSeries Optional. Is item series.
	 * @param recursive Optional. Search recursive.
	 */
	public suspend fun getQueryFilters(
		userId: UUID? = null,
		parentId: UUID? = null,
		includeItemTypes: Collection<BaseItemKind>? = emptyList(),
		isAiring: Boolean? = null,
		isMovie: Boolean? = null,
		isSports: Boolean? = null,
		isKids: Boolean? = null,
		isNews: Boolean? = null,
		isSeries: Boolean? = null,
		recursive: Boolean? = null,
	): Response<QueryFilters> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["parentId"] = parentId
		queryParameters["includeItemTypes"] = includeItemTypes
		queryParameters["isAiring"] = isAiring
		queryParameters["isMovie"] = isMovie
		queryParameters["isSports"] = isSports
		queryParameters["isKids"] = isKids
		queryParameters["isNews"] = isNews
		queryParameters["isSeries"] = isSeries
		queryParameters["recursive"] = recursive
		val data = null
		val response = api.`get`<QueryFilters>("/Items/Filters2", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets legacy query filters.
	 *
	 * @param userId Optional. User id.
	 * @param parentId Optional. Parent id.
	 * @param includeItemTypes Optional. If specified, results will be filtered based on item type.
	 * This allows multiple, comma delimited.
	 * @param mediaTypes Optional. Filter by MediaType. Allows multiple, comma delimited.
	 */
	public suspend fun getQueryFiltersLegacy(
		userId: UUID? = null,
		parentId: UUID? = null,
		includeItemTypes: Collection<BaseItemKind>? = emptyList(),
		mediaTypes: Collection<String>? = emptyList(),
	): Response<QueryFiltersLegacy> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["parentId"] = parentId
		queryParameters["includeItemTypes"] = includeItemTypes
		queryParameters["mediaTypes"] = mediaTypes
		val data = null
		val response = api.`get`<QueryFiltersLegacy>("/Items/Filters", pathParameters, queryParameters,
				data)
		return response
	}
}
