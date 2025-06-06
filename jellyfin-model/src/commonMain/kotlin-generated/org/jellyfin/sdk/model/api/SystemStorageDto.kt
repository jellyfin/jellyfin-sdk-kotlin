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
 * Contains informations about the systems storage.
 */
@Serializable
public data class SystemStorageDto(
	/**
	 * The Storage information of the program data folder.
	 */
	@SerialName("ProgramDataFolder")
	public val programDataFolder: FolderStorageDto,
	/**
	 * The Storage information of the web UI resources folder.
	 */
	@SerialName("WebFolder")
	public val webFolder: FolderStorageDto,
	/**
	 * The Storage information of the folder where images are cached.
	 */
	@SerialName("ImageCacheFolder")
	public val imageCacheFolder: FolderStorageDto,
	/**
	 * The Storage information of the cache folder.
	 */
	@SerialName("CacheFolder")
	public val cacheFolder: FolderStorageDto,
	/**
	 * The Storage information of the folder where logfiles are saved to.
	 */
	@SerialName("LogFolder")
	public val logFolder: FolderStorageDto,
	/**
	 * The Storage information of the folder where metadata is stored.
	 */
	@SerialName("InternalMetadataFolder")
	public val internalMetadataFolder: FolderStorageDto,
	/**
	 * The Storage information of the transcoding cache.
	 */
	@SerialName("TranscodingTempFolder")
	public val transcodingTempFolder: FolderStorageDto,
	/**
	 * The storage informations of all libraries.
	 */
	@SerialName("Libraries")
	public val libraries: List<LibraryStorageDto>,
)
