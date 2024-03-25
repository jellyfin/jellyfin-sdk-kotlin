// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Group update without data.
 */
@Serializable
public data class GroupUpdate(
	/**
	 * The group identifier.
	 */
	@SerialName("GroupId")
	public val groupId: UUID,
	/**
	 * The update type.
	 */
	@SerialName("Type")
	public val type: GroupUpdateType,
)
