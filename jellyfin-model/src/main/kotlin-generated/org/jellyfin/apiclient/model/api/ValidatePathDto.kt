// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Validate path object.
 */
@Serializable
public data class ValidatePathDto(
	/**
	 * Gets or sets a value indicating whether validate if path is writable.
	 */
	@SerialName("ValidateWritable")
	public val validateWritable: Boolean,
	/**
	 * Gets or sets the path.
	 */
	@SerialName("Path")
	public val path: String? = null,
	/**
	 * Gets or sets is path file.
	 */
	@SerialName("IsFile")
	public val isFile: Boolean? = null
)
