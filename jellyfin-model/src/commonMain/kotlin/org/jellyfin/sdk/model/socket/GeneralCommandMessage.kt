@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.UUIDSerializer

@Serializable
public data class GeneralCommandMessage(
	@SerialName("MessageId")
	override val messageId: UUID,

	@SerialName("Name")
	val command: GeneralCommandType,

	@SerialName("ControllingUserId")
	val userId: UUID,

	@SerialName("Arguments")
	val arguments: Map<String, String> = emptyMap(),
) : IncomingSocketMessage
