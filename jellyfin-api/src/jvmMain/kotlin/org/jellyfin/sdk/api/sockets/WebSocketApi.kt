package org.jellyfin.sdk.api.sockets

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.http.URLProtocol
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.http.takeFrom
import io.ktor.util.error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlinx.serialization.serializer
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.operations.Api
import org.jellyfin.sdk.model.api.SessionMessageType
import org.jellyfin.sdk.model.api.SessionMessageType.ACTIVITY_LOG_ENTRY
import org.jellyfin.sdk.model.api.SessionMessageType.ACTIVITY_LOG_ENTRY_START
import org.jellyfin.sdk.model.api.SessionMessageType.ACTIVITY_LOG_ENTRY_STOP
import org.jellyfin.sdk.model.api.SessionMessageType.FORCE_KEEP_ALIVE
import org.jellyfin.sdk.model.api.SessionMessageType.GENERAL_COMMAND
import org.jellyfin.sdk.model.api.SessionMessageType.KEEP_ALIVE
import org.jellyfin.sdk.model.api.SessionMessageType.LIBRARY_CHANGED
import org.jellyfin.sdk.model.api.SessionMessageType.PACKAGE_INSTALLATION_CANCELLED
import org.jellyfin.sdk.model.api.SessionMessageType.PACKAGE_INSTALLATION_COMPLETED
import org.jellyfin.sdk.model.api.SessionMessageType.PACKAGE_INSTALLATION_FAILED
import org.jellyfin.sdk.model.api.SessionMessageType.PACKAGE_INSTALLING
import org.jellyfin.sdk.model.api.SessionMessageType.PACKAGE_UNINSTALLED
import org.jellyfin.sdk.model.api.SessionMessageType.PLAY
import org.jellyfin.sdk.model.api.SessionMessageType.PLAYSTATE
import org.jellyfin.sdk.model.api.SessionMessageType.REFRESH_PROGRESS
import org.jellyfin.sdk.model.api.SessionMessageType.RESTART_REQUIRED
import org.jellyfin.sdk.model.api.SessionMessageType.SCHEDULED_TASKS_INFO
import org.jellyfin.sdk.model.api.SessionMessageType.SCHEDULED_TASKS_INFO_START
import org.jellyfin.sdk.model.api.SessionMessageType.SCHEDULED_TASKS_INFO_STOP
import org.jellyfin.sdk.model.api.SessionMessageType.SCHEDULED_TASK_ENDED
import org.jellyfin.sdk.model.api.SessionMessageType.SERIES_TIMER_CANCELLED
import org.jellyfin.sdk.model.api.SessionMessageType.SERIES_TIMER_CREATED
import org.jellyfin.sdk.model.api.SessionMessageType.SERVER_RESTARTING
import org.jellyfin.sdk.model.api.SessionMessageType.SERVER_SHUTTING_DOWN
import org.jellyfin.sdk.model.api.SessionMessageType.SESSIONS
import org.jellyfin.sdk.model.api.SessionMessageType.SESSIONS_START
import org.jellyfin.sdk.model.api.SessionMessageType.SESSIONS_STOP
import org.jellyfin.sdk.model.api.SessionMessageType.SYNC_PLAY_COMMAND
import org.jellyfin.sdk.model.api.SessionMessageType.SYNC_PLAY_GROUP_UPDATE
import org.jellyfin.sdk.model.api.SessionMessageType.TIMER_CANCELLED
import org.jellyfin.sdk.model.api.SessionMessageType.TIMER_CREATED
import org.jellyfin.sdk.model.api.SessionMessageType.USER_DATA_CHANGED
import org.jellyfin.sdk.model.api.SessionMessageType.USER_DELETED
import org.jellyfin.sdk.model.api.SessionMessageType.USER_UPDATED
import org.jellyfin.sdk.model.socket.ActivityLogEntryMessage
import org.jellyfin.sdk.model.socket.ForceKeepAliveMessage
import org.jellyfin.sdk.model.socket.GeneralCommandMessage
import org.jellyfin.sdk.model.socket.IncomingSocketMessage
import org.jellyfin.sdk.model.socket.KeepAliveMessage
import org.jellyfin.sdk.model.socket.LibraryChangedMessage
import org.jellyfin.sdk.model.socket.OutgoingSocketMessage
import org.jellyfin.sdk.model.socket.PackageInstallationCancelledMessage
import org.jellyfin.sdk.model.socket.PackageInstallationCompletedMessage
import org.jellyfin.sdk.model.socket.PackageInstallationFailedMessage
import org.jellyfin.sdk.model.socket.PackageInstallingMessage
import org.jellyfin.sdk.model.socket.PackageUninstalledMessage
import org.jellyfin.sdk.model.socket.PlayMessage
import org.jellyfin.sdk.model.socket.PlayStateMessage
import org.jellyfin.sdk.model.socket.RefreshProgressMessage
import org.jellyfin.sdk.model.socket.RestartRequiredMessage
import org.jellyfin.sdk.model.socket.ScheduledTaskEndedMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoMessage
import org.jellyfin.sdk.model.socket.SeriesTimerCancelledMessage
import org.jellyfin.sdk.model.socket.SeriesTimerCreatedMessage
import org.jellyfin.sdk.model.socket.ServerRestartingMessage
import org.jellyfin.sdk.model.socket.ServerShuttingDownMessage
import org.jellyfin.sdk.model.socket.SessionsMessage
import org.jellyfin.sdk.model.socket.SyncPlayCommandMessage
import org.jellyfin.sdk.model.socket.SyncPlayGroupUpdateMessage
import org.jellyfin.sdk.model.socket.TimerCancelledMessage
import org.jellyfin.sdk.model.socket.TimerCreatedMessage
import org.jellyfin.sdk.model.socket.UserDataChangedMessage
import org.jellyfin.sdk.model.socket.UserDeletedMessage
import org.jellyfin.sdk.model.socket.UserUpdatedMessage

