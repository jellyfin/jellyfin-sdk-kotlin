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
 * Class ServiceInfo.
 */
@Serializable
public data class LiveTvServiceInfo(
	/**
	 * The name.
	 */
	@SerialName("Name")
	public val name: String? = null,
	/**
	 * The home page URL.
	 */
	@SerialName("HomePageUrl")
	public val homePageUrl: String? = null,
	/**
	 * The status.
	 */
	@SerialName("Status")
	public val status: LiveTvServiceStatus,
	/**
	 * The status message.
	 */
	@SerialName("StatusMessage")
	public val statusMessage: String? = null,
	/**
	 * The version.
	 */
	@SerialName("Version")
	public val version: String? = null,
	/**
	 * A value indicating whether this instance has update available.
	 */
	@SerialName("HasUpdateAvailable")
	public val hasUpdateAvailable: Boolean,
	/**
	 * A value indicating whether this instance is visible.
	 */
	@SerialName("IsVisible")
	public val isVisible: Boolean,
	@SerialName("Tuners")
	public val tuners: List<String>? = null,
)
