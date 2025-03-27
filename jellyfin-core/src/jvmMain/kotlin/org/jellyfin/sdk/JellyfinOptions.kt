package org.jellyfin.sdk

import org.jellyfin.sdk.api.client.ApiClientFactory
import org.jellyfin.sdk.api.okhttp.OkHttpFactory
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.ServerVersion

public actual data class JellyfinOptions(
	public actual val clientInfo: ClientInfo?,
	public actual val deviceInfo: DeviceInfo?,
	public actual val apiClientFactory: ApiClientFactory,
	public actual val socketConnectionFactory: SocketConnectionFactory,
	public actual val minimumServerVersion: ServerVersion,
) {
	public actual class Builder {
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null
		public var apiClientFactory: ApiClientFactory? = null
		public var socketConnectionFactory: SocketConnectionFactory? = null
		public var minimumServerVersion: ServerVersion = Jellyfin.minimumVersion

		private val defaultClientFactory by lazy { OkHttpFactory() }

		public actual fun build(): JellyfinOptions = JellyfinOptions(
			clientInfo = clientInfo,
			deviceInfo = deviceInfo,
			apiClientFactory = apiClientFactory ?: defaultClientFactory,
			socketConnectionFactory = socketConnectionFactory ?: defaultClientFactory,
			minimumServerVersion = minimumServerVersion,
		)
	}

	public actual companion object
}
