package org.jellyfin.sdk.util

import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.UUID

public fun interface ApiClientFactory {
	@Suppress("LongParameterList")
	public fun create(
		baseUrl: String?,
		accessToken: String?,
		userId: UUID?,
		clientInfo: ClientInfo,
		deviceInfo: DeviceInfo,
		httpClientOptions: HttpClientOptions,
		socketConnectionFactory: SocketConnectionFactory,
	): ApiClient
}
