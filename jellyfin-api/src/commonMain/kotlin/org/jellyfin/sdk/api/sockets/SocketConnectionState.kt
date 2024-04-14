package org.jellyfin.sdk.api.sockets

/**
 * Possible states for a [SocketConnection]. This will start out as [DISCONNECTED] with no error set and switch to
 * [CONNECTING] when attempting to initialize a session. The [MESSAGE] state means that the socket is connected.
 *
 * The server is always the first to send a message, so we don't have an intermediate state between [CONNECTING] and
 * [MESSAGE].
 */
public sealed class SocketConnectionState {
	/**
	 * There is no current connection. This is the default state.
	 */
	public data class DISCONNECTED(val error: Throwable? = null) : SocketConnectionState()

	/**
	 * A connection is currently in progress.
	 */
	public data object CONNECTING : SocketConnectionState()

	/**
	 * The last received message in an active connection.
	 */
	public data class MESSAGE(val message: String) : SocketConnectionState()
}
