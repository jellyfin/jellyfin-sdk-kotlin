// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.EncodingContext
import org.jellyfin.sdk.model.api.SubtitleDeliveryMethod
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * A video hls playlist stream.
 */
@Serializable
public data class GetMasterHlsVideoPlaylistRequest(
	/**
	 * The item id.
	 */
	@SerialName("itemId")
	public val itemId: UUID,
	/**
	 * Optional. If true, the original file will be streamed statically without any encoding. Use
	 * either no url extension or the original file extension. true/false.
	 */
	@SerialName("static")
	public val static: Boolean? = null,
	/**
	 * The streaming parameters.
	 */
	@SerialName("params")
	public val params: String? = null,
	/**
	 * The tag.
	 */
	@SerialName("tag")
	public val tag: String? = null,
	/**
	 * The play session id.
	 */
	@SerialName("playSessionId")
	public val playSessionId: String? = null,
	/**
	 * The segment container.
	 */
	@SerialName("segmentContainer")
	public val segmentContainer: String? = null,
	/**
	 * The segment length.
	 */
	@SerialName("segmentLength")
	public val segmentLength: Int? = null,
	/**
	 * The minimum number of segments.
	 */
	@SerialName("minSegments")
	public val minSegments: Int? = null,
	/**
	 * The media version id, if playing an alternate version.
	 */
	@SerialName("mediaSourceId")
	public val mediaSourceId: String,
	/**
	 * The device id of the client requesting. Used to stop encoding processes when needed.
	 */
	@SerialName("deviceId")
	public val deviceId: String? = null,
	/**
	 * Optional. Specify an audio codec to encode to, e.g. mp3.
	 */
	@SerialName("audioCodec")
	public val audioCodec: String? = null,
	/**
	 * Whether or not to allow automatic stream copy if requested values match the original source.
	 * Defaults to true.
	 */
	@SerialName("enableAutoStreamCopy")
	public val enableAutoStreamCopy: Boolean? = null,
	/**
	 * Whether or not to allow copying of the video stream url.
	 */
	@SerialName("allowVideoStreamCopy")
	public val allowVideoStreamCopy: Boolean? = null,
	/**
	 * Whether or not to allow copying of the audio stream url.
	 */
	@SerialName("allowAudioStreamCopy")
	public val allowAudioStreamCopy: Boolean? = null,
	/**
	 * Optional. Whether to break on non key frames.
	 */
	@SerialName("breakOnNonKeyFrames")
	public val breakOnNonKeyFrames: Boolean? = null,
	/**
	 * Optional. Specify a specific audio sample rate, e.g. 44100.
	 */
	@SerialName("audioSampleRate")
	public val audioSampleRate: Int? = null,
	/**
	 * Optional. The maximum audio bit depth.
	 */
	@SerialName("maxAudioBitDepth")
	public val maxAudioBitDepth: Int? = null,
	/**
	 * Optional. Specify an audio bitrate to encode to, e.g. 128000. If omitted this will be left to
	 * encoder defaults.
	 */
	@SerialName("audioBitRate")
	public val audioBitRate: Int? = null,
	/**
	 * Optional. Specify a specific number of audio channels to encode to, e.g. 2.
	 */
	@SerialName("audioChannels")
	public val audioChannels: Int? = null,
	/**
	 * Optional. Specify a maximum number of audio channels to encode to, e.g. 2.
	 */
	@SerialName("maxAudioChannels")
	public val maxAudioChannels: Int? = null,
	/**
	 * Optional. Specify a specific an encoder profile (varies by encoder), e.g. main, baseline, high.
	 */
	@SerialName("profile")
	public val profile: String? = null,
	/**
	 * Optional. Specify a level for the encoder profile (varies by encoder), e.g. 3, 3.1.
	 */
	@SerialName("level")
	public val level: String? = null,
	/**
	 * Optional. A specific video framerate to encode to, e.g. 23.976. Generally this should be omitted
	 * unless the device has specific requirements.
	 */
	@SerialName("framerate")
	public val framerate: Float? = null,
	/**
	 * Optional. A specific maximum video framerate to encode to, e.g. 23.976. Generally this should be
	 * omitted unless the device has specific requirements.
	 */
	@SerialName("maxFramerate")
	public val maxFramerate: Float? = null,
	/**
	 * Whether or not to copy timestamps when transcoding with an offset. Defaults to false.
	 */
	@SerialName("copyTimestamps")
	public val copyTimestamps: Boolean? = null,
	/**
	 * Optional. Specify a starting offset, in ticks. 1 tick = 10000 ms.
	 */
	@SerialName("startTimeTicks")
	public val startTimeTicks: Long? = null,
	/**
	 * Optional. The fixed horizontal resolution of the encoded video.
	 */
	@SerialName("width")
	public val width: Int? = null,
	/**
	 * Optional. The fixed vertical resolution of the encoded video.
	 */
	@SerialName("height")
	public val height: Int? = null,
	/**
	 * Optional. The maximum horizontal resolution of the encoded video.
	 */
	@SerialName("maxWidth")
	public val maxWidth: Int? = null,
	/**
	 * Optional. The maximum vertical resolution of the encoded video.
	 */
	@SerialName("maxHeight")
	public val maxHeight: Int? = null,
	/**
	 * Optional. Specify a video bitrate to encode to, e.g. 500000. If omitted this will be left to
	 * encoder defaults.
	 */
	@SerialName("videoBitRate")
	public val videoBitRate: Int? = null,
	/**
	 * Optional. The index of the subtitle stream to use. If omitted no subtitles will be used.
	 */
	@SerialName("subtitleStreamIndex")
	public val subtitleStreamIndex: Int? = null,
	/**
	 * Optional. Specify the subtitle delivery method.
	 */
	@SerialName("subtitleMethod")
	public val subtitleMethod: SubtitleDeliveryMethod? = null,
	/**
	 * Optional.
	 */
	@SerialName("maxRefFrames")
	public val maxRefFrames: Int? = null,
	/**
	 * Optional. The maximum video bit depth.
	 */
	@SerialName("maxVideoBitDepth")
	public val maxVideoBitDepth: Int? = null,
	/**
	 * Optional. Whether to require avc.
	 */
	@SerialName("requireAvc")
	public val requireAvc: Boolean? = null,
	/**
	 * Optional. Whether to deinterlace the video.
	 */
	@SerialName("deInterlace")
	public val deInterlace: Boolean? = null,
	/**
	 * Optional. Whether to require a non anamorphic stream.
	 */
	@SerialName("requireNonAnamorphic")
	public val requireNonAnamorphic: Boolean? = null,
	/**
	 * Optional. The maximum number of audio channels to transcode.
	 */
	@SerialName("transcodingMaxAudioChannels")
	public val transcodingMaxAudioChannels: Int? = null,
	/**
	 * Optional. The limit of how many cpu cores to use.
	 */
	@SerialName("cpuCoreLimit")
	public val cpuCoreLimit: Int? = null,
	/**
	 * The live stream id.
	 */
	@SerialName("liveStreamId")
	public val liveStreamId: String? = null,
	/**
	 * Optional. Whether to enable the MpegtsM2Ts mode.
	 */
	@SerialName("enableMpegtsM2TsMode")
	public val enableMpegtsM2TsMode: Boolean? = null,
	/**
	 * Optional. Specify a video codec to encode to, e.g. h264.
	 */
	@SerialName("videoCodec")
	public val videoCodec: String? = null,
	/**
	 * Optional. Specify a subtitle codec to encode to.
	 */
	@SerialName("subtitleCodec")
	public val subtitleCodec: String? = null,
	/**
	 * Optional. The transcoding reason.
	 */
	@SerialName("transcodeReasons")
	public val transcodeReasons: String? = null,
	/**
	 * Optional. The index of the audio stream to use. If omitted the first audio stream will be used.
	 */
	@SerialName("audioStreamIndex")
	public val audioStreamIndex: Int? = null,
	/**
	 * Optional. The index of the video stream to use. If omitted the first video stream will be used.
	 */
	@SerialName("videoStreamIndex")
	public val videoStreamIndex: Int? = null,
	/**
	 * Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 */
	@SerialName("context")
	public val context: EncodingContext? = null,
	/**
	 * Optional. The streaming options.
	 */
	@SerialName("streamOptions")
	public val streamOptions: Map<String, String?>? = null,
	/**
	 * Enable adaptive bitrate streaming.
	 */
	@SerialName("enableAdaptiveBitrateStreaming")
	public val enableAdaptiveBitrateStreaming: Boolean? = true,
	/**
	 * Enable trickplay image playlists being added to master playlist.
	 */
	@SerialName("enableTrickplay")
	public val enableTrickplay: Boolean? = true,
	/**
	 * Whether to enable Audio Encoding.
	 */
	@SerialName("enableAudioVbrEncoding")
	public val enableAudioVbrEncoding: Boolean? = true,
	/**
	 * Whether to always burn in subtitles when transcoding.
	 */
	@SerialName("alwaysBurnInSubtitleWhenTranscoding")
	public val alwaysBurnInSubtitleWhenTranscoding: Boolean? = false,
)
