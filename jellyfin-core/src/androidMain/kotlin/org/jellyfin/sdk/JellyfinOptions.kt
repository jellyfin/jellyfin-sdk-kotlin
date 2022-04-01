package org.jellyfin.sdk

import android.content.Context
import org.jellyfin.sdk.android.androidDevice
import org.jellyfin.sdk.api.client.KtorClient
import org.jellyfin.sdk.api.sockets.OkHttpWebsocketSession
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.util.ApiClientFactory

public actual data class JellyfinOptions(
	public val context: Context,
	public actual val clientInfo: ClientInfo?,
	public actual val deviceInfo: DeviceInfo?,
	public actual val apiClientFactory: ApiClientFactory,
	public actual val socketConnectionFactory: SocketConnectionFactory,
) {
	public actual class Builder {
		public var context: Context? = null
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null
		public var apiClientFactory: ApiClientFactory = ApiClientFactory(::KtorClient)
		public var socketConnectionFactory: SocketConnectionFactory = SocketConnectionFactory(::OkHttpWebsocketSession)

		public actual fun build(): JellyfinOptions = JellyfinOptions(
			context = requireNotNull(context) {
				"An Android context is required when using the jellyfin-android platform."
			},
			clientInfo = clientInfo,
			deviceInfo = deviceInfo ?: androidDevice(context!!),
			apiClientFactory = apiClientFactory,
			socketConnectionFactory = socketConnectionFactory,
		)
	}

	public actual companion object
}
