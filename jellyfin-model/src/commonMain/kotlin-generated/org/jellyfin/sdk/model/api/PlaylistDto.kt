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
 * DTO for playlists.
 */
@Serializable
public data class PlaylistDto(
	/**
	 * A value indicating whether the playlist is publicly readable.
	 */
	@SerialName("OpenAccess")
	public val openAccess: Boolean,
	/**
	 * The share permissions.
	 */
	@SerialName("Shares")
	public val shares: List<PlaylistUserPermissions>,
	/**
	 * The item ids.
	 */
	@SerialName("ItemIds")
	public val itemIds: List<UUID>,
)
