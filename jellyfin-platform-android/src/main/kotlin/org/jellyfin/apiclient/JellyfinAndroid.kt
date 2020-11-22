package org.jellyfin.apiclient

import android.content.Context
import org.jellyfin.apiclient.discovery.AndroidBroadcastAddressesProvider
import org.jellyfin.apiclient.interaction.androidDevice

/**
 * Add default Android configuration.
 * Only run after setting the logger.
 */
public fun JellyfinOptions.Builder.android(context: Context) {
	discoveryBroadcastAddressesProvider = AndroidBroadcastAddressesProvider(context)
	deviceInfo = androidDevice(context)
}
