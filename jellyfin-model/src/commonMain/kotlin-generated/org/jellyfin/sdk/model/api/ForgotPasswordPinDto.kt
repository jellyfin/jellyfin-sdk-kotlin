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
 * Forgot Password Pin enter request body DTO.
 */
@Serializable
public data class ForgotPasswordPinDto(
	/**
	 * The entered pin to have the password reset.
	 */
	@SerialName("Pin")
	public val pin: String,
)
