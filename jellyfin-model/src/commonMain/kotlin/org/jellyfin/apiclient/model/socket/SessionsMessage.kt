@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.api.SessionInfo
import org.jellyfin.apiclient.model.serializer.UUIDSerializer
import java.util.*

@Serializable
public data class SessionsMessage(
	@SerialName("MessageId")
	override val messageId: UUID,
	@SerialName("Data")
	val session: List<SessionInfo>,
) : IncomingSocketMessage
