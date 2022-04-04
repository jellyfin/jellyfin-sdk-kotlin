package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
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
	private val outgoingMessageChannel: Channel<String>,
	context: CoroutineContext,
) : SocketInstanceConnection {
	private companion object {
		private const val CLOSE_REASON_NORMAL = 1000
	}

	private val coroutineScope = CoroutineScope(context)
	private val client = OkHttpClient.Builder().apply {
		followRedirects(clientOptions.followRedirects)

		connectTimeout(clientOptions.connectTimeout, TimeUnit.MILLISECONDS)
		readTimeout(clientOptions.socketTimeout, TimeUnit.MILLISECONDS)
		writeTimeout(clientOptions.socketTimeout, TimeUnit.MILLISECONDS)
	}.build()
	private var webSocket: WebSocket? = null
	private var messageForwardJob: Job? = null
	private val state = MutableStateFlow(SocketInstanceState.DISCONNECTED)

	private val listener = object : WebSocketListener() {
		override fun onOpen(webSocket: WebSocket, response: Response) {
			logger.info { "WebSocket has opened" }
			state.value = SocketInstanceState.CONNECTED
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
			state.value = SocketInstanceState.DISCONNECTED

			messageForwardJob?.cancel()
			messageForwardJob = null
		}

		@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
		override fun onClosed(closedWebSocket: WebSocket, code: Int, reason: String) {
			logger.info { "WebSocket has closed, code=$code, reason=$reason" }
			state.value = SocketInstanceState.DISCONNECTED

			if (webSocket == closedWebSocket) webSocket = null
		}

		@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
		override fun onFailure(failedWebSocket: WebSocket, t: Throwable, response: Response?) {
			logger.warn(t) { "WebSocket has failed" }
			state.value = SocketInstanceState.ERROR

			// When onFailure is called, the onClosing and onClosed functions are not called
			messageForwardJob?.cancel()
			messageForwardJob = null
			if (webSocket == failedWebSocket) webSocket = null
		}
	}

	override suspend fun connect(url: String): Boolean {
		if (webSocket != null) disconnect()

		val request = Request.Builder().apply {
			url(url)
		}.build()

		state.value = SocketInstanceState.CONNECTING
		webSocket = client.newWebSocket(request, listener)

		messageForwardJob = coroutineScope.launch {
			for (message in outgoingMessageChannel) {
				logger.info { "Sending (raw) message $message" }

				webSocket?.send(message)
			}
		}

		return state.first { it != SocketInstanceState.CONNECTING } == SocketInstanceState.CONNECTED
	}

	override suspend fun disconnect() {
		state.value = SocketInstanceState.DISCONNECTED
		webSocket?.close(CLOSE_REASON_NORMAL, null)
		webSocket = null
	}
}
