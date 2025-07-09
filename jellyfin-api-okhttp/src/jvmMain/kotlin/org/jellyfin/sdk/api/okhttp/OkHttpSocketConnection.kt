package org.jellyfin.sdk.api.okhttp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import mu.KotlinLogging
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.SocketConnection
import org.jellyfin.sdk.api.sockets.SocketConnectionState

private val logger = KotlinLogging.logger {}

public class OkHttpSocketConnection(
	private val client: OkHttpClient,
	scope: CoroutineScope,
) : SocketConnection {
	private companion object {
		private const val CLOSE_REASON_NORMAL = 1000
	}

	private var webSocket: WebSocket? = null
	private val _state = MutableStateFlow<SocketConnectionState>(SocketConnectionState.Disconnected())
	public override val state: StateFlow<SocketConnectionState> = _state.asStateFlow()

	private val listener = object : WebSocketListener() {
		override fun onOpen(webSocket: WebSocket, response: Response) {
			logger.debug { "WebSocket has opened" }
		}

		override fun onMessage(webSocket: WebSocket, text: String) {
			logger.debug { "Receiving (raw) message $text" }

			scope.launch {
				_state.value = SocketConnectionState.Message(text)
			}
		}

		override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
			logger.debug { "WebSocket is closing, code=$code, reason=$reason" }
		}

		@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
		override fun onClosed(closedWebSocket: WebSocket, code: Int, reason: String) {
			logger.debug { "WebSocket has closed, code=$code, reason=$reason" }
			_state.value = SocketConnectionState.Disconnected()

			if (webSocket == closedWebSocket) webSocket = null
		}

		@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
		override fun onFailure(failedWebSocket: WebSocket, t: Throwable, response: Response?) {
			logger.warn(t) { "WebSocket has failed" }
			_state.value = SocketConnectionState.Disconnected(t)

			if (webSocket == failedWebSocket) webSocket = null
		}
	}

	override suspend fun connect(
		url: String,
		clientName: String,
		clientVersion: String,
		deviceId: String,
		deviceName: String,
		accessToken: String,
	): Boolean {
		if (webSocket != null) disconnect()

		val request = Request.Builder().apply {
			url(url)
			val authorization = AuthorizationHeaderBuilder.buildHeader(
				clientName = clientName,
				clientVersion = clientVersion,
				deviceId = deviceId,
				deviceName = deviceName,
				accessToken = accessToken,
			)
			header("Authorization", authorization)
			header("User-Agent", "${clientName}/${clientVersion} via jellyfin-sdk-kotlin (OkHttp/${OkHttp.VERSION})")
		}.build()

		_state.value = SocketConnectionState.Connecting
		logger.info { "Connecting to $url" }
		webSocket = client.newWebSocket(request, listener)

		// Wait until the first non-connect message and check if it is not disconnected
		// to ensure we have a valid connection
		return state.first { it != SocketConnectionState.Connecting } !is SocketConnectionState.Disconnected
	}

	override suspend fun send(message: String): Boolean {
		logger.debug { "Sending (raw) message $message" }

		val ws = webSocket

		// No existing socket
		if (ws == null) {
			logger.warn { "Unable to send message: webSocket is null" }
			return false
		}

		val sent = ws.send(message)
		if (!sent) logger.warn { "Unable to send message: OkHttp returned false" }

		return sent
	}

	override suspend fun disconnect() {
		if (webSocket != null) {
			logger.info { "Disconnecting" }
			webSocket?.close(CLOSE_REASON_NORMAL, null)
			webSocket = null
		}
	}
}
