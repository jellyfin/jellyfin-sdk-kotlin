package org.jellyfin.apiclient.discovery

import java.net.InetAddress

/**
 * Broadcast address provider definition that can be implemented to support different platforms for
 * server discovery.
 */
interface DiscoveryBroadcastAddressesProvider {
	/**
	 * Provide broadcast addresses
	 */
	suspend fun getBroadcastAddresses(): Collection<InetAddress>
}
