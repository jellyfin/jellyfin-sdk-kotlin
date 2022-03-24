// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ProfileCondition(
	@SerialName("Condition")
	public val condition: ProfileConditionType,
	@SerialName("Property")
	public val `property`: ProfileConditionValue,
	@SerialName("Value")
	public val `value`: String? = null,
	@SerialName("IsRequired")
	public val isRequired: Boolean,
)
