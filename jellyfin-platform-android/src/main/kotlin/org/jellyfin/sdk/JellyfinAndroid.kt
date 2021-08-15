package org.jellyfin.sdk

import android.content.Context
//import org.jellyfin.sdk.discovery.AndroidBroadcastAddressesProvider
import org.jellyfin.sdk.interaction.androidDevice

/**
 * Add default Android configuration.
 * Only run after setting the logger.
 */
public fun JellyfinOptions.Builder.android(context: Context) {
	// FIXME
	// discoveryBroadcastAddressesProvider = AndroidBroadcastAddressesProvider(context)
	deviceInfo = androidDevice(context)
}
