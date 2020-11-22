package org.jellyfin.apiclient

import org.jellyfin.apiclient.discovery.DiscoveryBroadcastAddressesProvider
import org.jellyfin.apiclient.discovery.JavaNetBroadcastAddressesProvider
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo

public data class JellyfinOptions(
	val discoverBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider,
	val clientInfo: ClientInfo?,
	val deviceInfo: DeviceInfo?
) {
	public class Builder {
		public var discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
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
