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
 * A class for subtitle profile information.
 */
@Serializable
public data class SubtitleProfile(
	/**
	 * The format.
	 */
	@SerialName("Format")
	public val format: String? = null,
	/**
	 * The delivery method.
	 */
	@SerialName("Method")
	public val method: SubtitleDeliveryMethod,
	/**
	 * The DIDL mode.
	 */
	@SerialName("DidlMode")
	public val didlMode: String? = null,
	/**
	 * The language.
	 */
	@SerialName("Language")
	public val language: String? = null,
	/**
	 * The container.
	 */
	@SerialName("Container")
	public val container: String? = null,
)
