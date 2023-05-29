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
public enum class ProfileConditionValue(
	public val serialName: String,
) {
	@SerialName("AudioChannels")
	AUDIO_CHANNELS("AudioChannels"),
	@SerialName("AudioBitrate")
	AUDIO_BITRATE("AudioBitrate"),
	@SerialName("AudioProfile")
	AUDIO_PROFILE("AudioProfile"),
	@SerialName("Width")
	WIDTH("Width"),
	@SerialName("Height")
	HEIGHT("Height"),
	@SerialName("Has64BitOffsets")
	HAS_64_BIT_OFFSETS("Has64BitOffsets"),
	@SerialName("PacketLength")
	PACKET_LENGTH("PacketLength"),
	@SerialName("VideoBitDepth")
	VIDEO_BIT_DEPTH("VideoBitDepth"),
	@SerialName("VideoBitrate")
	VIDEO_BITRATE("VideoBitrate"),
	@SerialName("VideoFramerate")
	VIDEO_FRAMERATE("VideoFramerate"),
	@SerialName("VideoLevel")
	VIDEO_LEVEL("VideoLevel"),
	@SerialName("VideoProfile")
	VIDEO_PROFILE("VideoProfile"),
	@SerialName("VideoTimestamp")
	VIDEO_TIMESTAMP("VideoTimestamp"),
	@SerialName("IsAnamorphic")
	IS_ANAMORPHIC("IsAnamorphic"),
	@SerialName("RefFrames")
	REF_FRAMES("RefFrames"),
	@SerialName("NumAudioStreams")
	NUM_AUDIO_STREAMS("NumAudioStreams"),
	@SerialName("NumVideoStreams")
	NUM_VIDEO_STREAMS("NumVideoStreams"),
	@SerialName("IsSecondaryAudio")
	IS_SECONDARY_AUDIO("IsSecondaryAudio"),
	@SerialName("VideoCodecTag")
	VIDEO_CODEC_TAG("VideoCodecTag"),
	@SerialName("IsAvc")
	IS_AVC("IsAvc"),
	@SerialName("IsInterlaced")
	IS_INTERLACED("IsInterlaced"),
	@SerialName("AudioSampleRate")
	AUDIO_SAMPLE_RATE("AudioSampleRate"),
	@SerialName("AudioBitDepth")
	AUDIO_BIT_DEPTH("AudioBitDepth"),
	@SerialName("VideoRangeType")
	VIDEO_RANGE_TYPE("VideoRangeType"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): ProfileConditionValue? = when (serialName) {
			"AudioChannels" -> AUDIO_CHANNELS
			"AudioBitrate" -> AUDIO_BITRATE
			"AudioProfile" -> AUDIO_PROFILE
			"Width" -> WIDTH
			"Height" -> HEIGHT
			"Has64BitOffsets" -> HAS_64_BIT_OFFSETS
			"PacketLength" -> PACKET_LENGTH
			"VideoBitDepth" -> VIDEO_BIT_DEPTH
			"VideoBitrate" -> VIDEO_BITRATE
			"VideoFramerate" -> VIDEO_FRAMERATE
			"VideoLevel" -> VIDEO_LEVEL
			"VideoProfile" -> VIDEO_PROFILE
			"VideoTimestamp" -> VIDEO_TIMESTAMP
			"IsAnamorphic" -> IS_ANAMORPHIC
			"RefFrames" -> REF_FRAMES
			"NumAudioStreams" -> NUM_AUDIO_STREAMS
			"NumVideoStreams" -> NUM_VIDEO_STREAMS
			"IsSecondaryAudio" -> IS_SECONDARY_AUDIO
			"VideoCodecTag" -> VIDEO_CODEC_TAG
			"IsAvc" -> IS_AVC
			"IsInterlaced" -> IS_INTERLACED
			"AudioSampleRate" -> AUDIO_SAMPLE_RATE
			"AudioBitDepth" -> AUDIO_BIT_DEPTH
			"VideoRangeType" -> VIDEO_RANGE_TYPE
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): ProfileConditionValue =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
