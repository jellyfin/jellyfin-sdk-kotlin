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
 * Defines the types of content an individual Jellyfin.Database.Implementations.Entities.MediaSegment represents.
 */
@Serializable
public enum class MediaSegmentType(
	public val serialName: String,
) {
	@SerialName("Unknown")
	UNKNOWN("Unknown"),
	@SerialName("Commercial")
	COMMERCIAL("Commercial"),
	@SerialName("Preview")
	PREVIEW("Preview"),
	@SerialName("Recap")
	RECAP("Recap"),
	@SerialName("Outro")
	OUTRO("Outro"),
	@SerialName("Intro")
	INTRO("Intro"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): MediaSegmentType? = when (serialName) {
			"Unknown" -> UNKNOWN
			"Commercial" -> COMMERCIAL
			"Preview" -> PREVIEW
			"Recap" -> RECAP
			"Outro" -> OUTRO
			"Intro" -> INTRO
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): MediaSegmentType = requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
