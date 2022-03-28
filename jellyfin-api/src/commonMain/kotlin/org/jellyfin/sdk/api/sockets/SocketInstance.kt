package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.client.util.UrlBuilder
import org.jellyfin.sdk.api.sockets.exception.SocketStoppedException
import org.jellyfin.sdk.api.sockets.helper.KeepAliveHelper
import org.jellyfin.sdk.api.sockets.helper.ListenerHelper
import org.jellyfin.sdk.api.sockets.listener.SocketListener
import org.jellyfin.sdk.api.sockets.listener.SocketListenerDefinition
import org.jellyfin.sdk.model.socket.ForceKeepAliveMessage
import org.jellyfin.sdk.model.socket.OutgoingSocketMessage
import org.jellyfin.sdk.model.socket.PeriodicListenerPeriod
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}

public class SocketInstance internal constructor(
	private val api: ApiClient,
	private val socketConnectionFactory: SocketConnectionFactory,
	context: CoroutineContext = Dispatchers.Default,
) {
	private companion object {
		private const val SOCKET_URL = "/socket"
		private const val QUERY_ACCESS_TOKEN = "api_key"
		private const val QUERY_DEVICE_ID = "deviceId"
		private const val MESSAGE_INTERVAL = 1_000L // second
	}

	private val coroutineContext = context + SupervisorJob()
	private val coroutineScope = CoroutineScope(coroutineContext)

	private var credentialsChanged = false
	private var baseUrl = api.baseUrl
	private var accessToken = api.accessToken
	private var deviceInfo = api.deviceInfo

	private val _state = MutableStateFlow(SocketInstanceState.DISCONNECTED)
	public val state: StateFlow<SocketInstanceState> = _state

	private var connection: SocketInstanceConnection? = null
	private val incomingMessages = Channel<String>()
	private val outgoingMessages = Channel<String>()

	private val listenerHelper = ListenerHelper()
	private val keepAliveHelper = KeepAliveHelper(coroutineScope)

	private var messageForwardJob: Job? = null
	private val updateConnectionStateMutex = Mutex()

	/**
	 * Update the server url and access token.
	 * Takes the latest values from the ApiClient and reconnects if the values changed.
	 */
	public suspend fun updateCredentials(): Boolean {
		logger.debug { "Credential update requested" }

		if (state.value == SocketInstanceState.ERROR) {
			logger.info { "Unable to update credentials: state is error" }
			return false
		}

		if (api.baseUrl == null) {
			logger.info { "Unable to update credentials: api.baseUrl is null. Disconnecting." }
			connection?.disconnect()
			_state.value = SocketInstanceState.ERROR
			return false
		}

		val newBaseUrl = requireNotNull(api.baseUrl)
		val newAccessToken = api.accessToken
		val newDeviceInfo = api.deviceInfo

		// No changes - do nothing
		if (baseUrl == newBaseUrl && accessToken == newAccessToken && deviceInfo == newDeviceInfo) {
			logger.debug { "Unable to update credentials: credentials did not change" }
			return false
		}

		logger.info { "Updating credentials for $baseUrl" }

		baseUrl = newBaseUrl
		accessToken = newAccessToken
		deviceInfo = newDeviceInfo

		credentialsChanged = true

		return updateConnectionState()
	}

	private suspend fun updateConnectionState(): Boolean = updateConnectionStateMutex.withLock {
		// Don't update when an error occurred, client should manually call [reconnect]
		if (state.value == SocketInstanceState.ERROR) {
			logger.info { "Unable to update connection state: state is error" }
			return false
		}

		// Remove listeners that don't want credential changes
		if (credentialsChanged) listenerHelper.reportCredentialChangedReconnect()

		val connected = connection != null // TODO make smarter (handle disconnect etc.)

		when {
			// Stop if there's no listeners
			listenerHelper.listeners.isEmpty() -> connection?.disconnect()
			// Reconnect when credentials changed or not connected
			credentialsChanged || !connected -> reconnect()
			// Update subscriptions when not reconnecting or disconnecting
			else -> updateSubscriptions()
		}

		// Make sure credentials changed is set to false
		credentialsChanged = false

		return true
	}

	private suspend fun updateSubscriptions() {
		val subscriptions = listenerHelper.subscriptions

		// Remove subscriptions not in use anymore
		for (subscription in listenerHelper.activeSubscriptions.reversed()) {
			if (!subscriptions.contains(subscription)) {
				logger.info { "Removing subscription for ${subscription.messageType.simpleName}" }
				publish(subscription.createStopMessage())
				listenerHelper.activeSubscriptions.remove(subscription)
			}
		}

		// Add new subscriptions
		// Period is not configurable as it is barely used by the server
		// The initialDelay is never used in fact
		val period = PeriodicListenerPeriod(0, MESSAGE_INTERVAL)
		for (subscription in subscriptions) {
			if (!listenerHelper.activeSubscriptions.contains(subscription)) {
				logger.info { "Adding subscription for ${subscription.messageType.simpleName}" }
				publish(subscription.createStartMessage(period))
				listenerHelper.activeSubscriptions.add(subscription)
			}
		}
	}

	public suspend fun reconnect() {
		logger.debug { "Reconnect requested" }

		// Already connecting
		if (state.value == SocketInstanceState.CONNECTING) return

		// Explicitly stopped
		if (state.value == SocketInstanceState.STOPPED) throw SocketStoppedException()

		logger.info { "Reconnecting" }
		_state.value = SocketInstanceState.CONNECTING

		connection?.disconnect()

		connection = socketConnectionFactory.create(
			api.httpClientOptions,
			incomingMessages,
			outgoingMessages,
			coroutineContext,
		).apply {
			val connected = connect(UrlBuilder.buildUrl(
				baseUrl = requireNotNull(baseUrl),
				pathTemplate = SOCKET_URL,
				queryParameters = mapOf(
					QUERY_DEVICE_ID to deviceInfo.id,
					QUERY_ACCESS_TOKEN to accessToken,
				)
			).replace(Regex("^http"), "ws"))

			if (connected) {
				messageForwardJob?.cancel()
				messageForwardJob = coroutineScope.launch {
					for (message in incomingMessages) forwardMessage(message)
				}
				listenerHelper.activeSubscriptions.clear()
				updateSubscriptions()
				_state.value = SocketInstanceState.CONNECTED
			} else {
				_state.value = SocketInstanceState.ERROR
				// TODO retry: failed connecting
			}
		}
	}

	/**
	 * Stops the connection and removes all listeners. The instance cannot be started again.
	 * Calling [reconnect] after this function is not allowed.
	 */
	public suspend fun stop() {
		logger.info { "Stopping socket instance" }

		connection?.disconnect()
		messageForwardJob?.cancel()
		listenerHelper.reset()
		incomingMessages.close()
		outgoingMessages.close()
		coroutineScope.cancel()

		_state.value = SocketInstanceState.STOPPED
	}

	/**
	 * Add a listener. This is the underlying function for [addGlobalListener], [addListener] or
	 * [addGeneralCommandsListener].
	 */
	public fun addListenerDefinition(definition: SocketListenerDefinition): SocketListener {
		val listener = listenerHelper.addListenerDefinition(this, definition)
		coroutineScope.launch { updateConnectionState() }
		return listener
	}

	/**
	 * Removes a listener.
	 */
	public fun removeListener(listener: SocketListener) {
		listenerHelper.removeListener(listener)
		coroutineScope.launch { updateConnectionState() }
	}

	private fun forwardMessage(rawMessage: String) {
		val message = ApiSerializer.decodeSocketMessage(rawMessage) ?: return

		if (message is ForceKeepAliveMessage) keepAliveHelper.reset(this, message.value.seconds)
		else listenerHelper.forwardMessage(message)
	}

	/**
	 * Send a message to the server. This function is normally not used by the application.
	 */
	public suspend fun publish(message: OutgoingSocketMessage) {
		outgoingMessages.send(ApiSerializer.encodeSocketMessage(message))
	}
}
