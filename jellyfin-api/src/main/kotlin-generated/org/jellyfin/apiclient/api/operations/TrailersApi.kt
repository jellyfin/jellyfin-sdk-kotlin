// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.api.operations

import java.time.LocalDateTime
import java.util.UUID
import kotlin.Any
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.model.api.BaseItemDtoQueryResult
import org.jellyfin.apiclient.model.api.ItemFilter
import org.jellyfin.apiclient.model.api.LocationType

public class TrailersApi(
	private val api: KtorClient
) {
	/**
	 * Finds movies and trailers similar to a given trailer.
	 *
	 * @param userId The user id.
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
	 * This allows multiple, comma delimeted.
	 * @param excludeLocationTypes Optional. If specified, results will be filtered based on the
	 * LocationType. This allows multiple, comma delimeted.
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
	 * @param excludeItemIds Optional. If specified, results will be filtered by exxcluding item ids.
	 * This allows multiple, comma delimeted.
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
	 * allows multiple, comma delimeted. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines.
	 * @param excludeItemTypes Optional. If specified, results will be filtered based on item type.
	 * This allows multiple, comma delimeted.
	 * @param filters Optional. Specify additional filters to apply. This allows multiple, comma
	 * delimeted. Options: IsFolder, IsNotFolder, IsUnplayed, IsPlayed, IsFavorite, IsResumable, Likes,
	 * Dislikes.
	 * @param isFavorite Optional filter by items that are marked as favorite, or not.
	 * @param mediaTypes Optional filter by MediaType. Allows multiple, comma delimited.
	 * @param imageTypes Optional. If specified, results will be filtered based on those containing
	 * image types. This allows multiple, comma delimited.
	 * @param sortBy Optional. Specify one or more sort orders, comma delimeted. Options: Album,
	 * AlbumArtist, Artist, Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount,
	 * PremiereDate, ProductionYear, SortName, Random, Revenue, Runtime.
	 * @param isPlayed Optional filter by items that are played, or not.
	 * @param genres Optional. If specified, results will be filtered based on genre. This allows
	 * multiple, pipe delimeted.
	 * @param officialRatings Optional. If specified, results will be filtered based on OfficialRating.
	 * This allows multiple, pipe delimeted.
	 * @param tags Optional. If specified, results will be filtered based on tag. This allows multiple,
	 * pipe delimeted.
	 * @param years Optional. If specified, results will be filtered based on production year. This
	 * allows multiple, comma delimeted.
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
	 * multiple, pipe delimeted.
	 * @param artists Optional. If specified, results will be filtered based on artists. This allows
	 * multiple, pipe delimeted.
	 * @param excludeArtistIds Optional. If specified, results will be filtered based on artist id.
	 * This allows multiple, pipe delimeted.
	 * @param artistIds Optional. If specified, results will be filtered to include only those
	 * containing the specified artist id.
	 * @param albumArtistIds Optional. If specified, results will be filtered to include only those
	 * containing the specified album artist id.
	 * @param contributingArtistIds Optional. If specified, results will be filtered to include only
	 * those containing the specified contributing artist id.
	 * @param albums Optional. If specified, results will be filtered based on album. This allows
	 * multiple, pipe delimeted.
	 * @param albumIds Optional. If specified, results will be filtered based on album id. This allows
	 * multiple, pipe delimeted.
	 * @param ids Optional. If specific items are needed, specify a list of item id's to retrieve. This
	 * allows multiple, comma delimited.
	 * @param videoTypes Optional filter by VideoType (videofile, dvd, bluray, iso). Allows multiple,
	 * comma delimeted.
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
	 * @param seriesStatus Optional filter by Series Status. Allows multiple, comma delimeted.
	 * @param nameStartsWithOrGreater Optional filter by items whose name is sorted equally or greater
	 * than a given input string.
	 * @param nameStartsWith Optional filter by items whose name is sorted equally than a given input
	 * string.
	 * @param nameLessThan Optional filter by items whose name is equally or lesser than a given input
	 * string.
	 * @param studioIds Optional. If specified, results will be filtered based on studio id. This
	 * allows multiple, pipe delimeted.
	 * @param genreIds Optional. If specified, results will be filtered based on genre id. This allows
	 * multiple, pipe delimeted.
	 * @param enableTotalRecordCount Optional. Enable the total record count.
	 * @param enableImages Optional, include image information in output.
	 */
	public suspend fun getTrailers(
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
		locationTypes: String? = null,
		excludeLocationTypes: List<LocationType>? = emptyList(),
		isMissing: Boolean? = null,
		isUnaired: Boolean? = null,
		minCommunityRating: Double? = null,
		minCriticRating: Double? = null,
		minPremiereDate: LocalDateTime? = null,
		minDateLastSaved: LocalDateTime? = null,
		minDateLastSavedForUser: LocalDateTime? = null,
		maxPremiereDate: LocalDateTime? = null,
		hasOverview: Boolean? = null,
		hasImdbId: Boolean? = null,
		hasTmdbId: Boolean? = null,
		hasTvdbId: Boolean? = null,
		excludeItemIds: String? = null,
		startIndex: Int? = null,
		limit: Int? = null,
		recursive: Boolean? = null,
		searchTerm: String? = null,
		sortOrder: String? = null,
		parentId: String? = null,
		fields: String? = null,
		excludeItemTypes: String? = null,
		filters: List<ItemFilter>? = emptyList(),
		isFavorite: Boolean? = null,
		mediaTypes: String? = null,
		imageTypes: String? = null,
		sortBy: String? = null,
		isPlayed: Boolean? = null,
		genres: String? = null,
		officialRatings: String? = null,
		tags: String? = null,
		years: String? = null,
		enableUserData: Boolean? = null,
		imageTypeLimit: Int? = null,
		enableImageTypes: String? = null,
		person: String? = null,
		personIds: String? = null,
		personTypes: String? = null,
		studios: String? = null,
		artists: String? = null,
		excludeArtistIds: String? = null,
		artistIds: String? = null,
		albumArtistIds: String? = null,
		contributingArtistIds: String? = null,
		albums: String? = null,
		albumIds: String? = null,
		ids: String? = null,
		videoTypes: String? = null,
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
		seriesStatus: String? = null,
		nameStartsWithOrGreater: String? = null,
		nameStartsWith: String? = null,
		nameLessThan: String? = null,
		studioIds: String? = null,
		genreIds: String? = null,
		enableTotalRecordCount: Boolean = true,
		enableImages: Boolean? = true
	): Response<BaseItemDtoQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["userId"] = userId
		queryParameters["maxOfficialRating"] = maxOfficialRating
		queryParameters["hasThemeSong"] = hasThemeSong
		queryParameters["hasThemeVideo"] = hasThemeVideo
		queryParameters["hasSubtitles"] = hasSubtitles
		queryParameters["hasSpecialFeature"] = hasSpecialFeature
		queryParameters["hasTrailer"] = hasTrailer
		queryParameters["adjacentTo"] = adjacentTo
		queryParameters["parentIndexNumber"] = parentIndexNumber
		queryParameters["hasParentalRating"] = hasParentalRating
		queryParameters["isHd"] = isHd
		queryParameters["is4K"] = is4k
		queryParameters["locationTypes"] = locationTypes
		queryParameters["excludeLocationTypes"] = excludeLocationTypes
		queryParameters["isMissing"] = isMissing
		queryParameters["isUnaired"] = isUnaired
		queryParameters["minCommunityRating"] = minCommunityRating
		queryParameters["minCriticRating"] = minCriticRating
		queryParameters["minPremiereDate"] = minPremiereDate
		queryParameters["minDateLastSaved"] = minDateLastSaved
		queryParameters["minDateLastSavedForUser"] = minDateLastSavedForUser
		queryParameters["maxPremiereDate"] = maxPremiereDate
		queryParameters["hasOverview"] = hasOverview
		queryParameters["hasImdbId"] = hasImdbId
		queryParameters["hasTmdbId"] = hasTmdbId
		queryParameters["hasTvdbId"] = hasTvdbId
		queryParameters["excludeItemIds"] = excludeItemIds
		queryParameters["startIndex"] = startIndex
		queryParameters["limit"] = limit
		queryParameters["recursive"] = recursive
		queryParameters["searchTerm"] = searchTerm
		queryParameters["sortOrder"] = sortOrder
		queryParameters["parentId"] = parentId
		queryParameters["fields"] = fields
		queryParameters["excludeItemTypes"] = excludeItemTypes
		queryParameters["filters"] = filters
		queryParameters["isFavorite"] = isFavorite
		queryParameters["mediaTypes"] = mediaTypes
		queryParameters["imageTypes"] = imageTypes
		queryParameters["sortBy"] = sortBy
		queryParameters["isPlayed"] = isPlayed
		queryParameters["genres"] = genres
		queryParameters["officialRatings"] = officialRatings
		queryParameters["tags"] = tags
		queryParameters["years"] = years
		queryParameters["enableUserData"] = enableUserData
		queryParameters["imageTypeLimit"] = imageTypeLimit
		queryParameters["enableImageTypes"] = enableImageTypes
		queryParameters["person"] = person
		queryParameters["personIds"] = personIds
		queryParameters["personTypes"] = personTypes
		queryParameters["studios"] = studios
		queryParameters["artists"] = artists
		queryParameters["excludeArtistIds"] = excludeArtistIds
		queryParameters["artistIds"] = artistIds
		queryParameters["albumArtistIds"] = albumArtistIds
		queryParameters["contributingArtistIds"] = contributingArtistIds
		queryParameters["albums"] = albums
		queryParameters["albumIds"] = albumIds
		queryParameters["ids"] = ids
		queryParameters["videoTypes"] = videoTypes
		queryParameters["minOfficialRating"] = minOfficialRating
		queryParameters["isLocked"] = isLocked
		queryParameters["isPlaceHolder"] = isPlaceHolder
		queryParameters["hasOfficialRating"] = hasOfficialRating
		queryParameters["collapseBoxSetItems"] = collapseBoxSetItems
		queryParameters["minWidth"] = minWidth
		queryParameters["minHeight"] = minHeight
		queryParameters["maxWidth"] = maxWidth
		queryParameters["maxHeight"] = maxHeight
		queryParameters["is3D"] = is3d
		queryParameters["seriesStatus"] = seriesStatus
		queryParameters["nameStartsWithOrGreater"] = nameStartsWithOrGreater
		queryParameters["nameStartsWith"] = nameStartsWith
		queryParameters["nameLessThan"] = nameLessThan
		queryParameters["studioIds"] = studioIds
		queryParameters["genreIds"] = genreIds
		queryParameters["enableTotalRecordCount"] = enableTotalRecordCount
		queryParameters["enableImages"] = enableImages
		val data = null
		val response = api.`get`<BaseItemDtoQueryResult>("/Trailers", pathParameters, queryParameters,
				data)
		return response
	}
}
