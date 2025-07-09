package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.flow.StateFlow

/**
 * Reusable WebSocket connection. Constructed using [SocketConnectionFactory].
 */
public interface SocketConnection {
	/**
	 * State of the connection. Includes received messages.
	 */
	public val state: StateFlow<SocketConnectionState>

	/**
	 * Connect to [url]. If there is an existing connection it should be automatically disconnected. After a connection
	 * is initialized the state should be updated until [disconnect] is called or the connection is closed by other
	 * means (server closed, network issues, new connect call etc.).
	 *
	 * @return true when connected, false when connection failed.
	 *
	 * @see disconnect
	 */
	public suspend fun connect(
		url: String,
		clientName: String,
		clientVersion: String,
		deviceId: String,
		deviceName: String,
		accessToken: String,
	): Boolean

	/**
	 * Send a message to this connection. Messages may be added to an internal queue and be slightly delayed.
	 * The implementation must enqueue messages send while still connecting.
	 *
	 * @return false when failed to send/queue. true when it's likely the message will be sent. It may still be possible
	 * that a message fails to send from a queue to the server.
	 */
	public suspend fun send(message: String): Boolean

	/**
	 * Disconnect the WebSocket. Will do nothing when there is no connection.
	 *
	 * @see connect
	 */
	public suspend fun disconnect()
}
