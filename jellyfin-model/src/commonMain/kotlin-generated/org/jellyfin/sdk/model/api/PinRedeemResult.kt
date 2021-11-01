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
public data class PinRedeemResult(
	/**
	 * Gets or sets a value indicating whether this MediaBrowser.Model.Users.PinRedeemResult is
	 * success.
	 */
	@SerialName("Success")
	public val success: Boolean,
	/**
	 * Gets or sets the users reset.
	 */
	@SerialName("UsersReset")
	public val usersReset: List<String>
)
