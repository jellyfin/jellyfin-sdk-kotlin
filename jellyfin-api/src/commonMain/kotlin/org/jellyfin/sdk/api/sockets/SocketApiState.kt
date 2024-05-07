package org.jellyfin.sdk.api.sockets

/**
 * The current connection state of the SocketAPI. This is always [Disconnected] when there are no active subscriptions.
 */
public sealed class SocketApiState {
	/**
	 * There is no current connection. This is the default state.
	 * Optionally contains the [error] that caused the socket to disconnect.
	 */
	public data class Disconnected(val error: Throwable? = null) : SocketApiState()

	/**
	 * A connection is currently in progress.
	 */
	public data object Connecting : SocketApiState()

	/**
	 * There is an active connection.
	 */
	public data object Connected : SocketApiState()
}
