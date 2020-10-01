@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.serializer.UUIDSerializer
import java.util.*

@Serializable
data class PlayStateMessage(
	@SerialName("MessageId")
	override val messageId: UUID,

	@SerialName("Data")
	val request: PlaystateRequest,
) : IncomingSocketMessage
