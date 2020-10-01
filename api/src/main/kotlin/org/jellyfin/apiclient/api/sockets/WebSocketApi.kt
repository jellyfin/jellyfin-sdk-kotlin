package org.jellyfin.apiclient.api.sockets

import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.model.socket.*
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
@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class WebSocketApi(
	private val api: KtorClient
) {
	companion object {
		// Mapping for types to message
		val incomingMessageSerializers = mapOf(
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
	}

	private val logger = LoggerFactory.getLogger("WebSocketApi")

	private val json = Json {
		// Based on DefaultJson
		isLenient = false
		ignoreUnknownKeys = true
		allowSpecialFloatingPointValues = true
		useArrayPolymorphism = false
		encodeDefaults = true
	}

	private var socketJob: Job? = null

	private val incomingMessageChannel by lazy {
		BroadcastChannel<IncomingSocketMessage>(BUFFERED)
	}

	private val outgoingMessageChannel by lazy {
		Channel<String>(BUFFERED)
	}

	private suspend fun ReceiveChannel<Frame>.read() = consumeAsFlow()
		.onEach {
			val message = it.readSocketMessage() ?: return@onEach

			// Send message
			incomingMessageChannel.send(message)
		}
		.catch { it.printStackTrace() }
		.onCompletion {
			// Reconnect
			// TODO Only reconnect if listeners exist
			logger.debug("Socket receiver completed")
			reconnect()
		}
		.collect()

	private suspend fun SendChannel<Frame>.write() = outgoingMessageChannel
		.receiveAsFlow()
		.onEach { text ->
			logger.info("Sending message {}", text)
			send(Frame.Text(text))
		}
		.catch { it.printStackTrace() }
		.collect()

	private suspend fun ensureConnected() {
		logger.debug("Validating connection")

		if (socketJob == null) reconnect()
	}

	suspend fun reconnect() {
		// Get base url and access token and check if they're set
		val baseUrl = checkNotNull(api.baseUrl)
		val accessToken = checkNotNull(api.accessToken)

		logger.debug("Reconnect requested")

		// Close existing socket
		socketJob?.cancel()

		// Open socket
		socketJob = GlobalScope.launch {
			// Create web socket connection
			api.client.ws({
				url {
					// Create from base URL
					takeFrom(baseUrl.replace("^http".toRegex(), "ws"))

					// Assign path making sure to remove duplicated slashes between the base and appended path
					encodedPath = "${encodedPath.trimEnd('/')}/socket"

					// Add required parameters
					parameter("api_key", accessToken)
					parameter("deviceId", api.deviceInfo.id)
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
		val json = json.parseToJsonElement(text)
		require(json is JsonObject)

		// Read id
		val messageId = json["MessageId"]?.jsonPrimitive?.content
		require(messageId is String)

		// Read type
		val type = json["MessageType"]?.jsonPrimitive?.content
		require(type is String)

		// Get serializer for type
		val dataSerializer = incomingMessageSerializers[type]
		if (dataSerializer == null) {
			logger.warn("Message type {} appears to be missing in the incomingMessageSerializers map.", type)
			return null
		}

		// Modify JSON to flatten the Data object
		val modifiedJson = buildJsonObject {
			put("MessageId", messageId)

			// Flatten data object or keep it when it's not an object
			val data = json["Data"]
			if (data is JsonObject) data.entries.forEach { (key, value) -> put(key, value) }
			else put("Data", data!!)
		}

		return api.json.decodeFromJsonElement(dataSerializer, modifiedJson) as? IncomingSocketMessage
	}

	suspend inline fun <reified T : OutgoingSocketMessage> publish(message: T) = publish(message, serializer())

	suspend fun <T : OutgoingSocketMessage> publish(message: T, serializer: KSerializer<T>) {
		val jsonObject = json.encodeToJsonElement(serializer, message).jsonObject
		val messageType = serializer.descriptor.serialName

		val text = json.encodeToString(buildJsonObject {
			put("MessageType", messageType)

			val data = jsonObject["Data"]
			if (data != null) put("Data", data)
			else putJsonObject("Data") {
				jsonObject.entries
					.filterNot { (key, _) -> key == "MessageType" }
					.forEach { (key, value) -> put(key, value) }
			}
		})

		outgoingMessageChannel.send(text)
	}

	suspend fun subscribe(): Flow<IncomingSocketMessage> {
		ensureConnected()

		return incomingMessageChannel.asFlow()
	}
}
