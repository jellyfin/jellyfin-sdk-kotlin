package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import kotlin.reflect.KClass

/**
 * The SocketApi maintains a WebSocket connection to the current server specified in its parent ApiClient. Each
 * ApiClient has one SocketApi instance. Updating the server URL, device info, client info or access token using the
 * update function will automatically propagate and reconnect the WebSocket.
 *
 * The WebSocket automatically disconnects when there are no active subscriptions. Subscriptions will automatically send
 * the appropriate start and stop messages to the server, the start messages will always request an interval of one
 * second. Prefer using individual subscriptions above the [subscribeAll] function for best performance.
 */
public interface SocketApi {
	/**
	 * Flow indicating the current status of the WebSocket connection.
	 */
	public val state: StateFlow<SocketApiState>

	/**
	 * Subscribe to all incoming WebSocket messages.
	 * @see subscribe
	 */
	public fun subscribeAll(): Flow<OutboundWebSocketMessage>

	/**
	 * Subscribe to a specific WebSocket message type.
	 * @see subscribeAll
	 */
	public fun <T : OutboundWebSocketMessage> subscribe(messageType: KClass<T>): Flow<T>
}
