package org.jellyfin.apiclient.discovery

import java.net.InetAddress
import java.net.NetworkInterface

/**
 * A broadcast address provider that works in the default JVM but not on Android
 */
class JavaNetBroadcastAddressesProvider : DiscoveryBroadcastAddressesProvider {
	override suspend fun getBroadcastAddresses(): Collection<InetAddress> =
		NetworkInterface.getNetworkInterfaces().toList()
			.filter { !it.isLoopback && it.isUp }
			.flatMap { networkInterface ->
				networkInterface.interfaceAddresses.mapNotNull { address ->
					address.broadcast
				}
			}
}
