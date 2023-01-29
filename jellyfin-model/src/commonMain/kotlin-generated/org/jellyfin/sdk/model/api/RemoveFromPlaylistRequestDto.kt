// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Class RemoveFromPlaylistRequestDto.
 */
@Serializable
public data class RemoveFromPlaylistRequestDto(
	/**
	 * The playlist identifiers ot the items. Ignored when clearing the playlist.
	 */
	@SerialName("PlaylistItemIds")
	public val playlistItemIds: List<UUID>,
	/**
	 * A value indicating whether the entire playlist should be cleared.
	 */
	@SerialName("ClearPlaylist")
	public val clearPlaylist: Boolean,
	/**
	 * A value indicating whether the playing item should be removed as well. Used only when clearing
	 * the playlist.
	 */
	@SerialName("ClearPlayingItem")
	public val clearPlayingItem: Boolean,
)
