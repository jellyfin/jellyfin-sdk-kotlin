package org.jellyfin.apiclient.discovery

/**
 * Contains the information exposed by the server on discovery
 * Used by [ServerDiscovery]
 */
data class DiscoveryServerInfo(
	val id: String,
	val address: String,
	val name: String,
	val endpointAddress: String?
)
