// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class PingRequestDto.
 */
@Serializable
public data class PingRequestDto(
	/**
	 * Gets or sets the ping time.
	 */
	@SerialName("Ping")
	public val ping: Long,
)
