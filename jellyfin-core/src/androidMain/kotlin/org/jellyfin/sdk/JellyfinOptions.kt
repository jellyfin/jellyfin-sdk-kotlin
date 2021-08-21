package org.jellyfin.sdk

import android.content.Context
import org.jellyfin.sdk.android.androidDevice
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

public actual data class JellyfinOptions(
	public val context: Context,
	public actual val clientInfo: ClientInfo?,
	public actual val deviceInfo: DeviceInfo?,
) {
	public actual class Builder {
		public var context: Context? = null
		public var clientInfo: ClientInfo? = null

		public actual fun build(): JellyfinOptions = JellyfinOptions(
			requireNotNull(context) { "An Android context is required when using the jellyfin-android platform." },
			clientInfo,
			deviceInfo = androidDevice(context!!),
		)
	}

	public actual companion object
}
