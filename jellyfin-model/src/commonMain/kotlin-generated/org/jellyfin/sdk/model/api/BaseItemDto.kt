// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(
	UUIDSerializer::class,
	DateTimeSerializer::class,
)

package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.DateTimeSerializer
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * This is strictly used as a data transfer object from the api layer.
 * This holds information about a BaseItem in a format that is convenient for the client.
 */
@Serializable
public data class BaseItemDto(
	/**
	 * The name.
	 */
	@SerialName("Name")
	public val name: String? = null,
	@SerialName("OriginalTitle")
	public val originalTitle: String? = null,
	/**
	 * The server identifier.
	 */
	@SerialName("ServerId")
	public val serverId: String? = null,
	/**
	 * The id.
	 */
	@SerialName("Id")
	public val id: UUID,
	/**
	 * The etag.
	 */
	@SerialName("Etag")
	public val etag: String? = null,
	/**
	 * The type of the source.
	 */
	@SerialName("SourceType")
	public val sourceType: String? = null,
	/**
	 * The playlist item identifier.
	 */
	@SerialName("PlaylistItemId")
	public val playlistItemId: String? = null,
	/**
	 * The date created.
	 */
	@SerialName("DateCreated")
	public val dateCreated: DateTime? = null,
	@SerialName("DateLastMediaAdded")
	public val dateLastMediaAdded: DateTime? = null,
	@SerialName("ExtraType")
	public val extraType: ExtraType? = null,
	@SerialName("AirsBeforeSeasonNumber")
	public val airsBeforeSeasonNumber: Int? = null,
	@SerialName("AirsAfterSeasonNumber")
	public val airsAfterSeasonNumber: Int? = null,
	@SerialName("AirsBeforeEpisodeNumber")
	public val airsBeforeEpisodeNumber: Int? = null,
	@SerialName("CanDelete")
	public val canDelete: Boolean? = null,
	@SerialName("CanDownload")
	public val canDownload: Boolean? = null,
	@SerialName("HasLyrics")
	public val hasLyrics: Boolean? = null,
	@SerialName("HasSubtitles")
	public val hasSubtitles: Boolean? = null,
	@SerialName("PreferredMetadataLanguage")
	public val preferredMetadataLanguage: String? = null,
	@SerialName("PreferredMetadataCountryCode")
	public val preferredMetadataCountryCode: String? = null,
	@SerialName("Container")
	public val container: String? = null,
	/**
	 * The name of the sort.
	 */
	@SerialName("SortName")
	public val sortName: String? = null,
	@SerialName("ForcedSortName")
	public val forcedSortName: String? = null,
	/**
	 * The video3 D format.
	 */
	@SerialName("Video3DFormat")
	public val video3dFormat: Video3dFormat? = null,
	/**
	 * The premiere date.
	 */
	@SerialName("PremiereDate")
	public val premiereDate: DateTime? = null,
	/**
	 * The external urls.
	 */
	@SerialName("ExternalUrls")
	public val externalUrls: List<ExternalUrl>? = null,
	/**
	 * The media versions.
	 */
	@SerialName("MediaSources")
	public val mediaSources: List<MediaSourceInfo>? = null,
	/**
	 * The critic rating.
	 */
	@SerialName("CriticRating")
	public val criticRating: Float? = null,
	@SerialName("ProductionLocations")
	public val productionLocations: List<String>? = null,
	/**
	 * The path.
	 */
	@SerialName("Path")
	public val path: String? = null,
	@SerialName("EnableMediaSourceDisplay")
	public val enableMediaSourceDisplay: Boolean? = null,
	/**
	 * The official rating.
	 */
	@SerialName("OfficialRating")
	public val officialRating: String? = null,
	/**
	 * The custom rating.
	 */
	@SerialName("CustomRating")
	public val customRating: String? = null,
	/**
	 * The channel identifier.
	 */
	@SerialName("ChannelId")
	public val channelId: UUID? = null,
	@SerialName("ChannelName")
	public val channelName: String? = null,
	/**
	 * The overview.
	 */
	@SerialName("Overview")
	public val overview: String? = null,
	/**
	 * The taglines.
	 */
	@SerialName("Taglines")
	public val taglines: List<String>? = null,
	/**
	 * The genres.
	 */
	@SerialName("Genres")
	public val genres: List<String>? = null,
	/**
	 * The community rating.
	 */
	@SerialName("CommunityRating")
	public val communityRating: Float? = null,
	/**
	 * The cumulative run time ticks.
	 */
	@SerialName("CumulativeRunTimeTicks")
	public val cumulativeRunTimeTicks: Long? = null,
	/**
	 * The run time ticks.
	 */
	@SerialName("RunTimeTicks")
	public val runTimeTicks: Long? = null,
	/**
	 * The play access.
	 */
	@SerialName("PlayAccess")
	public val playAccess: PlayAccess? = null,
	/**
	 * The aspect ratio.
	 */
	@SerialName("AspectRatio")
	public val aspectRatio: String? = null,
	/**
	 * The production year.
	 */
	@SerialName("ProductionYear")
	public val productionYear: Int? = null,
	/**
	 * A value indicating whether this instance is place holder.
	 */
	@SerialName("IsPlaceHolder")
	public val isPlaceHolder: Boolean? = null,
	/**
	 * The number.
	 */
	@SerialName("Number")
	public val number: String? = null,
	@SerialName("ChannelNumber")
	public val channelNumber: String? = null,
	/**
	 * The index number.
	 */
	@SerialName("IndexNumber")
	public val indexNumber: Int? = null,
	/**
	 * The index number end.
	 */
	@SerialName("IndexNumberEnd")
	public val indexNumberEnd: Int? = null,
	/**
	 * The parent index number.
	 */
	@SerialName("ParentIndexNumber")
	public val parentIndexNumber: Int? = null,
	/**
	 * The trailer urls.
	 */
	@SerialName("RemoteTrailers")
	public val remoteTrailers: List<MediaUrl>? = null,
	/**
	 * The provider ids.
	 */
	@SerialName("ProviderIds")
	public val providerIds: Map<String, String?>? = null,
	/**
	 * A value indicating whether this instance is HD.
	 */
	@SerialName("IsHD")
	public val isHd: Boolean? = null,
	/**
	 * A value indicating whether this instance is folder.
	 */
	@SerialName("IsFolder")
	public val isFolder: Boolean? = null,
	/**
	 * The parent id.
	 */
	@SerialName("ParentId")
	public val parentId: UUID? = null,
	/**
	 * The type.
	 */
	@SerialName("Type")
	public val type: BaseItemKind,
	/**
	 * The people.
	 */
	@SerialName("People")
	public val people: List<BaseItemPerson>? = null,
	/**
	 * The studios.
	 */
	@SerialName("Studios")
	public val studios: List<NameGuidPair>? = null,
	@SerialName("GenreItems")
	public val genreItems: List<NameGuidPair>? = null,
	/**
	 * Whether the item has a logo, this will hold the Id of the Parent that has one.
	 */
	@SerialName("ParentLogoItemId")
	public val parentLogoItemId: UUID? = null,
	/**
	 * Whether the item has any backdrops, this will hold the Id of the Parent that has one.
	 */
	@SerialName("ParentBackdropItemId")
	public val parentBackdropItemId: UUID? = null,
	/**
	 * The parent backdrop image tags.
	 */
	@SerialName("ParentBackdropImageTags")
	public val parentBackdropImageTags: List<String>? = null,
	/**
	 * The local trailer count.
	 */
	@SerialName("LocalTrailerCount")
	public val localTrailerCount: Int? = null,
	/**
	 * The user data for this item based on the user it's being requested for.
	 */
	@SerialName("UserData")
	public val userData: UserItemDataDto? = null,
	/**
	 * The recursive item count.
	 */
	@SerialName("RecursiveItemCount")
	public val recursiveItemCount: Int? = null,
	/**
	 * The child count.
	 */
	@SerialName("ChildCount")
	public val childCount: Int? = null,
	/**
	 * The name of the series.
	 */
	@SerialName("SeriesName")
	public val seriesName: String? = null,
	/**
	 * The series id.
	 */
	@SerialName("SeriesId")
	public val seriesId: UUID? = null,
	/**
	 * The season identifier.
	 */
	@SerialName("SeasonId")
	public val seasonId: UUID? = null,
	/**
	 * The special feature count.
	 */
	@SerialName("SpecialFeatureCount")
	public val specialFeatureCount: Int? = null,
	/**
	 * The display preferences id.
	 */
	@SerialName("DisplayPreferencesId")
	public val displayPreferencesId: String? = null,
	/**
	 * The status.
	 */
	@SerialName("Status")
	public val status: String? = null,
	/**
	 * The air time.
	 */
	@SerialName("AirTime")
	public val airTime: String? = null,
	/**
	 * The air days.
	 */
	@SerialName("AirDays")
	public val airDays: List<DayOfWeek>? = null,
	/**
	 * The tags.
	 */
	@SerialName("Tags")
	public val tags: List<String>? = null,
	/**
	 * The primary image aspect ratio, after image enhancements.
	 */
	@SerialName("PrimaryImageAspectRatio")
	public val primaryImageAspectRatio: Double? = null,
	/**
	 * The artists.
	 */
	@SerialName("Artists")
	public val artists: List<String>? = null,
	/**
	 * The artist items.
	 */
	@SerialName("ArtistItems")
	public val artistItems: List<NameGuidPair>? = null,
	/**
	 * The album.
	 */
	@SerialName("Album")
	public val album: String? = null,
	/**
	 * The type of the collection.
	 */
	@SerialName("CollectionType")
	public val collectionType: CollectionType? = null,
	/**
	 * The display order.
	 */
	@SerialName("DisplayOrder")
	public val displayOrder: String? = null,
	/**
	 * The album id.
	 */
	@SerialName("AlbumId")
	public val albumId: UUID? = null,
	/**
	 * The album image tag.
	 */
	@SerialName("AlbumPrimaryImageTag")
	public val albumPrimaryImageTag: String? = null,
	/**
	 * The series primary image tag.
	 */
	@SerialName("SeriesPrimaryImageTag")
	public val seriesPrimaryImageTag: String? = null,
	/**
	 * The album artist.
	 */
	@SerialName("AlbumArtist")
	public val albumArtist: String? = null,
	/**
	 * The album artists.
	 */
	@SerialName("AlbumArtists")
	public val albumArtists: List<NameGuidPair>? = null,
	/**
	 * The name of the season.
	 */
	@SerialName("SeasonName")
	public val seasonName: String? = null,
	/**
	 * The media streams.
	 */
	@SerialName("MediaStreams")
	public val mediaStreams: List<MediaStream>? = null,
	/**
	 * The type of the video.
	 */
	@SerialName("VideoType")
	public val videoType: VideoType? = null,
	/**
	 * The part count.
	 */
	@SerialName("PartCount")
	public val partCount: Int? = null,
	@SerialName("MediaSourceCount")
	public val mediaSourceCount: Int? = null,
	/**
	 * The image tags.
	 */
	@SerialName("ImageTags")
	public val imageTags: Map<ImageType, String>? = null,
	/**
	 * The backdrop image tags.
	 */
	@SerialName("BackdropImageTags")
	public val backdropImageTags: List<String>? = null,
	/**
	 * The screenshot image tags.
	 */
	@SerialName("ScreenshotImageTags")
	public val screenshotImageTags: List<String>? = null,
	/**
	 * The parent logo image tag.
	 */
	@SerialName("ParentLogoImageTag")
	public val parentLogoImageTag: String? = null,
	/**
	 * Whether the item has fan art, this will hold the Id of the Parent that has one.
	 */
	@SerialName("ParentArtItemId")
	public val parentArtItemId: UUID? = null,
	/**
	 * The parent art image tag.
	 */
	@SerialName("ParentArtImageTag")
	public val parentArtImageTag: String? = null,
	/**
	 * The series thumb image tag.
	 */
	@SerialName("SeriesThumbImageTag")
	public val seriesThumbImageTag: String? = null,
	/**
	 * Gets or sets the blurhashes for the image tags.
	 * Maps image type to dictionary mapping image tag to blurhash value.
	 */
	@SerialName("ImageBlurHashes")
	public val imageBlurHashes: Map<ImageType, Map<String, String>>? = null,
	/**
	 * The series studio.
	 */
	@SerialName("SeriesStudio")
	public val seriesStudio: String? = null,
	/**
	 * The parent thumb item id.
	 */
	@SerialName("ParentThumbItemId")
	public val parentThumbItemId: UUID? = null,
	/**
	 * The parent thumb image tag.
	 */
	@SerialName("ParentThumbImageTag")
	public val parentThumbImageTag: String? = null,
	/**
	 * The parent primary image item identifier.
	 */
	@SerialName("ParentPrimaryImageItemId")
	public val parentPrimaryImageItemId: String? = null,
	/**
	 * The parent primary image tag.
	 */
	@SerialName("ParentPrimaryImageTag")
	public val parentPrimaryImageTag: String? = null,
	/**
	 * The chapters.
	 */
	@SerialName("Chapters")
	public val chapters: List<ChapterInfo>? = null,
	/**
	 * The trickplay manifest.
	 */
	@SerialName("Trickplay")
	public val trickplay: Map<String, Map<String, TrickplayInfo>>? = null,
	/**
	 * The type of the location.
	 */
	@SerialName("LocationType")
	public val locationType: LocationType? = null,
	/**
	 * The type of the iso.
	 */
	@SerialName("IsoType")
	public val isoType: IsoType? = null,
	/**
	 * The type of the media.
	 */
	@SerialName("MediaType")
	public val mediaType: MediaType,
	/**
	 * The end date.
	 */
	@SerialName("EndDate")
	public val endDate: DateTime? = null,
	/**
	 * The locked fields.
	 */
	@SerialName("LockedFields")
	public val lockedFields: List<MetadataField>? = null,
	/**
	 * The trailer count.
	 */
	@SerialName("TrailerCount")
	public val trailerCount: Int? = null,
	/**
	 * The movie count.
	 */
	@SerialName("MovieCount")
	public val movieCount: Int? = null,
	/**
	 * The series count.
	 */
	@SerialName("SeriesCount")
	public val seriesCount: Int? = null,
	@SerialName("ProgramCount")
	public val programCount: Int? = null,
	/**
	 * The episode count.
	 */
	@SerialName("EpisodeCount")
	public val episodeCount: Int? = null,
	/**
	 * The song count.
	 */
	@SerialName("SongCount")
	public val songCount: Int? = null,
	/**
	 * The album count.
	 */
	@SerialName("AlbumCount")
	public val albumCount: Int? = null,
	@SerialName("ArtistCount")
	public val artistCount: Int? = null,
	/**
	 * The music video count.
	 */
	@SerialName("MusicVideoCount")
	public val musicVideoCount: Int? = null,
	/**
	 * A value indicating whether [enable internet providers].
	 */
	@SerialName("LockData")
	public val lockData: Boolean? = null,
	@SerialName("Width")
	public val width: Int? = null,
	@SerialName("Height")
	public val height: Int? = null,
	@SerialName("CameraMake")
	public val cameraMake: String? = null,
	@SerialName("CameraModel")
	public val cameraModel: String? = null,
	@SerialName("Software")
	public val software: String? = null,
	@SerialName("ExposureTime")
	public val exposureTime: Double? = null,
	@SerialName("FocalLength")
	public val focalLength: Double? = null,
	@SerialName("ImageOrientation")
	public val imageOrientation: ImageOrientation? = null,
	@SerialName("Aperture")
	public val aperture: Double? = null,
	@SerialName("ShutterSpeed")
	public val shutterSpeed: Double? = null,
	@SerialName("Latitude")
	public val latitude: Double? = null,
	@SerialName("Longitude")
	public val longitude: Double? = null,
	@SerialName("Altitude")
	public val altitude: Double? = null,
	@SerialName("IsoSpeedRating")
	public val isoSpeedRating: Int? = null,
	/**
	 * The series timer identifier.
	 */
	@SerialName("SeriesTimerId")
	public val seriesTimerId: String? = null,
	/**
	 * The program identifier.
	 */
	@SerialName("ProgramId")
	public val programId: String? = null,
	/**
	 * The channel primary image tag.
	 */
	@SerialName("ChannelPrimaryImageTag")
	public val channelPrimaryImageTag: String? = null,
	/**
	 * The start date of the recording, in UTC.
	 */
	@SerialName("StartDate")
	public val startDate: DateTime? = null,
	/**
	 * The completion percentage.
	 */
	@SerialName("CompletionPercentage")
	public val completionPercentage: Double? = null,
	/**
	 * A value indicating whether this instance is repeat.
	 */
	@SerialName("IsRepeat")
	public val isRepeat: Boolean? = null,
	/**
	 * The episode title.
	 */
	@SerialName("EpisodeTitle")
	public val episodeTitle: String? = null,
	/**
	 * The type of the channel.
	 */
	@SerialName("ChannelType")
	public val channelType: ChannelType? = null,
	/**
	 * The audio.
	 */
	@SerialName("Audio")
	public val audio: ProgramAudio? = null,
	/**
	 * A value indicating whether this instance is movie.
	 */
	@SerialName("IsMovie")
	public val isMovie: Boolean? = null,
	/**
	 * A value indicating whether this instance is sports.
	 */
	@SerialName("IsSports")
	public val isSports: Boolean? = null,
	/**
	 * A value indicating whether this instance is series.
	 */
	@SerialName("IsSeries")
	public val isSeries: Boolean? = null,
	/**
	 * A value indicating whether this instance is live.
	 */
	@SerialName("IsLive")
	public val isLive: Boolean? = null,
	/**
	 * A value indicating whether this instance is news.
	 */
	@SerialName("IsNews")
	public val isNews: Boolean? = null,
	/**
	 * A value indicating whether this instance is kids.
	 */
	@SerialName("IsKids")
	public val isKids: Boolean? = null,
	/**
	 * A value indicating whether this instance is premiere.
	 */
	@SerialName("IsPremiere")
	public val isPremiere: Boolean? = null,
	/**
	 * The timer identifier.
	 */
	@SerialName("TimerId")
	public val timerId: String? = null,
	/**
	 * The gain required for audio normalization.
	 */
	@SerialName("NormalizationGain")
	public val normalizationGain: Float? = null,
	/**
	 * The current program.
	 */
	@SerialName("CurrentProgram")
	public val currentProgram: BaseItemDto? = null,
)
