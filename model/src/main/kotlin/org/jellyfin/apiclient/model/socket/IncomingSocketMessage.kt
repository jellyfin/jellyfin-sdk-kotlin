@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.serializer.UUIDSerializer
import java.util.*

interface IncomingSocketMessage : SocketMessage {
	/**
	 * The id of the received message.
	 *
	 * Implementation note: Copy the @SerialName notation to the implementation side
	 */
	@SerialName("MessageId")
	val messageId: UUID
}
