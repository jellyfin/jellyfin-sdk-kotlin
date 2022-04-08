package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.flow.StateFlow

/**
 * Reusable WebSocket connection. Constructed using [SocketConnectionFactory].
 */
public interface SocketInstanceConnection {
	/**
	 * State of the connection. Requires at least the [SocketInstanceState.ERROR] and [SocketInstanceState.DISCONNECTED]
	 * states to be implemented.
	 */
	public val state: StateFlow<SocketInstanceState>

	/**
	 * Connect to [url]. If there is an existing connection it will be automatically closed. After the connection is
	 * initialized the messageListener supplied via the factory will be called until [disconnect] is called or the
	 * connection is closed by other means (server closed, network issues, new connect call etc).
	 *
	 * @return true when connected, false when connection failed.
	 *
	 * @see disconnect
	 */
	public suspend fun connect(url: String): Boolean

	/**
	 * Send a message to this connection. Messages might still fail to send due to network issues or other reasons when
	 * the returned value is true.
	 *
	 * @return false when failed to send/queue. true when it's likely the message will be sent.
	 */
	public suspend fun send(message: String): Boolean

	/**
	 * Disconnect the connection. Will do nothing when there is no connection.
	 *
	 * @see connect
	 */
	public suspend fun disconnect()
}
