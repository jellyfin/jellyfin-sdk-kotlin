// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.api.operations

import io.ktor.utils.io.ByteReadChannel
import java.util.UUID
import kotlin.Any
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.Map
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.model.api.BaseItemDtoQueryResult
import org.jellyfin.apiclient.model.api.EncodingContext
import org.jellyfin.apiclient.model.api.SubtitleDeliveryMethod

public class VideosApi(
	private val api: KtorClient
) {
	/**
	 * Gets a video stream.
	 *
	 * @param itemId The item id.
	 * @param container The video container. Possible values are: ts, webm, asf, wmv, ogv, mp4, m4v,
	 * mkv, mpeg, mpg, avi, 3gp, wmv, wtv, m2ts, mov, iso, flv.
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
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vpx, wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodingReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 */
	public suspend fun getVideoStreamWithExt(
		itemId: UUID,
		container: String? = null,
		stream: String,
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
		subtitleMethod: SubtitleDeliveryMethod,
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
		transcodingReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext,
		streamOptions: Map<String, String>? = emptyMap()
	): Response<ByteReadChannel> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["container"] = container
		pathParameters["stream"] = stream
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["static"] = static
		queryParameters["params"] = params
		queryParameters["tag"] = tag
		queryParameters["deviceProfileId"] = deviceProfileId
		queryParameters["playSessionId"] = playSessionId
		queryParameters["segmentContainer"] = segmentContainer
		queryParameters["segmentLength"] = segmentLength
		queryParameters["minSegments"] = minSegments
		queryParameters["mediaSourceId"] = mediaSourceId
		queryParameters["deviceId"] = deviceId
		queryParameters["audioCodec"] = audioCodec
		queryParameters["enableAutoStreamCopy"] = enableAutoStreamCopy
		queryParameters["allowVideoStreamCopy"] = allowVideoStreamCopy
		queryParameters["allowAudioStreamCopy"] = allowAudioStreamCopy
		queryParameters["breakOnNonKeyFrames"] = breakOnNonKeyFrames
		queryParameters["audioSampleRate"] = audioSampleRate
		queryParameters["maxAudioBitDepth"] = maxAudioBitDepth
		queryParameters["audioBitRate"] = audioBitRate
		queryParameters["audioChannels"] = audioChannels
		queryParameters["maxAudioChannels"] = maxAudioChannels
		queryParameters["profile"] = profile
		queryParameters["level"] = level
		queryParameters["framerate"] = framerate
		queryParameters["maxFramerate"] = maxFramerate
		queryParameters["copyTimestamps"] = copyTimestamps
		queryParameters["startTimeTicks"] = startTimeTicks
		queryParameters["width"] = width
		queryParameters["height"] = height
		queryParameters["videoBitRate"] = videoBitRate
		queryParameters["subtitleStreamIndex"] = subtitleStreamIndex
		queryParameters["subtitleMethod"] = subtitleMethod
		queryParameters["maxRefFrames"] = maxRefFrames
		queryParameters["maxVideoBitDepth"] = maxVideoBitDepth
		queryParameters["requireAvc"] = requireAvc
		queryParameters["deInterlace"] = deInterlace
		queryParameters["requireNonAnamorphic"] = requireNonAnamorphic
		queryParameters["transcodingMaxAudioChannels"] = transcodingMaxAudioChannels
		queryParameters["cpuCoreLimit"] = cpuCoreLimit
		queryParameters["liveStreamId"] = liveStreamId
		queryParameters["enableMpegtsM2TsMode"] = enableMpegtsM2TsMode
		queryParameters["videoCodec"] = videoCodec
		queryParameters["subtitleCodec"] = subtitleCodec
		queryParameters["transcodingReasons"] = transcodingReasons
		queryParameters["audioStreamIndex"] = audioStreamIndex
		queryParameters["videoStreamIndex"] = videoStreamIndex
		queryParameters["context"] = context
		queryParameters["streamOptions"] = streamOptions
		val data = null
		val response = api.`get`<ByteReadChannel>("/Videos/{itemId}/{stream}.{container}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets a video stream.
	 *
	 * @param itemId The item id.
	 * @param container The video container. Possible values are: ts, webm, asf, wmv, ogv, mp4, m4v,
	 * mkv, mpeg, mpg, avi, 3gp, wmv, wtv, m2ts, mov, iso, flv.
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
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vpx, wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodingReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 */
	public fun getVideoStreamWithExtUrl(
		itemId: UUID,
		container: String? = null,
		stream: String,
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
		subtitleMethod: SubtitleDeliveryMethod,
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
		transcodingReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext,
		streamOptions: Map<String, String>? = null
	): String {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["container"] = container
		pathParameters["stream"] = stream
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["static"] = static
		queryParameters["params"] = params
		queryParameters["tag"] = tag
		queryParameters["deviceProfileId"] = deviceProfileId
		queryParameters["playSessionId"] = playSessionId
		queryParameters["segmentContainer"] = segmentContainer
		queryParameters["segmentLength"] = segmentLength
		queryParameters["minSegments"] = minSegments
		queryParameters["mediaSourceId"] = mediaSourceId
		queryParameters["deviceId"] = deviceId
		queryParameters["audioCodec"] = audioCodec
		queryParameters["enableAutoStreamCopy"] = enableAutoStreamCopy
		queryParameters["allowVideoStreamCopy"] = allowVideoStreamCopy
		queryParameters["allowAudioStreamCopy"] = allowAudioStreamCopy
		queryParameters["breakOnNonKeyFrames"] = breakOnNonKeyFrames
		queryParameters["audioSampleRate"] = audioSampleRate
		queryParameters["maxAudioBitDepth"] = maxAudioBitDepth
		queryParameters["audioBitRate"] = audioBitRate
		queryParameters["audioChannels"] = audioChannels
		queryParameters["maxAudioChannels"] = maxAudioChannels
		queryParameters["profile"] = profile
		queryParameters["level"] = level
		queryParameters["framerate"] = framerate
		queryParameters["maxFramerate"] = maxFramerate
		queryParameters["copyTimestamps"] = copyTimestamps
		queryParameters["startTimeTicks"] = startTimeTicks
		queryParameters["width"] = width
		queryParameters["height"] = height
		queryParameters["videoBitRate"] = videoBitRate
		queryParameters["subtitleStreamIndex"] = subtitleStreamIndex
		queryParameters["subtitleMethod"] = subtitleMethod
		queryParameters["maxRefFrames"] = maxRefFrames
		queryParameters["maxVideoBitDepth"] = maxVideoBitDepth
		queryParameters["requireAvc"] = requireAvc
		queryParameters["deInterlace"] = deInterlace
		queryParameters["requireNonAnamorphic"] = requireNonAnamorphic
		queryParameters["transcodingMaxAudioChannels"] = transcodingMaxAudioChannels
		queryParameters["cpuCoreLimit"] = cpuCoreLimit
		queryParameters["liveStreamId"] = liveStreamId
		queryParameters["enableMpegtsM2TsMode"] = enableMpegtsM2TsMode
		queryParameters["videoCodec"] = videoCodec
		queryParameters["subtitleCodec"] = subtitleCodec
		queryParameters["transcodingReasons"] = transcodingReasons
		queryParameters["audioStreamIndex"] = audioStreamIndex
		queryParameters["videoStreamIndex"] = videoStreamIndex
		queryParameters["context"] = context
		queryParameters["streamOptions"] = streamOptions
		return api.createUrl("/Videos/{itemId}/{stream}.{container}", pathParameters, queryParameters)
	}

	/**
	 * Gets additional parts for a video.
	 *
	 * @param itemId The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 */
	public suspend fun getAdditionalPart(itemId: UUID, userId: UUID? = null):
			Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Videos/{itemId}/AdditionalParts",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Removes alternate video sources.
	 *
	 * @param itemId The item id.
	 */
	public suspend fun deleteAlternateSources(itemId: UUID): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.delete<Unit>("/Videos/{itemId}/AlternateSources", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets a video stream.
	 *
	 * @param itemId The item id.
	 * @param container The video container. Possible values are: ts, webm, asf, wmv, ogv, mp4, m4v,
	 * mkv, mpeg, mpg, avi, 3gp, wmv, wtv, m2ts, mov, iso, flv.
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
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vpx, wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodingReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 */
	public suspend fun getVideoStream(
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
		subtitleMethod: SubtitleDeliveryMethod,
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
		transcodingReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext,
		streamOptions: Map<String, String>? = emptyMap()
	): Response<ByteReadChannel> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["container"] = container
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["static"] = static
		queryParameters["params"] = params
		queryParameters["tag"] = tag
		queryParameters["deviceProfileId"] = deviceProfileId
		queryParameters["playSessionId"] = playSessionId
		queryParameters["segmentContainer"] = segmentContainer
		queryParameters["segmentLength"] = segmentLength
		queryParameters["minSegments"] = minSegments
		queryParameters["mediaSourceId"] = mediaSourceId
		queryParameters["deviceId"] = deviceId
		queryParameters["audioCodec"] = audioCodec
		queryParameters["enableAutoStreamCopy"] = enableAutoStreamCopy
		queryParameters["allowVideoStreamCopy"] = allowVideoStreamCopy
		queryParameters["allowAudioStreamCopy"] = allowAudioStreamCopy
		queryParameters["breakOnNonKeyFrames"] = breakOnNonKeyFrames
		queryParameters["audioSampleRate"] = audioSampleRate
		queryParameters["maxAudioBitDepth"] = maxAudioBitDepth
		queryParameters["audioBitRate"] = audioBitRate
		queryParameters["audioChannels"] = audioChannels
		queryParameters["maxAudioChannels"] = maxAudioChannels
		queryParameters["profile"] = profile
		queryParameters["level"] = level
		queryParameters["framerate"] = framerate
		queryParameters["maxFramerate"] = maxFramerate
		queryParameters["copyTimestamps"] = copyTimestamps
		queryParameters["startTimeTicks"] = startTimeTicks
		queryParameters["width"] = width
		queryParameters["height"] = height
		queryParameters["videoBitRate"] = videoBitRate
		queryParameters["subtitleStreamIndex"] = subtitleStreamIndex
		queryParameters["subtitleMethod"] = subtitleMethod
		queryParameters["maxRefFrames"] = maxRefFrames
		queryParameters["maxVideoBitDepth"] = maxVideoBitDepth
		queryParameters["requireAvc"] = requireAvc
		queryParameters["deInterlace"] = deInterlace
		queryParameters["requireNonAnamorphic"] = requireNonAnamorphic
		queryParameters["transcodingMaxAudioChannels"] = transcodingMaxAudioChannels
		queryParameters["cpuCoreLimit"] = cpuCoreLimit
		queryParameters["liveStreamId"] = liveStreamId
		queryParameters["enableMpegtsM2TsMode"] = enableMpegtsM2TsMode
		queryParameters["videoCodec"] = videoCodec
		queryParameters["subtitleCodec"] = subtitleCodec
		queryParameters["transcodingReasons"] = transcodingReasons
		queryParameters["audioStreamIndex"] = audioStreamIndex
		queryParameters["videoStreamIndex"] = videoStreamIndex
		queryParameters["context"] = context
		queryParameters["streamOptions"] = streamOptions
		val data = null
		val response = api.`get`<ByteReadChannel>("/Videos/{itemId}/stream", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets a video stream.
	 *
	 * @param itemId The item id.
	 * @param container The video container. Possible values are: ts, webm, asf, wmv, ogv, mp4, m4v,
	 * mkv, mpeg, mpg, avi, 3gp, wmv, wtv, m2ts, mov, iso, flv.
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
	 * will auto-select using the url's extension. Options: h265, h264, mpeg4, theora, vpx, wmv.
	 * @param subtitleCodec Optional. Specify a subtitle codec to encode to.
	 * @param transcodingReasons Optional. The transcoding reason.
	 * @param audioStreamIndex Optional. The index of the audio stream to use. If omitted the first
	 * audio stream will be used.
	 * @param videoStreamIndex Optional. The index of the video stream to use. If omitted the first
	 * video stream will be used.
	 * @param context Optional. The MediaBrowser.Model.Dlna.EncodingContext.
	 * @param streamOptions Optional. The streaming options.
	 */
	public fun getVideoStreamUrl(
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
		subtitleMethod: SubtitleDeliveryMethod,
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
		transcodingReasons: String? = null,
		audioStreamIndex: Int? = null,
		videoStreamIndex: Int? = null,
		context: EncodingContext,
		streamOptions: Map<String, String>? = null
	): String {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["container"] = container
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["static"] = static
		queryParameters["params"] = params
		queryParameters["tag"] = tag
		queryParameters["deviceProfileId"] = deviceProfileId
		queryParameters["playSessionId"] = playSessionId
		queryParameters["segmentContainer"] = segmentContainer
		queryParameters["segmentLength"] = segmentLength
		queryParameters["minSegments"] = minSegments
		queryParameters["mediaSourceId"] = mediaSourceId
		queryParameters["deviceId"] = deviceId
		queryParameters["audioCodec"] = audioCodec
		queryParameters["enableAutoStreamCopy"] = enableAutoStreamCopy
		queryParameters["allowVideoStreamCopy"] = allowVideoStreamCopy
		queryParameters["allowAudioStreamCopy"] = allowAudioStreamCopy
		queryParameters["breakOnNonKeyFrames"] = breakOnNonKeyFrames
		queryParameters["audioSampleRate"] = audioSampleRate
		queryParameters["maxAudioBitDepth"] = maxAudioBitDepth
		queryParameters["audioBitRate"] = audioBitRate
		queryParameters["audioChannels"] = audioChannels
		queryParameters["maxAudioChannels"] = maxAudioChannels
		queryParameters["profile"] = profile
		queryParameters["level"] = level
		queryParameters["framerate"] = framerate
		queryParameters["maxFramerate"] = maxFramerate
		queryParameters["copyTimestamps"] = copyTimestamps
		queryParameters["startTimeTicks"] = startTimeTicks
		queryParameters["width"] = width
		queryParameters["height"] = height
		queryParameters["videoBitRate"] = videoBitRate
		queryParameters["subtitleStreamIndex"] = subtitleStreamIndex
		queryParameters["subtitleMethod"] = subtitleMethod
		queryParameters["maxRefFrames"] = maxRefFrames
		queryParameters["maxVideoBitDepth"] = maxVideoBitDepth
		queryParameters["requireAvc"] = requireAvc
		queryParameters["deInterlace"] = deInterlace
		queryParameters["requireNonAnamorphic"] = requireNonAnamorphic
		queryParameters["transcodingMaxAudioChannels"] = transcodingMaxAudioChannels
		queryParameters["cpuCoreLimit"] = cpuCoreLimit
		queryParameters["liveStreamId"] = liveStreamId
		queryParameters["enableMpegtsM2TsMode"] = enableMpegtsM2TsMode
		queryParameters["videoCodec"] = videoCodec
		queryParameters["subtitleCodec"] = subtitleCodec
		queryParameters["transcodingReasons"] = transcodingReasons
		queryParameters["audioStreamIndex"] = audioStreamIndex
		queryParameters["videoStreamIndex"] = videoStreamIndex
		queryParameters["context"] = context
		queryParameters["streamOptions"] = streamOptions
		return api.createUrl("/Videos/{itemId}/stream", pathParameters, queryParameters)
	}

	/**
	 * Merges videos into a single record.
	 *
	 * @param itemIds Item id list. This allows multiple, comma delimited.
	 */
	public suspend fun mergeVersions(itemIds: String): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["itemIds"] = itemIds
		val data = null
		val response = api.post<Unit>("/Videos/MergeVersions", pathParameters, queryParameters, data)
		return response
	}
}
