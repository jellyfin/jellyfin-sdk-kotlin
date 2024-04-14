package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.CoroutineScope
import org.jellyfin.sdk.api.client.HttpClientOptions

/**
 * The factory to create a [SocketConnection].
 */
public fun interface SocketConnectionFactory {
	public fun create(
		clientOptions: HttpClientOptions,
		scope: CoroutineScope,
	): SocketConnection
}
