@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.ActivityLogEntry
import org.jellyfin.sdk.model.serializer.UUIDSerializer

@Serializable
public data class ActivityLogEntryMessage(
	@SerialName("MessageId")
	override val messageId: UUID,

	@SerialName("Data")
	val entries: List<ActivityLogEntry>,
) : IncomingSocketMessage
