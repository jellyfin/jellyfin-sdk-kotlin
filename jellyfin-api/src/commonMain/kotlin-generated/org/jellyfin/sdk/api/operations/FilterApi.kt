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
import kotlin.collections.buildMap
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.QueryFilters
import org.jellyfin.sdk.model.api.QueryFiltersLegacy
import org.jellyfin.sdk.model.api.request.GetQueryFiltersRequest

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
		val queryParameters = buildMap<String, Any?>(10) {
			put("userId", userId)
			put("parentId", parentId)
			put("includeItemTypes", includeItemTypes)
			put("isAiring", isAiring)
			put("isMovie", isMovie)
			put("isSports", isSports)
			put("isKids", isKids)
			put("isNews", isNews)
			put("isSeries", isSeries)
			put("recursive", recursive)
		}
		val data = null
		val response = api.`get`<QueryFilters>("/Items/Filters2", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets query filters.
	 *
	 * @param request The request paramaters
	 */
	public suspend fun getQueryFilters(request: GetQueryFiltersRequest = GetQueryFiltersRequest()):
			Response<QueryFilters> = getQueryFilters(
		userId = request.userId,
		parentId = request.parentId,
		includeItemTypes = request.includeItemTypes,
		isAiring = request.isAiring,
		isMovie = request.isMovie,
		isSports = request.isSports,
		isKids = request.isKids,
		isNews = request.isNews,
		isSeries = request.isSeries,
		recursive = request.recursive,
	)

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
		val queryParameters = buildMap<String, Any?>(4) {
			put("userId", userId)
			put("parentId", parentId)
			put("includeItemTypes", includeItemTypes)
			put("mediaTypes", mediaTypes)
		}
		val data = null
		val response = api.`get`<QueryFiltersLegacy>("/Items/Filters", pathParameters, queryParameters,
				data)
		return response
	}
}
