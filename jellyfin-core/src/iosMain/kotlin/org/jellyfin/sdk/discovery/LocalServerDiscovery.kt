package org.jellyfin.sdk.discovery

import kotlinx.coroutines.flow.Flow
import org.jellyfin.sdk.JellyfinOptions
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo

/**
 * Used to discover Jellyfin servers in the local network.
 *
 * Use the [discover] function to retrieve a flow of servers until the timeout is exceeded or
 * the maximum amount of servers has been retrieved.
 */
public actual class LocalServerDiscovery actual constructor(jellyfinOptions: JellyfinOptions) {
	public actual companion object {
		public const val DISCOVERY_MESSAGE: String = KtorLocalServerDiscovery.DISCOVERY_MESSAGE
		public const val DISCOVERY_PORT: Int = KtorLocalServerDiscovery.DISCOVERY_PORT
		public actual val DISCOVERY_TIMEOUT: Int = 500 // ms
		public actual val DISCOVERY_MAX_SERVERS: Int = 15
	}

	private val delegate = KtorLocalServerDiscovery()

	public actual fun discover(
		timeout: Int,
		maxServers: Int,
	): Flow<ServerDiscoveryInfo> = delegate.discover(timeout, maxServers)
}
