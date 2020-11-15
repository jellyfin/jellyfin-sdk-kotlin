@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.serializer.UUIDSerializer
import java.util.*

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
