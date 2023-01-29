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

/**
 * Class LibraryUpdateInfo.
 */
@Serializable
public data class LibraryUpdateInfo(
	/**
	 * The folders added to.
	 */
	@SerialName("FoldersAddedTo")
	public val foldersAddedTo: List<String>,
	/**
	 * The folders removed from.
	 */
	@SerialName("FoldersRemovedFrom")
	public val foldersRemovedFrom: List<String>,
	/**
	 * The items added.
	 */
	@SerialName("ItemsAdded")
	public val itemsAdded: List<String>,
	/**
	 * The items removed.
	 */
	@SerialName("ItemsRemoved")
	public val itemsRemoved: List<String>,
	/**
	 * The items updated.
	 */
	@SerialName("ItemsUpdated")
	public val itemsUpdated: List<String>,
	@SerialName("CollectionFolders")
	public val collectionFolders: List<String>,
	@SerialName("IsEmpty")
	public val isEmpty: Boolean,
)
