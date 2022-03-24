// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	UUIDSerializer::class,
	DateTimeSerializer::class,
)

package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.DateTimeSerializer
import org.jellyfin.sdk.model.serializer.UUIDSerializer

@Serializable
public data class DeviceInfo(
	@SerialName("Name")
	public val name: String? = null,
	/**
	 * Gets or sets the identifier.
	 */
	@SerialName("Id")
	public val id: String? = null,
	/**
	 * Gets or sets the last name of the user.
	 */
	@SerialName("LastUserName")
	public val lastUserName: String? = null,
	/**
	 * Gets or sets the name of the application.
	 */
	@SerialName("AppName")
	public val appName: String? = null,
	/**
	 * Gets or sets the application version.
	 */
	@SerialName("AppVersion")
	public val appVersion: String? = null,
	/**
	 * Gets or sets the last user identifier.
	 */
	@SerialName("LastUserId")
	public val lastUserId: UUID,
	/**
	 * Gets or sets the date last modified.
	 */
	@SerialName("DateLastActivity")
	public val dateLastActivity: DateTime,
	/**
	 * Gets or sets the capabilities.
	 */
	@SerialName("Capabilities")
	public val capabilities: ClientCapabilities? = null,
	@SerialName("IconUrl")
	public val iconUrl: String? = null,
)
