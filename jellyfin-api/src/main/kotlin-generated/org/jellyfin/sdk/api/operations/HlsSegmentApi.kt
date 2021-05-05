// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import io.ktor.utils.io.ByteReadChannel
import kotlin.Any
import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import org.jellyfin.sdk.api.client.KtorClient
import org.jellyfin.sdk.api.client.Response

public class HlsSegmentApi(
	private val api: KtorClient
) {
	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 */
	public suspend fun getHlsAudioSegmentLegacyAac(itemId: String, segmentId: String):
			Response<ByteReadChannel> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["segmentId"] = segmentId
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteReadChannel>("/Audio/{itemId}/hls/{segmentId}/stream.aac",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 * @param includeCredentials Add the access token to the url to make an authenticated request.
	 */
	public fun getHlsAudioSegmentLegacyAacUrl(
		itemId: String,
		segmentId: String,
		includeCredentials: Boolean = false
	): String {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["segmentId"] = segmentId
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Audio/{itemId}/hls/{segmentId}/stream.aac", pathParameters,
				queryParameters, includeCredentials)
	}

	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 */
	public suspend fun getHlsAudioSegmentLegacyMp3(itemId: String, segmentId: String):
			Response<ByteReadChannel> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["segmentId"] = segmentId
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteReadChannel>("/Audio/{itemId}/hls/{segmentId}/stream.mp3",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 * @param includeCredentials Add the access token to the url to make an authenticated request.
	 */
	public fun getHlsAudioSegmentLegacyMp3Url(
		itemId: String,
		segmentId: String,
		includeCredentials: Boolean = false
	): String {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["segmentId"] = segmentId
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Audio/{itemId}/hls/{segmentId}/stream.mp3", pathParameters,
				queryParameters, includeCredentials)
	}

	/**
	 * Gets a hls video playlist.
	 *
	 * @param itemId The video id.
	 * @param playlistId The playlist id.
	 */
	public suspend fun getHlsPlaylistLegacy(itemId: String, playlistId: String):
			Response<ByteReadChannel> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["playlistId"] = playlistId
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteReadChannel>("/Videos/{itemId}/hls/{playlistId}/stream.m3u8",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a hls video playlist.
	 *
	 * @param itemId The video id.
	 * @param playlistId The playlist id.
	 * @param includeCredentials Add the access token to the url to make an authenticated request.
	 */
	public fun getHlsPlaylistLegacyUrl(
		itemId: String,
		playlistId: String,
		includeCredentials: Boolean = true
	): String {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["playlistId"] = playlistId
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Videos/{itemId}/hls/{playlistId}/stream.m3u8", pathParameters,
				queryParameters, includeCredentials)
	}

	/**
	 * Gets a hls video segment.
	 *
	 * @param itemId The item id.
	 * @param playlistId The playlist id.
	 * @param segmentId The segment id.
	 * @param segmentContainer The segment container.
	 */
	public suspend fun getHlsVideoSegmentLegacy(
		itemId: String,
		playlistId: String,
		segmentId: String,
		segmentContainer: String
	): Response<ByteReadChannel> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["playlistId"] = playlistId
		pathParameters["segmentId"] = segmentId
		pathParameters["segmentContainer"] = segmentContainer
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response =
				api.`get`<ByteReadChannel>("/Videos/{itemId}/hls/{playlistId}/{segmentId}.{segmentContainer}",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a hls video segment.
	 *
	 * @param itemId The item id.
	 * @param playlistId The playlist id.
	 * @param segmentId The segment id.
	 * @param segmentContainer The segment container.
	 * @param includeCredentials Add the access token to the url to make an authenticated request.
	 */
	public fun getHlsVideoSegmentLegacyUrl(
		itemId: String,
		playlistId: String,
		segmentId: String,
		segmentContainer: String,
		includeCredentials: Boolean = false
	): String {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["itemId"] = itemId
		pathParameters["playlistId"] = playlistId
		pathParameters["segmentId"] = segmentId
		pathParameters["segmentContainer"] = segmentContainer
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Videos/{itemId}/hls/{playlistId}/{segmentId}.{segmentContainer}",
				pathParameters, queryParameters, includeCredentials)
	}

	/**
	 * Stops an active encoding.
	 *
	 * @param deviceId The device id of the client requesting. Used to stop encoding processes when
	 * needed.
	 * @param playSessionId The play session id.
	 */
	public suspend fun stopEncodingProcess(deviceId: String, playSessionId: String): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["deviceId"] = deviceId
		queryParameters["playSessionId"] = playSessionId
		val data = null
		val response = api.delete<Unit>("/Videos/ActiveEncodings", pathParameters, queryParameters, data)
		return response
	}
}
