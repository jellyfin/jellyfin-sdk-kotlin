package org.jellyfin.apiclient.discovery

//TODO: Move to model package
data class DiscoveryServerInfo(
	val id: String,
	val address: String,
	val name: String,
	val endpointAddress: String?
)
