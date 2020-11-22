package org.jellyfin.apiclient.discovery

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import java.net.NetworkInterface

/**
 * A broadcast address provider that works in the default JVM but not on Android
 */
public class JavaNetBroadcastAddressesProvider : DiscoveryBroadcastAddressesProvider {
	@Suppress("BlockingMethodInNonBlockingContext")
	override suspend fun getBroadcastAddresses(): Collection<InetAddress> = withContext(Dispatchers.IO) {
		NetworkInterface.getNetworkInterfaces().toList()
			.filter { !it.isLoopback && it.isUp }
			.flatMap { networkInterface ->
				networkInterface.interfaceAddresses.mapNotNull { address ->
					address.broadcast
				}
			}
	}
}
