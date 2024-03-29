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
 * An enum representing an algorithm to downmix 6ch+ to stereo.
 * Algorithms sourced from
 * https://superuser.com/questions/852400/properly-downmix-5-1-to-stereo-using-ffmpeg/1410620#1410620.
 */
@Serializable
public enum class DownMixStereoAlgorithms(
	public val serialName: String,
) {
	@SerialName("None")
	NONE("None"),
	@SerialName("Dave750")
	DAVE_750("Dave750"),
	@SerialName("NightmodeDialogue")
	NIGHTMODE_DIALOGUE("NightmodeDialogue"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): DownMixStereoAlgorithms? = when (serialName) {
			"None" -> NONE
			"Dave750" -> DAVE_750
			"NightmodeDialogue" -> NIGHTMODE_DIALOGUE
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): DownMixStereoAlgorithms =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
