package org.jellyfin.sdk.api.sockets

import kotlinx.coroutines.channels.Channel
import org.jellyfin.sdk.api.client.HttpClientOptions
import kotlin.coroutines.CoroutineContext

public fun interface SocketConnectionFactory {
	public suspend fun create(
		clientOptions: HttpClientOptions,
		incomingMessageChannel: Channel<String>,
		outgoingMessageChannel: Channel<String>,
		coroutineContext: CoroutineContext,
	): SocketInstanceConnection
}
