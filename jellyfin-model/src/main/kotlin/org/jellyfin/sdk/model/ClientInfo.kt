package org.jellyfin.sdk.model

/**
 * The client information is used to identify the client.
 */
public data class ClientInfo(
	/**
	 * Name of the client, this should normally not change.
	 */
	val name: String,

	/**
	 * The version of the client.
	 */
	val version: String,
)
