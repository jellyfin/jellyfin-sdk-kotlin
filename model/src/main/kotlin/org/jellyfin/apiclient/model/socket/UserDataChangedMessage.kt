@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.api.UserItemDataDto
import org.jellyfin.apiclient.model.serializer.UUIDSerializer
import java.util.*

@Serializable
data class UserDataChangedMessage(
	@SerialName("MessageId")
	override val messageId: UUID,
	@SerialName("UserId")
	val userId: UUID,
	@SerialName("UserDataList")
	val userDataList: List<UserItemDataDto>,
) : IncomingSocketMessage
