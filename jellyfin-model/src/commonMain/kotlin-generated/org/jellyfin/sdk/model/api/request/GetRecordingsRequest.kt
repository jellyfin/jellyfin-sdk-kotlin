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
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.RecordingStatus
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Live tv recordings.
 */
@Serializable
public data class GetRecordingsRequest(
	/**
	 * Optional. Filter by channel id.
	 */
	@SerialName("channelId")
	public val channelId: String? = null,
	/**
	 * Optional. Filter by user and attach user data.
	 */
	@SerialName("userId")
	public val userId: UUID? = null,
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
	 * Optional. Filter by recording status.
	 */
	@SerialName("status")
	public val status: RecordingStatus? = null,
	/**
	 * Optional. Filter by recordings that are in progress, or not.
	 */
	@SerialName("isInProgress")
	public val isInProgress: Boolean? = null,
	/**
	 * Optional. Filter by recordings belonging to a series timer.
	 */
	@SerialName("seriesTimerId")
	public val seriesTimerId: String? = null,
	/**
	 * Optional. Include image information in output.
	 */
	@SerialName("enableImages")
	public val enableImages: Boolean? = null,
	/**
	 * Optional. The max number of images to return, per image type.
	 */
	@SerialName("imageTypeLimit")
	public val imageTypeLimit: Int? = null,
	/**
	 * Optional. The image types to include in the output.
	 */
	@SerialName("enableImageTypes")
	public val enableImageTypes: Collection<ImageType>? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional. Include user data.
	 */
	@SerialName("enableUserData")
	public val enableUserData: Boolean? = null,
	/**
	 * Optional. Filter for movies.
	 */
	@SerialName("isMovie")
	public val isMovie: Boolean? = null,
	/**
	 * Optional. Filter for series.
	 */
	@SerialName("isSeries")
	public val isSeries: Boolean? = null,
	/**
	 * Optional. Filter for kids.
	 */
	@SerialName("isKids")
	public val isKids: Boolean? = null,
	/**
	 * Optional. Filter for sports.
	 */
	@SerialName("isSports")
	public val isSports: Boolean? = null,
	/**
	 * Optional. Filter for news.
	 */
	@SerialName("isNews")
	public val isNews: Boolean? = null,
	/**
	 * Optional. Filter for is library item.
	 */
	@SerialName("isLibraryItem")
	public val isLibraryItem: Boolean? = null,
	/**
	 * Optional. Return total record count.
	 */
	@SerialName("enableTotalRecordCount")
	public val enableTotalRecordCount: Boolean? = true,
)
