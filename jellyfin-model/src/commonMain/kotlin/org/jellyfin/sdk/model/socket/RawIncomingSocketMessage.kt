@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.SessionMessageType
import org.jellyfin.sdk.model.serializer.UUIDSerializer

@Serializable
public data class RawIncomingSocketMessage(
	@SerialName("MessageId")
	val id: UUID,
	@SerialName("MessageType")
	val type: SessionMessageType,
	@SerialName("Data")
	val data: JsonElement? = null,
)
