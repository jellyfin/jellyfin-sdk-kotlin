// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemSortBy
import org.jellyfin.sdk.model.api.SortOrder
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * All genres from a given item, folder, or the entire library.
 */
@Serializable
public data class GetGenresRequest(
	/**
	 * Optional. The record index to start at. All items with a lower index will be dropped from the
	 * results.
	 */
	@SerialName("startIndex")
	public val startIndex: Int? = null,
	/**
	 * Optional. The maximum number of records to return.
	 */
	@SerialName("limit")
	public val limit: Int? = null,
	/**
	 * The search term.
	 */
	@SerialName("searchTerm")
	public val searchTerm: String? = null,
	/**
	 * Specify this to localize the search to a specific item or folder. Omit to use the root.
	 */
	@SerialName("parentId")
	public val parentId: UUID? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional. If specified, results will be filtered out based on item type. This allows multiple,
	 * comma delimited.
	 */
	@SerialName("excludeItemTypes")
	public val excludeItemTypes: Collection<BaseItemKind>? = null,
	/**
	 * Optional. If specified, results will be filtered in based on item type. This allows multiple,
	 * comma delimited.
	 */
	@SerialName("includeItemTypes")
	public val includeItemTypes: Collection<BaseItemKind>? = null,
	/**
	 * Optional filter by items that are marked as favorite, or not.
	 */
	@SerialName("isFavorite")
	public val isFavorite: Boolean? = null,
	/**
	 * Optional, the max number of images to return, per image type.
	 */
	@SerialName("imageTypeLimit")
	public val imageTypeLimit: Int? = null,
	/**
	 * Optional. The image types to include in the output.
	 */
	@SerialName("enableImageTypes")
	public val enableImageTypes: Collection<ImageType>? = null,
	/**
	 * User id.
	 */
	@SerialName("userId")
	public val userId: UUID? = null,
	/**
	 * Optional filter by items whose name is sorted equally or greater than a given input string.
	 */
	@SerialName("nameStartsWithOrGreater")
	public val nameStartsWithOrGreater: String? = null,
	/**
	 * Optional filter by items whose name is sorted equally than a given input string.
	 */
	@SerialName("nameStartsWith")
	public val nameStartsWith: String? = null,
	/**
	 * Optional filter by items whose name is equally or lesser than a given input string.
	 */
	@SerialName("nameLessThan")
	public val nameLessThan: String? = null,
	/**
	 * Optional. Specify one or more sort orders, comma delimited.
	 */
	@SerialName("sortBy")
	public val sortBy: Collection<ItemSortBy>? = null,
	/**
	 * Sort Order - Ascending,Descending.
	 */
	@SerialName("sortOrder")
	public val sortOrder: Collection<SortOrder>? = null,
	/**
	 * Optional, include image information in output.
	 */
	@SerialName("enableImages")
	public val enableImages: Boolean? = true,
	/**
	 * Optional. Include total record count.
	 */
	@SerialName("enableTotalRecordCount")
	public val enableTotalRecordCount: Boolean? = true,
)
