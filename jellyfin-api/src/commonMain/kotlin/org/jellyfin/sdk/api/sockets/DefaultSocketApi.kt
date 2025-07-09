package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.data.SUBSCRIPTION_TYPES
import org.jellyfin.sdk.api.sockets.data.SubscriptionType
import org.jellyfin.sdk.api.sockets.data.subscriptionType
import org.jellyfin.sdk.model.api.ForceKeepAliveMessage
import org.jellyfin.sdk.model.api.InboundKeepAliveMessage
import org.jellyfin.sdk.model.api.InboundWebSocketMessage
import org.jellyfin.sdk.model.api.OutboundKeepAliveMessage
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import org.jellyfin.sdk.model.socket.PeriodicListenerPeriod
import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}

/**
 * Implementation of the Jellyfin WebSocket API. Each instance of this class maintains its own connection.
 * @see ApiClient.webSocket
 */
public class DefaultSocketApi(
	private val api: ApiClient,
	private val socketReconnectPolicy: SocketReconnectPolicy,
	socketConnectionFactory: SocketConnectionFactory,
) : SocketApi {
	private companion object {
		/**
		 * The URL of the WebSocket route.
		 */
		private const val SOCKET_URL = "/socket"

		/**
		 * The default interval used to ask subscription updates for.
		 */
		private val SUBSCRIPTION_PERIOD = PeriodicListenerPeriod(Duration.ZERO, 1.seconds)
	}

	/**
	 * Data class to hold socket credentials, used to check for changes in [notifyApiClientUpdate].
	 */
	private data class SocketCredentials(
		val url: String,
		val clientName: String,
		val clientVersion: String,
		val deviceId: String,
		val deviceName: String,
		val accessToken: String,
	) {
		val authorizationHeader = AuthorizationHeaderBuilder.buildHeader(
			clientName = clientName,
			clientVersion = clientVersion,
			deviceId = deviceId,
			deviceName = deviceName,
			accessToken = accessToken,
		)
	}

	/**
	 * Supervisor scope used for coroutines launched as part of the WebSocket connection.
	 */
	private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

	/**
	 * Instance of the actual socket connection.
	 */
	private val socketConnection = socketConnectionFactory.create(api.httpClientOptions, scope)

	/**
	 * Reference for the keep alive job.
	 * @see resetKeepAliveTicker
	 */
	private var keepAliveTicker: Job? = null

	/**
	 * Count of active subscriptions. Used to safely close WebSocket connections.
	 */
	private var _subscriptionCount = 0

	/**
	 * Amount of each subscription type currently active. Used to determine when to send start/stop messages.
	 */
	private var _currentSubscriptionTypes = mutableMapOf<SubscriptionType<*>, Int>()

	/**
	 * Currently used credentials in [reconnect].
	 */
	private var _currentCredentials: SocketCredentials? = null

	/**
	 * Mutex used to prevent multiple reconnections at once.
	 */
	private val reconnectMutex = Mutex()

	/**
	 * Flow indicating the current status of the WebSocket connection.
	 */
	override val state: StateFlow<SocketApiState> = socketConnection
		.state
		.map { connectionState ->
			when (connectionState) {
				SocketConnectionState.Connecting -> SocketApiState.Connecting
				is SocketConnectionState.Disconnected -> SocketApiState.Disconnected(connectionState.error)
				is SocketConnectionState.Message -> SocketApiState.Connected
			}
		}
		.distinctUntilChanged()
		.stateIn(scope, SharingStarted.WhileSubscribed(), SocketApiState.Disconnected())

	/**
	 * Internal message flow. This implements most of the behavior for connecting, reconnecting, keep alive messages and
	 * stopping the actual connection.
	 */
	private val messages: SharedFlow<OutboundWebSocketMessage> = socketConnection
		.state
		.onEach { connectionState ->
			// Automatically reconnect when the socket is closed while subscriptions are active
			if (_subscriptionCount > 0 && _currentCredentials != null && connectionState is SocketConnectionState.Disconnected && !reconnectMutex.isLocked) {
				socketReconnectPolicy.notifyDisconnected()

				val reconnectDelay = socketReconnectPolicy.getReconnectDelay()
				if (reconnectDelay == null) {
					logger.warn {
						"WebSocket connection closed with $_subscriptionCount active subscriptions. " +
							"Reconnect policy suggests giving up."
					}
				} else {
					logger.debug {
						"WebSocket connection closed with $_subscriptionCount active subscriptions. " +
							"Reconnect policy suggests waiting for $reconnectDelay."
					}

					// Schedule reconnect for 5 seconds
					scope.launch {
						delay(reconnectDelay)
						reconnect()
					}
				}
			}
		}
		.filterIsInstance<SocketConnectionState.Message>()
		.map { connectionState -> ApiSerializer.decodeSocketMessage(connectionState.message) }
		.filter { message ->
			when (message) {
				// Intercept ForceKeepAliveMessage and reset the timer
				is ForceKeepAliveMessage -> {
					resetKeepAliveTicker(message.data.seconds)
					false
				}

				// Ignore keep alive messages
				is OutboundKeepAliveMessage -> false

				// Keep all other message types
				else -> true
			}
		}
		.onStart { reconnect() }
		.onCompletion {
			keepAliveTicker?.cancel()
			socketConnection.disconnect()
		}
		.shareIn(scope, SharingStarted.WhileSubscribed(stopTimeout = 5.seconds))

	/**
	 * Function to register a subscription used by [subscribe] and [subscribeAll]. Returns a function that when invoked,
	 * cancels the subscriptions again. The start and stop messages will automatically be sent based on the amount of
	 * active subscriptions for each subscription type.
	 */
	private fun initializeSubscription(subscriptionTypes: Set<SubscriptionType<*>>): () -> Unit {
		// Increase subscription count
		_subscriptionCount++
		logger.debug("Subscription count changed to $_subscriptionCount")

		// Send start messages
		for (type in subscriptionTypes) {
			val currentUsage = _currentSubscriptionTypes.getOrDefault(type, 0)
			if (currentUsage == 0 && socketConnection.state.value is SocketConnectionState.Message) {
				scope.launch { publish(type.createStartMessage(SUBSCRIPTION_PERIOD)) }
			}
			_currentSubscriptionTypes[type] = currentUsage + 1
		}

		// Return function to be invoked when this subscription ends
		return {
			// Decrease subscription count
			_subscriptionCount--
			logger.info("Subscription count changed to $_subscriptionCount")

			// Disconnect when subscription count reaches zero
			val stopping = _subscriptionCount == 0
			if (stopping) scope.launch { socketConnection.disconnect() }

			// Send stop messages
			for (type in subscriptionTypes) {
				val newUsage = _currentSubscriptionTypes.getOrDefault(type, 0) - 1
				_currentSubscriptionTypes[type] = newUsage

				if (newUsage == 0 && !stopping) scope.launch { publish(type.createStopMessage()) }
			}
		}
	}

	/**
	 * Reset the keep alive ticker stored in [keepAliveTicker]. Called automatically in the [messages] flow filter.
	 */
	private fun resetKeepAliveTicker(lostTimeout: Duration) {
		// The server considers a socket lost after [lostTimeout] seconds
		// to make sure the socket doesn't get lost we divide the value by
		// 2 to get the delay between sending KeepAlive messages
		val delay = lostTimeout / 2
		logger.debug { "Using a KeepAlive message delay of ${delay.inWholeSeconds} seconds" }
		keepAliveTicker?.cancel()
		keepAliveTicker = scope.launch(Dispatchers.Unconfined) {
			while (true) {
				publish(InboundKeepAliveMessage())
				delay(delay)
			}
		}
	}

	private fun createSocketCredentials(): SocketCredentials? {
		val accessToken = api.accessToken
		if (api.baseUrl == null || accessToken == null) return null

		return SocketCredentials(
			url = api.createUrl(SOCKET_URL),
			clientName = api.clientInfo.name,
			clientVersion = api.clientInfo.version,
			deviceId = api.deviceInfo.id,
			deviceName = api.deviceInfo.name,
			accessToken = accessToken,
		)
	}

	/**
	 * Reconnect the WebSocket. This will automatically pull the latest credentials from the [ApiClient].
	 */
	private suspend fun reconnect(): Unit = reconnectMutex.withLock {
		val newCredentials = createSocketCredentials()
		_currentCredentials = newCredentials

		// Make sure we have no connection when there are no valid credentials.
		if (newCredentials == null) {
			socketConnection.disconnect()
			keepAliveTicker?.cancel()
		} else {
			// Attempt connection
			val connected = socketConnection.connect(
				url = newCredentials.url,
				clientName = newCredentials.clientName,
				clientVersion = newCredentials.clientVersion,
				deviceId = newCredentials.deviceId,
				deviceName = newCredentials.deviceName,
				accessToken = newCredentials.accessToken,
			)

			if (connected) {
				socketReconnectPolicy.notifyConnected()

				// Resend start messages for current subscription types
				for (type in _currentSubscriptionTypes.keys) {
					publish(type.createStartMessage(SUBSCRIPTION_PERIOD))
				}
			}
		}
	}

	/**
	 * Publish a message to the server. Messages must be enqueued by the [socketConnection] implementation when the
	 * WebSocket is still connecting. The queue is dropped on error/disconnect.
	 */
	private suspend fun publish(message: InboundWebSocketMessage) {
		val encoded = ApiSerializer.encodeSocketMessage(message)
		socketConnection.send(encoded)
	}

	/**
	 * Notify this SocketApi instance of changes in the parent [ApiClient].
	 */
	public fun notifyApiClientUpdate() {
		// Do nothing when there are no active subscriptions
		// note that this may be true when the state is still CONNECTING
		if (_subscriptionCount == 0) return

		// Check if credentials changes
		val newCredentials = createSocketCredentials()
		if (_currentCredentials == newCredentials) return

		// Initialize reconnect with new credentials
		socketReconnectPolicy.notifyUpdated()
		scope.launch { reconnect() }
	}

	/**
	 * Subscribe to all incoming WebSocket messages. Returns a flow that emits each deserialized message.
	 * @see subscribe
	 */
	override fun subscribeAll(): Flow<OutboundWebSocketMessage> = flow {
		val onComplete = initializeSubscription(SUBSCRIPTION_TYPES)
		currentCoroutineContext().job.invokeOnCompletion { onComplete() }

		messages.collect { emit(it) }
	}

	/**
	 * Subscribe to a specific WebSocket message type. Returns a flow that emits each deserialized message of type [T].
	 * @see subscribeAll
	 */
	override fun <T : OutboundWebSocketMessage> subscribe(messageType: KClass<T>): Flow<T> = flow {
		val subscriptionType = messageType.subscriptionType
		val onComplete = initializeSubscription(if (subscriptionType == null) emptySet() else setOf(subscriptionType))
		currentCoroutineContext().job.invokeOnCompletion { onComplete() }

		messages.filterIsInstance(messageType).collect { emit(it) }
	}
}
