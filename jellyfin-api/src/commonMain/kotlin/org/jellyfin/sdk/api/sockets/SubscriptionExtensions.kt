package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import org.jellyfin.sdk.model.api.GeneralCommandMessage
import org.jellyfin.sdk.model.api.GeneralCommandType
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import org.jellyfin.sdk.model.api.PlaystateCommand
import org.jellyfin.sdk.model.api.PlaystateMessage
import org.jellyfin.sdk.model.api.SendCommandType
import org.jellyfin.sdk.model.api.SyncPlayCommandMessage

/**
 * Subscribe to a specific WebSocket message type.
 */
public inline fun <reified T : OutboundWebSocketMessage> SocketApi.subscribe(): Flow<T> = subscribe(T::class)

/**
 * Subscribe to specific [GeneralCommandType] messages.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketApi.subscribeGeneralCommands(
	commands: Set<GeneralCommandType> = GeneralCommandType.values().toSet(),
): Flow<GeneralCommandMessage> = subscribe<GeneralCommandMessage>()
	.filter { message -> message.data?.name in commands }

/**
 * Subscribe to a specific [GeneralCommandType] message.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketApi.subscribeGeneralCommand(
	command: GeneralCommandType,
): Flow<GeneralCommandMessage> = subscribeGeneralCommands(setOf(command))

/**
 * Subscribe to specific [PlaystateCommand] messages.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketApi.subscribePlayStateCommands(
	commands: Set<PlaystateCommand> = PlaystateCommand.values().toSet(),
): Flow<PlaystateMessage> = subscribe<PlaystateMessage>()
	.filter { message -> message.data?.command in commands }

/**
 * Subscribe to a specific [PlaystateCommand] message.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketApi.subscribePlayStateCommand(
	command: PlaystateCommand,
): Flow<PlaystateMessage> = subscribePlayStateCommands(setOf(command))

/**
 * Subscribe to specific [SendCommandType] messages.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketApi.subscribeSyncPlayCommands(
	commands: Set<SendCommandType> = SendCommandType.values().toSet(),
): Flow<SyncPlayCommandMessage> = subscribe<SyncPlayCommandMessage>()
	.filter { message -> message.data?.command in commands }

/**
 * Subscribe to a specific [SendCommandType] message.
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun SocketApi.subscribeSyncPlayCommand(
	command: SendCommandType,
): Flow<SyncPlayCommandMessage> = subscribeSyncPlayCommands(setOf(command))
