# Using WebSockets

The WebSocket API can be used to interact with the Jellyfin WebSocket server. Get started by creating a new
authenticated API instance using the `createApi` function in the Jellyfin class.

Listening the WebSocket messages is done using subscriptions. Whenever one or multiple subscriptions are active the
WebSocket will automatically connect to the server. Credentials changes via the `update()` function will cause the
WebSocket to reconnect. There is a single WebSocket API for each ApiClient.

```kotlin
// Create an API instance
val api = jellyfin.createApi(baseUrl = "https://demo.jellyfin.org/stable/")

// Create a subscription for WebSocket all messages
api.webSocket.subscribeAll().collect { message ->
	println(message)
}
```

## Updating credentials

The WebSocket connection will reconnect when the server URL, access token, client information of device information
changes. All existing subscriptions stay active and automatically switch to the new credentials.

## Subscribe to messages

Subscriptions are used to receive the various types of websocket messages. A connection is automatically started and/or
closed depending on the availability of subscriptions. Multiple extension functions can be used to create a
subscription. They all return a flow that emits each message as soon as it is received.

## Subscribe to specific messages

Use the `subscribe<T>()` function to create a listener that receives a single message type.

```kotlin
api.webSocket.subscribe<UserDataChangedMessage>().collect { message ->
	// type of message is UserDataChangedMessage
	println("Received a message: $message")
}
```

## Subscribe to all messages

Use the `subscribeAll` function if you want to subscribe to all types of messages instead. This is not recommended if
you only want to receive a subset of message types.

```kotlin
api.webSocket.subscribeAll().collect { message ->
	// type of message is OutboundWebSocketMessage
	println("Received a message: $message")
}
```

## Subscribe to grouped message types

Some incoming messages are used for multiple kinds of information. These are the general commands, play state and
SyncPlay command messages. To filter the types of commands there are extensions functions available. All of them support
filtering for one or multiple commands to receive. All types will be sent when the commands parameter is omitted. This is
the same behavior as using the `subscribe<T>` function.

```kotlin
api.webSocket.subscribeGeneralCommand(GeneralCommandType.DISPLAY_MESSAGE).collect { message ->
	// type of message is GeneralCommandMessage
	println("Received a message: $message")
}

api.webSocket.subscribePlayStateCommands(
	commands = setOf(PlaystateCommand.NEXT_TRACK, PlaystateCommand.PREVIOUS_TRACK)
).collect { message ->
	// type of message is PlaystateMessage
	println("Received a message: $message")
}

api.webSocket.subscribeSyncPlayCommands(
	commands = setOf(SendCommandType.PAUSE, SendCommandType.UNPAUSE)
) { message ->
	// type of message is SyncPlayCommandMessage
	println("Received a message: $message")
}
```

## Sending messages

The SDK does not expose any functionality to send messages. Publishing messages is only used to keep the connection
alive or to request for certain message types to be sent. This is all managed by the SDK.

## Sample usage

- The [observe] command in the [kotlin-cli] sample uses websockets to listen for messages.
- The [jellyfin-androidtv] app uses websockets for remote media control and realtime data updates.

[observe]: https://github.com/jellyfin/jellyfin-sdk-kotlin/tree/master/samples/kotlin-cli/src/main/kotlin/org/jellyfin/sample/cli/command/Observe.kt

[kotlin-cli]: https://github.com/jellyfin/jellyfin-sdk-kotlin/tree/master/samples/kotlin-cli/

[jellyfin-androidtv]: https://github.com/jellyfin/jellyfin-androidtv
