// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Channel mapping options dto.
 */
@Serializable
public data class ChannelMappingOptionsDto(
	/**
	 * List of tuner channels.
	 */
	@SerialName("TunerChannels")
	public val tunerChannels: List<TunerChannelMapping>,
	/**
	 * List of provider channels.
	 */
	@SerialName("ProviderChannels")
	public val providerChannels: List<NameIdPair>,
	/**
	 * List of mappings.
	 */
	@SerialName("Mappings")
	public val mappings: List<NameValuePair>,
	/**
	 * Provider name.
	 */
	@SerialName("ProviderName")
	public val providerName: String? = null,
)
