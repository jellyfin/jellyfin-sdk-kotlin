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
 * Enum HardwareEncodingType.
 */
@Serializable
public enum class HardwareEncodingType(
	public val serialName: String,
) {
	@SerialName("AMF")
	AMF("AMF"),
	@SerialName("QSV")
	QSV("QSV"),
	@SerialName("NVENC")
	NVENC("NVENC"),
	@SerialName("V4L2M2M")
	V4L2M2M("V4L2M2M"),
	@SerialName("VAAPI")
	VAAPI("VAAPI"),
	@SerialName("VideoToolBox")
	VIDEO_TOOL_BOX("VideoToolBox"),
	;

	public override fun toString(): String = serialName

	public companion object {
		public fun fromNameOrNull(serialName: String): HardwareEncodingType? = when (serialName) {
			"AMF" -> AMF
			"QSV" -> QSV
			"NVENC" -> NVENC
			"V4L2M2M" -> V4L2M2M
			"VAAPI" -> VAAPI
			"VideoToolBox" -> VIDEO_TOOL_BOX
			else -> null
		}

		public fun fromName(serialName: String): HardwareEncodingType =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
