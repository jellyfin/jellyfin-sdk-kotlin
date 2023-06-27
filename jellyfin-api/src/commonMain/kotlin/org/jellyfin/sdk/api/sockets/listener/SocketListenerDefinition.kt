package org.jellyfin.sdk.api.sockets.listener

import org.jellyfin.sdk.api.sockets.data.SubscriptionType
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import kotlin.reflect.KClass

public data class SocketListenerDefinition(
	val subscribesTo: Set<SubscriptionType<*>>,
	val filterTypes: Set<KClass<out OutboundWebSocketMessage>>,
	val stopOnCredentialsChange: Boolean,
	val listener: SocketMessageReceiver<in OutboundWebSocketMessage>,
)
