package org.jellyfin.sdk

import org.jellyfin.sdk.discovery.DiscoveryBroadcastAddressesProvider
import org.jellyfin.sdk.discovery.JavaNetBroadcastAddressesProvider
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

public data class JellyfinOptions(
	val discoverBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider,
	val clientInfo: ClientInfo?,
	val deviceInfo: DeviceInfo?,
) {
	public class Builder {
		public var discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider =
			JavaNetBroadcastAddressesProvider()
		public var clientInfo: ClientInfo? = null
		public var deviceInfo: DeviceInfo? = null

		public fun build(): JellyfinOptions = JellyfinOptions(
			discoveryBroadcastAddressesProvider,
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
