package org.jellyfin.sdk.discovery

import java.net.InetAddress

public class MockDiscoveryBroadcastAddressesProvider : DiscoveryBroadcastAddressesProvider {
	override suspend fun getBroadcastAddresses(): Collection<InetAddress> = emptyList()
}
