package org.jellyfin.sdk

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

public data class JellyfinOptions constructor(
	public val clientInfo: ClientInfo?,
	public val deviceInfo: DeviceInfo?,
) {
	public class Builder {
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null

		public fun build(): JellyfinOptions = JellyfinOptions(
			clientInfo,
			deviceInfo
		)
	}

	public companion object {
		public fun build(init: Builder.() -> Unit): JellyfinOptions = Builder().run {
			init()
			build()
		}
	}
}
