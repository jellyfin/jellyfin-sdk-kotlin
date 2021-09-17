package org.jellyfin.sdk.api.extensions

import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.sockets.WebSocketApi

public val ApiClient.webSocket: WebSocketApi
	get() = getOrCreateApi { WebSocketApi(it) }
