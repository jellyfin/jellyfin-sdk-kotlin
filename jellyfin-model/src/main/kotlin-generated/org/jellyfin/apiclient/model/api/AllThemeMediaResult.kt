// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AllThemeMediaResult(
	/**
	 * Class ThemeMediaResult.
	 */
	@SerialName("ThemeVideosResult")
	public val themeVideosResult: ThemeMediaResult? = null,
	/**
	 * Class ThemeMediaResult.
	 */
	@SerialName("ThemeSongsResult")
	public val themeSongsResult: ThemeMediaResult? = null,
	/**
	 * Class ThemeMediaResult.
	 */
	@SerialName("SoundtrackSongsResult")
	public val soundtrackSongsResult: ThemeMediaResult? = null
)
