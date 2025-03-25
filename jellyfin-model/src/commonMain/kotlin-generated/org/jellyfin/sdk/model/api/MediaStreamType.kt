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
 * Enum MediaStreamType.
 */
@Serializable
public enum class MediaStreamType(
	public val serialName: String,
) {
	@SerialName("Audio")
	AUDIO("Audio"),
	@SerialName("Video")
	VIDEO("Video"),
	@SerialName("Subtitle")
	SUBTITLE("Subtitle"),
	@SerialName("EmbeddedImage")
	EMBEDDED_IMAGE("EmbeddedImage"),
	@SerialName("Data")
	DATA("Data"),
	@SerialName("Lyric")
	LYRIC("Lyric"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): MediaStreamType? = when (serialName) {
			"Audio" -> AUDIO
			"Video" -> VIDEO
			"Subtitle" -> SUBTITLE
			"EmbeddedImage" -> EMBEDDED_IMAGE
			"Data" -> DATA
			"Lyric" -> LYRIC
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): MediaStreamType = requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
