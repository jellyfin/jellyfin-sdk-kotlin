// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class PlaybackInfoResponse.
 */
@Serializable
public data class PlaybackInfoResponse(
	/**
	 * Gets or sets the media sources.
	 */
	@SerialName("MediaSources")
	public val mediaSources: List<MediaSourceInfo>? = null,
	/**
	 * Gets or sets the play session identifier.
	 */
	@SerialName("PlaySessionId")
	public val playSessionId: String? = null,
	@SerialName("ErrorCode")
	public val errorCode: PlaybackErrorCode? = null
)
