package org.jellyfin.sdk.api.sockets.listener

import org.jellyfin.sdk.model.api.OutboundWebSocketMessage

public fun interface SocketMessageReceiver<T : OutboundWebSocketMessage> {
	public fun onReceive(message: T)
}
