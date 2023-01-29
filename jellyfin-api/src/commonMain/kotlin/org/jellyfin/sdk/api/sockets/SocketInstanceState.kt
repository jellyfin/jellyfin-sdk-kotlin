package org.jellyfin.sdk.api.sockets

/**
 * Possible states for a [SocketInstance].
 */
public enum class SocketInstanceState {
	/**
	 * There is no connection.
	 */
	DISCONNECTED,

	/**
	 * A connection is currently in progress.
	 */
	CONNECTING,

	/**
	 * Successfully connected to the server.
	 */
	CONNECTED,

	/**
	 * An error occurred and the connection is stopped. A new connection attempt may be made using the reconnect function.
	 */
	ERROR,

	/**
	 * The instance is stopped and cannot be started again.
	 */
	STOPPED,
}
