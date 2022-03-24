// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AuthenticationResult(
	/**
	 * Class UserDto.
	 */
	@SerialName("User")
	public val user: UserDto? = null,
	/**
	 * Class SessionInfo.
	 */
	@SerialName("SessionInfo")
	public val sessionInfo: SessionInfo? = null,
	@SerialName("AccessToken")
	public val accessToken: String? = null,
	@SerialName("ServerId")
	public val serverId: String? = null,
)
