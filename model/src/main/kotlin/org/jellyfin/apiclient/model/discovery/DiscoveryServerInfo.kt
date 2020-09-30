package org.jellyfin.apiclient.model.discovery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the information exposed by the server via discovery
 */
@Serializable
data class DiscoveryServerInfo(
	/**
	 * The unique id of this server. Usually a UUID but not guaranteed.
	 */
	@SerialName("Id")
	val id: String,

	/**
	 * The announced address of the remote server.
	 * It is recommended to check if this address is accessible before storing it.
	 */
	@SerialName("Address")
	val address: String,

	/**
	 * The name of the server
	 */
	@SerialName("Name")
	val name: String
)
