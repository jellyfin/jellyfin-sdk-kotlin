package org.jellyfin.sdk.api.sockets.listener

import org.jellyfin.sdk.api.sockets.data.SubscriptionType
import org.jellyfin.sdk.model.socket.IncomingSocketMessage
import kotlin.reflect.KClass

public data class SocketListenerDefinition(
	val subscribesTo: Set<SubscriptionType<*>>,
	val filterTypes: Set<KClass<out IncomingSocketMessage>>,
	val stopOnCredentialsChange: Boolean,
	val listener: SocketMessageReceiver<in IncomingSocketMessage>,
)
