// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	UUIDSerializer::class,
	DateTimeSerializer::class,
)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Int
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.serializer.DateTimeSerializer
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * A list of next up episodes.
 */
@Serializable
public data class GetNextUpRequest(
	/**
	 * The user id of the user to get the next up episodes for.
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
	 * Optional. Specify additional fields of information to return in the output.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional. Filter by series id.
	 */
	@SerialName("seriesId")
	public val seriesId: UUID? = null,
	/**
	 * Optional. Specify this to localize the search to a specific item or folder. Omit to use the root.
	 */
	@SerialName("parentId")
	public val parentId: UUID? = null,
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
	 * Optional. Include user data.
	 */
	@SerialName("enableUserData")
	public val enableUserData: Boolean? = null,
	/**
	 * Optional. Starting date of shows to show in Next Up section.
	 */
	@SerialName("nextUpDateCutoff")
	public val nextUpDateCutoff: DateTime? = null,
	/**
	 * Whether to enable the total records count. Defaults to true.
	 */
	@SerialName("enableTotalRecordCount")
	public val enableTotalRecordCount: Boolean? = true,
	/**
	 * Whether to include resumable episodes in next up results.
	 */
	@SerialName("enableResumable")
	public val enableResumable: Boolean? = true,
	/**
	 * Whether to include watched episodes in next up results.
	 */
	@SerialName("enableRewatching")
	public val enableRewatching: Boolean? = false,
)
