@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.UUIDSerializer

public sealed interface IncomingSocketMessage : SocketMessage {
	/**
	 * The id of the received message.
	 *
	 * Implementation note: Copy the @SerialName notation to the implementation side
	 */
	@SerialName("MessageId")
	public val messageId: UUID
}
