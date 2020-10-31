// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class PackageVersionInfo.
 */
@Serializable
public data class VersionInfo(
	/**
	 * Gets or sets the version.
	 */
	@SerialName("version")
	public val version: String? = null,
	/**
	 * Gets or sets the changelog for this version.
	 */
	@SerialName("changelog")
	public val changelog: String? = null,
	/**
	 * Gets or sets the ABI that this version was built against.
	 */
	@SerialName("targetAbi")
	public val targetAbi: String? = null,
	/**
	 * Gets or sets the source URL.
	 */
	@SerialName("sourceUrl")
	public val sourceUrl: String? = null,
	/**
	 * Gets or sets a checksum for the binary.
	 */
	@SerialName("checksum")
	public val checksum: String? = null,
	/**
	 * Gets or sets a timestamp of when the binary was built.
	 */
	@SerialName("timestamp")
	public val timestamp: String? = null
)
