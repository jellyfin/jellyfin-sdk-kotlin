@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.serializer.UUIDSerializer
import java.util.*

@Serializable
public data class ServerRestartingMessage(
	@SerialName("MessageId")
	override val messageId: UUID,
) : IncomingSocketMessage
