// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Media Update Info Dto.
 */
@Serializable
public data class MediaUpdateInfoDto(
	/**
	 * Gets or sets the list of updates.
	 */
	@SerialName("Updates")
	public val updates: List<MediaUpdateInfoPathDto>? = null
)
