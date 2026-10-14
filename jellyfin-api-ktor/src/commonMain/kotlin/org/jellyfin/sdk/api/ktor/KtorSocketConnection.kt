package org.jellyfin.sdk.api.ktor

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.SocketConnection
import org.jellyfin.sdk.api.sockets.SocketConnectionState

private val logger = KotlinLogging.logger {}

// OkHttp upgrades http(s) urls automatically, Ktor requires ws(s)
internal fun String.toWebSocketUrl(): String {
	val builder = URLBuilder(this)

	builder.protocol = when (builder.protocol) {
		URLProtocol.HTTP -> URLProtocol.WS
		URLProtocol.HTTPS -> URLProtocol.WSS
		else -> return this
	}

	return builder.buildString()
}

public class KtorSocketConnection(
	private val client: HttpClient,
	private val scope: CoroutineScope,
) : SocketConnection {
	private var session: DefaultWebSocketSession? = null
	private var reader: Job? = null

	private val _state = MutableStateFlow<SocketConnectionState>(SocketConnectionState.Disconnected())
	public override val state: StateFlow<SocketConnectionState> = _state.asStateFlow()

	override suspend fun connect(
		url: String,
		clientName: String,
		clientVersion: String,
		deviceId: String,
		deviceName: String,
		accessToken: String,
		languages: List<String>,
	): Boolean {
		if (session != null) disconnect()

		_state.value = SocketConnectionState.Connecting
		logger.info { "Connecting to $url" }

		val newSession = try {
			client.webSocketSession(url.toWebSocketUrl()) {
				header(
					HttpHeaders.Authorization,
					AuthorizationHeaderBuilder.buildHeader(
						clientName = clientName,
						clientVersion = clientVersion,
						deviceId = deviceId,
						deviceName = deviceName,
						accessToken = accessToken,
					)
				)
				header(HttpHeaders.UserAgent, "$clientName/$clientVersion via jellyfin-sdk-kotlin (Ktor)")
				if (languages.isNotEmpty()) header(HttpHeaders.AcceptLanguage, languages.joinToString(","))
			}
		} catch (err: CancellationException) {
			throw err
		} catch (@Suppress("TooGenericExceptionCaught") err: Throwable) {
			logger.warn(err) { "WebSocket has failed" }
			_state.value = SocketConnectionState.Disconnected(err)
			return false
		}

		logger.debug { "WebSocket has opened" }
		session = newSession
		reader = scope.launch { read(newSession) }

		// Wait until the first message or disconnect to ensure we have a valid connection
		return state.first { it != SocketConnectionState.Connecting } !is SocketConnectionState.Disconnected
	}

	private suspend fun read(readSession: DefaultWebSocketSession) {
		try {
			for (frame in readSession.incoming) {
				if (frame is Frame.Text) {
					val text = frame.readText()
					logger.debug { "Receiving (raw) message $text" }
					_state.value = SocketConnectionState.Message(text)
				}
			}

			val reason = readSession.closeReason.await()
			logger.debug { "WebSocket has closed, code=${reason?.code}, reason=${reason?.message}" }
			_state.value = SocketConnectionState.Disconnected()
		} catch (err: CancellationException) {
			throw err
		} catch (@Suppress("TooGenericExceptionCaught") err: Throwable) {
			logger.warn(err) { "WebSocket has failed" }
			_state.value = SocketConnectionState.Disconnected(err)
		} finally {
			if (session === readSession) session = null
		}
	}

	override suspend fun send(message: String): Boolean {
		logger.debug { "Sending (raw) message $message" }

		val current = session
		if (current == null) {
			logger.warn { "Unable to send message: webSocket is null" }
			return false
		}

		return try {
			current.send(Frame.Text(message))
			true
		} catch (err: CancellationException) {
			throw err
		} catch (@Suppress("TooGenericExceptionCaught") err: Throwable) {
			logger.warn(err) { "Unable to send message" }
			false
		}
	}

	override suspend fun disconnect() {
		val current = session ?: return
		logger.info { "Disconnecting" }
		session = null

		try {
			current.close(CloseReason(CloseReason.Codes.NORMAL, ""))
		} catch (@Suppress("TooGenericExceptionCaught") err: Throwable) {
			logger.debug(err) { "Failed to close WebSocket cleanly" }
		}

		reader?.cancel()
		reader = null
		_state.value = SocketConnectionState.Disconnected()
	}
}
