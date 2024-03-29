// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A dto representing custom options for a device.
 */
@Serializable
public data class DeviceOptionsDto(
	/**
	 * The id.
	 */
	@SerialName("Id")
	public val id: Int,
	/**
	 * The device id.
	 */
	@SerialName("DeviceId")
	public val deviceId: String? = null,
	/**
	 * The custom name.
	 */
	@SerialName("CustomName")
	public val customName: String? = null,
)
