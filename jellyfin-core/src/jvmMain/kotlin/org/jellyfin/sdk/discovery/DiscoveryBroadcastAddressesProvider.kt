package org.jellyfin.sdk.discovery

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jellyfin.sdk.JellyfinOptions
import java.net.InetAddress
import java.net.NetworkInterface

/**
 * A broadcast address provider that works in the default JVM but not on Android
 */
public actual class DiscoveryBroadcastAddressesProvider actual constructor(jellyfinOptions: JellyfinOptions) {
	@Suppress("BlockingMethodInNonBlockingContext")
	public actual suspend fun getBroadcastAddresses(): Collection<InetAddress> = withContext(Dispatchers.IO) {
		NetworkInterface.getNetworkInterfaces().toList()
			.filter { !it.isLoopback && it.isUp }
			.flatMap { networkInterface ->
				networkInterface.interfaceAddresses.mapNotNull { address ->
					address.broadcast
				}
			}
	}
}
