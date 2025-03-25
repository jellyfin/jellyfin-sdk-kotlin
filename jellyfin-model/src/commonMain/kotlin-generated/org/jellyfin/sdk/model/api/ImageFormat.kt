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
 * Enum ImageOutputFormat.
 */
@Serializable
public enum class ImageFormat(
	public val serialName: String,
) {
	@SerialName("Bmp")
	BMP("Bmp"),
	@SerialName("Gif")
	GIF("Gif"),
	@SerialName("Jpg")
	JPG("Jpg"),
	@SerialName("Png")
	PNG("Png"),
	@SerialName("Webp")
	WEBP("Webp"),
	@SerialName("Svg")
	SVG("Svg"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): ImageFormat? = when (serialName) {
			"Bmp" -> BMP
			"Gif" -> GIF
			"Jpg" -> JPG
			"Png" -> PNG
			"Webp" -> WEBP
			"Svg" -> SVG
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): ImageFormat = requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
