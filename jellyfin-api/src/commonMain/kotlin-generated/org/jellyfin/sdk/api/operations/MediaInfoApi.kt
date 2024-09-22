// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import io.ktor.utils.io.ByteReadChannel
import kotlin.Any
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.buildMap
import kotlin.collections.emptyMap
import kotlin.require
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.api.client.extensions.post
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.LiveStreamResponse
import org.jellyfin.sdk.model.api.OpenLiveStreamDto
import org.jellyfin.sdk.model.api.PlaybackInfoDto
import org.jellyfin.sdk.model.api.PlaybackInfoResponse
import org.jellyfin.sdk.model.api.request.GetPostedPlaybackInfoDeprecatedRequest
import org.jellyfin.sdk.model.api.request.OpenLiveStreamRequest

public class MediaInfoApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Closes a media source.
	 *
	 * @param liveStreamId The livestream id.
	 */
	public suspend fun closeLiveStream(liveStreamId: String): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(1) {
			put("liveStreamId", liveStreamId)
		}
		val data = null
		val response = api.post<Unit>("/LiveStreams/Close", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Tests the network with a request with the size of the bitrate.
	 *
	 * @param size The bitrate. Defaults to 102400.
	 */
	public suspend fun getBitrateTestBytes(size: Int? = 102_400): Response<ByteReadChannel> {
		val pathParameters = emptyMap<String, Any?>()
		require(size in 1..100_000_000) { "Parameter \"size\" must be in range 1..100000000 (inclusive)." }
		val queryParameters = buildMap<String, Any?>(1) {
			put("size", size)
		}
		val data = null
		val response = api.`get`<ByteReadChannel>("/Playback/BitrateTest", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Tests the network with a request with the size of the bitrate.
	 *
	 * @param size The bitrate. Defaults to 102400.
	 */
	public fun getBitrateTestBytesUrl(size: Int? = 102_400): String {
		val pathParameters = emptyMap<String, Any?>()
		require(size in 1..100_000_000) { "Parameter \"size\" must be in range 1..100000000 (inclusive)." }
		val queryParameters = buildMap<String, Any?>(1) {
			put("size", size)
		}
		return api.createUrl("/Playback/BitrateTest", pathParameters, queryParameters)
	}

	/**
	 * Gets live playback media info for an item.
	 *
	 * @param itemId The item id.
	 * @param userId The user id.
	 */
	public suspend fun getPlaybackInfo(itemId: UUID, userId: UUID? = null):
			Response<PlaybackInfoResponse> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val data = null
		val response = api.`get`<PlaybackInfoResponse>("/Items/{itemId}/PlaybackInfo", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * For backwards compatibility parameters can be sent via Query or Body, with Query having higher
	 * precedence.
	 * Query parameters are obsolete.
	 *
	 * @param itemId The item id.
	 */
	public suspend fun getPostedPlaybackInfo(itemId: UUID, `data`: PlaybackInfoDto? = null):
			Response<PlaybackInfoResponse> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<PlaybackInfoResponse>("/Items/{itemId}/PlaybackInfo", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * For backwards compatibility parameters can be sent via Query or Body, with Query having higher
	 * precedence.
	 * Query parameters are obsolete.
	 *
	 * @param itemId The item id.
	 * @param userId The user id.
	 * @param maxStreamingBitrate The maximum streaming bitrate.
	 * @param startTimeTicks The start time in ticks.
	 * @param audioStreamIndex The audio stream index.
	 * @param subtitleStreamIndex The subtitle stream index.
	 * @param maxAudioChannels The maximum number of audio channels.
	 * @param mediaSourceId The media source id.
	 * @param liveStreamId The livestream id.
	 * @param autoOpenLiveStream Whether to auto open the livestream.
	 * @param enableDirectPlay Whether to enable direct play. Default: true.
	 * @param enableDirectStream Whether to enable direct stream. Default: true.
	 * @param enableTranscoding Whether to enable transcoding. Default: true.
	 * @param allowVideoStreamCopy Whether to allow to copy the video stream. Default: true.
	 * @param allowAudioStreamCopy Whether to allow to copy the audio stream. Default: true.
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	public suspend fun getPostedPlaybackInfoDeprecated(
		itemId: UUID,
		userId: UUID? = null,
		maxStreamingBitrate: Int? = null,
		startTimeTicks: Long? = null,
		audioStreamIndex: Int? = null,
		subtitleStreamIndex: Int? = null,
		maxAudioChannels: Int? = null,
		mediaSourceId: String? = null,
		liveStreamId: String? = null,
		autoOpenLiveStream: Boolean? = null,
		enableDirectPlay: Boolean? = null,
		enableDirectStream: Boolean? = null,
		enableTranscoding: Boolean? = null,
		allowVideoStreamCopy: Boolean? = null,
		allowAudioStreamCopy: Boolean? = null,
		`data`: PlaybackInfoDto? = null,
	): Response<PlaybackInfoResponse> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(14) {
			put("userId", userId)
			put("maxStreamingBitrate", maxStreamingBitrate)
			put("startTimeTicks", startTimeTicks)
			put("audioStreamIndex", audioStreamIndex)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("maxAudioChannels", maxAudioChannels)
			put("mediaSourceId", mediaSourceId)
			put("liveStreamId", liveStreamId)
			put("autoOpenLiveStream", autoOpenLiveStream)
			put("enableDirectPlay", enableDirectPlay)
			put("enableDirectStream", enableDirectStream)
			put("enableTranscoding", enableTranscoding)
			put("allowVideoStreamCopy", allowVideoStreamCopy)
			put("allowAudioStreamCopy", allowAudioStreamCopy)
		}
		val response = api.post<PlaybackInfoResponse>("/Items/{itemId}/PlaybackInfo", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * For backwards compatibility parameters can be sent via Query or Body, with Query having higher
	 * precedence.
	 * Query parameters are obsolete.
	 *
	 * @param request The request parameters
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	public suspend fun getPostedPlaybackInfoDeprecated(request: GetPostedPlaybackInfoDeprecatedRequest,
			`data`: PlaybackInfoDto? = null): Response<PlaybackInfoResponse> =
			getPostedPlaybackInfoDeprecated(
		itemId = request.itemId,
		userId = request.userId,
		maxStreamingBitrate = request.maxStreamingBitrate,
		startTimeTicks = request.startTimeTicks,
		audioStreamIndex = request.audioStreamIndex,
		subtitleStreamIndex = request.subtitleStreamIndex,
		maxAudioChannels = request.maxAudioChannels,
		mediaSourceId = request.mediaSourceId,
		liveStreamId = request.liveStreamId,
		autoOpenLiveStream = request.autoOpenLiveStream,
		enableDirectPlay = request.enableDirectPlay,
		enableDirectStream = request.enableDirectStream,
		enableTranscoding = request.enableTranscoding,
		allowVideoStreamCopy = request.allowVideoStreamCopy,
		allowAudioStreamCopy = request.allowAudioStreamCopy,
		`data` = `data`,
	)

	/**
	 * Opens a media source.
	 *
	 * @param openToken The open token.
	 * @param userId The user id.
	 * @param playSessionId The play session id.
	 * @param maxStreamingBitrate The maximum streaming bitrate.
	 * @param startTimeTicks The start time in ticks.
	 * @param audioStreamIndex The audio stream index.
	 * @param subtitleStreamIndex The subtitle stream index.
	 * @param maxAudioChannels The maximum number of audio channels.
	 * @param itemId The item id.
	 * @param enableDirectPlay Whether to enable direct play. Default: true.
	 * @param enableDirectStream Whether to enable direct stream. Default: true.
	 * @param alwaysBurnInSubtitleWhenTranscoding Always burn-in subtitle when transcoding.
	 */
	public suspend fun openLiveStream(
		openToken: String? = null,
		userId: UUID? = null,
		playSessionId: String? = null,
		maxStreamingBitrate: Int? = null,
		startTimeTicks: Long? = null,
		audioStreamIndex: Int? = null,
		subtitleStreamIndex: Int? = null,
		maxAudioChannels: Int? = null,
		itemId: UUID? = null,
		enableDirectPlay: Boolean? = null,
		enableDirectStream: Boolean? = null,
		alwaysBurnInSubtitleWhenTranscoding: Boolean? = null,
		`data`: OpenLiveStreamDto? = null,
	): Response<LiveStreamResponse> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(12) {
			put("openToken", openToken)
			put("userId", userId)
			put("playSessionId", playSessionId)
			put("maxStreamingBitrate", maxStreamingBitrate)
			put("startTimeTicks", startTimeTicks)
			put("audioStreamIndex", audioStreamIndex)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("maxAudioChannels", maxAudioChannels)
			put("itemId", itemId)
			put("enableDirectPlay", enableDirectPlay)
			put("enableDirectStream", enableDirectStream)
			put("alwaysBurnInSubtitleWhenTranscoding", alwaysBurnInSubtitleWhenTranscoding)
		}
		val response = api.post<LiveStreamResponse>("/LiveStreams/Open", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Opens a media source.
	 *
	 * @param request The request parameters
	 */
	public suspend fun openLiveStream(request: OpenLiveStreamRequest = OpenLiveStreamRequest(),
			`data`: OpenLiveStreamDto? = null): Response<LiveStreamResponse> = openLiveStream(
		openToken = request.openToken,
		userId = request.userId,
		playSessionId = request.playSessionId,
		maxStreamingBitrate = request.maxStreamingBitrate,
		startTimeTicks = request.startTimeTicks,
		audioStreamIndex = request.audioStreamIndex,
		subtitleStreamIndex = request.subtitleStreamIndex,
		maxAudioChannels = request.maxAudioChannels,
		itemId = request.itemId,
		enableDirectPlay = request.enableDirectPlay,
		enableDirectStream = request.enableDirectStream,
		alwaysBurnInSubtitleWhenTranscoding = request.alwaysBurnInSubtitleWhenTranscoding,
		`data` = `data`,
	)
}
