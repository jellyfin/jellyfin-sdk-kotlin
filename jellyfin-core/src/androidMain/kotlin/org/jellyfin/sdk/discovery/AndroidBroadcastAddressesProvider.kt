package org.jellyfin.sdk.discovery

import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import androidx.core.content.getSystemService
import org.jellyfin.sdk.JellyfinOptions
import org.jellyfin.sdk.util.InetAddress

/**
 * A broadcast address provider that uses the WifiManager service to retrieve the broadcast address
 */
public actual class DiscoveryBroadcastAddressesProvider actual constructor(
	private val jellyfinOptions: JellyfinOptions,
) {
	/**
	 * Retrieve the broadcast address using the Android WifiManager.
	 * Requires the ACCESS_WIFI_STATE permission which is not enabled by default.
	 */
	@RequiresPermission("android.permission.ACCESS_WIFI_STATE")
	@Suppress("MagicNumber")
	public actual suspend fun getBroadcastAddresses(): Collection<InetAddress> {
		val wifi = jellyfinOptions.context.getSystemService<WifiManager>()
		val dhcp = wifi?.dhcpInfo ?: return emptyList()
		val broadcast = dhcp.ipAddress and dhcp.netmask or dhcp.netmask.inv()
		val quads = ByteArray(4)
		for (k in 0..3) quads[k] = (broadcast shr k * 8).toByte()
		return listOf(InetAddress.getByAddress(quads))
	}
}
