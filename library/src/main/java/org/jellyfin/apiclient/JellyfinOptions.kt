package org.jellyfin.apiclient

import org.jellyfin.apiclient.discovery.DiscoveryBroadcastAddressesProvider
import org.jellyfin.apiclient.discovery.JavaNetBroadcastAddressesProvider
import org.jellyfin.apiclient.logging.ILogger
import org.jellyfin.apiclient.logging.NullLogger
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo

data class JellyfinOptions(
	val logger: ILogger,
	val discoverBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider,
	val clientInfo: ClientInfo?,
	val deviceInfo: DeviceInfo?
) {
	class Builder {
		var logger: ILogger = NullLogger()
		var discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
		var clientInfo: ClientInfo? = null
		var deviceInfo: DeviceInfo? = null

		fun build() = JellyfinOptions(
			logger,
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
