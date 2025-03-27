package org.jellyfin.sdk

import org.jellyfin.sdk.api.client.ApiClientFactory
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.ServerVersion

public expect class JellyfinOptions {
	public val clientInfo: ClientInfo?
	public val deviceInfo: DeviceInfo?
	public val apiClientFactory: ApiClientFactory
	public val socketConnectionFactory: SocketConnectionFactory
	public val minimumServerVersion: ServerVersion

	@Suppress("EmptyDefaultConstructor")
	public class Builder() {
		public fun build(): JellyfinOptions
	}

	public companion object
}

public inline fun createJellyfinOptions(
	init: JellyfinOptions.Builder.() -> Unit,
): JellyfinOptions = JellyfinOptions.Builder().apply(init).build()
