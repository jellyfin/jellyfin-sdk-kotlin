package org.jellyfin.apiclient.discovery

import java.net.InetAddress

/**
 * Interface for broadcast address providers to support different platforms to use server discovery
 */
interface IDiscoveryBroadcastAddressesProvider {
	/**
	 * Provide broadcast addresses
	 */
	suspend fun getBroadcastAddresses(): Collection<InetAddress>
}
