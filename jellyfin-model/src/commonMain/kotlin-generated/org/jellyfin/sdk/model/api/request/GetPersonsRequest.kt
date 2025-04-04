// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemFilter
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * All persons.
 */
@Serializable
public data class GetPersonsRequest(
	/**
	 * Optional. The maximum number of records to return.
	 */
	@SerialName("limit")
	public val limit: Int? = null,
	/**
	 * The search term.
	 */
	@SerialName("searchTerm")
	public val searchTerm: String? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional. Specify additional filters to apply.
	 */
	@SerialName("filters")
	public val filters: Collection<ItemFilter>? = null,
	/**
	 * Optional filter by items that are marked as favorite, or not. userId is required.
	 */
	@SerialName("isFavorite")
	public val isFavorite: Boolean? = null,
	/**
	 * Optional, include user data.
	 */
	@SerialName("enableUserData")
	public val enableUserData: Boolean? = null,
	/**
	 * Optional, the max number of images to return, per image type.
	 */
	@SerialName("imageTypeLimit")
	public val imageTypeLimit: Int? = null,
	/**
	 * Optional. The image types to include in the output.
	 */
	@SerialName("enableImageTypes")
	public val enableImageTypes: Collection<ImageType>? = null,
	/**
	 * Optional. If specified results will be filtered to exclude those containing the specified PersonType. Allows multiple, comma-delimited.
	 */
	@SerialName("excludePersonTypes")
	public val excludePersonTypes: Collection<String>? = null,
	/**
	 * Optional. If specified results will be filtered to include only those containing the specified PersonType. Allows multiple, comma-delimited.
	 */
	@SerialName("personTypes")
	public val personTypes: Collection<String>? = null,
	/**
	 * Optional. If specified, person results will be filtered on items related to said persons.
	 */
	@SerialName("appearsInItemId")
	public val appearsInItemId: UUID? = null,
	/**
	 * User id.
	 */
	@SerialName("userId")
	public val userId: UUID? = null,
	/**
	 * Optional, include image information in output.
	 */
	@SerialName("enableImages")
	public val enableImages: Boolean? = true,
)
