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
 * Library type options dto.
 */
@Serializable
public data class LibraryTypeOptionsDto(
	/**
	 * Gets or sets the type.
	 */
	@SerialName("Type")
	public val type: String? = null,
	/**
	 * Gets or sets the metadata fetchers.
	 */
	@SerialName("MetadataFetchers")
	public val metadataFetchers: List<LibraryOptionInfoDto>,
	/**
	 * Gets or sets the image fetchers.
	 */
	@SerialName("ImageFetchers")
	public val imageFetchers: List<LibraryOptionInfoDto>,
	/**
	 * Gets or sets the supported image types.
	 */
	@SerialName("SupportedImageTypes")
	public val supportedImageTypes: List<ImageType>,
	/**
	 * Gets or sets the default image options.
	 */
	@SerialName("DefaultImageOptions")
	public val defaultImageOptions: List<ImageOption>
)
