package org.jellyfin.apiclient

import android.content.Context
import org.jellyfin.apiclient.discovery.AndroidBroadcastAddressesProvider

/**
 * Add default Android configuration.
 * Only run after setting the logger.
 */
fun JellyfinOptions.Builder.android(context: Context) {
	discoveryBroadcastAddressesProvider = AndroidBroadcastAddressesProvider(context)
}
