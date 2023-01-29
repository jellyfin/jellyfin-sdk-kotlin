// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class MediaSourceType(
	public val serialName: String,
) {
	@SerialName("Default")
	DEFAULT("Default"),
	@SerialName("Grouping")
	GROUPING("Grouping"),
	@SerialName("Placeholder")
	PLACEHOLDER("Placeholder"),
	;

	public override fun toString(): String = serialName
}
