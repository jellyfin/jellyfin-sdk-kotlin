package org.jellyfin.sdk.discovery

import org.jellyfin.sdk.util.InetAddress

/**
 * Broadcast address provider definition that can be implemented to support different platforms for
 * server discovery.
 */
public expect class DiscoveryBroadcastAddressesProvider constructor() {
	/**
	 * Provide broadcast addresses
	 */
	public suspend fun getBroadcastAddresses(): Collection<InetAddress>
}
