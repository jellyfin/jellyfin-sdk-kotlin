package org.jellyfin.apiclient

import org.jellyfin.apiclient.discovery.DiscoveryBroadcastAddressesProvider
import org.jellyfin.apiclient.discovery.JavaNetBroadcastAddressesProvider
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo

data class JellyfinOptions(
	val discoverBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider,
	val clientInfo: ClientInfo?,
	val deviceInfo: DeviceInfo?
) {
	class Builder {
		var discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
		var clientInfo: ClientInfo? = null
		var deviceInfo: DeviceInfo? = null

		fun build() = JellyfinOptions(
			discoveryBroadcastAddressesProvider,
			clientInfo,
			deviceInfo
		)
	}

	companion object {
		fun build(init: Builder.() -> Unit)= Builder().run {
			init()
			build()
		}
	}
}
