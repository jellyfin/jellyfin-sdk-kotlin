package org.jellyfin.sdk.api.sockets

/**
 * Possible states for a [SocketConnection]. This will start out as [Disconnected] with no error set and switch to
 * [Connecting] when attempting to initialize a session. The [Message] state means that the socket is connected.
 *
 * The server is always the first to send a message, so we don't have an intermediate state between [Connecting] and
 * [Message].
 */
public sealed class SocketConnectionState {
	/**
	 * There is no current connection. This is the default state.
	 */
	public data class Disconnected(val error: Throwable? = null) : SocketConnectionState()

	/**
	 * A connection is currently in progress.
	 */
	public data object Connecting : SocketConnectionState()

	/**
	 * The last received message in an active connection.
	 */
	public data class Message(val message: String) : SocketConnectionState()
}
