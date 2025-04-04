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
 * Enum ImageResolution.
 */
@Serializable
public enum class ImageResolution(
	public val serialName: String,
) {
	@SerialName("MatchSource")
	MATCH_SOURCE("MatchSource"),
	@SerialName("P144")
	P144("P144"),
	@SerialName("P240")
	P240("P240"),
	@SerialName("P360")
	P360("P360"),
	@SerialName("P480")
	P480("P480"),
	@SerialName("P720")
	P720("P720"),
	@SerialName("P1080")
	P1080("P1080"),
	@SerialName("P1440")
	P1440("P1440"),
	@SerialName("P2160")
	P2160("P2160"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): ImageResolution? = when (serialName) {
			"MatchSource" -> MATCH_SOURCE
			"P144" -> P144
			"P240" -> P240
			"P360" -> P360
			"P480" -> P480
			"P720" -> P720
			"P1080" -> P1080
			"P1440" -> P1440
			"P2160" -> P2160
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): ImageResolution = requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
