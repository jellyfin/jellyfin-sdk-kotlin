package org.jellyfin.sdk.api.sockets.data

import org.jellyfin.sdk.model.socket.IncomingSocketMessage
import org.jellyfin.sdk.model.socket.OutgoingSocketMessage
import org.jellyfin.sdk.model.socket.PeriodicListenerPeriod
import kotlin.reflect.KClass

/**
 * Information about a subscription. Contains the incoming message type and the outgoing messages types used
 * to start and stop the subscription.
 */
public data class SubscriptionType<MESSAGE : IncomingSocketMessage>(
	val messageType: KClass<MESSAGE>,
	val createStartMessage: (period: PeriodicListenerPeriod) -> OutgoingSocketMessage,
	val createStopMessage: () -> OutgoingSocketMessage,
)

/**
 * Create instance of [SubscriptionType].
 */
internal inline fun <reified MESSAGE : IncomingSocketMessage> subscriptionType(
	noinline createStartMessage: (period: PeriodicListenerPeriod) -> OutgoingSocketMessage,
	noinline createStopMessage: () -> OutgoingSocketMessage,
): SubscriptionType<MESSAGE> = SubscriptionType(MESSAGE::class, createStartMessage, createStopMessage)

/**
 * Find the subscription type for a given [IncomingSocketMessage]. Used for automatic subscription handling in the
 * WebSocket API.
 */
public val KClass<out IncomingSocketMessage>.subscriptionType: SubscriptionType<out IncomingSocketMessage>?
	get() = SUBSCRIPTION_TYPES.firstOrNull { it.messageType == this }