private val logger = KotlinLogging.logger {}

/**
 * Provides realtime communication with the Jellyfin server.
 * Automatically connects when subscribed to or when messages are sent and disconnects when no subscribers left.
 *
 * When the connections drops it will reconnect automatically.
 * Will not react to access token or server url changes, call .reconnect() to act on these changes.
 *
 * The user should verify the access token is correct as the server does not respond to bad authorization.
 */
public class WebSocketApi(
	private val api: ApiClient,
) : Api {
	private companion object {
		private const val JSON_PROP_DATA = "Data"
		private const val JSON_PROP_MESSAGE_ID = "MessageId"
		private const val JSON_PROP_MESSAGE_TYPE = "MessageType"
		private const val RECONNECT_DELAY = 3 * 1000L // milliseconds
	}

	@Suppress("ComplexMethod")
	private fun SessionMessageType.getSerializer() = when (this) {
		FORCE_KEEP_ALIVE -> serializer<ForceKeepAliveMessage>()
		GENERAL_COMMAND -> serializer<GeneralCommandMessage>()
		USER_DATA_CHANGED -> serializer<UserDataChangedMessage>()
		SESSIONS -> serializer<SessionsMessage>()
		PLAY -> serializer<PlayMessage>()
		SYNC_PLAY_COMMAND -> serializer<SyncPlayCommandMessage>()
		SYNC_PLAY_GROUP_UPDATE -> serializer<SyncPlayGroupUpdateMessage>()
		PLAYSTATE -> serializer<PlayStateMessage>()
		RESTART_REQUIRED -> serializer<RestartRequiredMessage>()
		SERVER_SHUTTING_DOWN -> serializer<ServerShuttingDownMessage>()
		SERVER_RESTARTING -> serializer<ServerRestartingMessage>()
		LIBRARY_CHANGED -> serializer<LibraryChangedMessage>()
		USER_DELETED -> serializer<UserDeletedMessage>()
		USER_UPDATED -> serializer<UserUpdatedMessage>()
		SERIES_TIMER_CREATED -> serializer<SeriesTimerCreatedMessage>()
		TIMER_CREATED -> serializer<TimerCreatedMessage>()
		SERIES_TIMER_CANCELLED -> serializer<SeriesTimerCancelledMessage>()
		TIMER_CANCELLED -> serializer<TimerCancelledMessage>()
		REFRESH_PROGRESS -> serializer<RefreshProgressMessage>()
		SCHEDULED_TASK_ENDED -> serializer<ScheduledTaskEndedMessage>()
		PACKAGE_INSTALLATION_CANCELLED -> serializer<PackageInstallationCancelledMessage>()
		PACKAGE_INSTALLATION_FAILED -> serializer<PackageInstallationFailedMessage>()
		PACKAGE_INSTALLATION_COMPLETED -> serializer<PackageInstallationCompletedMessage>()
		PACKAGE_INSTALLING -> serializer<PackageInstallingMessage>()
		PACKAGE_UNINSTALLED -> serializer<PackageUninstalledMessage>()
		ACTIVITY_LOG_ENTRY -> serializer<ActivityLogEntryMessage>()
		SCHEDULED_TASKS_INFO -> serializer<ScheduledTasksInfoMessage>()

		// Shared type, only implemented as outgoing message
		KEEP_ALIVE -> serializer<KeepAliveMessage>()

		// Send only - should not be possible to receive
		ACTIVITY_LOG_ENTRY_START -> null
		ACTIVITY_LOG_ENTRY_STOP -> null
		SESSIONS_START -> null
		SESSIONS_STOP -> null
		SCHEDULED_TASKS_INFO_START -> null
		SCHEDULED_TASKS_INFO_STOP -> null
	} ?: throw NotImplementedError("Messages of type $this should not be sent by the server.")

	private val client: HttpClient = HttpClient {
		followRedirects = api.httpClientOptions.followRedirects

		install(HttpTimeout) {
			connectTimeoutMillis = api.httpClientOptions.connectTimeout
			requestTimeoutMillis = api.httpClientOptions.requestTimeout
			socketTimeoutMillis = api.httpClientOptions.socketTimeout
		}

		install(WebSockets)
	}

	private val scope = CoroutineScope(Dispatchers.IO)
	private var socketJob: Job? = null
	private val subscriptions = mutableListOf<SocketSubscription>()

	private val outgoingMessageChannel by lazy {
		Channel<String>(BUFFERED)
	}

	private suspend fun ReceiveChannel<Frame>.read() = consumeAsFlow()
		.onEach {
			val message = it.readSocketMessage() ?: return@onEach

			// Send message to subscriptions
			subscriptions.forEach { subscription -> subscription.callback(message) }
		}
		.catch { logger.error(it) }
		.onCompletion {
			// Reconnect
			logger.debug("Socket receiver completed, found ${subscriptions.size} subscriptions")
			delay(RECONNECT_DELAY)
			if (subscriptions.isNotEmpty()) reconnect()
		}
		.collect()

	private suspend fun SendChannel<Frame>.write() = outgoingMessageChannel
		.receiveAsFlow()
		.onEach { text ->
			logger.info { "Sending message $text" }
			send(Frame.Text(text))
		}
		.catch { logger.error(it) }
		.collect()

	private fun subscriptionsChanged() {
		logger.debug("Subscriptions changed to ${subscriptions.size}")

		if (socketJob != null && subscriptions.isEmpty()) {
			logger.info("Dropping connection")
			socketJob?.cancel()
		} else if (socketJob == null && subscriptions.isNotEmpty()) {
			logger.info("Creating connection")
			reconnect()
		}
	}

	/**
	 * Call to (re)connect the WebSocket. Does not close current listeners.
	 */
	public fun reconnect() {
		// Get access token and check if it's not null
		val accessToken = checkNotNull(api.accessToken)

		logger.debug("Reconnect requested")

		// Close existing socket
		socketJob?.cancel()

		// Open socket
		socketJob = scope.launch {
			// Create web socket connection
			client.ws({
				url {
					takeFrom(api.createUrl(
						pathTemplate = "/socket",
						queryParameters = mapOf(
							"api_key" to accessToken,
							"deviceId" to api.deviceInfo.id
						)
					))

					protocol = when (protocol) {
						URLProtocol.HTTP -> URLProtocol.WS
						URLProtocol.HTTPS -> URLProtocol.WSS

						// Default to WS
						else -> URLProtocol.WS
					}
				}
			}) {
				// Bind to messaging channels
				joinAll(
					launch { incoming.read() },
					launch { outgoing.write() },
				)
			}
		}
	}

	private fun Frame.readSocketMessage(): IncomingSocketMessage? {
		require(this is Frame.Text)

		// Read text from frame
		val text = readText()
		logger.info { "Received message $text" }

		// Read JSON object from text
		val message = ApiSerializer.json.parseToJsonElement(text)
		require(message is JsonObject)

		// Read id
		val messageId = message[JSON_PROP_MESSAGE_ID]?.jsonPrimitive?.content
		require(messageId is String)

		// Read type
		val type = message[JSON_PROP_MESSAGE_TYPE]?.let {
			ApiSerializer.json.decodeFromJsonElement<SessionMessageType>(it)
		}
		requireNotNull(type)

		// Get serializer for type
		val dataSerializer = type.getSerializer()

		// Modify JSON to flatten the Data object
		val modifiedJson = buildJsonObject {
			put(JSON_PROP_MESSAGE_ID, messageId)

			// Flatten data object or keep it when it's not an object
			val data = message[JSON_PROP_DATA]
			if (data is JsonObject) data.entries.forEach { (key, value) -> put(key, value) }
			else put(JSON_PROP_DATA, data!!)
		}

		return ApiSerializer.json.decodeFromJsonElement(dataSerializer, modifiedJson) as? IncomingSocketMessage
	}

	/**
	 * Publish a message to the server.
	 */
	@ExperimentalSerializationApi
	public suspend inline fun <reified T : OutgoingSocketMessage> publish(message: T): Unit =
		publish(message, serializer())

	/**
	 * Publish a message to the server.
	 */
	@ExperimentalSerializationApi
	public suspend fun <T : OutgoingSocketMessage> publish(message: T, serializer: KSerializer<T>) {
		val jsonObject = ApiSerializer.json.encodeToJsonElement(serializer, message).jsonObject
		val messageType = serializer.descriptor.serialName

		val text = ApiSerializer.json.encodeToString(buildJsonObject {
			put(JSON_PROP_MESSAGE_TYPE, messageType)

			val data = jsonObject[JSON_PROP_DATA]
			if (data != null) put(JSON_PROP_DATA, data)
			else putJsonObject(JSON_PROP_DATA) {
				jsonObject.entries
					.filterNot { (key, _) -> key == JSON_PROP_MESSAGE_TYPE }
					.forEach { (key, value) -> put(key, value) }
			}
		})

		outgoingMessageChannel.send(text)
	}

	/**
	 * Start listening to messages. Calls the [block] for each incoming message until
	 * [SocketSubscription.cancel] is invoked.
	 */
	public fun subscribe(block: suspend (IncomingSocketMessage) -> Unit): SocketSubscription {
		val subscription = SocketSubscription(this, block)
		subscriptions += subscription
		subscriptionsChanged()

		return subscription
	}

	/**
	 * A [Flow] based version of a subscription.
	 */
	@ExperimentalCoroutinesApi
	public fun subscribe(): Flow<IncomingSocketMessage> = callbackFlow {
		// Create subscription and send messages to flow
		val subscription = subscribe {
			trySend(it)
		}

		// Cancel subscription when flow closes
		awaitClose {
			subscription.cancel()
		}
	}

	/**
	 * Cancel a subscription by removing it from the API.
	 * Automatically closes the WebSocket if no subscriptions are left.
	 */
	public fun cancelSubscription(subscription: SocketSubscription) {
		subscriptions -= subscription
		subscriptionsChanged()
	}
}
