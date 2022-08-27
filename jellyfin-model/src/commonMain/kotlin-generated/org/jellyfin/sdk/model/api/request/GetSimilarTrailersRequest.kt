// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Int
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Gets similar items.
 */
@Serializable
public data class GetSimilarTrailersRequest(
	/**
	 * The item id.
	 */
	@SerialName("itemId")
	public val itemId: UUID,
	/**
	 * Exclude artist ids.
	 */
	@SerialName("excludeArtistIds")
	public val excludeArtistIds: Collection<UUID>? = null,
	/**
	 * Optional. Filter by user id, and attach user data.
	 */
	@SerialName("userId")
	public val userId: UUID? = null,
	/**
	 * Optional. The maximum number of records to return.
	 */
	@SerialName("limit")
	public val limit: Int? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output. This allows
	 * multiple, comma delimited. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
)
