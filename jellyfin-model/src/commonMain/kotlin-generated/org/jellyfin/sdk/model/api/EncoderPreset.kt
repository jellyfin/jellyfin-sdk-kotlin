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
 * Enum containing encoder presets.
 */
@Serializable
public enum class EncoderPreset(
	public val serialName: String,
) {
	@SerialName("auto")
	AUTO("auto"),
	@SerialName("placebo")
	PLACEBO("placebo"),
	@SerialName("veryslow")
	VERYSLOW("veryslow"),
	@SerialName("slower")
	SLOWER("slower"),
	@SerialName("slow")
	SLOW("slow"),
	@SerialName("medium")
	MEDIUM("medium"),
	@SerialName("fast")
	FAST("fast"),
	@SerialName("faster")
	FASTER("faster"),
	@SerialName("veryfast")
	VERYFAST("veryfast"),
	@SerialName("superfast")
	SUPERFAST("superfast"),
	@SerialName("ultrafast")
	ULTRAFAST("ultrafast"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): EncoderPreset? = when (serialName) {
			"auto" -> AUTO
			"placebo" -> PLACEBO
			"veryslow" -> VERYSLOW
			"slower" -> SLOWER
			"slow" -> SLOW
			"medium" -> MEDIUM
			"fast" -> FAST
			"faster" -> FASTER
			"veryfast" -> VERYFAST
			"superfast" -> SUPERFAST
			"ultrafast" -> ULTRAFAST
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): EncoderPreset = requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
