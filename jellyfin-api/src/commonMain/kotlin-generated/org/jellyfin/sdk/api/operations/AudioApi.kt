// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import io.ktor.utils.io.ByteReadChannel
import kotlin.Any
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.buildMap
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.EncodingContext
import org.jellyfin.sdk.model.api.SubtitleDeliveryMethod
import org.jellyfin.sdk.model.api.request.GetAudioStreamByContainerRequest
import org.jellyfin.sdk.model.api.request.GetAudioStreamRequest

public class AudioApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Gets an audio stream.
	 *
	 * @param itemId The item id.
	 * @param container The audio container.
	 * @param static Optional. If true, the original file will be streamed statically without any
	 * encoding. Use either no url extension or the original file extension. true/false.
	 * @param params The streaming parameters.
	 * @param tag The tag.
	 * @param deviceProfileId Optional. The dlna device profile id to utilize.
	 * @param playSessionId The play session id.
	 * @param segmentContainer The segment container.
	 * @param segmentLength The segment length.
	 * @param minSegments The minimum number of segments.
	 * @param mediaSourceId The media version id, if playing an alternate version.
	 * @param deviceId The device id of the client requesting. Used to stop encoding processes when
	 * needed.
	 * @param audioCodec Optional. Specify a audio codec to encode to, e.g. mp3. If omitted the server
	 * will auto-select using the url's extension. Options: aac, mp3, vorbis, wma.
	 * @param enableAutoStreamCopy Whether or not to allow automatic stream copy if requested values
	 * match the original source. Defaults to true.
	 * @param allowVideoStreamCopy Whether or not to allow copying of the video stream url.
	 * @param allowAudioStreamCopy Whether or not to allow copying of the audio stream url.
	 * @param breakOnNonKeyFrames Optional. Whether to break on non key frames.
	 * @param audioSampleRate Optional. Specify a specific audio sample rate, e.g. 44100.
	 * @param maxAudioBitDepth Optional. The maximum audio bit depth.
	 * @param audioBitRate Optional. Specify an audio bitrate to encode to, e.g. 128000. If omitted
	 * this will be left to encoder defaults.
	 * @param audioChannels Optional. Specify a specific number of audio channels to encode to, e.g. 2.
	 * @param maxAudioChannels Optional. Specify a maximum number of audio channels to encode to, e.g.
	 * 2.
	 * @param profile Optional. Specify a specific an encoder profile (varies by encoder), e.g. main,
	 * baseline, high.
	 * @param level Optional. Specify a level for the encoder profile (varies by encoder), e.g. 3, 3.1.
	 * @param framerate Optional. A specific video framerate to encode to, e.g. 23.976. Generally this
	 * should be omitted unless the device has specific requirements.
	 * @param maxFramerate Optional. A specific maximum video framerate to encode to, e.g. 23.976.
	 * Generally this should be omitted unless the device has specific requirements.
	 * @param copyTimestamps Whether or not to copy timestamps when transcoding with an offset.
	 * Defaults to false.
	 * @param startTimeTicks Optional. Specify a starting offset, in ticks. 1 tick = 10000 ms.
	 * @param width Optional. The fixed horizontal resolution of the encoded video.
	 * @param height Optional. The fixed vertical resolution of the encoded video.
	 * @param videoBitRate Optional. Specify a video bitrate to encode to, e.g. 500000. If omitted this
	 * will be left to encoder defaults.
	 * @param subtitleStreamIndex Optional. The index of the subtitle stream to use. If omitted no
	 * subtitles will be used.
	 * @param subtitleMethod Optional. Specify the subtitle delivery method.
	 * @param maxRefFrames Optional.
	 * @param maxVideoBitDepth Optional. The maximum video bit depth.
	 * @param requireAvc Optional. Whether to require avc.
	 * @param deInterlace Optional. Whether to deinterlace the video.
	 * @param requireNonAnamorphic Optional. Whether to require a non anamorphic stream.
	 * @param transcodingMaxAudioChannels Optional. The maximum number of audio channels to transcode.
	 * @param cpuCoreLimit Optional. The limit of how many cpu cores to use.
	 * @param liveStreamId The live stream id.
	 * @param enableMpegtsM2TsMode Optional. Whether to enable the MpegtsM2Ts mode.
	 * @param videoCodec Optional. Specify a video codec to encode to, e.g. h264. If omitted the server
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vp8, vp9, vpx
	 * (deprecated), wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodeReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 */
	public suspend fun getAudioStream(
		itemId: UUID,
		container: String? = null,
		static: Boolean? = null,
		params: String? = null,
		tag: String? = null,
		deviceProfileId: String? = null,
		playSessionId: String? = null,
		segmentContainer: String? = null,
		segmentLength: Int? = null,
		minSegments: Int? = null,
		mediaSourceId: String? = null,
		deviceId: String? = null,
		audioCodec: String? = null,
		enableAutoStreamCopy: Boolean? = null,
		allowVideoStreamCopy: Boolean? = null,
		allowAudioStreamCopy: Boolean? = null,
		breakOnNonKeyFrames: Boolean? = null,
		audioSampleRate: Int? = null,
		maxAudioBitDepth: Int? = null,
		audioBitRate: Int? = null,
		audioChannels: Int? = null,
		maxAudioChannels: Int? = null,
		profile: String? = null,
		level: String? = null,
		framerate: Float? = null,
		maxFramerate: Float? = null,
		copyTimestamps: Boolean? = null,
		startTimeTicks: Long? = null,
		width: Int? = null,
		height: Int? = null,
		videoBitRate: Int? = null,
		subtitleStreamIndex: Int? = null,
		subtitleMethod: SubtitleDeliveryMethod? = null,
		maxRefFrames: Int? = null,
		maxVideoBitDepth: Int? = null,
		requireAvc: Boolean? = null,
		deInterlace: Boolean? = null,
		requireNonAnamorphic: Boolean? = null,
		transcodingMaxAudioChannels: Int? = null,
		cpuCoreLimit: Int? = null,
		liveStreamId: String? = null,
		enableMpegtsM2TsMode: Boolean? = null,
		videoCodec: String? = null,
		subtitleCodec: String? = null,
		transcodeReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext? = null,
		streamOptions: Map<String, String?>? = emptyMap(),
	): Response<ByteReadChannel> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(48) {
			put("container", container)
			put("static", static)
			put("params", params)
			put("tag", tag)
			put("deviceProfileId", deviceProfileId)
			put("playSessionId", playSessionId)
			put("segmentContainer", segmentContainer)
			put("segmentLength", segmentLength)
			put("minSegments", minSegments)
			put("mediaSourceId", mediaSourceId)
			put("deviceId", deviceId)
			put("audioCodec", audioCodec)
			put("enableAutoStreamCopy", enableAutoStreamCopy)
			put("allowVideoStreamCopy", allowVideoStreamCopy)
			put("allowAudioStreamCopy", allowAudioStreamCopy)
			put("breakOnNonKeyFrames", breakOnNonKeyFrames)
			put("audioSampleRate", audioSampleRate)
			put("maxAudioBitDepth", maxAudioBitDepth)
			put("audioBitRate", audioBitRate)
			put("audioChannels", audioChannels)
			put("maxAudioChannels", maxAudioChannels)
			put("profile", profile)
			put("level", level)
			put("framerate", framerate)
			put("maxFramerate", maxFramerate)
			put("copyTimestamps", copyTimestamps)
			put("startTimeTicks", startTimeTicks)
			put("width", width)
			put("height", height)
			put("videoBitRate", videoBitRate)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("subtitleMethod", subtitleMethod)
			put("maxRefFrames", maxRefFrames)
			put("maxVideoBitDepth", maxVideoBitDepth)
			put("requireAvc", requireAvc)
			put("deInterlace", deInterlace)
			put("requireNonAnamorphic", requireNonAnamorphic)
			put("transcodingMaxAudioChannels", transcodingMaxAudioChannels)
			put("cpuCoreLimit", cpuCoreLimit)
			put("liveStreamId", liveStreamId)
			put("enableMpegtsM2TsMode", enableMpegtsM2TsMode)
			put("videoCodec", videoCodec)
			put("subtitleCodec", subtitleCodec)
			put("transcodeReasons", transcodeReasons)
			put("audioStreamIndex", audioStreamIndex)
			put("videoStreamIndex", videoStreamIndex)
			put("context", context)
			put("streamOptions", streamOptions)
		}
		val data = null
		val response = api.`get`<ByteReadChannel>("/Audio/{itemId}/stream", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets an audio stream.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getAudioStream(request: GetAudioStreamRequest): Response<ByteReadChannel> =
			getAudioStream(
		itemId = request.itemId,
		container = request.container,
		static = request.static,
		params = request.params,
		tag = request.tag,
		deviceProfileId = request.deviceProfileId,
		playSessionId = request.playSessionId,
		segmentContainer = request.segmentContainer,
		segmentLength = request.segmentLength,
		minSegments = request.minSegments,
		mediaSourceId = request.mediaSourceId,
		deviceId = request.deviceId,
		audioCodec = request.audioCodec,
		enableAutoStreamCopy = request.enableAutoStreamCopy,
		allowVideoStreamCopy = request.allowVideoStreamCopy,
		allowAudioStreamCopy = request.allowAudioStreamCopy,
		breakOnNonKeyFrames = request.breakOnNonKeyFrames,
		audioSampleRate = request.audioSampleRate,
		maxAudioBitDepth = request.maxAudioBitDepth,
		audioBitRate = request.audioBitRate,
		audioChannels = request.audioChannels,
		maxAudioChannels = request.maxAudioChannels,
		profile = request.profile,
		level = request.level,
		framerate = request.framerate,
		maxFramerate = request.maxFramerate,
		copyTimestamps = request.copyTimestamps,
		startTimeTicks = request.startTimeTicks,
		width = request.width,
		height = request.height,
		videoBitRate = request.videoBitRate,
		subtitleStreamIndex = request.subtitleStreamIndex,
		subtitleMethod = request.subtitleMethod,
		maxRefFrames = request.maxRefFrames,
		maxVideoBitDepth = request.maxVideoBitDepth,
		requireAvc = request.requireAvc,
		deInterlace = request.deInterlace,
		requireNonAnamorphic = request.requireNonAnamorphic,
		transcodingMaxAudioChannels = request.transcodingMaxAudioChannels,
		cpuCoreLimit = request.cpuCoreLimit,
		liveStreamId = request.liveStreamId,
		enableMpegtsM2TsMode = request.enableMpegtsM2TsMode,
		videoCodec = request.videoCodec,
		subtitleCodec = request.subtitleCodec,
		transcodeReasons = request.transcodeReasons,
		audioStreamIndex = request.audioStreamIndex,
		videoStreamIndex = request.videoStreamIndex,
		context = request.context,
		streamOptions = request.streamOptions,
	)

	/**
	 * Gets an audio stream.
	 *
	 * @param itemId The item id.
	 * @param container The audio container.
	 * @param static Optional. If true, the original file will be streamed statically without any
	 * encoding. Use either no url extension or the original file extension. true/false.
	 * @param params The streaming parameters.
	 * @param tag The tag.
	 * @param deviceProfileId Optional. The dlna device profile id to utilize.
	 * @param playSessionId The play session id.
	 * @param segmentContainer The segment container.
	 * @param segmentLength The segment length.
	 * @param minSegments The minimum number of segments.
	 * @param mediaSourceId The media version id, if playing an alternate version.
	 * @param deviceId The device id of the client requesting. Used to stop encoding processes when
	 * needed.
	 * @param audioCodec Optional. Specify a audio codec to encode to, e.g. mp3. If omitted the server
	 * will auto-select using the url's extension. Options: aac, mp3, vorbis, wma.
	 * @param enableAutoStreamCopy Whether or not to allow automatic stream copy if requested values
	 * match the original source. Defaults to true.
	 * @param allowVideoStreamCopy Whether or not to allow copying of the video stream url.
	 * @param allowAudioStreamCopy Whether or not to allow copying of the audio stream url.
	 * @param breakOnNonKeyFrames Optional. Whether to break on non key frames.
	 * @param audioSampleRate Optional. Specify a specific audio sample rate, e.g. 44100.
	 * @param maxAudioBitDepth Optional. The maximum audio bit depth.
	 * @param audioBitRate Optional. Specify an audio bitrate to encode to, e.g. 128000. If omitted
	 * this will be left to encoder defaults.
	 * @param audioChannels Optional. Specify a specific number of audio channels to encode to, e.g. 2.
	 * @param maxAudioChannels Optional. Specify a maximum number of audio channels to encode to, e.g.
	 * 2.
	 * @param profile Optional. Specify a specific an encoder profile (varies by encoder), e.g. main,
	 * baseline, high.
	 * @param level Optional. Specify a level for the encoder profile (varies by encoder), e.g. 3, 3.1.
	 * @param framerate Optional. A specific video framerate to encode to, e.g. 23.976. Generally this
	 * should be omitted unless the device has specific requirements.
	 * @param maxFramerate Optional. A specific maximum video framerate to encode to, e.g. 23.976.
	 * Generally this should be omitted unless the device has specific requirements.
	 * @param copyTimestamps Whether or not to copy timestamps when transcoding with an offset.
	 * Defaults to false.
	 * @param startTimeTicks Optional. Specify a starting offset, in ticks. 1 tick = 10000 ms.
	 * @param width Optional. The fixed horizontal resolution of the encoded video.
	 * @param height Optional. The fixed vertical resolution of the encoded video.
	 * @param videoBitRate Optional. Specify a video bitrate to encode to, e.g. 500000. If omitted this
	 * will be left to encoder defaults.
	 * @param subtitleStreamIndex Optional. The index of the subtitle stream to use. If omitted no
	 * subtitles will be used.
	 * @param subtitleMethod Optional. Specify the subtitle delivery method.
	 * @param maxRefFrames Optional.
	 * @param maxVideoBitDepth Optional. The maximum video bit depth.
	 * @param requireAvc Optional. Whether to require avc.
	 * @param deInterlace Optional. Whether to deinterlace the video.
	 * @param requireNonAnamorphic Optional. Whether to require a non anamorphic stream.
	 * @param transcodingMaxAudioChannels Optional. The maximum number of audio channels to transcode.
	 * @param cpuCoreLimit Optional. The limit of how many cpu cores to use.
	 * @param liveStreamId The live stream id.
	 * @param enableMpegtsM2TsMode Optional. Whether to enable the MpegtsM2Ts mode.
	 * @param videoCodec Optional. Specify a video codec to encode to, e.g. h264. If omitted the server
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vp8, vp9, vpx
	 * (deprecated), wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodeReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 * @param includeCredentials Add the access token to the url to make an authenticated request.
	 */
	public fun getAudioStreamUrl(
		itemId: UUID,
		container: String? = null,
		static: Boolean? = null,
		params: String? = null,
		tag: String? = null,
		deviceProfileId: String? = null,
		playSessionId: String? = null,
		segmentContainer: String? = null,
		segmentLength: Int? = null,
		minSegments: Int? = null,
		mediaSourceId: String? = null,
		deviceId: String? = null,
		audioCodec: String? = null,
		enableAutoStreamCopy: Boolean? = null,
		allowVideoStreamCopy: Boolean? = null,
		allowAudioStreamCopy: Boolean? = null,
		breakOnNonKeyFrames: Boolean? = null,
		audioSampleRate: Int? = null,
		maxAudioBitDepth: Int? = null,
		audioBitRate: Int? = null,
		audioChannels: Int? = null,
		maxAudioChannels: Int? = null,
		profile: String? = null,
		level: String? = null,
		framerate: Float? = null,
		maxFramerate: Float? = null,
		copyTimestamps: Boolean? = null,
		startTimeTicks: Long? = null,
		width: Int? = null,
		height: Int? = null,
		videoBitRate: Int? = null,
		subtitleStreamIndex: Int? = null,
		subtitleMethod: SubtitleDeliveryMethod? = null,
		maxRefFrames: Int? = null,
		maxVideoBitDepth: Int? = null,
		requireAvc: Boolean? = null,
		deInterlace: Boolean? = null,
		requireNonAnamorphic: Boolean? = null,
		transcodingMaxAudioChannels: Int? = null,
		cpuCoreLimit: Int? = null,
		liveStreamId: String? = null,
		enableMpegtsM2TsMode: Boolean? = null,
		videoCodec: String? = null,
		subtitleCodec: String? = null,
		transcodeReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext? = null,
		streamOptions: Map<String, String?>? = emptyMap(),
		includeCredentials: Boolean = false,
	): String {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(48) {
			put("container", container)
			put("static", static)
			put("params", params)
			put("tag", tag)
			put("deviceProfileId", deviceProfileId)
			put("playSessionId", playSessionId)
			put("segmentContainer", segmentContainer)
			put("segmentLength", segmentLength)
			put("minSegments", minSegments)
			put("mediaSourceId", mediaSourceId)
			put("deviceId", deviceId)
			put("audioCodec", audioCodec)
			put("enableAutoStreamCopy", enableAutoStreamCopy)
			put("allowVideoStreamCopy", allowVideoStreamCopy)
			put("allowAudioStreamCopy", allowAudioStreamCopy)
			put("breakOnNonKeyFrames", breakOnNonKeyFrames)
			put("audioSampleRate", audioSampleRate)
			put("maxAudioBitDepth", maxAudioBitDepth)
			put("audioBitRate", audioBitRate)
			put("audioChannels", audioChannels)
			put("maxAudioChannels", maxAudioChannels)
			put("profile", profile)
			put("level", level)
			put("framerate", framerate)
			put("maxFramerate", maxFramerate)
			put("copyTimestamps", copyTimestamps)
			put("startTimeTicks", startTimeTicks)
			put("width", width)
			put("height", height)
			put("videoBitRate", videoBitRate)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("subtitleMethod", subtitleMethod)
			put("maxRefFrames", maxRefFrames)
			put("maxVideoBitDepth", maxVideoBitDepth)
			put("requireAvc", requireAvc)
			put("deInterlace", deInterlace)
			put("requireNonAnamorphic", requireNonAnamorphic)
			put("transcodingMaxAudioChannels", transcodingMaxAudioChannels)
			put("cpuCoreLimit", cpuCoreLimit)
			put("liveStreamId", liveStreamId)
			put("enableMpegtsM2TsMode", enableMpegtsM2TsMode)
			put("videoCodec", videoCodec)
			put("subtitleCodec", subtitleCodec)
			put("transcodeReasons", transcodeReasons)
			put("audioStreamIndex", audioStreamIndex)
			put("videoStreamIndex", videoStreamIndex)
			put("context", context)
			put("streamOptions", streamOptions)
		}
		return api.createUrl("/Audio/{itemId}/stream", pathParameters, queryParameters,
				includeCredentials)
	}

	/**
	 * Gets an audio stream.
	 *
	 * @param itemId The item id.
	 * @param container The audio container.
	 * @param static Optional. If true, the original file will be streamed statically without any
	 * encoding. Use either no url extension or the original file extension. true/false.
	 * @param params The streaming parameters.
	 * @param tag The tag.
	 * @param deviceProfileId Optional. The dlna device profile id to utilize.
	 * @param playSessionId The play session id.
	 * @param segmentContainer The segment container.
	 * @param segmentLength The segment lenght.
	 * @param minSegments The minimum number of segments.
	 * @param mediaSourceId The media version id, if playing an alternate version.
	 * @param deviceId The device id of the client requesting. Used to stop encoding processes when
	 * needed.
	 * @param audioCodec Optional. Specify a audio codec to encode to, e.g. mp3. If omitted the server
	 * will auto-select using the url's extension. Options: aac, mp3, vorbis, wma.
	 * @param enableAutoStreamCopy Whether or not to allow automatic stream copy if requested values
	 * match the original source. Defaults to true.
	 * @param allowVideoStreamCopy Whether or not to allow copying of the video stream url.
	 * @param allowAudioStreamCopy Whether or not to allow copying of the audio stream url.
	 * @param breakOnNonKeyFrames Optional. Whether to break on non key frames.
	 * @param audioSampleRate Optional. Specify a specific audio sample rate, e.g. 44100.
	 * @param maxAudioBitDepth Optional. The maximum audio bit depth.
	 * @param audioBitRate Optional. Specify an audio bitrate to encode to, e.g. 128000. If omitted
	 * this will be left to encoder defaults.
	 * @param audioChannels Optional. Specify a specific number of audio channels to encode to, e.g. 2.
	 * @param maxAudioChannels Optional. Specify a maximum number of audio channels to encode to, e.g.
	 * 2.
	 * @param profile Optional. Specify a specific an encoder profile (varies by encoder), e.g. main,
	 * baseline, high.
	 * @param level Optional. Specify a level for the encoder profile (varies by encoder), e.g. 3, 3.1.
	 * @param framerate Optional. A specific video framerate to encode to, e.g. 23.976. Generally this
	 * should be omitted unless the device has specific requirements.
	 * @param maxFramerate Optional. A specific maximum video framerate to encode to, e.g. 23.976.
	 * Generally this should be omitted unless the device has specific requirements.
	 * @param copyTimestamps Whether or not to copy timestamps when transcoding with an offset.
	 * Defaults to false.
	 * @param startTimeTicks Optional. Specify a starting offset, in ticks. 1 tick = 10000 ms.
	 * @param width Optional. The fixed horizontal resolution of the encoded video.
	 * @param height Optional. The fixed vertical resolution of the encoded video.
	 * @param videoBitRate Optional. Specify a video bitrate to encode to, e.g. 500000. If omitted this
	 * will be left to encoder defaults.
	 * @param subtitleStreamIndex Optional. The index of the subtitle stream to use. If omitted no
	 * subtitles will be used.
	 * @param subtitleMethod Optional. Specify the subtitle delivery method.
	 * @param maxRefFrames Optional.
	 * @param maxVideoBitDepth Optional. The maximum video bit depth.
	 * @param requireAvc Optional. Whether to require avc.
	 * @param deInterlace Optional. Whether to deinterlace the video.
	 * @param requireNonAnamorphic Optional. Whether to require a non anamporphic stream.
	 * @param transcodingMaxAudioChannels Optional. The maximum number of audio channels to transcode.
	 * @param cpuCoreLimit Optional. The limit of how many cpu cores to use.
	 * @param liveStreamId The live stream id.
	 * @param enableMpegtsM2TsMode Optional. Whether to enable the MpegtsM2Ts mode.
	 * @param videoCodec Optional. Specify a video codec to encode to, e.g. h264. If omitted the server
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vp8, vp9, vpx
	 * (deprecated), wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodeReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 */
	public suspend fun getAudioStreamByContainer(
		itemId: UUID,
		container: String,
		static: Boolean? = null,
		params: String? = null,
		tag: String? = null,
		deviceProfileId: String? = null,
		playSessionId: String? = null,
		segmentContainer: String? = null,
		segmentLength: Int? = null,
		minSegments: Int? = null,
		mediaSourceId: String? = null,
		deviceId: String? = null,
		audioCodec: String? = null,
		enableAutoStreamCopy: Boolean? = null,
		allowVideoStreamCopy: Boolean? = null,
		allowAudioStreamCopy: Boolean? = null,
		breakOnNonKeyFrames: Boolean? = null,
		audioSampleRate: Int? = null,
		maxAudioBitDepth: Int? = null,
		audioBitRate: Int? = null,
		audioChannels: Int? = null,
		maxAudioChannels: Int? = null,
		profile: String? = null,
		level: String? = null,
		framerate: Float? = null,
		maxFramerate: Float? = null,
		copyTimestamps: Boolean? = null,
		startTimeTicks: Long? = null,
		width: Int? = null,
		height: Int? = null,
		videoBitRate: Int? = null,
		subtitleStreamIndex: Int? = null,
		subtitleMethod: SubtitleDeliveryMethod? = null,
		maxRefFrames: Int? = null,
		maxVideoBitDepth: Int? = null,
		requireAvc: Boolean? = null,
		deInterlace: Boolean? = null,
		requireNonAnamorphic: Boolean? = null,
		transcodingMaxAudioChannels: Int? = null,
		cpuCoreLimit: Int? = null,
		liveStreamId: String? = null,
		enableMpegtsM2TsMode: Boolean? = null,
		videoCodec: String? = null,
		subtitleCodec: String? = null,
		transcodeReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext? = null,
		streamOptions: Map<String, String?>? = emptyMap(),
	): Response<ByteReadChannel> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("container", container)
		}
		val queryParameters = buildMap<String, Any?>(47) {
			put("static", static)
			put("params", params)
			put("tag", tag)
			put("deviceProfileId", deviceProfileId)
			put("playSessionId", playSessionId)
			put("segmentContainer", segmentContainer)
			put("segmentLength", segmentLength)
			put("minSegments", minSegments)
			put("mediaSourceId", mediaSourceId)
			put("deviceId", deviceId)
			put("audioCodec", audioCodec)
			put("enableAutoStreamCopy", enableAutoStreamCopy)
			put("allowVideoStreamCopy", allowVideoStreamCopy)
			put("allowAudioStreamCopy", allowAudioStreamCopy)
			put("breakOnNonKeyFrames", breakOnNonKeyFrames)
			put("audioSampleRate", audioSampleRate)
			put("maxAudioBitDepth", maxAudioBitDepth)
			put("audioBitRate", audioBitRate)
			put("audioChannels", audioChannels)
			put("maxAudioChannels", maxAudioChannels)
			put("profile", profile)
			put("level", level)
			put("framerate", framerate)
			put("maxFramerate", maxFramerate)
			put("copyTimestamps", copyTimestamps)
			put("startTimeTicks", startTimeTicks)
			put("width", width)
			put("height", height)
			put("videoBitRate", videoBitRate)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("subtitleMethod", subtitleMethod)
			put("maxRefFrames", maxRefFrames)
			put("maxVideoBitDepth", maxVideoBitDepth)
			put("requireAvc", requireAvc)
			put("deInterlace", deInterlace)
			put("requireNonAnamorphic", requireNonAnamorphic)
			put("transcodingMaxAudioChannels", transcodingMaxAudioChannels)
			put("cpuCoreLimit", cpuCoreLimit)
			put("liveStreamId", liveStreamId)
			put("enableMpegtsM2TsMode", enableMpegtsM2TsMode)
			put("videoCodec", videoCodec)
			put("subtitleCodec", subtitleCodec)
			put("transcodeReasons", transcodeReasons)
			put("audioStreamIndex", audioStreamIndex)
			put("videoStreamIndex", videoStreamIndex)
			put("context", context)
			put("streamOptions", streamOptions)
		}
		val data = null
		val response = api.`get`<ByteReadChannel>("/Audio/{itemId}/stream.{container}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets an audio stream.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getAudioStreamByContainer(request: GetAudioStreamByContainerRequest):
			Response<ByteReadChannel> = getAudioStreamByContainer(
		itemId = request.itemId,
		container = request.container,
		static = request.static,
		params = request.params,
		tag = request.tag,
		deviceProfileId = request.deviceProfileId,
		playSessionId = request.playSessionId,
		segmentContainer = request.segmentContainer,
		segmentLength = request.segmentLength,
		minSegments = request.minSegments,
		mediaSourceId = request.mediaSourceId,
		deviceId = request.deviceId,
		audioCodec = request.audioCodec,
		enableAutoStreamCopy = request.enableAutoStreamCopy,
		allowVideoStreamCopy = request.allowVideoStreamCopy,
		allowAudioStreamCopy = request.allowAudioStreamCopy,
		breakOnNonKeyFrames = request.breakOnNonKeyFrames,
		audioSampleRate = request.audioSampleRate,
		maxAudioBitDepth = request.maxAudioBitDepth,
		audioBitRate = request.audioBitRate,
		audioChannels = request.audioChannels,
		maxAudioChannels = request.maxAudioChannels,
		profile = request.profile,
		level = request.level,
		framerate = request.framerate,
		maxFramerate = request.maxFramerate,
		copyTimestamps = request.copyTimestamps,
		startTimeTicks = request.startTimeTicks,
		width = request.width,
		height = request.height,
		videoBitRate = request.videoBitRate,
		subtitleStreamIndex = request.subtitleStreamIndex,
		subtitleMethod = request.subtitleMethod,
		maxRefFrames = request.maxRefFrames,
		maxVideoBitDepth = request.maxVideoBitDepth,
		requireAvc = request.requireAvc,
		deInterlace = request.deInterlace,
		requireNonAnamorphic = request.requireNonAnamorphic,
		transcodingMaxAudioChannels = request.transcodingMaxAudioChannels,
		cpuCoreLimit = request.cpuCoreLimit,
		liveStreamId = request.liveStreamId,
		enableMpegtsM2TsMode = request.enableMpegtsM2TsMode,
		videoCodec = request.videoCodec,
		subtitleCodec = request.subtitleCodec,
		transcodeReasons = request.transcodeReasons,
		audioStreamIndex = request.audioStreamIndex,
		videoStreamIndex = request.videoStreamIndex,
		context = request.context,
		streamOptions = request.streamOptions,
	)

	/**
	 * Gets an audio stream.
	 *
	 * @param itemId The item id.
	 * @param container The audio container.
	 * @param static Optional. If true, the original file will be streamed statically without any
	 * encoding. Use either no url extension or the original file extension. true/false.
	 * @param params The streaming parameters.
	 * @param tag The tag.
	 * @param deviceProfileId Optional. The dlna device profile id to utilize.
	 * @param playSessionId The play session id.
	 * @param segmentContainer The segment container.
	 * @param segmentLength The segment lenght.
	 * @param minSegments The minimum number of segments.
	 * @param mediaSourceId The media version id, if playing an alternate version.
	 * @param deviceId The device id of the client requesting. Used to stop encoding processes when
	 * needed.
	 * @param audioCodec Optional. Specify a audio codec to encode to, e.g. mp3. If omitted the server
	 * will auto-select using the url's extension. Options: aac, mp3, vorbis, wma.
	 * @param enableAutoStreamCopy Whether or not to allow automatic stream copy if requested values
	 * match the original source. Defaults to true.
	 * @param allowVideoStreamCopy Whether or not to allow copying of the video stream url.
	 * @param allowAudioStreamCopy Whether or not to allow copying of the audio stream url.
	 * @param breakOnNonKeyFrames Optional. Whether to break on non key frames.
	 * @param audioSampleRate Optional. Specify a specific audio sample rate, e.g. 44100.
	 * @param maxAudioBitDepth Optional. The maximum audio bit depth.
	 * @param audioBitRate Optional. Specify an audio bitrate to encode to, e.g. 128000. If omitted
	 * this will be left to encoder defaults.
	 * @param audioChannels Optional. Specify a specific number of audio channels to encode to, e.g. 2.
	 * @param maxAudioChannels Optional. Specify a maximum number of audio channels to encode to, e.g.
	 * 2.
	 * @param profile Optional. Specify a specific an encoder profile (varies by encoder), e.g. main,
	 * baseline, high.
	 * @param level Optional. Specify a level for the encoder profile (varies by encoder), e.g. 3, 3.1.
	 * @param framerate Optional. A specific video framerate to encode to, e.g. 23.976. Generally this
	 * should be omitted unless the device has specific requirements.
	 * @param maxFramerate Optional. A specific maximum video framerate to encode to, e.g. 23.976.
	 * Generally this should be omitted unless the device has specific requirements.
	 * @param copyTimestamps Whether or not to copy timestamps when transcoding with an offset.
	 * Defaults to false.
	 * @param startTimeTicks Optional. Specify a starting offset, in ticks. 1 tick = 10000 ms.
	 * @param width Optional. The fixed horizontal resolution of the encoded video.
	 * @param height Optional. The fixed vertical resolution of the encoded video.
	 * @param videoBitRate Optional. Specify a video bitrate to encode to, e.g. 500000. If omitted this
	 * will be left to encoder defaults.
	 * @param subtitleStreamIndex Optional. The index of the subtitle stream to use. If omitted no
	 * subtitles will be used.
	 * @param subtitleMethod Optional. Specify the subtitle delivery method.
	 * @param maxRefFrames Optional.
	 * @param maxVideoBitDepth Optional. The maximum video bit depth.
	 * @param requireAvc Optional. Whether to require avc.
	 * @param deInterlace Optional. Whether to deinterlace the video.
	 * @param requireNonAnamorphic Optional. Whether to require a non anamporphic stream.
	 * @param transcodingMaxAudioChannels Optional. The maximum number of audio channels to transcode.
	 * @param cpuCoreLimit Optional. The limit of how many cpu cores to use.
	 * @param liveStreamId The live stream id.
	 * @param enableMpegtsM2TsMode Optional. Whether to enable the MpegtsM2Ts mode.
	 * @param videoCodec Optional. Specify a video codec to encode to, e.g. h264. If omitted the server
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vp8, vp9, vpx
	 * (deprecated), wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodeReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 * @param includeCredentials Add the access token to the url to make an authenticated request.
	 */
	public fun getAudioStreamByContainerUrl(
		itemId: UUID,
		container: String,
		static: Boolean? = null,
		params: String? = null,
		tag: String? = null,
		deviceProfileId: String? = null,
		playSessionId: String? = null,
		segmentContainer: String? = null,
		segmentLength: Int? = null,
		minSegments: Int? = null,
		mediaSourceId: String? = null,
		deviceId: String? = null,
		audioCodec: String? = null,
		enableAutoStreamCopy: Boolean? = null,
		allowVideoStreamCopy: Boolean? = null,
		allowAudioStreamCopy: Boolean? = null,
		breakOnNonKeyFrames: Boolean? = null,
		audioSampleRate: Int? = null,
		maxAudioBitDepth: Int? = null,
		audioBitRate: Int? = null,
		audioChannels: Int? = null,
		maxAudioChannels: Int? = null,
		profile: String? = null,
		level: String? = null,
		framerate: Float? = null,
		maxFramerate: Float? = null,
		copyTimestamps: Boolean? = null,
		startTimeTicks: Long? = null,
		width: Int? = null,
		height: Int? = null,
		videoBitRate: Int? = null,
		subtitleStreamIndex: Int? = null,
		subtitleMethod: SubtitleDeliveryMethod? = null,
		maxRefFrames: Int? = null,
		maxVideoBitDepth: Int? = null,
		requireAvc: Boolean? = null,
		deInterlace: Boolean? = null,
		requireNonAnamorphic: Boolean? = null,
		transcodingMaxAudioChannels: Int? = null,
		cpuCoreLimit: Int? = null,
		liveStreamId: String? = null,
		enableMpegtsM2TsMode: Boolean? = null,
		videoCodec: String? = null,
		subtitleCodec: String? = null,
		transcodeReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext? = null,
		streamOptions: Map<String, String?>? = emptyMap(),
		includeCredentials: Boolean = false,
	): String {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("container", container)
		}
		val queryParameters = buildMap<String, Any?>(47) {
			put("static", static)
			put("params", params)
			put("tag", tag)
			put("deviceProfileId", deviceProfileId)
			put("playSessionId", playSessionId)
			put("segmentContainer", segmentContainer)
			put("segmentLength", segmentLength)
			put("minSegments", minSegments)
			put("mediaSourceId", mediaSourceId)
			put("deviceId", deviceId)
			put("audioCodec", audioCodec)
			put("enableAutoStreamCopy", enableAutoStreamCopy)
			put("allowVideoStreamCopy", allowVideoStreamCopy)
			put("allowAudioStreamCopy", allowAudioStreamCopy)
			put("breakOnNonKeyFrames", breakOnNonKeyFrames)
			put("audioSampleRate", audioSampleRate)
			put("maxAudioBitDepth", maxAudioBitDepth)
			put("audioBitRate", audioBitRate)
			put("audioChannels", audioChannels)
			put("maxAudioChannels", maxAudioChannels)
			put("profile", profile)
			put("level", level)
			put("framerate", framerate)
			put("maxFramerate", maxFramerate)
			put("copyTimestamps", copyTimestamps)
			put("startTimeTicks", startTimeTicks)
			put("width", width)
			put("height", height)
			put("videoBitRate", videoBitRate)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("subtitleMethod", subtitleMethod)
			put("maxRefFrames", maxRefFrames)
			put("maxVideoBitDepth", maxVideoBitDepth)
			put("requireAvc", requireAvc)
			put("deInterlace", deInterlace)
			put("requireNonAnamorphic", requireNonAnamorphic)
			put("transcodingMaxAudioChannels", transcodingMaxAudioChannels)
			put("cpuCoreLimit", cpuCoreLimit)
			put("liveStreamId", liveStreamId)
			put("enableMpegtsM2TsMode", enableMpegtsM2TsMode)
			put("videoCodec", videoCodec)
			put("subtitleCodec", subtitleCodec)
			put("transcodeReasons", transcodeReasons)
			put("audioStreamIndex", audioStreamIndex)
			put("videoStreamIndex", videoStreamIndex)
			put("context", context)
			put("streamOptions", streamOptions)
		}
		return api.createUrl("/Audio/{itemId}/stream.{container}", pathParameters, queryParameters,
				includeCredentials)
	}
}
