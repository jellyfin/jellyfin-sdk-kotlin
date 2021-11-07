package org.jellyfin.sdk.api.sockets.listener

import org.jellyfin.sdk.model.socket.IncomingSocketMessage

public fun interface SocketMessageReceiver<T : IncomingSocketMessage> {
	public fun onReceive(message: T)
}
