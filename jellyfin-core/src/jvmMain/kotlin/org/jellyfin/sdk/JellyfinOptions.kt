package org.jellyfin.sdk

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

public actual data class JellyfinOptions(
	public actual val clientInfo: ClientInfo?,
	public actual val deviceInfo: DeviceInfo?,
) {
	public actual class Builder {
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null

		public actual fun build(): JellyfinOptions = JellyfinOptions(
			clientInfo,
			deviceInfo,
		)
	}

	public actual companion object
}
