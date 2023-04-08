// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlin.requireNotNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class NotificationLevel(
	public val serialName: String,
) {
	@SerialName("Normal")
	NORMAL("Normal"),
	@SerialName("Warning")
	WARNING("Warning"),
	@SerialName("Error")
	ERROR("Error"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	public override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): NotificationLevel? = when (serialName) {
			"Normal" -> NORMAL
			"Warning" -> WARNING
			"Error" -> ERROR
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): NotificationLevel =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
