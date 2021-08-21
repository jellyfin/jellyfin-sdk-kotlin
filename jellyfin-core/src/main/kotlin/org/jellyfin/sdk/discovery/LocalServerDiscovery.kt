package org.jellyfin.sdk.discovery

import kotlinx.coroutines.flow.Flow
import org.jellyfin.sdk.JellyfinOptions
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo

public expect class LocalServerDiscovery(jellyfinOptions: JellyfinOptions) {
	public companion object {
		public val DISCOVERY_TIMEOUT: Int
		public val DISCOVERY_MAX_SERVERS: Int
	}

	/**
	 * Discover servers on the local network
	 *
	 * @param timeout Timeout per receive in milliseconds
	 */
	public fun discover(
		timeout: Int = DISCOVERY_TIMEOUT,
		maxServers: Int = DISCOVERY_MAX_SERVERS,
	): Flow<ServerDiscoveryInfo>
}
