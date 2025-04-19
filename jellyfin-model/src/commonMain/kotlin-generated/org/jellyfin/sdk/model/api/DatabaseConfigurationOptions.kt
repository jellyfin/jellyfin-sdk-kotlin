// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Options to configure jellyfins managed database.
 */
@Serializable
public data class DatabaseConfigurationOptions(
	/**
	 * Or Sets the type of database jellyfin should use.
	 */
	@SerialName("DatabaseType")
	public val databaseType: String,
)
