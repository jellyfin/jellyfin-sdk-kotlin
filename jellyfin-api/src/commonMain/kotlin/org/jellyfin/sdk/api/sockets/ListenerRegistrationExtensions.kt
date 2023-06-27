package org.jellyfin.sdk.api.sockets

import org.jellyfin.sdk.api.sockets.data.SUBSCRIPTION_TYPES
import org.jellyfin.sdk.api.sockets.data.subscriptionType
import org.jellyfin.sdk.api.sockets.listener.SocketListener
import org.jellyfin.sdk.api.sockets.listener.SocketListenerDefinition
import org.jellyfin.sdk.api.sockets.listener.SocketMessageReceiver
import org.jellyfin.sdk.model.api.GeneralCommandMessage
import org.jellyfin.sdk.model.api.GeneralCommandType
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import org.jellyfin.sdk.model.api.PlaystateCommand
import org.jellyfin.sdk.model.api.PlaystateMessage
import org.jellyfin.sdk.model.api.SendCommandType
import org.jellyfin.sdk.model.api.SyncPlayCommandMessage

/**
 * Add a listener that listens to all message types.
 * If you want to listen for specific messages you can use [addListener] or [addGeneralCommandsListener] instead.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketInstance.addGlobalListener(
	stopOnCredentialsChange: Boolean = false,
	listener: SocketMessageReceiver<OutboundWebSocketMessage>,
): SocketListener {
	val definition = SocketListenerDefinition(
		subscribesTo = SUBSCRIPTION_TYPES,
		filterTypes = setOf(OutboundWebSocketMessage::class),
		stopOnCredentialsChange = stopOnCredentialsChange,
		listener = listener
	)
	return addListenerDefinition(definition)
}

/**
 * Add a listener that listens to a specific message type.
 */
public inline fun <reified T : OutboundWebSocketMessage> SocketInstance.addListener(
	stopOnCredentialsChange: Boolean = false,
	listener: SocketMessageReceiver<T>,
): SocketListener {
	val type = T::class
	val definition = SocketListenerDefinition(
		subscribesTo = type.subscriptionType?.let { setOf(it) }.orEmpty(),
		filterTypes = setOf(type),
		stopOnCredentialsChange = stopOnCredentialsChange,
		listener = { message ->
			if (message is T) listener.onReceive(message)
		}
	)
	return addListenerDefinition(definition)
}

/**
 * Add a listener that listens to certain [GeneralCommandType] entries in the [GeneralCommandMessage].
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketInstance.addGeneralCommandsListener(
	commands: Set<GeneralCommandType> = GeneralCommandType.values().toSet(),
	stopOnCredentialsChange: Boolean = false,
	listener: SocketMessageReceiver<GeneralCommandMessage>,
): SocketListener {
	val definition = SocketListenerDefinition(
		subscribesTo = emptySet(),
		filterTypes = setOf(GeneralCommandMessage::class),
		stopOnCredentialsChange = stopOnCredentialsChange,
		listener = { message ->
			if (message is GeneralCommandMessage && message.data?.name in commands) {
				listener.onReceive(message)
			}
		}
	)
	return addListenerDefinition(definition)
}

/**
 * Add a listener that listens to certain [PlaystateCommand] entries in the [PlaystateMessage].
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketInstance.addPlayStateCommandsListener(
	commands: Set<PlaystateCommand> = PlaystateCommand.values().toSet(),
	stopOnCredentialsChange: Boolean = false,
	listener: SocketMessageReceiver<PlaystateMessage>,
): SocketListener {
	val definition = SocketListenerDefinition(
		subscribesTo = emptySet(),
		filterTypes = setOf(PlaystateMessage::class),
		stopOnCredentialsChange = stopOnCredentialsChange,
		listener = { message ->
			if (message is PlaystateMessage && message.data?.command in commands) {
				listener.onReceive(message)
			}
		}
	)
	return addListenerDefinition(definition)
}

/**
 * Add a listener that listens to certain [SendCommandType] entries in the [SyncPlayCommandMessage].
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketInstance.addSyncPlayCommandsListener(
	commands: Set<SendCommandType> = SendCommandType.values().toSet(),
	stopOnCredentialsChange: Boolean = false,
	listener: SocketMessageReceiver<SyncPlayCommandMessage>,
): SocketListener {
	val definition = SocketListenerDefinition(
		subscribesTo = emptySet(),
		filterTypes = setOf(SyncPlayCommandMessage::class),
		stopOnCredentialsChange = stopOnCredentialsChange,
		listener = { message ->
			if (message is SyncPlayCommandMessage && message.data?.command in commands) {
				listener.onReceive(message)
			}
		}
	)
	return addListenerDefinition(definition)
}
