// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(DateTimeSerializer::class)

package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.serializer.DateTimeSerializer

@Serializable
public data class AlbumInfo(
	/**
	 * Gets or sets the name.
	 */
	@SerialName("Name")
	public val name: String? = null,
	/**
	 * Gets or sets the original title.
	 */
	@SerialName("OriginalTitle")
	public val originalTitle: String? = null,
	/**
	 * Gets or sets the path.
	 */
	@SerialName("Path")
	public val path: String? = null,
	/**
	 * Gets or sets the metadata language.
	 */
	@SerialName("MetadataLanguage")
	public val metadataLanguage: String? = null,
	/**
	 * Gets or sets the metadata country code.
	 */
	@SerialName("MetadataCountryCode")
	public val metadataCountryCode: String? = null,
	/**
	 * Gets or sets the provider ids.
	 */
	@SerialName("ProviderIds")
	public val providerIds: Map<String, String>? = null,
	/**
	 * Gets or sets the year.
	 */
	@SerialName("Year")
	public val year: Int? = null,
	@SerialName("IndexNumber")
	public val indexNumber: Int? = null,
	@SerialName("ParentIndexNumber")
	public val parentIndexNumber: Int? = null,
	@SerialName("PremiereDate")
	public val premiereDate: DateTime? = null,
	@SerialName("IsAutomated")
	public val isAutomated: Boolean,
	/**
	 * Gets or sets the album artist.
	 */
	@SerialName("AlbumArtists")
	public val albumArtists: List<String>,
	/**
	 * Gets or sets the artist provider ids.
	 */
	@SerialName("ArtistProviderIds")
	public val artistProviderIds: Map<String, String>,
	@SerialName("SongInfos")
	public val songInfos: List<SongInfo>
)
