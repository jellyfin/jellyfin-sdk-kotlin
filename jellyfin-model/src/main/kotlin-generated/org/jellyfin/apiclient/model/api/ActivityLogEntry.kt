// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	LocalDateTimeSerializer::class,
	UUIDSerializer::class
)

package org.jellyfin.apiclient.model.api

import java.time.LocalDateTime
import java.util.UUID
import kotlin.Deprecated
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.apiclient.model.serializer.LocalDateTimeSerializer
import org.jellyfin.apiclient.model.serializer.UUIDSerializer

@Serializable
public data class ActivityLogEntry(
	/**
	 * Gets or sets the identifier.
	 */
	@SerialName("Id")
	public val id: Long,
	/**
	 * Gets or sets the name.
	 */
	@SerialName("Name")
	public val name: String? = null,
	/**
	 * Gets or sets the overview.
	 */
	@SerialName("Overview")
	public val overview: String? = null,
	/**
	 * Gets or sets the short overview.
	 */
	@SerialName("ShortOverview")
	public val shortOverview: String? = null,
	/**
	 * Gets or sets the type.
	 */
	@SerialName("Type")
	public val type: String? = null,
	/**
	 * Gets or sets the item identifier.
	 */
	@SerialName("ItemId")
	public val itemId: String? = null,
	/**
	 * Gets or sets the date.
	 */
	@SerialName("Date")
	public val date: LocalDateTime,
	/**
	 * Gets or sets the user identifier.
	 */
	@SerialName("UserId")
	public val userId: UUID,
	/**
	 * Gets or sets the user primary image tag.
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	@SerialName("UserPrimaryImageTag")
	public val userPrimaryImageTag: String? = null,
	@SerialName("Severity")
	public val severity: LogLevel? = null
)
