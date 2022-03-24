// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Library options result dto.
 */
@Serializable
public data class LibraryOptionsResultDto(
	/**
	 * Gets or sets the metadata savers.
	 */
	@SerialName("MetadataSavers")
	public val metadataSavers: List<LibraryOptionInfoDto>? = null,
	/**
	 * Gets or sets the metadata readers.
	 */
	@SerialName("MetadataReaders")
	public val metadataReaders: List<LibraryOptionInfoDto>? = null,
	/**
	 * Gets or sets the subtitle fetchers.
	 */
	@SerialName("SubtitleFetchers")
	public val subtitleFetchers: List<LibraryOptionInfoDto>? = null,
	/**
	 * Gets or sets the type options.
	 */
	@SerialName("TypeOptions")
	public val typeOptions: List<LibraryTypeOptionsDto>? = null,
)
