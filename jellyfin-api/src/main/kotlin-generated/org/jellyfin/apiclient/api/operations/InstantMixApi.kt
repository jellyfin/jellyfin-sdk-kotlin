// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.api.operations

import java.util.UUID
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.model.api.BaseItemDtoQueryResult

public class InstantMixApi(
	private val api: KtorClient
) {
	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromAlbum(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["id"] = id
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Albums/{id}/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromArtists(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["id"] = id
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Artists/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromItem(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["id"] = id
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Items/{id}/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param name The genre name.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromMusicGenre(
		name: String,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["name"] = name
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/MusicGenres/{name}/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromMusicGenres(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["id"] = id
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/MusicGenres/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromPlaylist(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["id"] = id
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Playlists/{id}/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Creates an instant playlist based on a given song.
	 *
	 * @param id The item id.
	 * @param userId Optional. Filter by user id, and attach user data.
	 * @param limit Optional. The maximum number of records to return.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines, TrailerUrls.
	 * @param enableImages Optional. Include image information in output.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 */
	public suspend fun getInstantMixFromSong(
		id: UUID,
		userId: UUID? = null,
		limit: Int? = null,
		fields: String? = null,
		enableImages: Boolean? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["id"] = id
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["limit"] = limit
		queryParameters["fields"] = fields
		queryParameters["enableImages"] = enableImages
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Songs/{id}/InstantMix", pathParameters,
				queryParameters, data)
		return response
	}
}
