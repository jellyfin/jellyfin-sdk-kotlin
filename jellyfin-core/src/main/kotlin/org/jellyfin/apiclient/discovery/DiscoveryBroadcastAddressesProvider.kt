package org.jellyfin.apiclient.discovery

import java.net.InetAddress

/**
 * Broadcast address provider definition that can be implemented to support different platforms for
 * server discovery.
 */
public interface DiscoveryBroadcastAddressesProvider {
	/**
	 * Provide broadcast addresses
	 */
	public suspend fun getBroadcastAddresses(): Collection<InetAddress>
}
