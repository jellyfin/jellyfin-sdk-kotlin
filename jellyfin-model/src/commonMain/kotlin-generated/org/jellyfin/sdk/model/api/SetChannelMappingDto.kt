// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Set channel mapping dto.
 */
@Serializable
public data class SetChannelMappingDto(
	/**
	 * The provider id.
	 */
	@SerialName("ProviderId")
	public val providerId: String,
	/**
	 * The tuner channel id.
	 */
	@SerialName("TunerChannelId")
	public val tunerChannelId: String,
	/**
	 * The provider channel id.
	 */
	@SerialName("ProviderChannelId")
	public val providerChannelId: String,
)
