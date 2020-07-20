package org.jellyfin.apiclient

import org.jellyfin.apiclient.discovery.IDiscoveryBroadcastAddressesProvider
import org.jellyfin.apiclient.discovery.JavaNetBroadcastAddressesProvider
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient
import org.jellyfin.apiclient.logging.ILogger
import org.jellyfin.apiclient.logging.NullLogger

data class JellyfinOptions(
	val logger: ILogger,
	val discoverBroadcastAddressesProvider: IDiscoveryBroadcastAddressesProvider,
	val appInfo: AppInfo,
	val httpClient: IAsyncHttpClient
) {
	class Builder {
		var logger: ILogger = NullLogger()
		var discoveryBroadcastAddressesProvider: IDiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
		var appInfo: AppInfo = AppInfo(name = "Unknown", version = "?")
		var httpClient: IAsyncHttpClient = NoHttpClient()

		fun build() = JellyfinOptions(
			logger,
			discoveryBroadcastAddressesProvider,
			appInfo,
			httpClient
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
