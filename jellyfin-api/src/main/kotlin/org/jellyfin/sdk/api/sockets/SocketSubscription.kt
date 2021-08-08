package org.jellyfin.sdk.api.sockets

import org.jellyfin.sdk.model.socket.IncomingSocketMessage

/**
 * Subscription to the [WebSocketApi] that can be cancelled.
 */
public class SocketSubscription(
	private val webSocketApi: WebSocketApi,
	internal val callback: suspend (IncomingSocketMessage) -> Unit,
) {
	/**
	 * Cancel the subscription and stop listening for messages.
	 */
	public fun cancel(): Unit = webSocketApi.cancelSubscription(this)
}
