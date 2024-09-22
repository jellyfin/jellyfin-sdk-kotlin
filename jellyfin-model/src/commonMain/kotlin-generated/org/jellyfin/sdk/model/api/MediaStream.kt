// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class MediaStream.
 */
@Serializable
public data class MediaStream(
	/**
	 * The codec.
	 */
	@SerialName("Codec")
	public val codec: String? = null,
	/**
	 * The codec tag.
	 */
	@SerialName("CodecTag")
	public val codecTag: String? = null,
	/**
	 * The language.
	 */
	@SerialName("Language")
	public val language: String? = null,
	/**
	 * The color range.
	 */
	@SerialName("ColorRange")
	public val colorRange: String? = null,
	/**
	 * The color space.
	 */
	@SerialName("ColorSpace")
	public val colorSpace: String? = null,
	/**
	 * The color transfer.
	 */
	@SerialName("ColorTransfer")
	public val colorTransfer: String? = null,
	/**
	 * The color primaries.
	 */
	@SerialName("ColorPrimaries")
	public val colorPrimaries: String? = null,
	/**
	 * The Dolby Vision version major.
	 */
	@SerialName("DvVersionMajor")
	public val dvVersionMajor: Int? = null,
	/**
	 * The Dolby Vision version minor.
	 */
	@SerialName("DvVersionMinor")
	public val dvVersionMinor: Int? = null,
	/**
	 * The Dolby Vision profile.
	 */
	@SerialName("DvProfile")
	public val dvProfile: Int? = null,
	/**
	 * The Dolby Vision level.
	 */
	@SerialName("DvLevel")
	public val dvLevel: Int? = null,
	/**
	 * The Dolby Vision rpu present flag.
	 */
	@SerialName("RpuPresentFlag")
	public val rpuPresentFlag: Int? = null,
	/**
	 * The Dolby Vision el present flag.
	 */
	@SerialName("ElPresentFlag")
	public val elPresentFlag: Int? = null,
	/**
	 * The Dolby Vision bl present flag.
	 */
	@SerialName("BlPresentFlag")
	public val blPresentFlag: Int? = null,
	/**
	 * The Dolby Vision bl signal compatibility id.
	 */
	@SerialName("DvBlSignalCompatibilityId")
	public val dvBlSignalCompatibilityId: Int? = null,
	/**
	 * The Rotation in degrees.
	 */
	@SerialName("Rotation")
	public val rotation: Int? = null,
	/**
	 * The comment.
	 */
	@SerialName("Comment")
	public val comment: String? = null,
	/**
	 * The time base.
	 */
	@SerialName("TimeBase")
	public val timeBase: String? = null,
	/**
	 * The codec time base.
	 */
	@SerialName("CodecTimeBase")
	public val codecTimeBase: String? = null,
	/**
	 * The title.
	 */
	@SerialName("Title")
	public val title: String? = null,
	/**
	 * The video range.
	 */
	@SerialName("VideoRange")
	public val videoRange: VideoRange,
	/**
	 * The video range type.
	 */
	@SerialName("VideoRangeType")
	public val videoRangeType: VideoRangeType,
	/**
	 * The video dovi title.
	 */
	@SerialName("VideoDoViTitle")
	public val videoDoViTitle: String? = null,
	@SerialName("LocalizedUndefined")
	public val localizedUndefined: String? = null,
	@SerialName("LocalizedDefault")
	public val localizedDefault: String? = null,
	@SerialName("LocalizedForced")
	public val localizedForced: String? = null,
	@SerialName("LocalizedExternal")
	public val localizedExternal: String? = null,
	@SerialName("LocalizedHearingImpaired")
	public val localizedHearingImpaired: String? = null,
	@SerialName("DisplayTitle")
	public val displayTitle: String? = null,
	@SerialName("NalLengthSize")
	public val nalLengthSize: String? = null,
	/**
	 * A value indicating whether this instance is interlaced.
	 */
	@SerialName("IsInterlaced")
	public val isInterlaced: Boolean,
	@SerialName("IsAVC")
	public val isAvc: Boolean? = null,
	/**
	 * The channel layout.
	 */
	@SerialName("ChannelLayout")
	public val channelLayout: String? = null,
	/**
	 * The bit rate.
	 */
	@SerialName("BitRate")
	public val bitRate: Int? = null,
	/**
	 * The bit depth.
	 */
	@SerialName("BitDepth")
	public val bitDepth: Int? = null,
	/**
	 * The reference frames.
	 */
	@SerialName("RefFrames")
	public val refFrames: Int? = null,
	/**
	 * The length of the packet.
	 */
	@SerialName("PacketLength")
	public val packetLength: Int? = null,
	/**
	 * The channels.
	 */
	@SerialName("Channels")
	public val channels: Int? = null,
	/**
	 * The sample rate.
	 */
	@SerialName("SampleRate")
	public val sampleRate: Int? = null,
	/**
	 * A value indicating whether this instance is default.
	 */
	@SerialName("IsDefault")
	public val isDefault: Boolean,
	/**
	 * A value indicating whether this instance is forced.
	 */
	@SerialName("IsForced")
	public val isForced: Boolean,
	/**
	 * A value indicating whether this instance is for the hearing impaired.
	 */
	@SerialName("IsHearingImpaired")
	public val isHearingImpaired: Boolean,
	/**
	 * The height.
	 */
	@SerialName("Height")
	public val height: Int? = null,
	/**
	 * The width.
	 */
	@SerialName("Width")
	public val width: Int? = null,
	/**
	 * The average frame rate.
	 */
	@SerialName("AverageFrameRate")
	public val averageFrameRate: Float? = null,
	/**
	 * The real frame rate.
	 */
	@SerialName("RealFrameRate")
	public val realFrameRate: Float? = null,
	/**
	 * Gets the framerate used as reference.
	 * Prefer AverageFrameRate, if that is null or an unrealistic value
	 * then fallback to RealFrameRate.
	 */
	@SerialName("ReferenceFrameRate")
	public val referenceFrameRate: Float? = null,
	/**
	 * The profile.
	 */
	@SerialName("Profile")
	public val profile: String? = null,
	/**
	 * The type.
	 */
	@SerialName("Type")
	public val type: MediaStreamType,
	/**
	 * The aspect ratio.
	 */
	@SerialName("AspectRatio")
	public val aspectRatio: String? = null,
	/**
	 * The index.
	 */
	@SerialName("Index")
	public val index: Int,
	/**
	 * The score.
	 */
	@SerialName("Score")
	public val score: Int? = null,
	/**
	 * A value indicating whether this instance is external.
	 */
	@SerialName("IsExternal")
	public val isExternal: Boolean,
	/**
	 * The method.
	 */
	@SerialName("DeliveryMethod")
	public val deliveryMethod: SubtitleDeliveryMethod? = null,
	/**
	 * The delivery URL.
	 */
	@SerialName("DeliveryUrl")
	public val deliveryUrl: String? = null,
	/**
	 * A value indicating whether this instance is external URL.
	 */
	@SerialName("IsExternalUrl")
	public val isExternalUrl: Boolean? = null,
	@SerialName("IsTextSubtitleStream")
	public val isTextSubtitleStream: Boolean,
	/**
	 * A value indicating whether [supports external stream].
	 */
	@SerialName("SupportsExternalStream")
	public val supportsExternalStream: Boolean,
	/**
	 * The filename.
	 */
	@SerialName("Path")
	public val path: String? = null,
	/**
	 * The pixel format.
	 */
	@SerialName("PixelFormat")
	public val pixelFormat: String? = null,
	/**
	 * The level.
	 */
	@SerialName("Level")
	public val level: Double? = null,
	/**
	 * Whether this instance is anamorphic.
	 */
	@SerialName("IsAnamorphic")
	public val isAnamorphic: Boolean? = null,
) {
	/**
	 * The audio spatial format.
	 */
	@SerialName("AudioSpatialFormat")
	public val audioSpatialFormat: AudioSpatialFormat = AudioSpatialFormat.NONE
}
