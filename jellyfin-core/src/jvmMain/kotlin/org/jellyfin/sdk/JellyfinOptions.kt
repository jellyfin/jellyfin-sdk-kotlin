package org.jellyfin.sdk

import org.jellyfin.sdk.api.client.KtorClient
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.util.ApiClientFactory

public actual data class JellyfinOptions(
	public actual val clientInfo: ClientInfo?,
	public actual val deviceInfo: DeviceInfo?,
	public actual val apiClientFactory: ApiClientFactory,
) {
	public actual class Builder {
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null
		public var apiClientFactory: ApiClientFactory = ApiClientFactory(::KtorClient)

		public actual fun build(): JellyfinOptions = JellyfinOptions(
			clientInfo = clientInfo,
			deviceInfo = deviceInfo,
			apiClientFactory = apiClientFactory,
		)
	}

	public actual companion object
}
