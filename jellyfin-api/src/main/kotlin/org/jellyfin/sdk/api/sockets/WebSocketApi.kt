package org.jellyfin.sdk.api.sockets

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.util.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.model.socket.*
import org.slf4j.LoggerFactory

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
	private val api: ApiClient
) {
	private companion object {
		// Mapping for types to message
		private val incomingMessageSerializers = mapOf(
			"ForceKeepAlive" to serializer<ForceKeepAliveMessage>(),
			"GeneralCommand" to serializer<GeneralCommandMessage>(),
			"UserDataChanged" to serializer<UserDataChangedMessage>(),
			"Sessions" to serializer<SessionsMessage>(),
			"Play" to serializer<PlayMessage>(),
			"SyncPlayCommand" to serializer<SyncPlayCommandMessage>(),
			"SyncPlayGroupUpdate" to serializer<SyncPlayGroupUpdateMessage>(),
			"PlayState" to serializer<PlayStateMessage>(),
			"RestartRequired" to serializer<RestartRequiredMessage>(),
			"ServerShuttingDown" to serializer<ServerShuttingDownMessage>(),
			"ServerRestarting" to serializer<ServerRestartingMessage>(),
			"LibraryChanged" to serializer<LibraryChangedMessage>(),
			"UserDeleted" to serializer<UserDeletedMessage>(),
			"UserUpdated" to serializer<UserUpdatedMessage>(),
			"SeriesTimerCreated" to serializer<SeriesTimerCreatedMessage>(),
			"TimerCreated" to serializer<TimerCreatedMessage>(),
			"SeriesTimerCancelled" to serializer<SeriesTimerCancelledMessage>(),
			"TimerCancelled" to serializer<TimerCancelledMessage>(),
			"RefreshProgress" to serializer<RefreshProgressMessage>(),
			"ScheduledTaskEnded" to serializer<ScheduledTaskEndedMessage>(),
			"PackageInstallationCancelled" to serializer<PackageInstallationCancelledMessage>(),
			"PackageInstallationFailed" to serializer<PackageInstallationFailedMessage>(),
			"PackageInstallationCompleted" to serializer<PackageInstallationCompletedMessage>(),
			"PackageInstalling" to serializer<PackageInstallingMessage>(),
			"PackageUninstalled" to serializer<PackageUninstalledMessage>(),
			"ActivityLogEntry" to serializer<ActivityLogEntryMessage>(),
			"ScheduledTasksInfo" to serializer<ScheduledTasksInfoMessage>(),

			// Shared type, only implemented as outgoing message
			"KeepAlive" to serializer<KeepAliveMessage>(),
		)

		private const val JSON_PROP_DATA = "Data"
		private const val JSON_PROP_MESSAGE_ID = "MessageId"
		private const val JSON_PROP_MESSAGE_TYPE = "MessageType"
		private const val RECONNECT_DELAY = 3 * 1000L // milliseconds
	}

	private val logger = LoggerFactory.getLogger("WebSocketApi")

	private val client: HttpClient = HttpClient {
		install(HttpTimeout) {
			connectTimeoutMillis = 10000
		}

		install(WebSockets)
	}

	private val json = Json {
		// Based on DefaultJson
		isLenient = false
		ignoreUnknownKeys = true
		allowSpecialFloatingPointValues = true
		useArrayPolymorphism = false
		encodeDefaults = true
	}

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
			logger.info("Sending message {}", text)
			send(Frame.Text(text))
		}
		.catch { logger.error(it) }
		.collect()

	private suspend fun subscriptionsChanged() {
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
	public suspend fun reconnect() {
		// Get access token and check if it's not null
		val accessToken = checkNotNull(api.accessToken)

		logger.debug("Reconnect requested")

		// Close existing socket
		socketJob?.cancel()

		// Open socket
		socketJob = GlobalScope.launch {
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
		logger.info("Received message {}", text)

		// Read JSON object from text
		val message = json.parseToJsonElement(text)
		require(message is JsonObject)

		// Read id
		val messageId = message[JSON_PROP_MESSAGE_ID]?.jsonPrimitive?.content
		require(messageId is String)

		// Read type
		val type = message[JSON_PROP_MESSAGE_TYPE]?.jsonPrimitive?.content
		require(type is String)

		// Get serializer for type
		val dataSerializer = incomingMessageSerializers[type]
		if (dataSerializer == null) {
			logger.warn("Message type {} appears to be missing in the incomingMessageSerializers map.", type)
			return null
		}

		// Modify JSON to flatten the Data object
		val modifiedJson = buildJsonObject {
			put(JSON_PROP_MESSAGE_ID, messageId)

			// Flatten data object or keep it when it's not an object
			val data = message[JSON_PROP_DATA]
			if (data is JsonObject) data.entries.forEach { (key, value) -> put(key, value) }
			else put(JSON_PROP_DATA, data!!)
		}

		return json.decodeFromJsonElement(dataSerializer, modifiedJson) as? IncomingSocketMessage
	}

	/**
	 * Publish a message to the server.
	 */
	public suspend inline fun <reified T : OutgoingSocketMessage> publish(message: T): Unit = publish(message, serializer())

	/**
	 * Publish a message to the server.
	 */
	public suspend fun <T : OutgoingSocketMessage> publish(message: T, serializer: KSerializer<T>) {
		val jsonObject = json.encodeToJsonElement(serializer, message).jsonObject
		val messageType = serializer.descriptor.serialName

		val text = json.encodeToString(buildJsonObject {
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
	public suspend fun subscribe(block: (IncomingSocketMessage) -> Unit): SocketSubscription {
		val subscription = SocketSubscription(this, block)
		subscriptions += subscription
		subscriptionsChanged()

		return subscription
	}

	/**
	 * A [Flow] based version of a subscription.
	 */
	public suspend fun subscribe(): Flow<IncomingSocketMessage> = callbackFlow {
		// Create subscription and send messages to flow
		val subscription = subscribe {
			sendBlocking(it)
		}

		// Cancel subscription when flow closes
		awaitClose {
			runBlocking {
				subscription.cancel()
			}
		}
	}

	/**
	 * Cancel a subscription by removing it from the API.
	 * Automatically closes the WebSocket if no subscriptions are left.
	 */
	public suspend fun cancelSubscription(subscription: SocketSubscription) {
		subscriptions -= subscription
		subscriptionsChanged()
	}
}

