package org.jellyfin.apiclient.model.dto;

import org.jellyfin.apiclient.model.drawing.ImageOrientation;
import org.jellyfin.apiclient.model.entities.ExtraType;
import org.jellyfin.apiclient.model.entities.ImageType;
import org.jellyfin.apiclient.model.entities.IsoType;
import org.jellyfin.apiclient.model.entities.LocationType;
import org.jellyfin.apiclient.model.entities.MediaStream;
import org.jellyfin.apiclient.model.entities.MediaUrl;
import org.jellyfin.apiclient.model.entities.MetadataFields;
import org.jellyfin.apiclient.model.entities.SeriesStatus;
import org.jellyfin.apiclient.model.entities.Video3DFormat;
import org.jellyfin.apiclient.model.entities.VideoType;
import org.jellyfin.apiclient.model.library.PlayAccess;
import org.jellyfin.apiclient.model.livetv.ChannelType;
import org.jellyfin.apiclient.model.livetv.ProgramAudio;
import org.jellyfin.apiclient.model.livetv.RecordingStatus;
import org.jellyfin.apiclient.model.providers.ExternalUrl;

/** 
 This is strictly used as a data transfer object from the api layer.
 This holds information about a BaseItem in a format that is convenient for the client.
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, ID = {Id}, Type = {Type}")] public class BaseItemDto : IHasProviderIds, IItemDto, IHasServerId, IHasSyncInfo
public class BaseItemDto
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	private String OriginalTitle;
	public final String getOriginalTitle()
	{
		return OriginalTitle;
	}
	public final void setOriginalTitle(String value)
	{
		OriginalTitle = value;
	}

	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}

	/** 
	 Gets or sets the id.
	 
	 <value>The id.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	/** 
	 Gets or sets the etag.
	 
	 <value>The etag.</value>
	*/
	private String Etag;
	public final String getEtag()
	{
		return Etag;
	}
	public final void setEtag(String value)
	{
		Etag = value;
	}

	/** 
	 Gets or sets the type of the source.
	 
	 <value>The type of the source.</value>
	*/
	private String SourceType;
	public final String getSourceType()
	{
		return SourceType;
	}
	public final void setSourceType(String value)
	{
		SourceType = value;
	}

	/** 
	 Gets or sets the playlist item identifier.
	 
	 <value>The playlist item identifier.</value>
	*/
	private String PlaylistItemId;
	public final String getPlaylistItemId()
	{
		return PlaylistItemId;
	}
	public final void setPlaylistItemId(String value)
	{
		PlaylistItemId = value;
	}

	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date DateCreated = null;
	public final java.util.Date getDateCreated()
	{
		return DateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		DateCreated = value;
	}

	private java.util.Date DateLastMediaAdded = null;
	public final java.util.Date getDateLastMediaAdded()
	{
		return DateLastMediaAdded;
	}
	public final void setDateLastMediaAdded(java.util.Date value)
	{
		DateLastMediaAdded = value;
	}
	private ExtraType ExtraType = null;
	public final ExtraType getExtraType()
	{
		return ExtraType;
	}
	public final void setExtraType(ExtraType value)
	{
		ExtraType = value;
	}

	private Integer AirsBeforeSeasonNumber = null;
	public final Integer getAirsBeforeSeasonNumber()
	{
		return AirsBeforeSeasonNumber;
	}
	public final void setAirsBeforeSeasonNumber(Integer value)
	{
		AirsBeforeSeasonNumber = value;
	}
	private Integer AirsAfterSeasonNumber = null;
	public final Integer getAirsAfterSeasonNumber()
	{
		return AirsAfterSeasonNumber;
	}
	public final void setAirsAfterSeasonNumber(Integer value)
	{
		AirsAfterSeasonNumber = value;
	}
	private Integer AirsBeforeEpisodeNumber = null;
	public final Integer getAirsBeforeEpisodeNumber()
	{
		return AirsBeforeEpisodeNumber;
	}
	public final void setAirsBeforeEpisodeNumber(Integer value)
	{
		AirsBeforeEpisodeNumber = value;
	}
	private Integer AbsoluteEpisodeNumber = null;
	public final Integer getAbsoluteEpisodeNumber()
	{
		return AbsoluteEpisodeNumber;
	}
	public final void setAbsoluteEpisodeNumber(Integer value)
	{
		AbsoluteEpisodeNumber = value;
	}
	private Boolean DisplaySpecialsWithSeasons = null;
	public final Boolean getDisplaySpecialsWithSeasons()
	{
		return DisplaySpecialsWithSeasons;
	}
	public final void setDisplaySpecialsWithSeasons(Boolean value)
	{
		DisplaySpecialsWithSeasons = value;
	}
	private Boolean CanDelete = null;
	public final Boolean getCanDelete()
	{
		return CanDelete;
	}
	public final void setCanDelete(Boolean value)
	{
		CanDelete = value;
	}
	private Boolean CanDownload = null;
	public final Boolean getCanDownload()
	{
		return CanDownload;
	}
	public final void setCanDownload(Boolean value)
	{
		CanDownload = value;
	}

	private Boolean HasSubtitles = null;
	public final Boolean getHasSubtitles()
	{
		return HasSubtitles;
	}
	public final void setHasSubtitles(Boolean value)
	{
		HasSubtitles = value;
	}

	private String PreferredMetadataLanguage;
	public final String getPreferredMetadataLanguage()
	{
		return PreferredMetadataLanguage;
	}
	public final void setPreferredMetadataLanguage(String value)
	{
		PreferredMetadataLanguage = value;
	}
	private String PreferredMetadataCountryCode;
	public final String getPreferredMetadataCountryCode()
	{
		return PreferredMetadataCountryCode;
	}
	public final void setPreferredMetadataCountryCode(String value)
	{
		PreferredMetadataCountryCode = value;
	}

	private String AwardSummary;
	public final String getAwardSummary()
	{
		return AwardSummary;
	}
	public final void setAwardSummary(String value)
	{
		AwardSummary = value;
	}
	private String ShareUrl;
	public final String getShareUrl()
	{
		return ShareUrl;
	}
	public final void setShareUrl(String value)
	{
		ShareUrl = value;
	}

	private Float Metascore = null;
	public final Float getMetascore()
	{
		return Metascore;
	}
	public final void setMetascore(Float value)
	{
		Metascore = value;
	}
	private Boolean HasDynamicCategories = null;
	public final Boolean getHasDynamicCategories()
	{
		return HasDynamicCategories;
	}
	public final void setHasDynamicCategories(Boolean value)
	{
		HasDynamicCategories = value;
	}

	private Integer AnimeSeriesIndex = null;
	public final Integer getAnimeSeriesIndex()
	{
		return AnimeSeriesIndex;
	}
	public final void setAnimeSeriesIndex(Integer value)
	{
		AnimeSeriesIndex = value;
	}

	private String Container;
	public final String getContainer()
	{
		return Container;
	}
	public final void setContainer(String value)
	{
		Container = value;
	}

	/** 
	 Gets or sets the DVD season number.
	 
	 <value>The DVD season number.</value>
	*/
	private Integer DvdSeasonNumber = null;
	public final Integer getDvdSeasonNumber()
	{
		return DvdSeasonNumber;
	}
	public final void setDvdSeasonNumber(Integer value)
	{
		DvdSeasonNumber = value;
	}
	/** 
	 Gets or sets the DVD episode number.
	 
	 <value>The DVD episode number.</value>
	*/
	private Float DvdEpisodeNumber = null;
	public final Float getDvdEpisodeNumber()
	{
		return DvdEpisodeNumber;
	}
	public final void setDvdEpisodeNumber(Float value)
	{
		DvdEpisodeNumber = value;
	}

	/** 
	 Gets or sets the name of the sort.
	 
	 <value>The name of the sort.</value>
	*/
	private String SortName;
	public final String getSortName()
	{
		return SortName;
	}
	public final void setSortName(String value)
	{
		SortName = value;
	}
	private String ForcedSortName;
	public final String getForcedSortName()
	{
		return ForcedSortName;
	}
	public final void setForcedSortName(String value)
	{
		ForcedSortName = value;
	}

	/** 
	 Gets or sets the video3 D format.
	 
	 <value>The video3 D format.</value>
	*/
	private Video3DFormat Video3DFormat = null;
	public final Video3DFormat getVideo3DFormat()
	{
		return Video3DFormat;
	}
	public final void setVideo3DFormat(Video3DFormat value)
	{
		Video3DFormat = value;
	}

	/** 
	 Gets or sets the premiere date.
	 
	 <value>The premiere date.</value>
	*/
	private java.util.Date PremiereDate = null;
	public final java.util.Date getPremiereDate()
	{
		return PremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		PremiereDate = value;
	}

	/** 
	 Gets or sets the external urls.
	 
	 <value>The external urls.</value>
	*/
	private ExternalUrl[] ExternalUrls;
	public final ExternalUrl[] getExternalUrls()
	{
		return ExternalUrls;
	}
	public final void setExternalUrls(ExternalUrl[] value)
	{
		ExternalUrls = value;
	}

	/** 
	 Gets or sets the media versions.
	 
	 <value>The media versions.</value>
	*/
	private java.util.ArrayList<MediaSourceInfo> MediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return MediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		MediaSources = value;
	}

	/** 
	 Gets or sets the critic rating.
	 
	 <value>The critic rating.</value>
	*/
	private Float CriticRating = null;
	public final Float getCriticRating()
	{
		return CriticRating;
	}
	public final void setCriticRating(Float value)
	{
		CriticRating = value;
	}

	/** 
	 Gets or sets the game system.
	 
	 <value>The game system.</value>
	*/
	private String GameSystem;
	public final String getGameSystem()
	{
		return GameSystem;
	}
	public final void setGameSystem(String value)
	{
		GameSystem = value;
	}

	private String[] ProductionLocations;
	public final String[] getProductionLocations()
	{
		return ProductionLocations;
	}
	public final void setProductionLocations(String[] value)
	{
		ProductionLocations = value;
	}

	/** 
	 Gets or sets the critic rating summary.
	 
	 <value>The critic rating summary.</value>
	*/
	private String CriticRatingSummary;
	public final String getCriticRatingSummary()
	{
		return CriticRatingSummary;
	}
	public final void setCriticRatingSummary(String value)
	{
		CriticRatingSummary = value;
	}

	private java.util.ArrayList<String> MultiPartGameFiles;
	public final java.util.ArrayList<String> getMultiPartGameFiles()
	{
		return MultiPartGameFiles;
	}
	public final void setMultiPartGameFiles(java.util.ArrayList<String> value)
	{
		MultiPartGameFiles = value;
	}

	/** 
	 Gets or sets the path.
	 
	 <value>The path.</value>
	*/
	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
	}

	/** 
	 Gets or sets the official rating.
	 
	 <value>The official rating.</value>
	*/
	private String OfficialRating;
	public final String getOfficialRating()
	{
		return OfficialRating;
	}
	public final void setOfficialRating(String value)
	{
		OfficialRating = value;
	}

	/** 
	 Gets or sets the custom rating.
	 
	 <value>The custom rating.</value>
	*/
	private String CustomRating;
	public final String getCustomRating()
	{
		return CustomRating;
	}
	public final void setCustomRating(String value)
	{
		CustomRating = value;
	}

	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String ChannelId;
	public final String getChannelId()
	{
		return ChannelId;
	}
	public final void setChannelId(String value)
	{
		ChannelId = value;
	}
	private String ChannelName;
	public final String getChannelName()
	{
		return ChannelName;
	}
	public final void setChannelName(String value)
	{
		ChannelName = value;
	}
	private String ServiceName;
	public final String getServiceName()
	{
		return ServiceName;
	}
	public final void setServiceName(String value)
	{
		ServiceName = value;
	}

	/** 
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String Overview;
	public final String getOverview()
	{
		return Overview;
	}
	public final void setOverview(String value)
	{
		Overview = value;
	}

	/** 
	 Gets or sets the short overview.
	 
	 <value>The short overview.</value>
	*/
	private String ShortOverview;
	public final String getShortOverview()
	{
		return ShortOverview;
	}
	public final void setShortOverview(String value)
	{
		ShortOverview = value;
	}

	/** 
	 Gets or sets the taglines.
	 
	 <value>The taglines.</value>
	*/
	private java.util.ArrayList<String> Taglines;
	public final java.util.ArrayList<String> getTaglines()
	{
		return Taglines;
	}
	public final void setTaglines(java.util.ArrayList<String> value)
	{
		Taglines = value;
	}

	/** 
	 Gets or sets the genres.
	 
	 <value>The genres.</value>
	*/
	@Deprecated
	private java.util.ArrayList<String> Genres;
	@Deprecated
	public final java.util.ArrayList<String> getGenres()
	{
		return Genres;
	}
	@Deprecated
	public final void setGenres(java.util.ArrayList<String> value)
	{
		Genres = value;
	}

	/** 
	 Gets or sets the series genres.
	 
	 <value>The series genres.</value>
	*/
	private java.util.ArrayList<String> SeriesGenres;
	public final java.util.ArrayList<String> getSeriesGenres()
	{
		return SeriesGenres;
	}
	public final void setSeriesGenres(java.util.ArrayList<String> value)
	{
		SeriesGenres = value;
	}

	/** 
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Float CommunityRating = null;
	public final Float getCommunityRating()
	{
		return CommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		CommunityRating = value;
	}

	/** 
	 Gets or sets the vote count.
	 
	 <value>The vote count.</value>
	*/
	private Integer VoteCount = null;
	public final Integer getVoteCount()
	{
		return VoteCount;
	}
	public final void setVoteCount(Integer value)
	{
		VoteCount = value;
	}

	/** 
	 Gets or sets the cumulative run time ticks.
	 
	 <value>The cumulative run time ticks.</value>
	*/
	private Long CumulativeRunTimeTicks = null;
	public final Long getCumulativeRunTimeTicks()
	{
		return CumulativeRunTimeTicks;
	}
	public final void setCumulativeRunTimeTicks(Long value)
	{
		CumulativeRunTimeTicks = value;
	}

	/** 
	 Gets or sets the original run time ticks.
	 
	 <value>The original run time ticks.</value>
	*/
	private Long OriginalRunTimeTicks = null;
	public final Long getOriginalRunTimeTicks()
	{
		return OriginalRunTimeTicks;
	}
	public final void setOriginalRunTimeTicks(Long value)
	{
		OriginalRunTimeTicks = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long RunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}

	/** 
	 Gets or sets the play access.
	 
	 <value>The play access.</value>
	*/
	private PlayAccess PlayAccess = getPlayAccess().values()[0];
	public final PlayAccess getPlayAccess()
	{
		return PlayAccess;
	}
	public final void setPlayAccess(PlayAccess value)
	{
		PlayAccess = value;
	}

	/** 
	 Gets or sets the aspect ratio.
	 
	 <value>The aspect ratio.</value>
	*/
	private String AspectRatio;
	public final String getAspectRatio()
	{
		return AspectRatio;
	}
	public final void setAspectRatio(String value)
	{
		AspectRatio = value;
	}

	/** 
	 Gets or sets the production year.
	 
	 <value>The production year.</value>
	*/
	private Integer ProductionYear = null;
	public final Integer getProductionYear()
	{
		return ProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		ProductionYear = value;
	}

	/** 
	 Gets or sets the players supported by a game.
	 
	 <value>The players.</value>
	*/
	private Integer Players = null;
	public final Integer getPlayers()
	{
		return Players;
	}
	public final void setPlayers(Integer value)
	{
		Players = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is place holder.
	 
	 <value><c>null</c> if [is place holder] contains no value, <c>true</c> if [is place holder]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsPlaceHolder = null;
	public final Boolean getIsPlaceHolder()
	{
		return IsPlaceHolder;
	}
	public final void setIsPlaceHolder(Boolean value)
	{
		IsPlaceHolder = value;
	}

	/** 
	 Gets or sets the number.
	 
	 <value>The number.</value>
	*/
	private String Number;
	public final String getNumber()
	{
		return Number;
	}
	public final void setNumber(String value)
	{
		Number = value;
	}
	private String ChannelNumber;
	public final String getChannelNumber()
	{
		return ChannelNumber;
	}
	public final void setChannelNumber(String value)
	{
		ChannelNumber = value;
	}

	/** 
	 Gets or sets the index number.
	 
	 <value>The index number.</value>
	*/
	private Integer IndexNumber = null;
	public final Integer getIndexNumber()
	{
		return IndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		IndexNumber = value;
	}

	/** 
	 Gets or sets the index number end.
	 
	 <value>The index number end.</value>
	*/
	private Integer IndexNumberEnd = null;
	public final Integer getIndexNumberEnd()
	{
		return IndexNumberEnd;
	}
	public final void setIndexNumberEnd(Integer value)
	{
		IndexNumberEnd = value;
	}

	/** 
	 Gets or sets the parent index number.
	 
	 <value>The parent index number.</value>
	*/
	private Integer ParentIndexNumber = null;
	public final Integer getParentIndexNumber()
	{
		return ParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		ParentIndexNumber = value;
	}

	/** 
	 Gets or sets the trailer urls.
	 
	 <value>The trailer urls.</value>
	*/
	private java.util.ArrayList<MediaUrl> RemoteTrailers;
	public final java.util.ArrayList<MediaUrl> getRemoteTrailers()
	{
		return RemoteTrailers;
	}
	public final void setRemoteTrailers(java.util.ArrayList<MediaUrl> value)
	{
		RemoteTrailers = value;
	}

	/** 
	 Gets or sets the soundtrack ids.
	 
	 <value>The soundtrack ids.</value>
	*/
	private String[] SoundtrackIds;
	public final String[] getSoundtrackIds()
	{
		return SoundtrackIds;
	}
	public final void setSoundtrackIds(String[] value)
	{
		SoundtrackIds = value;
	}

	/** 
	 Gets or sets the provider ids.
	 
	 <value>The provider ids.</value>
	*/
	private java.util.HashMap<String, String> ProviderIds;
	public final java.util.HashMap<String, String> getProviderIds()
	{
		return ProviderIds;
	}
	public final void setProviderIds(java.util.HashMap<String, String> value)
	{
		ProviderIds = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is HD.
	 
	 <value><c>null</c> if [is HD] contains no value, <c>true</c> if [is HD]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsHD = null;
	public final Boolean getIsHD()
	{
		return IsHD;
	}
	public final void setIsHD(Boolean value)
	{
		IsHD = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is folder.
	 
	 <value><c>true</c> if this instance is folder; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsFolder = null;
	public final Boolean getIsFolder()
	{
		return IsFolder;
	}
	public final void setIsFolder(Boolean value)
	{
		IsFolder = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsFolderItem
	public final boolean getIsFolderItem()
	{
		Boolean tempVar = getIsFolder();
		return (tempVar != null) ? tempVar : false;
	}

	/** 
	 Gets or sets the parent id.
	 
	 <value>The parent id.</value>
	*/
	private String ParentId;
	public final String getParentId()
	{
		return ParentId;
	}
	public final void setParentId(String value)
	{
		ParentId = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String Type;

	@Deprecated
	public final String getType()
	{
		return Type;
	}

	@Deprecated
	public final void setType(String value)
	{
		Type = value;
	}

	public final void setBaseItemType(BaseItemType Type) {
		this.Type = Type.name();
	}

	public final BaseItemType getBaseItemType() {
		return BaseItemType.valueOf(Type);
	}

	/** 
	 Gets or sets the people.
	 
	 <value>The people.</value>
	*/
	private BaseItemPerson[] People;
	public final BaseItemPerson[] getPeople()
	{
		return People;
	}
	public final void setPeople(BaseItemPerson[] value)
	{
		People = value;
	}

	/**
	 Gets or sets the studios.

	 <value>The studios.</value>
	 */
	private StudioDto[] Studios;
	public final StudioDto[] getStudios()
	{
		return Studios;
	}
	public final void setStudios(StudioDto[] value)
	{
		Studios = value;
	}
	/**
	 Gets or sets the genres.

	 <value>The genres.</value>
	 */
	private GenreDto[] GenreItems;
	public final GenreDto[] getGenreItems()
	{
		return GenreItems;
	}
	public final void setGenreItems(GenreDto[] value)
	{
		GenreItems  = value;
	}

	/** 
	 If the item does not have a logo, this will hold the Id of the Parent that has one.
	 
	 <value>The parent logo item id.</value>
	*/
	private String ParentLogoItemId;
	public final String getParentLogoItemId()
	{
		return ParentLogoItemId;
	}
	public final void setParentLogoItemId(String value)
	{
		ParentLogoItemId = value;
	}

	/** 
	 If the item does not have any backdrops, this will hold the Id of the Parent that has one.
	 
	 <value>The parent backdrop item id.</value>
	*/
	private String ParentBackdropItemId;
	public final String getParentBackdropItemId()
	{
		return ParentBackdropItemId;
	}
	public final void setParentBackdropItemId(String value)
	{
		ParentBackdropItemId = value;
	}

	/** 
	 Gets or sets the parent backdrop image tags.
	 
	 <value>The parent backdrop image tags.</value>
	*/
	private java.util.ArrayList<String> ParentBackdropImageTags;
	public final java.util.ArrayList<String> getParentBackdropImageTags()
	{
		return ParentBackdropImageTags;
	}
	public final void setParentBackdropImageTags(java.util.ArrayList<String> value)
	{
		ParentBackdropImageTags = value;
	}

	/** 
	 Gets or sets the local trailer count.
	 
	 <value>The local trailer count.</value>
	*/
	private Integer LocalTrailerCount = null;
	public final Integer getLocalTrailerCount()
	{
		return LocalTrailerCount;
	}
	public final void setLocalTrailerCount(Integer value)
	{
		LocalTrailerCount = value;
	}

	/** 
	 User data for this item based on the user it's being requested for
	 
	 <value>The user data.</value>
	*/
	private UserItemDataDto UserData;
	public final UserItemDataDto getUserData()
	{
		return UserData;
	}
	public final void setUserData(UserItemDataDto value)
	{
		UserData = value;
	}
	/** 
	 Gets or sets the season user data.
	 
	 <value>The season user data.</value>
	*/
	private UserItemDataDto SeasonUserData;
	public final UserItemDataDto getSeasonUserData()
	{
		return SeasonUserData;
	}
	public final void setSeasonUserData(UserItemDataDto value)
	{
		SeasonUserData = value;
	}

	/** 
	 Gets or sets the recursive item count.
	 
	 <value>The recursive item count.</value>
	*/
	private Integer RecursiveItemCount = null;
	public final Integer getRecursiveItemCount()
	{
		return RecursiveItemCount;
	}
	public final void setRecursiveItemCount(Integer value)
	{
		RecursiveItemCount = value;
	}

	/** 
	 Gets or sets the child count.
	 
	 <value>The child count.</value>
	*/
	private Integer ChildCount = null;
	public final Integer getChildCount()
	{
		return ChildCount;
	}
	public final void setChildCount(Integer value)
	{
		ChildCount = value;
	}

	/** 
	 Gets or sets the name of the series.
	 
	 <value>The name of the series.</value>
	*/
	private String SeriesName;
	public final String getSeriesName()
	{
		return SeriesName;
	}
	public final void setSeriesName(String value)
	{
		SeriesName = value;
	}

	/** 
	 Gets or sets the series id.
	 
	 <value>The series id.</value>
	*/
	private String SeriesId;
	public final String getSeriesId()
	{
		return SeriesId;
	}
	public final void setSeriesId(String value)
	{
		SeriesId = value;
	}

	/** 
	 Gets or sets the season identifier.
	 
	 <value>The season identifier.</value>
	*/
	private String SeasonId;
	public final String getSeasonId()
	{
		return SeasonId;
	}
	public final void setSeasonId(String value)
	{
		SeasonId = value;
	}

	/** 
	 Gets or sets the special feature count.
	 
	 <value>The special feature count.</value>
	*/
	private Integer SpecialFeatureCount = null;
	public final Integer getSpecialFeatureCount()
	{
		return SpecialFeatureCount;
	}
	public final void setSpecialFeatureCount(Integer value)
	{
		SpecialFeatureCount = value;
	}

	/** 
	 Gets or sets the display preferences id.
	 
	 <value>The display preferences id.</value>
	*/
	private String DisplayPreferencesId;
	public final String getDisplayPreferencesId()
	{
		return DisplayPreferencesId;
	}
	public final void setDisplayPreferencesId(String value)
	{
		DisplayPreferencesId = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private String Status;
	public final String getStatus()
	{
		return Status;
	}
	public final void setStatus(String value)
	{
		Status = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public Nullable<SeriesStatus> SeriesStatus
	public final SeriesStatus getSeriesStatus()
	{
		if (getStatus() == null || getStatus().isEmpty())
		{
			return null;
		}

//C# TO JAVA CONVERTER WARNING: Java does not have an 'ignoreCase' parameter for the static 'valueOf' method of enum types:
//ORIGINAL LINE: return (SeriesStatus)Enum.Parse(typeof(SeriesStatus), Status, true);
		return SeriesStatus.valueOf(getStatus());
	}
	public final void setSeriesStatus(SeriesStatus value)
	{
		if (value == null)
		{
			setStatus(null);
		} else {
			setStatus(value.toString());
		}
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public Nullable<RecordingStatus> RecordingStatus
	public final RecordingStatus getRecordingStatus()
	{
		if (getStatus() == null || getStatus().isEmpty())
		{
			return null;
		}

//C# TO JAVA CONVERTER WARNING: Java does not have an 'ignoreCase' parameter for the static 'valueOf' method of enum types:
//ORIGINAL LINE: return (RecordingStatus)Enum.Parse(typeof(RecordingStatus), Status, true);
		return RecordingStatus.valueOf(getStatus());
	}
	public final void setRecordingStatus(RecordingStatus value)
	{
		if (value == null)
		{
			setStatus(null);
		} else {
			setStatus(value.toString());
		}
	}

	/** 
	 Gets or sets the air time.
	 
	 <value>The air time.</value>
	*/
	private String AirTime;
	public final String getAirTime()
	{
		return AirTime;
	}
	public final void setAirTime(String value)
	{
		AirTime = value;
	}

	/** 
	 Gets or sets the air days.
	 
	 <value>The air days.</value>
	*/
	private java.util.ArrayList<String> AirDays;
	public final java.util.ArrayList<String> getAirDays()
	{
		return AirDays;
	}
	public final void setAirDays(java.util.ArrayList<String> value)
	{
		AirDays = value;
	}

	/** 
	 Gets or sets the index options.
	 
	 <value>The index options.</value>
	*/
	private String[] IndexOptions;
	public final String[] getIndexOptions()
	{
		return IndexOptions;
	}
	public final void setIndexOptions(String[] value)
	{
		IndexOptions = value;
	}

	/** 
	 Gets or sets the tags.
	 
	 <value>The tags.</value>
	*/
	private java.util.ArrayList<String> Tags;
	public final java.util.ArrayList<String> getTags()
	{
		return Tags;
	}
	public final void setTags(java.util.ArrayList<String> value)
	{
		Tags = value;
	}

	/** 
	 Gets or sets the keywords.
	 
	 <value>The keywords.</value>
	*/
	private java.util.ArrayList<String> Keywords;
	public final java.util.ArrayList<String> getKeywords()
	{
		return Keywords;
	}
	public final void setKeywords(java.util.ArrayList<String> value)
	{
		Keywords = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio, after image enhancements.
	 
	 <value>The primary image aspect ratio.</value>
	*/
	private Double PrimaryImageAspectRatio = null;
	public final Double getPrimaryImageAspectRatio()
	{
		return PrimaryImageAspectRatio;
	}
	public final void setPrimaryImageAspectRatio(Double value)
	{
		PrimaryImageAspectRatio = value;
	}

	/** 
	 Gets or sets the artists.
	 
	 <value>The artists.</value>
	*/
	private java.util.ArrayList<String> Artists;
	public final java.util.ArrayList<String> getArtists()
	{
		return Artists;
	}
	public final void setArtists(java.util.ArrayList<String> value)
	{
		Artists = value;
	}

	/** 
	 Gets or sets the artist items.
	 
	 <value>The artist items.</value>
	*/
	private java.util.ArrayList<NameIdPair> ArtistItems;
	public final java.util.ArrayList<NameIdPair> getArtistItems()
	{
		return ArtistItems;
	}
	public final void setArtistItems(java.util.ArrayList<NameIdPair> value)
	{
		ArtistItems = value;
	}

	/** 
	 Gets or sets the album.
	 
	 <value>The album.</value>
	*/
	private String Album;
	public final String getAlbum()
	{
		return Album;
	}
	public final void setAlbum(String value)
	{
		Album = value;
	}

	/** 
	 Gets or sets the type of the collection.
	 
	 <value>The type of the collection.</value>
	*/
	private String CollectionType;
	public final String getCollectionType()
	{
		return CollectionType;
	}
	public final void setCollectionType(String value)
	{
		CollectionType = value;
	}

	/** 
	 Gets or sets the type of the original collection.
	 
	 <value>The type of the original collection.</value>
	*/
	private String OriginalCollectionType;
	public final String getOriginalCollectionType()
	{
		return OriginalCollectionType;
	}
	public final void setOriginalCollectionType(String value)
	{
		OriginalCollectionType = value;
	}

	/** 
	 Gets or sets the display order.
	 
	 <value>The display order.</value>
	*/
	private String DisplayOrder;
	public final String getDisplayOrder()
	{
		return DisplayOrder;
	}
	public final void setDisplayOrder(String value)
	{
		DisplayOrder = value;
	}

	/** 
	 Gets or sets the album id.
	 
	 <value>The album id.</value>
	*/
	private String AlbumId;
	public final String getAlbumId()
	{
		return AlbumId;
	}
	public final void setAlbumId(String value)
	{
		AlbumId = value;
	}
	/** 
	 Gets or sets the album image tag.
	 
	 <value>The album image tag.</value>
	*/
	private String AlbumPrimaryImageTag;
	public final String getAlbumPrimaryImageTag()
	{
		return AlbumPrimaryImageTag;
	}
	public final void setAlbumPrimaryImageTag(String value)
	{
		AlbumPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the series primary image tag.
	 
	 <value>The series primary image tag.</value>
	*/
	private String SeriesPrimaryImageTag;
	public final String getSeriesPrimaryImageTag()
	{
		return SeriesPrimaryImageTag;
	}
	public final void setSeriesPrimaryImageTag(String value)
	{
		SeriesPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the album artist.
	 
	 <value>The album artist.</value>
	*/
	private String AlbumArtist;
	public final String getAlbumArtist()
	{
		return AlbumArtist;
	}
	public final void setAlbumArtist(String value)
	{
		AlbumArtist = value;
	}

	/** 
	 Gets or sets the album artists.
	 
	 <value>The album artists.</value>
	*/
	private java.util.ArrayList<NameIdPair> AlbumArtists;
	public final java.util.ArrayList<NameIdPair> getAlbumArtists()
	{
		return AlbumArtists;
	}
	public final void setAlbumArtists(java.util.ArrayList<NameIdPair> value)
	{
		AlbumArtists = value;
	}

	/** 
	 Gets or sets the name of the season.
	 
	 <value>The name of the season.</value>
	*/
	private String SeasonName;
	public final String getSeasonName()
	{
		return SeasonName;
	}
	public final void setSeasonName(String value)
	{
		SeasonName = value;
	}

	/** 
	 Gets or sets the media streams.
	 
	 <value>The media streams.</value>
	*/
	private java.util.ArrayList<MediaStream> MediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return MediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		MediaStreams = value;
	}

	/** 
	 Gets or sets the type of the video.
	 
	 <value>The type of the video.</value>
	*/
	private VideoType VideoType = null;
	public final VideoType getVideoType()
	{
		return VideoType;
	}
	public final void setVideoType(VideoType value)
	{
		VideoType = value;
	}

	/** 
	 Gets or sets the display type of the media.
	 
	 <value>The display type of the media.</value>
	*/
	private String DisplayMediaType;
	public final String getDisplayMediaType()
	{
		return DisplayMediaType;
	}
	public final void setDisplayMediaType(String value)
	{
		DisplayMediaType = value;
	}

	/** 
	 Gets or sets the part count.
	 
	 <value>The part count.</value>
	*/
	private Integer PartCount = null;
	public final Integer getPartCount()
	{
		return PartCount;
	}
	public final void setPartCount(Integer value)
	{
		PartCount = value;
	}
	private Integer MediaSourceCount = null;
	public final Integer getMediaSourceCount()
	{
		return MediaSourceCount;
	}
	public final void setMediaSourceCount(Integer value)
	{
		MediaSourceCount = value;
	}


	/** 
	 Gets or sets a value indicating whether [supports playlists].
	 
	 <value><c>true</c> if [supports playlists]; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool SupportsPlaylists
	public final boolean getSupportsPlaylists()
	{
		return getRunTimeTicks() != null || getIsFolderItem() || getIsGenre() || getIsMusicGenre() || getIsArtist();
	}


	/**
	 Gets or sets the image tags.

	 <value>The image tags.</value>
	*/
	private java.util.HashMap<ImageType, String> ImageTags;
	public final java.util.HashMap<ImageType, String> getImageTags()
	{
		return ImageTags;
	}
	public final void setImageTags(java.util.HashMap<ImageType, String> value)
	{
		ImageTags = value;
	}

	/**
	 Gets or sets the backdrop image tags.

	 <value>The backdrop image tags.</value>
	*/
	private java.util.ArrayList<String> BackdropImageTags;
	public final java.util.ArrayList<String> getBackdropImageTags()
	{
		return BackdropImageTags;
	}
	public final void setBackdropImageTags(java.util.ArrayList<String> value)
	{
		BackdropImageTags = value;
	}

	/**
	 Gets or sets the screenshot image tags.

	 <value>The screenshot image tags.</value>
	*/
	private java.util.ArrayList<String> ScreenshotImageTags;
	public final java.util.ArrayList<String> getScreenshotImageTags()
	{
		return ScreenshotImageTags;
	}
	public final void setScreenshotImageTags(java.util.ArrayList<String> value)
	{
		ScreenshotImageTags = value;
	}

	/**
	 Gets or sets the parent logo image tag.

	 <value>The parent logo image tag.</value>
	*/
	private String ParentLogoImageTag;
	public final String getParentLogoImageTag()
	{
		return ParentLogoImageTag;
	}
	public final void setParentLogoImageTag(String value)
	{
		ParentLogoImageTag = value;
	}

	/**
	 If the item does not have a art, this will hold the Id of the Parent that has one.

	 <value>The parent art item id.</value>
	*/
	private String ParentArtItemId;
	public final String getParentArtItemId()
	{
		return ParentArtItemId;
	}
	public final void setParentArtItemId(String value)
	{
		ParentArtItemId = value;
	}

	/**
	 Gets or sets the parent art image tag.

	 <value>The parent art image tag.</value>
	*/
	private String ParentArtImageTag;
	public final String getParentArtImageTag()
	{
		return ParentArtImageTag;
	}
	public final void setParentArtImageTag(String value)
	{
		ParentArtImageTag = value;
	}

	/**
	 Gets or sets the series thumb image tag.

	 <value>The series thumb image tag.</value>
	*/
	private String SeriesThumbImageTag;
	public final String getSeriesThumbImageTag()
	{
		return SeriesThumbImageTag;
	}
	public final void setSeriesThumbImageTag(String value)
	{
		SeriesThumbImageTag = value;
	}

	/**
	 Gets or sets the series studio.

	 <value>The series studio.</value>
	*/
	private String SeriesStudio;
	public final String getSeriesStudio()
	{
		return SeriesStudio;
	}
	public final void setSeriesStudio(String value)
	{
		SeriesStudio = value;
	}

	/**
	 Gets or sets the parent thumb item id.

	 <value>The parent thumb item id.</value>
	*/
	private String ParentThumbItemId;
	public final String getParentThumbItemId()
	{
		return ParentThumbItemId;
	}
	public final void setParentThumbItemId(String value)
	{
		ParentThumbItemId = value;
	}

	/**
	 Gets or sets the parent thumb image tag.

	 <value>The parent thumb image tag.</value>
	*/
	private String ParentThumbImageTag;
	public final String getParentThumbImageTag()
	{
		return ParentThumbImageTag;
	}
	public final void setParentThumbImageTag(String value)
	{
		ParentThumbImageTag = value;
	}

	/**
	 Gets or sets the parent primary image item identifier.

	 <value>The parent primary image item identifier.</value>
	*/
	private String ParentPrimaryImageItemId;
	public final String getParentPrimaryImageItemId()
	{
		return ParentPrimaryImageItemId;
	}
	public final void setParentPrimaryImageItemId(String value)
	{
		ParentPrimaryImageItemId = value;
	}

	/**
	 Gets or sets the parent primary image tag.

	 <value>The parent primary image tag.</value>
	*/
	private String ParentPrimaryImageTag;
	public final String getParentPrimaryImageTag()
	{
		return ParentPrimaryImageTag;
	}
	public final void setParentPrimaryImageTag(String value)
	{
		ParentPrimaryImageTag = value;
	}

	/**
	 Gets or sets the chapters.

	 <value>The chapters.</value>
	*/
	private java.util.ArrayList<ChapterInfoDto> Chapters;
	public final java.util.ArrayList<ChapterInfoDto> getChapters()
	{
		return Chapters;
	}
	public final void setChapters(java.util.ArrayList<ChapterInfoDto> value)
	{
		Chapters = value;
	}

	/**
	 Gets or sets the type of the location.

	 <value>The type of the location.</value>
	*/
	private LocationType LocationType = getLocationType().values()[0];
	public final LocationType getLocationType()
	{
		return LocationType;
	}
	public final void setLocationType(LocationType value)
	{
		LocationType = value;
	}

	/**
	 Gets or sets the type of the iso.

	 <value>The type of the iso.</value>
	*/
	private IsoType IsoType = null;
	public final IsoType getIsoType()
	{
		return IsoType;
	}
	public final void setIsoType(IsoType value)
	{
		IsoType = value;
	}

	/**
	 Gets or sets the type of the media.

	 <value>The type of the media.</value>
	*/
	private String MediaType;
	public final String getMediaType()
	{
		return MediaType;
	}
	public final void setMediaType(String value)
	{
		MediaType = value;
	}

	/**
	 Gets or sets the end date.

	 <value>The end date.</value>
	*/
	private java.util.Date EndDate = null;
	public final java.util.Date getEndDate()
	{
		return EndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		EndDate = value;
	}

	/**
	 Gets or sets the home page URL.

	 <value>The home page URL.</value>
	*/
	private String HomePageUrl;
	public final String getHomePageUrl()
	{
		return HomePageUrl;
	}
	public final void setHomePageUrl(String value)
	{
		HomePageUrl = value;
	}

	/**
	 Gets or sets the budget.

	 <value>The budget.</value>
	*/
	private Double Budget = null;
	public final Double getBudget()
	{
		return Budget;
	}
	public final void setBudget(Double value)
	{
		Budget = value;
	}

	/**
	 Gets or sets the revenue.

	 <value>The revenue.</value>
	*/
	private Double Revenue = null;
	public final Double getRevenue()
	{
		return Revenue;
	}
	public final void setRevenue(Double value)
	{
		Revenue = value;
	}

	/**
	 Gets or sets the locked fields.

	 <value>The locked fields.</value>
	*/
	private java.util.ArrayList<MetadataFields> LockedFields;
	public final java.util.ArrayList<MetadataFields> getLockedFields()
	{
		return LockedFields;
	}
	public final void setLockedFields(java.util.ArrayList<MetadataFields> value)
	{
		LockedFields = value;
	}

	/**
	 Gets or sets the trailer count.

	 <value>The trailer count.</value>
	*/
	private Integer TrailerCount = null;
	public final Integer getTrailerCount()
	{
		return TrailerCount;
	}
	public final void setTrailerCount(Integer value)
	{
		TrailerCount = value;
	}
	/**
	 Gets or sets the movie count.

	 <value>The movie count.</value>
	*/
	private Integer MovieCount = null;
	public final Integer getMovieCount()
	{
		return MovieCount;
	}
	public final void setMovieCount(Integer value)
	{
		MovieCount = value;
	}
	/**
	 Gets or sets the series count.

	 <value>The series count.</value>
	*/
	private Integer SeriesCount = null;
	public final Integer getSeriesCount()
	{
		return SeriesCount;
	}
	public final void setSeriesCount(Integer value)
	{
		SeriesCount = value;
	}
	private Integer ProgramCount = null;
	public final Integer getProgramCount()
	{
		return ProgramCount;
	}
	public final void setProgramCount(Integer value)
	{
		ProgramCount = value;
	}
	/**
	 Gets or sets the episode count.

	 <value>The episode count.</value>
	*/
	private Integer EpisodeCount = null;
	public final Integer getEpisodeCount()
	{
		return EpisodeCount;
	}
	public final void setEpisodeCount(Integer value)
	{
		EpisodeCount = value;
	}
	/**
	 Gets or sets the game count.

	 <value>The game count.</value>
	*/
	private Integer GameCount = null;
	public final Integer getGameCount()
	{
		return GameCount;
	}
	public final void setGameCount(Integer value)
	{
		GameCount = value;
	}
	/**
	 Gets or sets the song count.

	 <value>The song count.</value>
	*/
	private Integer SongCount = null;
	public final Integer getSongCount()
	{
		return SongCount;
	}
	public final void setSongCount(Integer value)
	{
		SongCount = value;
	}
	/**
	 Gets or sets the album count.

	 <value>The album count.</value>
	*/
	private Integer AlbumCount = null;
	public final Integer getAlbumCount()
	{
		return AlbumCount;
	}
	public final void setAlbumCount(Integer value)
	{
		AlbumCount = value;
	}
	private Integer ArtistCount = null;
	public final Integer getArtistCount()
	{
		return ArtistCount;
	}
	public final void setArtistCount(Integer value)
	{
		ArtistCount = value;
	}
	/**
	 Gets or sets the music video count.

	 <value>The music video count.</value>
	*/
	private Integer MusicVideoCount = null;
	public final Integer getMusicVideoCount()
	{
		return MusicVideoCount;
	}
	public final void setMusicVideoCount(Integer value)
	{
		MusicVideoCount = value;
	}

	/**
	 Gets or sets a value indicating whether [enable internet providers].

	 <value><c>true</c> if [enable internet providers]; otherwise, <c>false</c>.</value>
	*/
	private Boolean LockData = null;
	public final Boolean getLockData()
	{
		return LockData;
	}
	public final void setLockData(Boolean value)
	{
		LockData = value;
	}

	private Integer Width = null;
	public final Integer getWidth()
	{
		return Width;
	}
	public final void setWidth(Integer value)
	{
		Width = value;
	}
	private Integer Height = null;
	public final Integer getHeight()
	{
		return Height;
	}
	public final void setHeight(Integer value)
	{
		Height = value;
	}
	private String CameraMake;
	public final String getCameraMake()
	{
		return CameraMake;
	}
	public final void setCameraMake(String value)
	{
		CameraMake = value;
	}
	private String CameraModel;
	public final String getCameraModel()
	{
		return CameraModel;
	}
	public final void setCameraModel(String value)
	{
		CameraModel = value;
	}
	private String Software;
	public final String getSoftware()
	{
		return Software;
	}
	public final void setSoftware(String value)
	{
		Software = value;
	}
	private Double ExposureTime = null;
	public final Double getExposureTime()
	{
		return ExposureTime;
	}
	public final void setExposureTime(Double value)
	{
		ExposureTime = value;
	}
	private Double FocalLength = null;
	public final Double getFocalLength()
	{
		return FocalLength;
	}
	public final void setFocalLength(Double value)
	{
		FocalLength = value;
	}
	private ImageOrientation ImageOrientation = null;
	public final ImageOrientation getImageOrientation()
	{
		return ImageOrientation;
	}
	public final void setImageOrientation(ImageOrientation value)
	{
		ImageOrientation = value;
	}
	private Double Aperture = null;
	public final Double getAperture()
	{
		return Aperture;
	}
	public final void setAperture(Double value)
	{
		Aperture = value;
	}
	private Double ShutterSpeed = null;
	public final Double getShutterSpeed()
	{
		return ShutterSpeed;
	}
	public final void setShutterSpeed(Double value)
	{
		ShutterSpeed = value;
	}
	private Double Latitude = null;
	public final Double getLatitude()
	{
		return Latitude;
	}
	public final void setLatitude(Double value)
	{
		Latitude = value;
	}
	private Double Longitude = null;
	public final Double getLongitude()
	{
		return Longitude;
	}
	public final void setLongitude(Double value)
	{
		Longitude = value;
	}
	private Double Altitude = null;
	public final Double getAltitude()
	{
		return Altitude;
	}
	public final void setAltitude(Double value)
	{
		Altitude = value;
	}
	private Integer IsoSpeedRating = null;
	public final Integer getIsoSpeedRating()
	{
		return IsoSpeedRating;
	}
	public final void setIsoSpeedRating(Integer value)
	{
		IsoSpeedRating = value;
	}

	/**
	 Used by RecordingGroup
	*/
	private Integer RecordingCount = null;
	public final Integer getRecordingCount()
	{
		return RecordingCount;
	}
	public final void setRecordingCount(Integer value)
	{
		RecordingCount = value;
	}

	/**
	 Gets or sets the series timer identifier.

	 <value>The series timer identifier.</value>
	*/
	private String SeriesTimerId;
	public final String getSeriesTimerId()
	{
		return SeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		SeriesTimerId = value;
	}

	/**
	 Gets a value indicating whether this instance can resume.

	 <value><c>true</c> if this instance can resume; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool CanResume
	public final boolean getCanResume()
	{
		return getUserData() != null && getUserData().getPlaybackPositionTicks() > 0;
	}

	/**
	 Gets the resume position ticks.

	 <value>The resume position ticks.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public long ResumePositionTicks
	public final long getResumePositionTicks()
	{
		return getUserData() == null ? 0 : getUserData().getPlaybackPositionTicks();
	}

	/**
	 Gets the backdrop count.

	 <value>The backdrop count.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public int BackdropCount
	public final int getBackdropCount()
	{
		return getBackdropImageTags() == null ? 0 : getBackdropImageTags().size();
	}

	/**
	 Gets the screenshot count.

	 <value>The screenshot count.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public int ScreenshotCount
	public final int getScreenshotCount()
	{
		return getScreenshotImageTags() == null ? 0 : getScreenshotImageTags().size();
	}

	/**
	 Gets a value indicating whether this instance has banner.

	 <value><c>true</c> if this instance has banner; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasBanner
	public final boolean getHasBanner()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Banner);
	}

	/**
	 Gets a value indicating whether this instance has art.

	 <value><c>true</c> if this instance has art; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasArtImage
	public final boolean getHasArtImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Art);
	}

	/**
	 Gets a value indicating whether this instance has logo.

	 <value><c>true</c> if this instance has logo; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasLogo
	public final boolean getHasLogo()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Logo);
	}

	/**
	 Gets a value indicating whether this instance has thumb.

	 <value><c>true</c> if this instance has thumb; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasThumb
	public final boolean getHasThumb()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Thumb);
	}

	/**
	 Gets a value indicating whether this instance has thumb.

	 <value><c>true</c> if this instance has thumb; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasBackdrop
	public final boolean getHasBackdrop()
	{
		return (getBackdropImageTags() != null && getBackdropImageTags().size() > 0) || (getParentBackdropImageTags() != null && getParentBackdropImageTags().size() > 0);
	}

	/**
	 Gets a value indicating whether this instance has primary image.

	 <value><c>true</c> if this instance has primary image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasPrimaryImage
	public final boolean getHasPrimaryImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Primary);
	}

	/**
	 Gets a value indicating whether this instance has disc image.

	 <value><c>true</c> if this instance has disc image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasDiscImage
	public final boolean getHasDiscImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Disc);
	}

	/**
	 Gets a value indicating whether this instance has box image.

	 <value><c>true</c> if this instance has box image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasBoxImage
	public final boolean getHasBoxImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Box);
	}

	/**
	 Gets a value indicating whether this instance has box image.

	 <value><c>true</c> if this instance has box image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasBoxRearImage
	public final boolean getHasBoxRearImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.BoxRear);
	}

	/**
	 Gets a value indicating whether this instance has menu image.

	 <value><c>true</c> if this instance has menu image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasMenuImage
	public final boolean getHasMenuImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Menu);
	}

	/**
	 Gets a value indicating whether this instance is video.

	 <value><c>true</c> if this instance is video; otherwise, <c>false</c>.</value>
	*/
	public final boolean getIsVideo()
	{
		return org.jellyfin.apiclient.model.entities.MediaType.Video.equalsIgnoreCase(getMediaType());
	}

	/**
	 Gets a value indicating whether this instance is audio.

	 <value><c>true</c> if this instance is audio; otherwise, <c>false</c>.</value>
	*/
	public final boolean getIsAudio()
	{
		return org.jellyfin.apiclient.model.entities.MediaType.Audio.equalsIgnoreCase(getMediaType());
	}

	/**
	 Gets a value indicating whether this instance is game.

	 <value><c>true</c> if this instance is game; otherwise, <c>false</c>.</value>
	*/
	public final boolean getIsGame()
	{
		return org.jellyfin.apiclient.model.entities.MediaType.Game.equalsIgnoreCase(getMediaType());
	}

	/**
	 Gets a value indicating whether this instance is person.

	 <value><c>true</c> if this instance is person; otherwise, <c>false</c>.</value>
	*/
	public final boolean getIsPerson()
	{
		return getBaseItemType() == BaseItemType.Person;
	}

	/**
	 Gets a value indicating whether this instance is root.

	 <value><c>true</c> if this instance is root; otherwise, <c>false</c>.</value>
	*/
	public final boolean getIsRoot()
	{
		return getBaseItemType() == BaseItemType.AggregateFolder;
	}

	public final boolean getIsMusicGenre()
	{
		return getBaseItemType() == BaseItemType.MusicGenre;
	}

	public final boolean getIsGenre()
	{
		return getBaseItemType() == BaseItemType.Genre;
	}

	public final boolean getIsArtist()
	{
		return getBaseItemType() == BaseItemType.MusicArtist;
	}

	public final boolean getIsAlbum()
	{
		return getBaseItemType() == BaseItemType.MusicAlbum;
	}

	public final boolean getIsStudio()
	{
		return getBaseItemType() == BaseItemType.Studio;
	}

	public final boolean getSupportsSimilarItems()
	{
		return getBaseItemType() == BaseItemType.Movie
				|| getBaseItemType() == BaseItemType.Series
				|| getBaseItemType() == BaseItemType.MusicAlbum
				|| getBaseItemType() == BaseItemType.MusicArtist
				|| getBaseItemType() == BaseItemType.Program
				|| getBaseItemType() == BaseItemType.Recording
				|| getBaseItemType() == BaseItemType.ChannelVideoItem;
	}

	/** 
	 Gets or sets the program identifier.
	 
	 <value>The program identifier.</value>
	*/
	private String ProgramId;
	public final String getProgramId()
	{
		return ProgramId;
	}
	public final void setProgramId(String value)
	{
		ProgramId = value;
	}

	/** 
	 Gets or sets the channel primary image tag.
	 
	 <value>The channel primary image tag.</value>
	*/
	private String ChannelPrimaryImageTag;
	public final String getChannelPrimaryImageTag()
	{
		return ChannelPrimaryImageTag;
	}
	public final void setChannelPrimaryImageTag(String value)
	{
		ChannelPrimaryImageTag = value;
	}

	/** 
	 The start date of the recording, in UTC.
	*/
	private java.util.Date StartDate = null;
	public final java.util.Date getStartDate()
	{
		return StartDate;
	}
	public final void setStartDate(java.util.Date value)
	{
		StartDate = value;
	}

	/** 
	 Gets or sets the completion percentage.
	 
	 <value>The completion percentage.</value>
	*/
	private Double CompletionPercentage = null;
	public final Double getCompletionPercentage()
	{
		return CompletionPercentage;
	}
	public final void setCompletionPercentage(Double value)
	{
		CompletionPercentage = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is repeat.
	 
	 <value><c>true</c> if this instance is repeat; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsRepeat = null;
	public final Boolean getIsRepeat()
	{
		return IsRepeat;
	}
	public final void setIsRepeat(Boolean value)
	{
		IsRepeat = value;
	}

	/** 
	 Gets or sets the episode title.
	 
	 <value>The episode title.</value>
	*/
	private String EpisodeTitle;
	public final String getEpisodeTitle()
	{
		return EpisodeTitle;
	}
	public final void setEpisodeTitle(String value)
	{
		EpisodeTitle = value;
	}

	/** 
	 Gets or sets the type of the channel.
	 
	 <value>The type of the channel.</value>
	*/
	private ChannelType ChannelType = null;
	public final ChannelType getChannelType()
	{
		return ChannelType;
	}
	public final void setChannelType(ChannelType value)
	{
		ChannelType = value;
	}

	/** 
	 Gets or sets the audio.
	 
	 <value>The audio.</value>
	*/
	private ProgramAudio Audio = null;
	public final ProgramAudio getAudio()
	{
		return Audio;
	}
	public final void setAudio(ProgramAudio value)
	{
		Audio = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is movie.
	 
	 <value><c>true</c> if this instance is movie; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsMovie = null;
	public final Boolean getIsMovie()
	{
		return IsMovie;
	}
	public final void setIsMovie(Boolean value)
	{
		IsMovie = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is sports.
	 
	 <value><c>true</c> if this instance is sports; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsSports = null;
	public final Boolean getIsSports()
	{
		return IsSports;
	}
	public final void setIsSports(Boolean value)
	{
		IsSports = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is series.
	 
	 <value><c>true</c> if this instance is series; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsSeries = null;
	public final Boolean getIsSeries()
	{
		return IsSeries;
	}
	public final void setIsSeries(Boolean value)
	{
		IsSeries = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is live.
	 
	 <value><c>true</c> if this instance is live; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsLive = null;
	public final Boolean getIsLive()
	{
		return IsLive;
	}
	public final void setIsLive(Boolean value)
	{
		IsLive = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is news.
	 
	 <value><c>true</c> if this instance is news; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsNews = null;
	public final Boolean getIsNews()
	{
		return IsNews;
	}
	public final void setIsNews(Boolean value)
	{
		IsNews = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is kids.
	 
	 <value><c>true</c> if this instance is kids; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsKids = null;
	public final Boolean getIsKids()
	{
		return IsKids;
	}
	public final void setIsKids(Boolean value)
	{
		IsKids = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is premiere.
	 
	 <value><c>true</c> if this instance is premiere; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsPremiere = null;
	public final Boolean getIsPremiere()
	{
		return IsPremiere;
	}
	public final void setIsPremiere(Boolean value)
	{
		IsPremiere = value;
	}

	/** 
	 Gets or sets the timer identifier.
	 
	 <value>The timer identifier.</value>
	*/
	private String TimerId;
	public final String getTimerId()
	{
		return TimerId;
	}
	public final void setTimerId(String value)
	{
		TimerId = value;
	}
	/** 
	 Gets or sets the current program.
	 
	 <value>The current program.</value>
	*/
	private BaseItemDto CurrentProgram;
	public final BaseItemDto getCurrentProgram()
	{
		return CurrentProgram;
	}
	public final void setCurrentProgram(BaseItemDto value)
	{
		CurrentProgram = value;
	}
}
