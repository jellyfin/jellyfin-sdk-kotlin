# Using WebSockets

Added in v1.2.0, the new WebSocket implementation can be used to interact with the Jellyfin WebSocket server. This API
is not supported with the Java language because it heavily relies on coroutines and inline functions.

Get started by creating a new authenticated API instance using the `createApi` function in the Jellyfin class.

```kotlin
val api = jellyfin.createApi(baseUrl = "https://demo.jellyfin.org/stable/")
```

## Connecting

The socket connection is managed by an "instance". You can have multiple of these instances at the same time. However,
it is recommended to use a single instance during the lifecycle of your application. Use the `ws()`
function from the ApiClient class to create a new instance.

```kotlin
val instance = api.ws()
```

You can close an instance when it's no longer in use with the `stop()` function.

```kotlin
instance.stop()
```

## Updating credentials

An instance does not automatically refresh credentials. You'll need to manually refresh the instance when the access
token, server or device info change. Use the `updateCredentials()` function to apply these changes. The instance
automatically reconnects when required.

```kotlin
instance.updateCredentials()
```

## Listen for messages

Listeners are used to receive the various types of websocket messages. A connection is automatically started and/or
closed depending on the active listeners. Multiple helper functions can be used to register a listener. They all return
a `SocketListener` object that can be used to remove the listener later with the `removeListener()` function on the
instance or the `stop()` on the listener.

## Listen for specific messages

Use the `addListener()` function to create a listener that receives a single type.

```kotlin
instance.addListener<UserDataChangedMessage> { message ->
	// type of message is UserDataChangedMessage
	println("Received a message: $message")
}
```

## Listen for all messages

If you want to listen for all types of messages instead. Use the `addGlobalListener` function.

```kotlin
instance.addGlobalListener { message ->
	// type of message is IncomingSocketMessage
	println("Received a message: $message")
}
```

## Listen for grouped message types

Some incoming messages are used for multiple kinds of information. These are the general, play state and SyncPlay
commands. To filter the types of commands there are a few helper functions available. All of them support a "commands"
parameter to define the message types to receive. All types will be sent when the commands parameter is omitted. This is
the same behavior as using `addListener`.

```kotlin
instance.addGeneralCommandsListener(
	commands = setOf(GeneralCommandType.DISPLAY_MESSAGE)
) { message ->
	// type of message is GeneralCommandMessage
	println("Received a message: $message")
}

instance.addPlayStateCommandsListener(
	commands = setOf(PlaystateCommand.NEXT_TRACK, PlaystateCommand.PREVIOUS_TRACK)
) { message ->
	// type of message is PlayStateMessage
	println("Received a message: $message")
}

instance.addSyncPlayCommandsListener(
	commands = setOf(SendCommandType.PAUSE, SendCommandType.UNPAUSE)
) { message ->
	// type of message is SyncPlayCommandMessage
	println("Received a message: $message")
}
```

## Advanced listeners

All previously mentioned functions to add listeners use the `addListenerDefinition` function under the hood. This
function is not recommended being used directly. Use the other functions instead. The function receives a listener
definition.

An example for listening to both LibraryChangedMessage and UserDataChangedMessage messages:

```kotlin
instance.addListenerDefinition(
	SocketListenerDefinition(
		subscribesTo = emptySet(),
		filterTypes = setOf(LibraryChangedMessage::class, UserDataChangedMessage::class),
		stopOnCredentialsChange = false,
		listener = { message ->
			// type of message is IncomingSocketMessage
			println("Received a message: $message")
		}
	)
)
```

## Sending messages

The Jellyfin server uses HTTP endpoints, mostly in the SessionApi, to manipulate state. The only messages send by a
client are to enable subscriptions. These subscriptions are automatically managed by the SDK. The `publish()` function
can still be used if you need to send your own messages. The function receives a `OutgoingSocketMessage` type and sends
it to the server.

```kotlin
instance.publish(SessionsStartMessage())
```

> **Note**: Do not send start and stop messages manually. This can confuse the SDK and cause unknown behavior.

## Message Types

The following messages types are supported in the SDK.

### Incoming

- GeneralCommandMessage
- UserDataChangedMessage
- SessionsMessage
- PlayMessage
- SyncPlayCommandMessage
- SyncPlayGroupUpdateMessage
- PlayStateMessage
- RestartRequiredMessage
- ServerShuttingDownMessage
- ServerRestartingMessage
- LibraryChangedMessage
- UserDeletedMessage
- UserUpdatedMessage
- SeriesTimerCreatedMessage
- TimerCreatedMessage
- SeriesTimerCancelledMessage
- TimerCancelledMessage
- RefreshProgressMessage
- ScheduledTaskEndedMessage
- PackageInstallationCancelledMessage
- PackageInstallationFailedMessage
- PackageInstallationCompletedMessage
- PackageInstallingMessage
- PackageUninstalledMessage
- ActivityLogEntryMessage
- ScheduledTasksInfoMessage

### Outgoing

- ActivityLogEntryStartMessage and ActivityLogEntryStopMessage
- SessionsStartMessage and SessionsStopMessage
- ScheduledTasksInfoStartMessage and ScheduledTasksInfoStopMessage

## Sample usage

- The [observe] command in the [kotlin-cli] sample uses websockets to listen for messages.
- The [jellyfin-androidtv] app uses websockets for remote media control and realtime data updates.

[observe]: https://github.com/jellyfin/jellyfin-sdk-kotlin/tree/master/samples/kotlin-cli/src/main/kotlin/org/jellyfin/sample/cli/command/Observe.kt

[kotlin-cli]: https://github.com/jellyfin/jellyfin-sdk-kotlin/tree/master/samples/kotlin-cli/

[jellyfin-androidtv]: https://github.com/jellyfin/jellyfin-androidtv
