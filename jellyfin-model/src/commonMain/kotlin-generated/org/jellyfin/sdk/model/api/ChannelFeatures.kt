// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.UUIDSerializer

@Serializable
public data class ChannelFeatures(
	/**
	 * The name.
	 */
	@SerialName("Name")
	public val name: String,
	/**
	 * The identifier.
	 */
	@SerialName("Id")
	public val id: UUID,
	/**
	 * A value indicating whether this instance can search.
	 */
	@SerialName("CanSearch")
	public val canSearch: Boolean,
	/**
	 * The media types.
	 */
	@SerialName("MediaTypes")
	public val mediaTypes: List<ChannelMediaType>,
	/**
	 * The content types.
	 */
	@SerialName("ContentTypes")
	public val contentTypes: List<ChannelMediaContentType>,
	/**
	 * The maximum number of records the channel allows retrieving at a time.
	 */
	@SerialName("MaxPageSize")
	public val maxPageSize: Int? = null,
	/**
	 * The automatic refresh levels.
	 */
	@SerialName("AutoRefreshLevels")
	public val autoRefreshLevels: Int? = null,
	/**
	 * The default sort orders.
	 */
	@SerialName("DefaultSortFields")
	public val defaultSortFields: List<ChannelItemSortField>,
	/**
	 * A value indicating whether a sort ascending/descending toggle is supported.
	 */
	@SerialName("SupportsSortOrderToggle")
	public val supportsSortOrderToggle: Boolean,
	/**
	 * A value indicating whether [supports latest media].
	 */
	@SerialName("SupportsLatestMedia")
	public val supportsLatestMedia: Boolean,
	/**
	 * A value indicating whether this instance can filter.
	 */
	@SerialName("CanFilter")
	public val canFilter: Boolean,
	/**
	 * A value indicating whether [supports content downloading].
	 */
	@SerialName("SupportsContentDownloading")
	public val supportsContentDownloading: Boolean,
)
