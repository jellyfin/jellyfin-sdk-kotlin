// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.ByteArray
import kotlin.String
import kotlin.Unit
import kotlin.collections.buildMap
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.api.client.extensions.delete

public class HlsSegmentApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 */
	public suspend fun getHlsAudioSegmentLegacyAac(itemId: String, segmentId: String): Response<ByteArray> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("segmentId", segmentId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteArray>("/Audio/{itemId}/hls/{segmentId}/stream.aac", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 */
	public fun getHlsAudioSegmentLegacyAacUrl(itemId: String, segmentId: String): String {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("segmentId", segmentId)
		}
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Audio/{itemId}/hls/{segmentId}/stream.aac", pathParameters, queryParameters)
	}

	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 */
	public suspend fun getHlsAudioSegmentLegacyMp3(itemId: String, segmentId: String): Response<ByteArray> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("segmentId", segmentId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteArray>("/Audio/{itemId}/hls/{segmentId}/stream.mp3", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets the specified audio segment for an audio item.
	 *
	 * @param itemId The item id.
	 * @param segmentId The segment id.
	 */
	public fun getHlsAudioSegmentLegacyMp3Url(itemId: String, segmentId: String): String {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("segmentId", segmentId)
		}
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Audio/{itemId}/hls/{segmentId}/stream.mp3", pathParameters, queryParameters)
	}

	/**
	 * Gets a hls video playlist.
	 *
	 * @param itemId The video id.
	 * @param playlistId The playlist id.
	 */
	public suspend fun getHlsPlaylistLegacy(itemId: String, playlistId: String): Response<ByteArray> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("playlistId", playlistId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteArray>("/Videos/{itemId}/hls/{playlistId}/stream.m3u8", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a hls video playlist.
	 *
	 * @param itemId The video id.
	 * @param playlistId The playlist id.
	 */
	public fun getHlsPlaylistLegacyUrl(itemId: String, playlistId: String): String {
		val pathParameters = buildMap<String, Any?>(2) {
			put("itemId", itemId)
			put("playlistId", playlistId)
		}
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Videos/{itemId}/hls/{playlistId}/stream.m3u8", pathParameters, queryParameters)
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
		segmentContainer: String,
	): Response<ByteArray> {
		val pathParameters = buildMap<String, Any?>(4) {
			put("itemId", itemId)
			put("playlistId", playlistId)
			put("segmentId", segmentId)
			put("segmentContainer", segmentContainer)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<ByteArray>("/Videos/{itemId}/hls/{playlistId}/{segmentId}.{segmentContainer}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a hls video segment.
	 *
	 * @param itemId The item id.
	 * @param playlistId The playlist id.
	 * @param segmentId The segment id.
	 * @param segmentContainer The segment container.
	 */
	public fun getHlsVideoSegmentLegacyUrl(
		itemId: String,
		playlistId: String,
		segmentId: String,
		segmentContainer: String,
	): String {
		val pathParameters = buildMap<String, Any?>(4) {
			put("itemId", itemId)
			put("playlistId", playlistId)
			put("segmentId", segmentId)
			put("segmentContainer", segmentContainer)
		}
		val queryParameters = emptyMap<String, Any?>()
		return api.createUrl("/Videos/{itemId}/hls/{playlistId}/{segmentId}.{segmentContainer}", pathParameters, queryParameters)
	}

	/**
	 * Stops an active encoding.
	 *
	 * @param deviceId The device id of the client requesting. Used to stop encoding processes when needed.
	 * @param playSessionId The play session id.
	 */
	public suspend fun stopEncodingProcess(deviceId: String, playSessionId: String): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(2) {
			put("deviceId", deviceId)
			put("playSessionId", playSessionId)
		}
		val data = null
		val response = api.delete<Unit>("/Videos/ActiveEncodings", pathParameters, queryParameters, data)
		return response
	}
}
