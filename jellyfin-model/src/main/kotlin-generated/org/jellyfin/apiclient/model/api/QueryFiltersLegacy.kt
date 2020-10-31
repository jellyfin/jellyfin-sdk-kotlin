// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class QueryFiltersLegacy(
	@SerialName("Genres")
	public val genres: List<String>? = null,
	@SerialName("Tags")
	public val tags: List<String>? = null,
	@SerialName("OfficialRatings")
	public val officialRatings: List<String>? = null,
	@SerialName("Years")
	public val years: List<Int>? = null
)
