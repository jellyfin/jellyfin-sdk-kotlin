package org.jellyfin.sdk.api.sockets.data

import org.jellyfin.sdk.model.socket.IncomingSocketMessage
import org.jellyfin.sdk.model.socket.OutgoingSocketMessage
import org.jellyfin.sdk.model.socket.PeriodicListenerPeriod
import kotlin.reflect.KClass

/**
 * This is an internal type. Do not use this in your application.
 */
public data class SubscriptionType<MESSAGE : IncomingSocketMessage>(
	val messageType: KClass<MESSAGE>,
	val createStartMessage: (period: PeriodicListenerPeriod) -> OutgoingSocketMessage,
	val createStopMessage: () -> OutgoingSocketMessage,
)

/**
 * This is an internal type. Do not use this in your application.
 */
internal inline fun <reified MESSAGE : IncomingSocketMessage> subscriptionType(
	noinline createStartMessage: (period: PeriodicListenerPeriod) -> OutgoingSocketMessage,
	noinline createStopMessage: () -> OutgoingSocketMessage,
): SubscriptionType<MESSAGE> = SubscriptionType(MESSAGE::class, createStartMessage, createStopMessage)

/**
 * This is an internal type. Do not use this in your application.
 */
public val KClass<out IncomingSocketMessage>.subscriptionType: SubscriptionType<out IncomingSocketMessage>?
	get() = SUBSCRIPTION_TYPES.firstOrNull { it.messageType == this }
