@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.SessionInfo
import org.jellyfin.sdk.model.serializer.UUIDSerializer

@Serializable
public data class SessionsMessage(
	@SerialName("MessageId")
	override val messageId: UUID,
	@SerialName("Data")
	val session: List<SessionInfo>,
) : IncomingSocketMessage
