// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LiveTvInfo(
	/**
	 * The services.
	 */
	@SerialName("Services")
	public val services: List<LiveTvServiceInfo>,
	/**
	 * A value indicating whether this instance is enabled.
	 */
	@SerialName("IsEnabled")
	public val isEnabled: Boolean,
	/**
	 * The enabled users.
	 */
	@SerialName("EnabledUsers")
	public val enabledUsers: List<String>,
)
