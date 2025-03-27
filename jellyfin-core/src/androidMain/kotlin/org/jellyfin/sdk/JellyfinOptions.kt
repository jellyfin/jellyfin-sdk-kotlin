package org.jellyfin.sdk

import android.content.Context
import org.jellyfin.sdk.android.androidDevice
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.ServerVersion
import org.jellyfin.sdk.api.client.ApiClientFactory
import org.jellyfin.sdk.api.okhttp.OkHttpFactory

public actual data class JellyfinOptions(
	public val context: Context,
	public actual val clientInfo: ClientInfo?,
	public actual val deviceInfo: DeviceInfo?,
	public actual val apiClientFactory: ApiClientFactory,
	public actual val socketConnectionFactory: SocketConnectionFactory,
	public actual val minimumServerVersion: ServerVersion,
) {
	public actual class Builder {
		public var context: Context? = null
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null
		public var apiClientFactory: ApiClientFactory? = null
		public var socketConnectionFactory: SocketConnectionFactory? = null
		public var minimumServerVersion: ServerVersion = Jellyfin.minimumVersion

		private val defaultClientFactory by lazy { OkHttpFactory() }

		public actual fun build(): JellyfinOptions = JellyfinOptions(
			context = requireNotNull(context) {
				"An Android context is required when using the jellyfin-android platform."
			},
			clientInfo = clientInfo,
			deviceInfo = deviceInfo ?: androidDevice(context!!),
			apiClientFactory = apiClientFactory ?: defaultClientFactory,
			socketConnectionFactory = socketConnectionFactory ?: defaultClientFactory,
			minimumServerVersion = minimumServerVersion,
		)
	}

	public actual companion object
}
