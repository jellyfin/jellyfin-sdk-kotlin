package org.jellyfin.sdk.api.sockets.listener

import org.jellyfin.sdk.api.sockets.SocketInstance

public class SocketListener(
	public val definition: SocketListenerDefinition,
	public val instance: SocketInstance,
) {
	/**
	 * Stop listening to new messages. Listener cannot be started again.
	 */
	public fun stop() {
		instance.removeListener(this)
	}
}
