// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class IgnoreWaitRequestDto.
 */
@Serializable
public data class IgnoreWaitRequestDto(
	/**
	 * Gets or sets a value indicating whether the client should be ignored.
	 */
	@SerialName("IgnoreWait")
	public val ignoreWait: Boolean
)
