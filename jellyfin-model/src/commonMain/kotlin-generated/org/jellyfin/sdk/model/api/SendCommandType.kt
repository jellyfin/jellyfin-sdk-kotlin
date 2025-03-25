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

/**
 * Enum SendCommandType.
 */
@Serializable
public enum class SendCommandType(
	public val serialName: String,
) {
	@SerialName("Unpause")
	UNPAUSE("Unpause"),
	@SerialName("Pause")
	PAUSE("Pause"),
	@SerialName("Stop")
	STOP("Stop"),
	@SerialName("Seek")
	SEEK("Seek"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): SendCommandType? = when (serialName) {
			"Unpause" -> UNPAUSE
			"Pause" -> PAUSE
			"Stop" -> STOP
			"Seek" -> SEEK
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): SendCommandType = requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
