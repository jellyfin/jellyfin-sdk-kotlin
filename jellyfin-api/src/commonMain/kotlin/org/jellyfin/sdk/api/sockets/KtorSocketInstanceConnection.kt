package org.jellyfin.sdk.api.sockets

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.websocket.ClientWebSocketSession
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.utils.io.core.EOFException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.exception.ApiClientException
import kotlin.coroutines.CoroutineContext

private val logger = KotlinLogging.logger {}

public class KtorSocketInstanceConnection(
	clientOptions: HttpClientOptions,
	private val incomingMessageChannel: Channel<String>,
	private val outgoingMessageChannel: Channel<String>,
	context: CoroutineContext,
) : SocketInstanceConnection {
	private val coroutineScope = CoroutineScope(context)

	private val client by lazy {
		HttpClient {
			followRedirects = clientOptions.followRedirects

			install(HttpTimeout) {
				connectTimeoutMillis = clientOptions.connectTimeout
				socketTimeoutMillis = clientOptions.socketTimeout
				// ignore clientOptions.requestTimeout to prevent the socket from closing
				requestTimeoutMillis = null
			}

			install(WebSockets)
		}
	}

	private var connection: ClientWebSocketSession? = null

	public override suspend fun connect(url: String): Boolean {
		logger.info { "Connecting to $url" }

		// Make sure there is no existing connection
		connection?.close()

		// Create new connection
		return try {
			connection = client.webSocketSession {
				url(url)
			}.apply {
				logger.info { "Connected" }

				// Attach message channels
				attach(this)
			}

			true
		} catch (err: Throwable) {
			logger.error(err) { "Not connected" }

			false
		}
	}

	private fun attach(session: DefaultClientWebSocketSession) {
		logger.debug { "Attaching message channels" }

		// Receive messages
		coroutineScope.launch {
			try {
				for (frame in session.incoming) {
					if (frame !is Frame.Text) continue

					val message = frame.readText()
					logger.info { "Receiving (raw) message $message" }
					incomingMessageChannel.send(message)
				}
			} catch (err: EOFException) {
				// Ignored: propagated by Ktor to the closeReason
				logger.debug(err) { "Socket closed with EOFException" }
			} catch (err: Throwable) {
				throw ApiClientException("Unknown exception while receiving WebSocket message", err)
			} finally {
				// Channel closed, wait for the reason to be set
				logger.info { "Incoming channel closed, cancelling connection" }
				val reason = try {
					session.closeReason.await()
				} catch (err: EOFException) {
					CloseReason(CloseReason.Codes.INTERNAL_ERROR, "EOFException (abrupt connection issue)")
				}
				logger.info { "Close reason was $reason" }
				// TODO send disconnect notification to SocketInstance for retry policy
				disconnect()
			}
		}

		// Send messages
		coroutineScope.launch {
			for (message in outgoingMessageChannel) {
				logger.info { "Sending (raw) message $message" }

				try {
					connection?.outgoing?.send(Frame.Text(message))
				} catch (err: EOFException) {
					// Ignored: dealt with by incoming message job
					logger.debug(err) { "Socket closed with EOFException" }
				} catch (err: Throwable) {
					throw ApiClientException("Unknown exception while sending WebSocket message", err)
				}
			}
		}
	}

	override suspend fun disconnect() {
		logger.info { "Disconnecting" }

		if (connection != null) {
			connection!!.close()
			connection = null
		}

		coroutineScope.cancel()
	}
}
