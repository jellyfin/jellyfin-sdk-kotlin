package org.jellyfin.apiclient

import android.content.Context
import org.jellyfin.apiclient.discovery.AndroidBroadcastAddressesProvider
import org.jellyfin.apiclient.interaction.VolleyHttpClient

/**
 * Add default Android configuration.
 * Only run after setting the logger.
 */
fun JellyfinOptions.Builder.android(context: Context) {
	discoveryBroadcastAddressesProvider = AndroidBroadcastAddressesProvider(context)
	httpClient = VolleyHttpClient(logger, context)
}
