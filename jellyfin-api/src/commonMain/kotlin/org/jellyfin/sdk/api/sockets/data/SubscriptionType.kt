package org.jellyfin.sdk.api.sockets.data

import org.jellyfin.sdk.model.api.InboundWebSocketMessage
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import org.jellyfin.sdk.model.socket.PeriodicListenerPeriod
import kotlin.reflect.KClass

/**
 * Information about a subscription. Contains the incoming message type and the outgoing messages types used
 * to start and stop the subscription.
 */
internal data class SubscriptionType<MESSAGE : OutboundWebSocketMessage>(
	val messageType: KClass<MESSAGE>,
	val createStartMessage: (period: PeriodicListenerPeriod) -> InboundWebSocketMessage,
	val createStopMessage: () -> InboundWebSocketMessage,
)

/**
 * Create instance of [SubscriptionType].
 */
internal inline fun <reified MESSAGE : OutboundWebSocketMessage> subscriptionType(
	noinline createStartMessage: (period: PeriodicListenerPeriod) -> InboundWebSocketMessage,
	noinline createStopMessage: () -> InboundWebSocketMessage,
): SubscriptionType<MESSAGE> = SubscriptionType(MESSAGE::class, createStartMessage, createStopMessage)

/**
 * Find the subscription type for a given [OutboundWebSocketMessage]. Used for automatic subscription handling in the
 * WebSocket API.
 */
internal val KClass<out OutboundWebSocketMessage>.subscriptionType: SubscriptionType<out OutboundWebSocketMessage>?
	get() = SUBSCRIPTION_TYPES.firstOrNull { it.messageType == this }
