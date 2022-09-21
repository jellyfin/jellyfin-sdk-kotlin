// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(DateTimeSerializer::class)

package org.jellyfin.sdk.model.api

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.serializer.DateTimeSerializer

@Serializable
public data class LogFile(
	/**
	 * The date created.
	 */
	@SerialName("DateCreated")
	public val dateCreated: DateTime,
	/**
	 * The date modified.
	 */
	@SerialName("DateModified")
	public val dateModified: DateTime,
	/**
	 * The size.
	 */
	@SerialName("Size")
	public val size: Long,
	/**
	 * The name.
	 */
	@SerialName("Name")
	public val name: String? = null,
)
