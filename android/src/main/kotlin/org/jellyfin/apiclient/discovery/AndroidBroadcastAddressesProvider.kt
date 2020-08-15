package org.jellyfin.apiclient.discovery

import android.content.Context
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import androidx.core.content.getSystemService
import java.net.InetAddress

/**
 * A broadcast address provider that uses the WifiManager service to retrieve the broadcast address
 */
class AndroidBroadcastAddressesProvider(
	private val context: Context
) : IDiscoveryBroadcastAddressesProvider {
	/**
	 * Retrieve the broadcast address using the Android WifiManager
	 */
	@RequiresPermission("android.permission.ACCESS_WIFI_STATE")
	override suspend fun getBroadcastAddresses(): Collection<InetAddress> {
		val wifi = context.getSystemService<WifiManager>() ?: return emptyList()
		val dhcp = wifi.dhcpInfo ?: return emptyList()
		val broadcast = dhcp.ipAddress and dhcp.netmask or dhcp.netmask.inv()
		val quads = ByteArray(4)
		for (k in 0..3) quads[k] = (broadcast shr k * 8).toByte()
		return listOf(InetAddress.getByAddress(quads))
	}
}
