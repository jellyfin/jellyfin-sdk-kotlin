// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlin.collections.buildMap
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemDtoQueryResult
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemSortBy
import org.jellyfin.sdk.model.api.MediaType
import org.jellyfin.sdk.model.api.SortOrder
import org.jellyfin.sdk.model.api.request.GetYearsRequest

public class YearsApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Gets a year.
	 *
	 * @param year The year.
	 * @param userId Optional. Filter by user id, and attach user data.
	 */
	public suspend fun getYear(year: Int, userId: UUID? = null): Response<BaseItemDto> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("year", year)
		}
		val queryParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val data = null
		val response = api.`get`<BaseItemDto>("/Years/{year}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Get years.
	 *
	 * @param startIndex Skips over a given number of items within the results. Use for paging.
	 * @param limit Optional. The maximum number of records to return.
	 * @param sortOrder Sort Order - Ascending,Descending.
	 * @param parentId Specify this to localize the search to a specific item or folder. Omit to use the root.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param excludeItemTypes Optional. If specified, results will be excluded based on item type. This allows multiple, comma delimited.
	 * @param includeItemTypes Optional. If specified, results will be included based on item type. This allows multiple, comma delimited.
	 * @param mediaTypes Optional. Filter by MediaType. Allows multiple, comma delimited.
	 * @param sortBy Optional. Specify one or more sort orders, comma delimited. Options: Album, AlbumArtist, Artist, Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount, PremiereDate, ProductionYear, SortName, Random, Revenue, Runtime.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 * @param userId User Id.
	 * @param recursive Search recursively.
	 * @param enableImages Optional. Include image information in output.
	 */
	public suspend fun getYears(
		startIndex: Int? = null,
		limit: Int? = null,
		sortOrder: Collection<SortOrder>? = emptyList(),
		parentId: UUID? = null,
		fields: Collection<ItemFields>? = emptyList(),
		excludeItemTypes: Collection<BaseItemKind>? = emptyList(),
		includeItemTypes: Collection<BaseItemKind>? = emptyList(),
		mediaTypes: Collection<MediaType>? = emptyList(),
		sortBy: Collection<ItemSortBy>? = emptyList(),
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
		userId: UUID? = null,
		recursive: Boolean? = true,
		enableImages: Boolean? = true,
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(15) {
			put("startIndex", startIndex)
			put("limit", limit)
			put("sortOrder", sortOrder)
			put("parentId", parentId)
			put("fields", fields)
			put("excludeItemTypes", excludeItemTypes)
			put("includeItemTypes", includeItemTypes)
			put("mediaTypes", mediaTypes)
			put("sortBy", sortBy)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
			put("userId", userId)
			put("recursive", recursive)
			put("enableImages", enableImages)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Years", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Get years.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getYears(request: GetYearsRequest = GetYearsRequest()): Response<BaseItemDtoQueryResult> = getYears(
		startIndex = request.startIndex,
		limit = request.limit,
		sortOrder = request.sortOrder,
		parentId = request.parentId,
		fields = request.fields,
		excludeItemTypes = request.excludeItemTypes,
		includeItemTypes = request.includeItemTypes,
		mediaTypes = request.mediaTypes,
		sortBy = request.sortBy,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
		userId = request.userId,
		recursive = request.recursive,
		enableImages = request.enableImages,
	)
}
