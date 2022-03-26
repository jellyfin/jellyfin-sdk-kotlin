package org.jellyfin.sdk.api.sockets

/**
 * Reusable WebSocket connection. Constructed using [SocketConnectionFactory].
 */
public interface SocketInstanceConnection {
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
	 * Disconnect the connection. Will do nothing when there is no connection.
	 *
	 * @see connect
	 */
	public suspend fun disconnect()
}
