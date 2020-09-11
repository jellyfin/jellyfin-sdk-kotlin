package org.jellyfin.apiclient

import org.jellyfin.apiclient.discovery.IDiscoveryBroadcastAddressesProvider
import org.jellyfin.apiclient.discovery.JavaNetBroadcastAddressesProvider
import org.jellyfin.apiclient.logging.ILogger
import org.jellyfin.apiclient.logging.NullLogger

data class JellyfinOptions(
	val logger: ILogger,
	val discoverBroadcastAddressesProvider: IDiscoveryBroadcastAddressesProvider,
	val appInfo: AppInfo
) {
	class Builder {
		var logger: ILogger = NullLogger()
		var discoveryBroadcastAddressesProvider: IDiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
		var appInfo: AppInfo = AppInfo(name = "Unknown", version = "?")

		fun build() = JellyfinOptions(
			logger,
			discoveryBroadcastAddressesProvider,
			appInfo
		)
	}

	companion object {
		fun build(init: Builder.() -> Unit): JellyfinOptions {
			val builder = Builder()
			builder.init()
			return builder.build()
		}
	}
}
