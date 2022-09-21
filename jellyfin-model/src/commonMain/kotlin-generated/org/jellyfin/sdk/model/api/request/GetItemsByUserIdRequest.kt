// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	UUIDSerializer::class,
	DateTimeSerializer::class,
)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemFilter
import org.jellyfin.sdk.model.api.LocationType
import org.jellyfin.sdk.model.api.SeriesStatus
import org.jellyfin.sdk.model.api.SortOrder
import org.jellyfin.sdk.model.api.VideoType
import org.jellyfin.sdk.model.serializer.DateTimeSerializer
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Items based on a query.
 */
@Serializable
public data class GetItemsByUserIdRequest(
	/**
	 * The user id supplied as query parameter.
	 */
	@SerialName("userId")
	public val userId: UUID,
	/**
	 * Optional filter by maximum official rating (PG, PG-13, TV-MA, etc).
	 */
	@SerialName("maxOfficialRating")
	public val maxOfficialRating: String? = null,
	/**
	 * Optional filter by items with theme songs.
	 */
	@SerialName("hasThemeSong")
	public val hasThemeSong: Boolean? = null,
	/**
	 * Optional filter by items with theme videos.
	 */
	@SerialName("hasThemeVideo")
	public val hasThemeVideo: Boolean? = null,
	/**
	 * Optional filter by items with subtitles.
	 */
	@SerialName("hasSubtitles")
	public val hasSubtitles: Boolean? = null,
	/**
	 * Optional filter by items with special features.
	 */
	@SerialName("hasSpecialFeature")
	public val hasSpecialFeature: Boolean? = null,
	/**
	 * Optional filter by items with trailers.
	 */
	@SerialName("hasTrailer")
	public val hasTrailer: Boolean? = null,
	/**
	 * Optional. Return items that are siblings of a supplied item.
	 */
	@SerialName("adjacentTo")
	public val adjacentTo: String? = null,
	/**
	 * Optional filter by parent index number.
	 */
	@SerialName("parentIndexNumber")
	public val parentIndexNumber: Int? = null,
	/**
	 * Optional filter by items that have or do not have a parental rating.
	 */
	@SerialName("hasParentalRating")
	public val hasParentalRating: Boolean? = null,
	/**
	 * Optional filter by items that are HD or not.
	 */
	@SerialName("isHd")
	public val isHd: Boolean? = null,
	/**
	 * Optional filter by items that are 4K or not.
	 */
	@SerialName("is4K")
	public val is4k: Boolean? = null,
	/**
	 * Optional. If specified, results will be filtered based on LocationType. This allows multiple,
	 * comma delimited.
	 */
	@SerialName("locationTypes")
	public val locationTypes: Collection<LocationType>? = null,
	/**
	 * Optional. If specified, results will be filtered based on the LocationType. This allows
	 * multiple, comma delimited.
	 */
	@SerialName("excludeLocationTypes")
	public val excludeLocationTypes: Collection<LocationType>? = null,
	/**
	 * Optional filter by items that are missing episodes or not.
	 */
	@SerialName("isMissing")
	public val isMissing: Boolean? = null,
	/**
	 * Optional filter by items that are unaired episodes or not.
	 */
	@SerialName("isUnaired")
	public val isUnaired: Boolean? = null,
	/**
	 * Optional filter by minimum community rating.
	 */
	@SerialName("minCommunityRating")
	public val minCommunityRating: Double? = null,
	/**
	 * Optional filter by minimum critic rating.
	 */
	@SerialName("minCriticRating")
	public val minCriticRating: Double? = null,
	/**
	 * Optional. The minimum premiere date. Format = ISO.
	 */
	@SerialName("minPremiereDate")
	public val minPremiereDate: DateTime? = null,
	/**
	 * Optional. The minimum last saved date. Format = ISO.
	 */
	@SerialName("minDateLastSaved")
	public val minDateLastSaved: DateTime? = null,
	/**
	 * Optional. The minimum last saved date for the current user. Format = ISO.
	 */
	@SerialName("minDateLastSavedForUser")
	public val minDateLastSavedForUser: DateTime? = null,
	/**
	 * Optional. The maximum premiere date. Format = ISO.
	 */
	@SerialName("maxPremiereDate")
	public val maxPremiereDate: DateTime? = null,
	/**
	 * Optional filter by items that have an overview or not.
	 */
	@SerialName("hasOverview")
	public val hasOverview: Boolean? = null,
	/**
	 * Optional filter by items that have an imdb id or not.
	 */
	@SerialName("hasImdbId")
	public val hasImdbId: Boolean? = null,
	/**
	 * Optional filter by items that have a tmdb id or not.
	 */
	@SerialName("hasTmdbId")
	public val hasTmdbId: Boolean? = null,
	/**
	 * Optional filter by items that have a tvdb id or not.
	 */
	@SerialName("hasTvdbId")
	public val hasTvdbId: Boolean? = null,
	/**
	 * Optional filter for live tv movies.
	 */
	@SerialName("isMovie")
	public val isMovie: Boolean? = null,
	/**
	 * Optional filter for live tv series.
	 */
	@SerialName("isSeries")
	public val isSeries: Boolean? = null,
	/**
	 * Optional filter for live tv news.
	 */
	@SerialName("isNews")
	public val isNews: Boolean? = null,
	/**
	 * Optional filter for live tv kids.
	 */
	@SerialName("isKids")
	public val isKids: Boolean? = null,
	/**
	 * Optional filter for live tv sports.
	 */
	@SerialName("isSports")
	public val isSports: Boolean? = null,
	/**
	 * Optional. If specified, results will be filtered by excluding item ids. This allows multiple,
	 * comma delimited.
	 */
	@SerialName("excludeItemIds")
	public val excludeItemIds: Collection<UUID>? = null,
	/**
	 * Optional. The record index to start at. All items with a lower index will be dropped from the
	 * results.
	 */
	@SerialName("startIndex")
	public val startIndex: Int? = null,
	/**
	 * Optional. The maximum number of records to return.
	 */
	@SerialName("limit")
	public val limit: Int? = null,
	/**
	 * When searching within folders, this determines whether or not the search will be recursive.
	 * true/false.
	 */
	@SerialName("recursive")
	public val recursive: Boolean? = null,
	/**
	 * Optional. Filter based on a search term.
	 */
	@SerialName("searchTerm")
	public val searchTerm: String? = null,
	/**
	 * Sort Order - Ascending,Descending.
	 */
	@SerialName("sortOrder")
	public val sortOrder: Collection<SortOrder>? = null,
	/**
	 * Specify this to localize the search to a specific item or folder. Omit to use the root.
	 */
	@SerialName("parentId")
	public val parentId: UUID? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output. This allows
	 * multiple, comma delimited. Options: Budget, Chapters, DateCreated, Genres, HomePageUrl,
	 * IndexOptions, MediaStreams, Overview, ParentId, Path, People, ProviderIds, PrimaryImageAspectRatio,
	 * Revenue, SortName, Studios, Taglines.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional. If specified, results will be filtered based on item type. This allows multiple, comma
	 * delimited.
	 */
	@SerialName("excludeItemTypes")
	public val excludeItemTypes: Collection<BaseItemKind>? = null,
	/**
	 * Optional. If specified, results will be filtered based on the item type. This allows multiple,
	 * comma delimited.
	 */
	@SerialName("includeItemTypes")
	public val includeItemTypes: Collection<BaseItemKind>? = null,
	/**
	 * Optional. Specify additional filters to apply. This allows multiple, comma delimited. Options:
	 * IsFolder, IsNotFolder, IsUnplayed, IsPlayed, IsFavorite, IsResumable, Likes, Dislikes.
	 */
	@SerialName("filters")
	public val filters: Collection<ItemFilter>? = null,
	/**
	 * Optional filter by items that are marked as favorite, or not.
	 */
	@SerialName("isFavorite")
	public val isFavorite: Boolean? = null,
	/**
	 * Optional filter by MediaType. Allows multiple, comma delimited.
	 */
	@SerialName("mediaTypes")
	public val mediaTypes: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on those containing image types. This
	 * allows multiple, comma delimited.
	 */
	@SerialName("imageTypes")
	public val imageTypes: Collection<ImageType>? = null,
	/**
	 * Optional. Specify one or more sort orders, comma delimited. Options: Album, AlbumArtist, Artist,
	 * Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount, PremiereDate,
	 * ProductionYear, SortName, Random, Revenue, Runtime.
	 */
	@SerialName("sortBy")
	public val sortBy: Collection<String>? = null,
	/**
	 * Optional filter by items that are played, or not.
	 */
	@SerialName("isPlayed")
	public val isPlayed: Boolean? = null,
	/**
	 * Optional. If specified, results will be filtered based on genre. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("genres")
	public val genres: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on OfficialRating. This allows multiple,
	 * pipe delimited.
	 */
	@SerialName("officialRatings")
	public val officialRatings: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on tag. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("tags")
	public val tags: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on production year. This allows multiple,
	 * comma delimited.
	 */
	@SerialName("years")
	public val years: Collection<Int>? = null,
	/**
	 * Optional, include user data.
	 */
	@SerialName("enableUserData")
	public val enableUserData: Boolean? = null,
	/**
	 * Optional, the max number of images to return, per image type.
	 */
	@SerialName("imageTypeLimit")
	public val imageTypeLimit: Int? = null,
	/**
	 * Optional. The image types to include in the output.
	 */
	@SerialName("enableImageTypes")
	public val enableImageTypes: Collection<ImageType>? = null,
	/**
	 * Optional. If specified, results will be filtered to include only those containing the specified
	 * person.
	 */
	@SerialName("person")
	public val person: String? = null,
	/**
	 * Optional. If specified, results will be filtered to include only those containing the specified
	 * person id.
	 */
	@SerialName("personIds")
	public val personIds: Collection<UUID>? = null,
	/**
	 * Optional. If specified, along with Person, results will be filtered to include only those
	 * containing the specified person and PersonType. Allows multiple, comma-delimited.
	 */
	@SerialName("personTypes")
	public val personTypes: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on studio. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("studios")
	public val studios: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on artists. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("artists")
	public val artists: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on artist id. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("excludeArtistIds")
	public val excludeArtistIds: Collection<UUID>? = null,
	/**
	 * Optional. If specified, results will be filtered to include only those containing the specified
	 * artist id.
	 */
	@SerialName("artistIds")
	public val artistIds: Collection<UUID>? = null,
	/**
	 * Optional. If specified, results will be filtered to include only those containing the specified
	 * album artist id.
	 */
	@SerialName("albumArtistIds")
	public val albumArtistIds: Collection<UUID>? = null,
	/**
	 * Optional. If specified, results will be filtered to include only those containing the specified
	 * contributing artist id.
	 */
	@SerialName("contributingArtistIds")
	public val contributingArtistIds: Collection<UUID>? = null,
	/**
	 * Optional. If specified, results will be filtered based on album. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("albums")
	public val albums: Collection<String>? = null,
	/**
	 * Optional. If specified, results will be filtered based on album id. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("albumIds")
	public val albumIds: Collection<UUID>? = null,
	/**
	 * Optional. If specific items are needed, specify a list of item id's to retrieve. This allows
	 * multiple, comma delimited.
	 */
	@SerialName("ids")
	public val ids: Collection<UUID>? = null,
	/**
	 * Optional filter by VideoType (videofile, dvd, bluray, iso). Allows multiple, comma delimited.
	 */
	@SerialName("videoTypes")
	public val videoTypes: Collection<VideoType>? = null,
	/**
	 * Optional filter by minimum official rating (PG, PG-13, TV-MA, etc).
	 */
	@SerialName("minOfficialRating")
	public val minOfficialRating: String? = null,
	/**
	 * Optional filter by items that are locked.
	 */
	@SerialName("isLocked")
	public val isLocked: Boolean? = null,
	/**
	 * Optional filter by items that are placeholders.
	 */
	@SerialName("isPlaceHolder")
	public val isPlaceHolder: Boolean? = null,
	/**
	 * Optional filter by items that have official ratings.
	 */
	@SerialName("hasOfficialRating")
	public val hasOfficialRating: Boolean? = null,
	/**
	 * Whether or not to hide items behind their boxsets.
	 */
	@SerialName("collapseBoxSetItems")
	public val collapseBoxSetItems: Boolean? = null,
	/**
	 * Optional. Filter by the minimum width of the item.
	 */
	@SerialName("minWidth")
	public val minWidth: Int? = null,
	/**
	 * Optional. Filter by the minimum height of the item.
	 */
	@SerialName("minHeight")
	public val minHeight: Int? = null,
	/**
	 * Optional. Filter by the maximum width of the item.
	 */
	@SerialName("maxWidth")
	public val maxWidth: Int? = null,
	/**
	 * Optional. Filter by the maximum height of the item.
	 */
	@SerialName("maxHeight")
	public val maxHeight: Int? = null,
	/**
	 * Optional filter by items that are 3D, or not.
	 */
	@SerialName("is3D")
	public val is3d: Boolean? = null,
	/**
	 * Optional filter by Series Status. Allows multiple, comma delimited.
	 */
	@SerialName("seriesStatus")
	public val seriesStatus: Collection<SeriesStatus>? = null,
	/**
	 * Optional filter by items whose name is sorted equally or greater than a given input string.
	 */
	@SerialName("nameStartsWithOrGreater")
	public val nameStartsWithOrGreater: String? = null,
	/**
	 * Optional filter by items whose name is sorted equally than a given input string.
	 */
	@SerialName("nameStartsWith")
	public val nameStartsWith: String? = null,
	/**
	 * Optional filter by items whose name is equally or lesser than a given input string.
	 */
	@SerialName("nameLessThan")
	public val nameLessThan: String? = null,
	/**
	 * Optional. If specified, results will be filtered based on studio id. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("studioIds")
	public val studioIds: Collection<UUID>? = null,
	/**
	 * Optional. If specified, results will be filtered based on genre id. This allows multiple, pipe
	 * delimited.
	 */
	@SerialName("genreIds")
	public val genreIds: Collection<UUID>? = null,
	/**
	 * Optional. Enable the total record count.
	 */
	@SerialName("enableTotalRecordCount")
	public val enableTotalRecordCount: Boolean? = true,
	/**
	 * Optional, include image information in output.
	 */
	@SerialName("enableImages")
	public val enableImages: Boolean? = true,
)
