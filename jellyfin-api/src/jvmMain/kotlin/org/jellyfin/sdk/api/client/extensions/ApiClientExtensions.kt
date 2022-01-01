@file:JvmName("JvmApiClientExtensionsKt")

package org.jellyfin.sdk.api.client.extensions

import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.sockets.WebSocketApi

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Replaced with the new SocketApi. This class will be removed in the next SDK release.")
public val ApiClient.webSocket: WebSocketApi
	get() = getOrCreateApi { WebSocketApi(it) }
