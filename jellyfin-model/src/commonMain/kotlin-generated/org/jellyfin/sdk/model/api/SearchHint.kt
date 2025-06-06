// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	UUIDSerializer::class,
	DateTimeSerializer::class,
)

package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Deprecated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.DateTimeSerializer
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Class SearchHintResult.
 */
@Serializable
public data class SearchHint(
	/**
	 * The item id.
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	@SerialName("ItemId")
	public val itemId: UUID,
	/**
	 * The item id.
	 */
	@SerialName("Id")
	public val id: UUID,
	/**
	 * The name.
	 */
	@SerialName("Name")
	public val name: String,
	/**
	 * The matched term.
	 */
	@SerialName("MatchedTerm")
	public val matchedTerm: String? = null,
	/**
	 * The index number.
	 */
	@SerialName("IndexNumber")
	public val indexNumber: Int? = null,
	/**
	 * The production year.
	 */
	@SerialName("ProductionYear")
	public val productionYear: Int? = null,
	/**
	 * The parent index number.
	 */
	@SerialName("ParentIndexNumber")
	public val parentIndexNumber: Int? = null,
	/**
	 * The image tag.
	 */
	@SerialName("PrimaryImageTag")
	public val primaryImageTag: String? = null,
	/**
	 * The thumb image tag.
	 */
	@SerialName("ThumbImageTag")
	public val thumbImageTag: String? = null,
	/**
	 * The thumb image item identifier.
	 */
	@SerialName("ThumbImageItemId")
	public val thumbImageItemId: String? = null,
	/**
	 * The backdrop image tag.
	 */
	@SerialName("BackdropImageTag")
	public val backdropImageTag: String? = null,
	/**
	 * The backdrop image item identifier.
	 */
	@SerialName("BackdropImageItemId")
	public val backdropImageItemId: String? = null,
	/**
	 * The base item kind.
	 */
	@SerialName("Type")
	public val type: BaseItemKind,
	/**
	 * A value indicating whether this instance is folder.
	 */
	@SerialName("IsFolder")
	public val isFolder: Boolean? = null,
	/**
	 * The run time ticks.
	 */
	@SerialName("RunTimeTicks")
	public val runTimeTicks: Long? = null,
	/**
	 * Media types.
	 */
	@SerialName("MediaType")
	public val mediaType: MediaType = MediaType.UNKNOWN,
	/**
	 * The start date.
	 */
	@SerialName("StartDate")
	public val startDate: DateTime? = null,
	/**
	 * The end date.
	 */
	@SerialName("EndDate")
	public val endDate: DateTime? = null,
	/**
	 * The series.
	 */
	@SerialName("Series")
	public val series: String? = null,
	/**
	 * The status.
	 */
	@SerialName("Status")
	public val status: String? = null,
	/**
	 * The album.
	 */
	@SerialName("Album")
	public val album: String? = null,
	/**
	 * The album id.
	 */
	@SerialName("AlbumId")
	public val albumId: UUID? = null,
	/**
	 * The album artist.
	 */
	@SerialName("AlbumArtist")
	public val albumArtist: String? = null,
	/**
	 * The artists.
	 */
	@SerialName("Artists")
	public val artists: List<String>,
	/**
	 * The song count.
	 */
	@SerialName("SongCount")
	public val songCount: Int? = null,
	/**
	 * The episode count.
	 */
	@SerialName("EpisodeCount")
	public val episodeCount: Int? = null,
	/**
	 * The channel identifier.
	 */
	@SerialName("ChannelId")
	public val channelId: UUID? = null,
	/**
	 * The name of the channel.
	 */
	@SerialName("ChannelName")
	public val channelName: String? = null,
	/**
	 * The primary image aspect ratio.
	 */
	@SerialName("PrimaryImageAspectRatio")
	public val primaryImageAspectRatio: Double? = null,
)
