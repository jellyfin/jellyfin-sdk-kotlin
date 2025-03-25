// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlin.collections.buildMap
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDtoQueryResult
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.request.GetInstantMixFromAlbumRequest
import org.jellyfin.sdk.model.api.request.GetInstantMixFromArtists2Request
import org.jellyfin.sdk.model.api.request.GetInstantMixFromArtistsRequest
import org.jellyfin.sdk.model.api.request.GetInstantMixFromItemRequest
import org.jellyfin.sdk.model.api.request.GetInstantMixFromMusicGenreByIdRequest
import org.jellyfin.sdk.model.api.request.GetInstantMixFromMusicGenreByNameRequest
import org.jellyfin.sdk.model.api.request.GetInstantMixFromPlaylistRequest
import org.jellyfin.sdk.model.api.request.GetInstantMixFromSongRequest

public class InstantMixApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Creates an instant playlist based on a given album.
	 *
	 * @param itemId The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromAlbum(
		itemId: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Albums/{itemId}/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given album.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromAlbum(request: GetInstantMixFromAlbumRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromAlbum(
		itemId = request.itemId,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given artist.
	 *
	 * @param itemId The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromArtists(
		itemId: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Artists/{itemId}/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given artist.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromArtists(request: GetInstantMixFromArtistsRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromArtists(
		itemId = request.itemId,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given artist.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	public suspend fun getInstantMixFromArtists2(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(8) {
			put("id", id)
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Artists/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given artist.
	 *
	 * @param request The request parameters
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	public suspend fun getInstantMixFromArtists2(request: GetInstantMixFromArtists2Request): Response<BaseItemDtoQueryResult> = getInstantMixFromArtists2(
		id = request.id,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given item.
	 *
	 * @param itemId The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromItem(
		itemId: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Items/{itemId}/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given item.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromItem(request: GetInstantMixFromItemRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromItem(
		itemId = request.itemId,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given genre.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromMusicGenreById(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(8) {
			put("id", id)
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/MusicGenres/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given genre.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromMusicGenreById(request: GetInstantMixFromMusicGenreByIdRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromMusicGenreById(
		id = request.id,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given genre.
	 *
	 * @param name The genre name.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromMusicGenreByName(
		name: String,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("name", name)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/MusicGenres/{name}/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given genre.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromMusicGenreByName(request: GetInstantMixFromMusicGenreByNameRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromMusicGenreByName(
		name = request.name,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given playlist.
	 *
	 * @param itemId The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromPlaylist(
		itemId: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Playlists/{itemId}/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given playlist.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromPlaylist(request: GetInstantMixFromPlaylistRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromPlaylist(
		itemId = request.itemId,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param itemId The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromSong(
		itemId: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: Collection<ItemFields>? = emptyList(),
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("itemId", itemId)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("userId", userId)
			put("limit", limit)
			put("fields", fields)
			put("enableImages", enableImages)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Songs/{itemId}/InstantMix", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param request The request parameters
	 */
	public suspend fun getInstantMixFromSong(request: GetInstantMixFromSongRequest): Response<BaseItemDtoQueryResult> = getInstantMixFromSong(
		itemId = request.itemId,
		userId = request.userId,
		limit = request.limit,
		fields = request.fields,
		enableImages = request.enableImages,
		enableUserData = request.enableUserData,
		imageTypeLimit = request.imageTypeLimit,
		enableImageTypes = request.enableImageTypes,
	)
}
