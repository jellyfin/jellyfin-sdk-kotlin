package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.jellyfin.sdk.api.client.HttpClientOptions
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

private val logger = KotlinLogging.logger {}

public class OkHttpWebsocketSession(
	clientOptions: HttpClientOptions,
	private val incomingMessageChannel: Channel<String>,
	context: CoroutineContext,
) : SocketInstanceConnection {
	private companion object {
		private const val HEADER_AUTHORIZATION = "Authorization"
		private const val CLOSE_REASON_NORMAL = 1000
	}

	private val coroutineScope = CoroutineScope(context)
	private val client = OkHttpClient.Builder().apply {
		followRedirects(clientOptions.followRedirects)

		connectTimeout(clientOptions.connectTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
		readTimeout(clientOptions.socketTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
		writeTimeout(clientOptions.socketTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
	}.build()
	private var webSocket: WebSocket? = null
	private val _state = MutableStateFlow(SocketInstanceState.DISCONNECTED)
	public override val state: StateFlow<SocketInstanceState> = _state.asStateFlow()

	private val listener = object : WebSocketListener() {
		override fun onOpen(webSocket: WebSocket, response: Response) {
			logger.info { "WebSocket has opened" }
			_state.value = SocketInstanceState.CONNECTED
		}

		override fun onMessage(webSocket: WebSocket, text: String) {
			logger.info { "Receiving (raw) message $text" }

			coroutineScope.launch {
				incomingMessageChannel.send(text)
			}
		}

		override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
			logger.warn { "Ignoring a binary message" }
		}

		override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
			logger.info { "WebSocket is closing, code=$code, reason=$reason" }
			_state.value = SocketInstanceState.DISCONNECTED
		}

		@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
		override fun onClosed(closedWebSocket: WebSocket, code: Int, reason: String) {
			logger.info { "WebSocket has closed, code=$code, reason=$reason" }
			_state.value = SocketInstanceState.DISCONNECTED

			if (webSocket == closedWebSocket) webSocket = null
		}

		@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
		override fun onFailure(failedWebSocket: WebSocket, t: Throwable, response: Response?) {
			logger.warn(t) { "WebSocket has failed" }
			_state.value = SocketInstanceState.ERROR

			if (webSocket == failedWebSocket) webSocket = null
		}
	}

	override suspend fun connect(url: String, authorization: String): Boolean {
		if (webSocket != null) disconnect()

		val request = Request.Builder().apply {
			url(url)
			header(HEADER_AUTHORIZATION, authorization)
		}.build()

		_state.value = SocketInstanceState.CONNECTING
		webSocket = client.newWebSocket(request, listener)

		return state.first { it != SocketInstanceState.CONNECTING } == SocketInstanceState.CONNECTED
	}

	override suspend fun send(message: String): Boolean {
		logger.info { "Sending (raw) message $message" }

		// Invalid state
		if (state.value != SocketInstanceState.CONNECTED) {
			logger.warn { "Unable to send message: invalid state (state=${state.value})" }
			return false
		}

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
		_state.value = SocketInstanceState.DISCONNECTED
		webSocket?.close(CLOSE_REASON_NORMAL, null)
		webSocket = null
	}
}
