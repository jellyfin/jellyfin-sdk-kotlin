package org.jellyfin.sdk.model

/**
 * The device information is used to identify the device the client application is running on.
 */
public data class DeviceInfo(
	/**
	 * Unique id of the device. Only one user may be authenticated per device. It is recommended to generate an unique
	 * value for each user in the client.
	 */
	val id: String,

	/**
	 * Name of the device.
	 */
	val name: String
)
