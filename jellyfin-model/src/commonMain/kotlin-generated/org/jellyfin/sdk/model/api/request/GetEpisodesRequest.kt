// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Int
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemSortBy
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Episodes for a tv season.
 */
@Serializable
public data class GetEpisodesRequest(
	/**
	 * The series id.
	 */
	@SerialName("seriesId")
	public val seriesId: UUID,
	/**
	 * The user id.
	 */
	@SerialName("userId")
	public val userId: UUID? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output. This allows multiple, comma delimited. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl, IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio, Revenue, SortName, Studios, Taglines, TrailerUrls.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional filter by season number.
	 */
	@SerialName("season")
	public val season: Int? = null,
	/**
	 * Optional. Filter by season id.
	 */
	@SerialName("seasonId")
	public val seasonId: UUID? = null,
	/**
	 * Optional. Filter by items that are missing episodes or not.
	 */
	@SerialName("isMissing")
	public val isMissing: Boolean? = null,
	/**
	 * Optional. Return items that are siblings of a supplied item.
	 */
	@SerialName("adjacentTo")
	public val adjacentTo: UUID? = null,
	/**
	 * Optional. Skip through the list until a given item is found.
	 */
	@SerialName("startItemId")
	public val startItemId: UUID? = null,
	/**
	 * Optional. The record index to start at. All items with a lower index will be dropped from the results.
	 */
	@SerialName("startIndex")
	public val startIndex: Int? = null,
	/**
	 * Optional. The maximum number of records to return.
	 */
	@SerialName("limit")
	public val limit: Int? = null,
	/**
	 * Optional, include image information in output.
	 */
	@SerialName("enableImages")
	public val enableImages: Boolean? = null,
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
	 * Optional. Include user data.
	 */
	@SerialName("enableUserData")
	public val enableUserData: Boolean? = null,
	/**
	 * Optional. Specify one or more sort orders, comma delimited. Options: Album, AlbumArtist, Artist, Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount, PremiereDate, ProductionYear, SortName, Random, Revenue, Runtime.
	 */
	@SerialName("sortBy")
	public val sortBy: ItemSortBy? = null,
)
