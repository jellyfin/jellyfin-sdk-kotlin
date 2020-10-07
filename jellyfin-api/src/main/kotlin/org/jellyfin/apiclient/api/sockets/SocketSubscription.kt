package org.jellyfin.apiclient.api.sockets

import org.jellyfin.apiclient.model.socket.IncomingSocketMessage

/**
 * Subscription to the [WebSocketApi] that can be cancelled.
 */
class SocketSubscription(
	private val webSocketApi: WebSocketApi,
	internal val callback: (IncomingSocketMessage) -> Unit
) {
	/**
	 * Cancel the subscription and stop listening for messages.
	 */
	suspend fun cancel() = webSocketApi.cancelSubscription(this)
}
