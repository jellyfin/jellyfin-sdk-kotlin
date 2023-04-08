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
public enum class RepeatMode(
	public val serialName: String,
) {
	@SerialName("RepeatNone")
	REPEAT_NONE("RepeatNone"),
	@SerialName("RepeatAll")
	REPEAT_ALL("RepeatAll"),
	@SerialName("RepeatOne")
	REPEAT_ONE("RepeatOne"),
	;

	public override fun toString(): String = serialName

	public companion object {
		public fun fromNameOrNull(serialName: String): RepeatMode? = when (serialName) {
			"RepeatNone" -> REPEAT_NONE
			"RepeatAll" -> REPEAT_ALL
			"RepeatOne" -> REPEAT_ONE
			else -> null
		}

		public fun fromName(serialName: String): RepeatMode =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
