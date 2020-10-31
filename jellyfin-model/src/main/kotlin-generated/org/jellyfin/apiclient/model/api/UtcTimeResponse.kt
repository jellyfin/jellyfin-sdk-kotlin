// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class UtcTimeResponse.
 */
@Serializable
public data class UtcTimeResponse(
	/**
	 * Gets or sets the UTC time when request has been received.
	 */
	@SerialName("RequestReceptionTime")
	public val requestReceptionTime: String? = null,
	/**
	 * Gets or sets the UTC time when response has been sent.
	 */
	@SerialName("ResponseTransmissionTime")
	public val responseTransmissionTime: String? = null
)
