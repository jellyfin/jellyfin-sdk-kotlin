// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlin.collections.buildMap
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.exception.MissingUserIdException
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDtoQueryResult
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemFilter
import org.jellyfin.sdk.model.api.LocationType
import org.jellyfin.sdk.model.api.SeriesStatus
import org.jellyfin.sdk.model.api.SortOrder
import org.jellyfin.sdk.model.api.VideoType

public class ItemsApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Gets items based on a query.
	 *
	 * @param userId The user id supplied as query parameter.
	 * @param maxOfficialRating Optional filter by maximum official rating (PG, PG-13, TV-MA, etc).
	 * @param hasThemeSong Optional filter by items with theme songs.
	 * @param hasThemeVideo Optional filter by items with theme videos.
	 * @param hasSubtitles Optional filter by items with subtitles.
	 * @param hasSpecialFeature Optional filter by items with special features.
	 * @param hasTrailer Optional filter by items with trailers.
	 * @param adjacentTo Optional. Return items that are siblings of a supplied item.
	 * @param parentIndexNumber Optional filter by parent index number.
	 * @param hasParentalRating Optional filter by items that have or do not have a parental rating.
	 * @param isHd Optional filter by items that are HD or not.
	 * @param is4k Optional filter by items that are 4K or not.
	 * @param locationTypes Optional. If specified, results will be filtered based on LocationType.
	 * This allows multiple, comma delimited.
	 * @param excludeLocationTypes Optional. If specified, results will be filtered based on the
	 * LocationType. This allows multiple, comma delimited.
	 * @param isMissing Optional filter by items that are missing episodes or not.
	 * @param isUnaired Optional filter by items that are unaired episodes or not.
	 * @param minCommunityRating Optional filter by minimum community rating.
	 * @param minCriticRating Optional filter by minimum critic rating.
	 * @param minPremiereDate Optional. The minimum premiere date. Format = ISO.
	 * @param minDateLastSaved Optional. The minimum last saved date. Format = ISO.
	 * @param minDateLastSavedForUser Optional. The minimum last saved date for the current user.
	 * Format = ISO.
	 * @param maxPremiereDate Optional. The maximum premiere date. Format = ISO.
	 * @param hasOverview Optional filter by items that have an overview or not.
	 * @param hasImdbId Optional filter by items that have an imdb id or not.
	 * @param hasTmdbId Optional filter by items that have a tmdb id or not.
	 * @param hasTvdbId Optional filter by items that have a tvdb id or not.
	 * @param isMovie Optional filter for live tv movies.
	 * @param isSeries Optional filter for live tv series.
	 * @param isNews Optional filter for live tv news.
	 * @param isKids Optional filter for live tv kids.
	 * @param isSports Optional filter for live tv sports.
	 * @param excludeItemIds Optional. If specified, results will be filtered by excluding item ids.
	 * This allows multiple, comma delimited.
	 * @param startIndex Optional. The record index to start at. All items with a lower index will be
	 * dropped from the results.
	 * @param limit Optional. The maximum number of records to return.
	 * @param recursive When searching within folders, this determines whether or not the search will
	 * be recursive. true/false.
	 * @param searchTerm Optional. Filter based on a search term.
	 * @param sortOrder Sort Order - Ascending,Descending.
	 * @param parentId Specify this to localize the search to a specific item or folder. Omit to use
	 * the root.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimited. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines.
	 * @param excludeItemTypes Optional. If specified, results will be filtered based on item type.
	 * This allows multiple, comma delimited.
	 * @param includeItemTypes Optional. If specified, results will be filtered based on the item type.
	 * This allows multiple, comma delimited.
	 * @param filters Optional. Specify additional filters to apply. This allows multiple, comma
	 * delimited. Options: IsFolder, IsNotFolder, IsUnplayed, IsPlayed, IsFavorite, IsResumable, Likes,
	 * Dislikes.
	 * @param isFavorite Optional filter by items that are marked as favorite, or not.
	 * @param mediaTypes Optional filter by MediaType. Allows multiple, comma delimited.
	 * @param imageTypes Optional. If specified, results will be filtered based on those containing
	 * image types. This allows multiple, comma delimited.
	 * @param sortBy Optional. Specify one or more sort orders, comma delimited. Options: Album,
	 * AlbumArtist, Artist, Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount,
	 * PremiereDate, ProductionYear, SortName, Random, Revenue, Runtime.
	 * @param isPlayed Optional filter by items that are played, or not.
	 * @param genres Optional. If specified, results will be filtered based on genre. This allows
	 * multiple, pipe delimited.
	 * @param officialRatings Optional. If specified, results will be filtered based on OfficialRating.
	 * This allows multiple, pipe delimited.
	 * @param tags Optional. If specified, results will be filtered based on tag. This allows multiple,
	 * pipe delimited.
	 * @param years Optional. If specified, results will be filtered based on production year. This
	 * allows multiple, comma delimited.
	 * @param enableUserData Optional, include user data.
	 * @param imageTypeLimit Optional, the max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 * @param person Optional. If specified, results will be filtered to include only those containing
	 * the specified person.
	 * @param personIds Optional. If specified, results will be filtered to include only those
	 * containing the specified person id.
	 * @param personTypes Optional. If specified, along with Person, results will be filtered to
	 * include only those containing the specified person and PersonType. Allows multiple,
	 * comma-delimited.
	 * @param studios Optional. If specified, results will be filtered based on studio. This allows
	 * multiple, pipe delimited.
	 * @param artists Optional. If specified, results will be filtered based on artists. This allows
	 * multiple, pipe delimited.
	 * @param excludeArtistIds Optional. If specified, results will be filtered based on artist id.
	 * This allows multiple, pipe delimited.
	 * @param artistIds Optional. If specified, results will be filtered to include only those
	 * containing the specified artist id.
	 * @param albumArtistIds Optional. If specified, results will be filtered to include only those
	 * containing the specified album artist id.
	 * @param contributingArtistIds Optional. If specified, results will be filtered to include only
	 * those containing the specified contributing artist id.
	 * @param albums Optional. If specified, results will be filtered based on album. This allows
	 * multiple, pipe delimited.
	 * @param albumIds Optional. If specified, results will be filtered based on album id. This allows
	 * multiple, pipe delimited.
	 * @param ids Optional. If specific items are needed, specify a list of item id's to retrieve. This
	 * allows multiple, comma delimited.
	 * @param videoTypes Optional filter by VideoType (videofile, dvd, bluray, iso). Allows multiple,
	 * comma delimited.
	 * @param minOfficialRating Optional filter by minimum official rating (PG, PG-13, TV-MA, etc).
	 * @param isLocked Optional filter by items that are locked.
	 * @param isPlaceHolder Optional filter by items that are placeholders.
	 * @param hasOfficialRating Optional filter by items that have official ratings.
	 * @param collapseBoxSetItems Whether or not to hide items behind their boxsets.
	 * @param minWidth Optional. Filter by the minimum width of the item.
	 * @param minHeight Optional. Filter by the minimum height of the item.
	 * @param maxWidth Optional. Filter by the maximum width of the item.
	 * @param maxHeight Optional. Filter by the maximum height of the item.
	 * @param is3d Optional filter by items that are 3D, or not.
	 * @param seriesStatus Optional filter by Series Status. Allows multiple, comma delimited.
	 * @param nameStartsWithOrGreater Optional filter by items whose name is sorted equally or greater
	 * than a given input string.
	 * @param nameStartsWith Optional filter by items whose name is sorted equally than a given input
	 * string.
	 * @param nameLessThan Optional filter by items whose name is equally or lesser than a given input
	 * string.
	 * @param studioIds Optional. If specified, results will be filtered based on studio id. This
	 * allows multiple, pipe delimited.
	 * @param genreIds Optional. If specified, results will be filtered based on genre id. This allows
	 * multiple, pipe delimited.
	 * @param enableTotalRecordCount Optional. Enable the total record count.
	 * @param enableImages Optional, include image information in output.
	 */
	public suspend fun getItems(
		userId: UUID? = null,
		maxOfficialRating: String? = null,
		hasThemeSong: Boolean? = null,
		hasThemeVideo: Boolean? = null,
		hasSubtitles: Boolean? = null,
		hasSpecialFeature: Boolean? = null,
		hasTrailer: Boolean? = null,
		adjacentTo: String? = null,
		parentIndexNumber: Int? = null,
		hasParentalRating: Boolean? = null,
		isHd: Boolean? = null,
		is4k: Boolean? = null,
		locationTypes: Collection<LocationType>? = emptyList(),
		excludeLocationTypes: Collection<LocationType>? = emptyList(),
		isMissing: Boolean? = null,
		isUnaired: Boolean? = null,
		minCommunityRating: Double? = null,
		minCriticRating: Double? = null,
		minPremiereDate: DateTime? = null,
		minDateLastSaved: DateTime? = null,
		minDateLastSavedForUser: DateTime? = null,
		maxPremiereDate: DateTime? = null,
		hasOverview: Boolean? = null,
		hasImdbId: Boolean? = null,
		hasTmdbId: Boolean? = null,
		hasTvdbId: Boolean? = null,
		isMovie: Boolean? = null,
		isSeries: Boolean? = null,
		isNews: Boolean? = null,
		isKids: Boolean? = null,
		isSports: Boolean? = null,
		excludeItemIds: Collection<UUID>? = emptyList(),
		startIndex: Int? = null,
		limit: Int? = null,
		recursive: Boolean? = null,
		searchTerm: String? = null,
		sortOrder: Collection<SortOrder>? = emptyList(),
		parentId: UUID? = null,
		fields: Collection<ItemFields>? = emptyList(),
		excludeItemTypes: Collection<BaseItemKind>? = emptyList(),
		includeItemTypes: Collection<BaseItemKind>? = emptyList(),
		filters: Collection<ItemFilter>? = emptyList(),
		isFavorite: Boolean? = null,
		mediaTypes: Collection<String>? = emptyList(),
		imageTypes: Collection<ImageType>? = emptyList(),
		sortBy: Collection<String>? = emptyList(),
		isPlayed: Boolean? = null,
		genres: Collection<String>? = emptyList(),
		officialRatings: Collection<String>? = emptyList(),
		tags: Collection<String>? = emptyList(),
		years: Collection<Int>? = emptyList(),
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
		person: String? = null,
		personIds: Collection<UUID>? = emptyList(),
		personTypes: Collection<String>? = emptyList(),
		studios: Collection<String>? = emptyList(),
		artists: Collection<String>? = emptyList(),
		excludeArtistIds: Collection<UUID>? = emptyList(),
		artistIds: Collection<UUID>? = emptyList(),
		albumArtistIds: Collection<UUID>? = emptyList(),
		contributingArtistIds: Collection<UUID>? = emptyList(),
		albums: Collection<String>? = emptyList(),
		albumIds: Collection<UUID>? = emptyList(),
		ids: Collection<UUID>? = emptyList(),
		videoTypes: Collection<VideoType>? = emptyList(),
		minOfficialRating: String? = null,
		isLocked: Boolean? = null,
		isPlaceHolder: Boolean? = null,
		hasOfficialRating: Boolean? = null,
		collapseBoxSetItems: Boolean? = null,
		minWidth: Int? = null,
		minHeight: Int? = null,
		maxWidth: Int? = null,
		maxHeight: Int? = null,
		is3d: Boolean? = null,
		seriesStatus: Collection<SeriesStatus>? = emptyList(),
		nameStartsWithOrGreater: String? = null,
		nameStartsWith: String? = null,
		nameLessThan: String? = null,
		studioIds: Collection<UUID>? = emptyList(),
		genreIds: Collection<UUID>? = emptyList(),
		enableTotalRecordCount: Boolean? = true,
		enableImages: Boolean? = true,
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(85) {
			put("userId", userId)
			put("maxOfficialRating", maxOfficialRating)
			put("hasThemeSong", hasThemeSong)
			put("hasThemeVideo", hasThemeVideo)
			put("hasSubtitles", hasSubtitles)
			put("hasSpecialFeature", hasSpecialFeature)
			put("hasTrailer", hasTrailer)
			put("adjacentTo", adjacentTo)
			put("parentIndexNumber", parentIndexNumber)
			put("hasParentalRating", hasParentalRating)
			put("isHd", isHd)
			put("is4K", is4k)
			put("locationTypes", locationTypes)
			put("excludeLocationTypes", excludeLocationTypes)
			put("isMissing", isMissing)
			put("isUnaired", isUnaired)
			put("minCommunityRating", minCommunityRating)
			put("minCriticRating", minCriticRating)
			put("minPremiereDate", minPremiereDate)
			put("minDateLastSaved", minDateLastSaved)
			put("minDateLastSavedForUser", minDateLastSavedForUser)
			put("maxPremiereDate", maxPremiereDate)
			put("hasOverview", hasOverview)
			put("hasImdbId", hasImdbId)
			put("hasTmdbId", hasTmdbId)
			put("hasTvdbId", hasTvdbId)
			put("isMovie", isMovie)
			put("isSeries", isSeries)
			put("isNews", isNews)
			put("isKids", isKids)
			put("isSports", isSports)
			put("excludeItemIds", excludeItemIds)
			put("startIndex", startIndex)
			put("limit", limit)
			put("recursive", recursive)
			put("searchTerm", searchTerm)
			put("sortOrder", sortOrder)
			put("parentId", parentId)
			put("fields", fields)
			put("excludeItemTypes", excludeItemTypes)
			put("includeItemTypes", includeItemTypes)
			put("filters", filters)
			put("isFavorite", isFavorite)
			put("mediaTypes", mediaTypes)
			put("imageTypes", imageTypes)
			put("sortBy", sortBy)
			put("isPlayed", isPlayed)
			put("genres", genres)
			put("officialRatings", officialRatings)
			put("tags", tags)
			put("years", years)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
			put("person", person)
			put("personIds", personIds)
			put("personTypes", personTypes)
			put("studios", studios)
			put("artists", artists)
			put("excludeArtistIds", excludeArtistIds)
			put("artistIds", artistIds)
			put("albumArtistIds", albumArtistIds)
			put("contributingArtistIds", contributingArtistIds)
			put("albums", albums)
			put("albumIds", albumIds)
			put("ids", ids)
			put("videoTypes", videoTypes)
			put("minOfficialRating", minOfficialRating)
			put("isLocked", isLocked)
			put("isPlaceHolder", isPlaceHolder)
			put("hasOfficialRating", hasOfficialRating)
			put("collapseBoxSetItems", collapseBoxSetItems)
			put("minWidth", minWidth)
			put("minHeight", minHeight)
			put("maxWidth", maxWidth)
			put("maxHeight", maxHeight)
			put("is3D", is3d)
			put("seriesStatus", seriesStatus)
			put("nameStartsWithOrGreater", nameStartsWithOrGreater)
			put("nameStartsWith", nameStartsWith)
			put("nameLessThan", nameLessThan)
			put("studioIds", studioIds)
			put("genreIds", genreIds)
			put("enableTotalRecordCount", enableTotalRecordCount)
			put("enableImages", enableImages)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Items", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets items based on a query.
	 *
	 * @param userId The user id supplied as query parameter.
	 * @param maxOfficialRating Optional filter by maximum official rating (PG, PG-13, TV-MA, etc).
	 * @param hasThemeSong Optional filter by items with theme songs.
	 * @param hasThemeVideo Optional filter by items with theme videos.
	 * @param hasSubtitles Optional filter by items with subtitles.
	 * @param hasSpecialFeature Optional filter by items with special features.
	 * @param hasTrailer Optional filter by items with trailers.
	 * @param adjacentTo Optional. Return items that are siblings of a supplied item.
	 * @param parentIndexNumber Optional filter by parent index number.
	 * @param hasParentalRating Optional filter by items that have or do not have a parental rating.
	 * @param isHd Optional filter by items that are HD or not.
	 * @param is4k Optional filter by items that are 4K or not.
	 * @param locationTypes Optional. If specified, results will be filtered based on LocationType.
	 * This allows multiple, comma delimited.
	 * @param excludeLocationTypes Optional. If specified, results will be filtered based on the
	 * LocationType. This allows multiple, comma delimited.
	 * @param isMissing Optional filter by items that are missing episodes or not.
	 * @param isUnaired Optional filter by items that are unaired episodes or not.
	 * @param minCommunityRating Optional filter by minimum community rating.
	 * @param minCriticRating Optional filter by minimum critic rating.
	 * @param minPremiereDate Optional. The minimum premiere date. Format = ISO.
	 * @param minDateLastSaved Optional. The minimum last saved date. Format = ISO.
	 * @param minDateLastSavedForUser Optional. The minimum last saved date for the current user.
	 * Format = ISO.
	 * @param maxPremiereDate Optional. The maximum premiere date. Format = ISO.
	 * @param hasOverview Optional filter by items that have an overview or not.
	 * @param hasImdbId Optional filter by items that have an imdb id or not.
	 * @param hasTmdbId Optional filter by items that have a tmdb id or not.
	 * @param hasTvdbId Optional filter by items that have a tvdb id or not.
	 * @param isMovie Optional filter for live tv movies.
	 * @param isSeries Optional filter for live tv series.
	 * @param isNews Optional filter for live tv news.
	 * @param isKids Optional filter for live tv kids.
	 * @param isSports Optional filter for live tv sports.
	 * @param excludeItemIds Optional. If specified, results will be filtered by excluding item ids.
	 * This allows multiple, comma delimited.
	 * @param startIndex Optional. The record index to start at. All items with a lower index will be
	 * dropped from the results.
	 * @param limit Optional. The maximum number of records to return.
	 * @param recursive When searching within folders, this determines whether or not the search will
	 * be recursive. true/false.
	 * @param searchTerm Optional. Filter based on a search term.
	 * @param sortOrder Sort Order - Ascending,Descending.
	 * @param parentId Specify this to localize the search to a specific item or folder. Omit to use
	 * the root.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimited. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines.
	 * @param excludeItemTypes Optional. If specified, results will be filtered based on item type.
	 * This allows multiple, comma delimited.
	 * @param includeItemTypes Optional. If specified, results will be filtered based on the item type.
	 * This allows multiple, comma delimited.
	 * @param filters Optional. Specify additional filters to apply. This allows multiple, comma
	 * delimited. Options: IsFolder, IsNotFolder, IsUnplayed, IsPlayed, IsFavorite, IsResumable, Likes,
	 * Dislikes.
	 * @param isFavorite Optional filter by items that are marked as favorite, or not.
	 * @param mediaTypes Optional filter by MediaType. Allows multiple, comma delimited.
	 * @param imageTypes Optional. If specified, results will be filtered based on those containing
	 * image types. This allows multiple, comma delimited.
	 * @param sortBy Optional. Specify one or more sort orders, comma delimited. Options: Album,
	 * AlbumArtist, Artist, Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount,
	 * PremiereDate, ProductionYear, SortName, Random, Revenue, Runtime.
	 * @param isPlayed Optional filter by items that are played, or not.
	 * @param genres Optional. If specified, results will be filtered based on genre. This allows
	 * multiple, pipe delimited.
	 * @param officialRatings Optional. If specified, results will be filtered based on OfficialRating.
	 * This allows multiple, pipe delimited.
	 * @param tags Optional. If specified, results will be filtered based on tag. This allows multiple,
	 * pipe delimited.
	 * @param years Optional. If specified, results will be filtered based on production year. This
	 * allows multiple, comma delimited.
	 * @param enableUserData Optional, include user data.
	 * @param imageTypeLimit Optional, the max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 * @param person Optional. If specified, results will be filtered to include only those containing
	 * the specified person.
	 * @param personIds Optional. If specified, results will be filtered to include only those
	 * containing the specified person id.
	 * @param personTypes Optional. If specified, along with Person, results will be filtered to
	 * include only those containing the specified person and PersonType. Allows multiple,
	 * comma-delimited.
	 * @param studios Optional. If specified, results will be filtered based on studio. This allows
	 * multiple, pipe delimited.
	 * @param artists Optional. If specified, results will be filtered based on artists. This allows
	 * multiple, pipe delimited.
	 * @param excludeArtistIds Optional. If specified, results will be filtered based on artist id.
	 * This allows multiple, pipe delimited.
	 * @param artistIds Optional. If specified, results will be filtered to include only those
	 * containing the specified artist id.
	 * @param albumArtistIds Optional. If specified, results will be filtered to include only those
	 * containing the specified album artist id.
	 * @param contributingArtistIds Optional. If specified, results will be filtered to include only
	 * those containing the specified contributing artist id.
	 * @param albums Optional. If specified, results will be filtered based on album. This allows
	 * multiple, pipe delimited.
	 * @param albumIds Optional. If specified, results will be filtered based on album id. This allows
	 * multiple, pipe delimited.
	 * @param ids Optional. If specific items are needed, specify a list of item id's to retrieve. This
	 * allows multiple, comma delimited.
	 * @param videoTypes Optional filter by VideoType (videofile, dvd, bluray, iso). Allows multiple,
	 * comma delimited.
	 * @param minOfficialRating Optional filter by minimum official rating (PG, PG-13, TV-MA, etc).
	 * @param isLocked Optional filter by items that are locked.
	 * @param isPlaceHolder Optional filter by items that are placeholders.
	 * @param hasOfficialRating Optional filter by items that have official ratings.
	 * @param collapseBoxSetItems Whether or not to hide items behind their boxsets.
	 * @param minWidth Optional. Filter by the minimum width of the item.
	 * @param minHeight Optional. Filter by the minimum height of the item.
	 * @param maxWidth Optional. Filter by the maximum width of the item.
	 * @param maxHeight Optional. Filter by the maximum height of the item.
	 * @param is3d Optional filter by items that are 3D, or not.
	 * @param seriesStatus Optional filter by Series Status. Allows multiple, comma delimited.
	 * @param nameStartsWithOrGreater Optional filter by items whose name is sorted equally or greater
	 * than a given input string.
	 * @param nameStartsWith Optional filter by items whose name is sorted equally than a given input
	 * string.
	 * @param nameLessThan Optional filter by items whose name is equally or lesser than a given input
	 * string.
	 * @param studioIds Optional. If specified, results will be filtered based on studio id. This
	 * allows multiple, pipe delimited.
	 * @param genreIds Optional. If specified, results will be filtered based on genre id. This allows
	 * multiple, pipe delimited.
	 * @param enableTotalRecordCount Optional. Enable the total record count.
	 * @param enableImages Optional, include image information in output.
	 */
	public suspend fun getItemsByUserId(
		userId: UUID = api.userId ?: throw MissingUserIdException(),
		maxOfficialRating: String? = null,
		hasThemeSong: Boolean? = null,
		hasThemeVideo: Boolean? = null,
		hasSubtitles: Boolean? = null,
		hasSpecialFeature: Boolean? = null,
		hasTrailer: Boolean? = null,
		adjacentTo: String? = null,
		parentIndexNumber: Int? = null,
		hasParentalRating: Boolean? = null,
		isHd: Boolean? = null,
		is4k: Boolean? = null,
		locationTypes: Collection<LocationType>? = emptyList(),
		excludeLocationTypes: Collection<LocationType>? = emptyList(),
		isMissing: Boolean? = null,
		isUnaired: Boolean? = null,
		minCommunityRating: Double? = null,
		minCriticRating: Double? = null,
		minPremiereDate: DateTime? = null,
		minDateLastSaved: DateTime? = null,
		minDateLastSavedForUser: DateTime? = null,
		maxPremiereDate: DateTime? = null,
		hasOverview: Boolean? = null,
		hasImdbId: Boolean? = null,
		hasTmdbId: Boolean? = null,
		hasTvdbId: Boolean? = null,
		isMovie: Boolean? = null,
		isSeries: Boolean? = null,
		isNews: Boolean? = null,
		isKids: Boolean? = null,
		isSports: Boolean? = null,
		excludeItemIds: Collection<UUID>? = emptyList(),
		startIndex: Int? = null,
		limit: Int? = null,
		recursive: Boolean? = null,
		searchTerm: String? = null,
		sortOrder: Collection<SortOrder>? = emptyList(),
		parentId: UUID? = null,
		fields: Collection<ItemFields>? = emptyList(),
		excludeItemTypes: Collection<BaseItemKind>? = emptyList(),
		includeItemTypes: Collection<BaseItemKind>? = emptyList(),
		filters: Collection<ItemFilter>? = emptyList(),
		isFavorite: Boolean? = null,
		mediaTypes: Collection<String>? = emptyList(),
		imageTypes: Collection<ImageType>? = emptyList(),
		sortBy: Collection<String>? = emptyList(),
		isPlayed: Boolean? = null,
		genres: Collection<String>? = emptyList(),
		officialRatings: Collection<String>? = emptyList(),
		tags: Collection<String>? = emptyList(),
		years: Collection<Int>? = emptyList(),
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
		person: String? = null,
		personIds: Collection<UUID>? = emptyList(),
		personTypes: Collection<String>? = emptyList(),
		studios: Collection<String>? = emptyList(),
		artists: Collection<String>? = emptyList(),
		excludeArtistIds: Collection<UUID>? = emptyList(),
		artistIds: Collection<UUID>? = emptyList(),
		albumArtistIds: Collection<UUID>? = emptyList(),
		contributingArtistIds: Collection<UUID>? = emptyList(),
		albums: Collection<String>? = emptyList(),
		albumIds: Collection<UUID>? = emptyList(),
		ids: Collection<UUID>? = emptyList(),
		videoTypes: Collection<VideoType>? = emptyList(),
		minOfficialRating: String? = null,
		isLocked: Boolean? = null,
		isPlaceHolder: Boolean? = null,
		hasOfficialRating: Boolean? = null,
		collapseBoxSetItems: Boolean? = null,
		minWidth: Int? = null,
		minHeight: Int? = null,
		maxWidth: Int? = null,
		maxHeight: Int? = null,
		is3d: Boolean? = null,
		seriesStatus: Collection<SeriesStatus>? = emptyList(),
		nameStartsWithOrGreater: String? = null,
		nameStartsWith: String? = null,
		nameLessThan: String? = null,
		studioIds: Collection<UUID>? = emptyList(),
		genreIds: Collection<UUID>? = emptyList(),
		enableTotalRecordCount: Boolean? = true,
		enableImages: Boolean? = true,
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = buildMap<String, Any?>(84) {
			put("maxOfficialRating", maxOfficialRating)
			put("hasThemeSong", hasThemeSong)
			put("hasThemeVideo", hasThemeVideo)
			put("hasSubtitles", hasSubtitles)
			put("hasSpecialFeature", hasSpecialFeature)
			put("hasTrailer", hasTrailer)
			put("adjacentTo", adjacentTo)
			put("parentIndexNumber", parentIndexNumber)
			put("hasParentalRating", hasParentalRating)
			put("isHd", isHd)
			put("is4K", is4k)
			put("locationTypes", locationTypes)
			put("excludeLocationTypes", excludeLocationTypes)
			put("isMissing", isMissing)
			put("isUnaired", isUnaired)
			put("minCommunityRating", minCommunityRating)
			put("minCriticRating", minCriticRating)
			put("minPremiereDate", minPremiereDate)
			put("minDateLastSaved", minDateLastSaved)
			put("minDateLastSavedForUser", minDateLastSavedForUser)
			put("maxPremiereDate", maxPremiereDate)
			put("hasOverview", hasOverview)
			put("hasImdbId", hasImdbId)
			put("hasTmdbId", hasTmdbId)
			put("hasTvdbId", hasTvdbId)
			put("isMovie", isMovie)
			put("isSeries", isSeries)
			put("isNews", isNews)
			put("isKids", isKids)
			put("isSports", isSports)
			put("excludeItemIds", excludeItemIds)
			put("startIndex", startIndex)
			put("limit", limit)
			put("recursive", recursive)
			put("searchTerm", searchTerm)
			put("sortOrder", sortOrder)
			put("parentId", parentId)
			put("fields", fields)
			put("excludeItemTypes", excludeItemTypes)
			put("includeItemTypes", includeItemTypes)
			put("filters", filters)
			put("isFavorite", isFavorite)
			put("mediaTypes", mediaTypes)
			put("imageTypes", imageTypes)
			put("sortBy", sortBy)
			put("isPlayed", isPlayed)
			put("genres", genres)
			put("officialRatings", officialRatings)
			put("tags", tags)
			put("years", years)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
			put("person", person)
			put("personIds", personIds)
			put("personTypes", personTypes)
			put("studios", studios)
			put("artists", artists)
			put("excludeArtistIds", excludeArtistIds)
			put("artistIds", artistIds)
			put("albumArtistIds", albumArtistIds)
			put("contributingArtistIds", contributingArtistIds)
			put("albums", albums)
			put("albumIds", albumIds)
			put("ids", ids)
			put("videoTypes", videoTypes)
			put("minOfficialRating", minOfficialRating)
			put("isLocked", isLocked)
			put("isPlaceHolder", isPlaceHolder)
			put("hasOfficialRating", hasOfficialRating)
			put("collapseBoxSetItems", collapseBoxSetItems)
			put("minWidth", minWidth)
			put("minHeight", minHeight)
			put("maxWidth", maxWidth)
			put("maxHeight", maxHeight)
			put("is3D", is3d)
			put("seriesStatus", seriesStatus)
			put("nameStartsWithOrGreater", nameStartsWithOrGreater)
			put("nameStartsWith", nameStartsWith)
			put("nameLessThan", nameLessThan)
			put("studioIds", studioIds)
			put("genreIds", genreIds)
			put("enableTotalRecordCount", enableTotalRecordCount)
			put("enableImages", enableImages)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Users/{userId}/Items", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets items based on a query.
	 *
	 * @param userId The user id.
	 * @param startIndex The start index.
	 * @param limit The item limit.
	 * @param searchTerm The search term.
	 * @param parentId Specify this to localize the search to a specific item or folder. Omit to use
	 * the root.
	 * @param fields Optional. Specify additional fields of information to return in the output. This
	 * allows multiple, comma delimited. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines.
	 * @param mediaTypes Optional. Filter by MediaType. Allows multiple, comma delimited.
	 * @param enableUserData Optional. Include user data.
	 * @param imageTypeLimit Optional. The max number of images to return, per image type.
	 * @param enableImageTypes Optional. The image types to include in the output.
	 * @param excludeItemTypes Optional. If specified, results will be filtered based on item type.
	 * This allows multiple, comma delimited.
	 * @param includeItemTypes Optional. If specified, results will be filtered based on the item type.
	 * This allows multiple, comma delimited.
	 * @param enableTotalRecordCount Optional. Enable the total record count.
	 * @param enableImages Optional. Include image information in output.
	 * @param excludeActiveSessions Optional. Whether to exclude the currently active sessions.
	 */
	public suspend fun getResumeItems(
		userId: UUID = api.userId ?: throw MissingUserIdException(),
		startIndex: Int? = null,
		limit: Int? = null,
		searchTerm: String? = null,
		parentId: UUID? = null,
		fields: Collection<ItemFields>? = emptyList(),
		mediaTypes: Collection<String>? = emptyList(),
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: Collection<ImageType>? = emptyList(),
		excludeItemTypes: Collection<BaseItemKind>? = emptyList(),
		includeItemTypes: Collection<BaseItemKind>? = emptyList(),
		enableTotalRecordCount: Boolean? = true,
		enableImages: Boolean? = true,
		excludeActiveSessions: Boolean? = false,
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = buildMap<String, Any?>(14) {
			put("startIndex", startIndex)
			put("limit", limit)
			put("searchTerm", searchTerm)
			put("parentId", parentId)
			put("fields", fields)
			put("mediaTypes", mediaTypes)
			put("enableUserData", enableUserData)
			put("imageTypeLimit", imageTypeLimit)
			put("enableImageTypes", enableImageTypes)
			put("excludeItemTypes", excludeItemTypes)
			put("includeItemTypes", includeItemTypes)
			put("enableTotalRecordCount", enableTotalRecordCount)
			put("enableImages", enableImages)
			put("excludeActiveSessions", excludeActiveSessions)
		}
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Users/{userId}/Items/Resume", pathParameters,
				queryParameters, data)
		return response
	}
}
