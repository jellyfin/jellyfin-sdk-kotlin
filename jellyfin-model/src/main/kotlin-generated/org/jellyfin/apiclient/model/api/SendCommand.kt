// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	UUIDSerializer::class,
	LocalDateTimeSerializer::class
)

package org.jellyfin.apiclient.model.api

import java.time.LocalDateTime
import java.util.UUID
import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.serializer.LocalDateTimeSerializer
import org.jellyfin.apiclient.model.serializer.UUIDSerializer

/**
 * Class SendCommand.
 */
@Serializable
public data class SendCommand(
	/**
	 * Gets the group identifier.
	 */
	@SerialName("GroupId")
	public val groupId: UUID,
	/**
	 * Gets the playlist identifier of the playing item.
	 */
	@SerialName("PlaylistItemId")
	public val playlistItemId: UUID,
	/**
	 * Gets or sets the UTC time when to execute the command.
	 */
	@SerialName("When")
	public val `when`: LocalDateTime,
	/**
	 * Gets the position ticks.
	 */
	@SerialName("PositionTicks")
	public val positionTicks: Long? = null,
	@SerialName("Command")
	public val command: SendCommandType? = null,
	/**
	 * Gets the UTC time when this command has been emitted.
	 */
	@SerialName("EmittedAt")
	public val emittedAt: LocalDateTime
)
