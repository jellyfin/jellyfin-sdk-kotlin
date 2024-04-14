package org.jellyfin.sdk.api.sockets

/**
 * @todo Description
 */
public sealed class SocketApiState {
	/**
	 * There is no current connection. This is the default state.
	 */
	public data class DISCONNECTED(val error: Throwable? = null) : SocketApiState()

	/**
	 * A connection is currently in progress.
	 */
	public data object CONNECTING : SocketApiState()

	/**
	 * There is an active connection.
	 */
	public data object CONNECTED : SocketApiState()
}
