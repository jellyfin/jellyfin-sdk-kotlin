package org.jellyfin.apiclient.api.sockets

import org.jellyfin.apiclient.model.socket.IncomingSocketMessage

/**
 * Subscription to the [WebSocketApi] that can be cancelled.
 */
public class SocketSubscription(
	private val webSocketApi: WebSocketApi,
	internal val callback: (IncomingSocketMessage) -> Unit
) {
	/**
	 * Cancel the subscription and stop listening for messages.
	 */
	public suspend fun cancel(): Unit = webSocketApi.cancelSubscription(this)
}
