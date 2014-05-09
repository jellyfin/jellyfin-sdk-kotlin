package MediaBrowser.Model.Dto;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Library.*;
import MediaBrowser.Model.Providers.*;

/** 
 This is strictly used as a data transfer object from the api layer.
 This holds information about a BaseItem in a format that is convenient for the client.
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, ID = {Id}, Type = {Type}")] public class BaseItemDto : IHasProviderIds, INotifyPropertyChanged, IItemDto
public class BaseItemDto implements IHasProviderIds, INotifyPropertyChanged, IItemDto
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the id.
	 
	 <value>The id.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date privateDateCreated = new java.util.Date();
	public final java.util.Date getDateCreated()
	{
		return privateDateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		privateDateCreated = value;
	}

	private java.util.Date privateDateLastMediaAdded = new java.util.Date();
	public final java.util.Date getDateLastMediaAdded()
	{
		return privateDateLastMediaAdded;
	}
	public final void setDateLastMediaAdded(java.util.Date value)
	{
		privateDateLastMediaAdded = value;
	}

	private Integer privateAirsBeforeSeasonNumber = new Integer();
	public final Integer getAirsBeforeSeasonNumber()
	{
		return privateAirsBeforeSeasonNumber;
	}
	public final void setAirsBeforeSeasonNumber(Integer value)
	{
		privateAirsBeforeSeasonNumber = value;
	}
	private Integer privateAirsAfterSeasonNumber = new Integer();
	public final Integer getAirsAfterSeasonNumber()
	{
		return privateAirsAfterSeasonNumber;
	}
	public final void setAirsAfterSeasonNumber(Integer value)
	{
		privateAirsAfterSeasonNumber = value;
	}
	private Integer privateAirsBeforeEpisodeNumber = new Integer();
	public final Integer getAirsBeforeEpisodeNumber()
	{
		return privateAirsBeforeEpisodeNumber;
	}
	public final void setAirsBeforeEpisodeNumber(Integer value)
	{
		privateAirsBeforeEpisodeNumber = value;
	}
	private Integer privateAbsoluteEpisodeNumber = new Integer();
	public final Integer getAbsoluteEpisodeNumber()
	{
		return privateAbsoluteEpisodeNumber;
	}
	public final void setAbsoluteEpisodeNumber(Integer value)
	{
		privateAbsoluteEpisodeNumber = value;
	}
	private Boolean privateDisplaySpecialsWithSeasons = new Boolean();
	public final Boolean getDisplaySpecialsWithSeasons()
	{
		return privateDisplaySpecialsWithSeasons;
	}
	public final void setDisplaySpecialsWithSeasons(Boolean value)
	{
		privateDisplaySpecialsWithSeasons = value;
	}

	private String privatePreferredMetadataLanguage;
	public final String getPreferredMetadataLanguage()
	{
		return privatePreferredMetadataLanguage;
	}
	public final void setPreferredMetadataLanguage(String value)
	{
		privatePreferredMetadataLanguage = value;
	}
	private String privatePreferredMetadataCountryCode;
	public final String getPreferredMetadataCountryCode()
	{
		return privatePreferredMetadataCountryCode;
	}
	public final void setPreferredMetadataCountryCode(String value)
	{
		privatePreferredMetadataCountryCode = value;
	}

	private String privateAwardSummary;
	public final String getAwardSummary()
	{
		return privateAwardSummary;
	}
	public final void setAwardSummary(String value)
	{
		privateAwardSummary = value;
	}

	private Float privateMetascore = new Float();
	public final Float getMetascore()
	{
		return privateMetascore;
	}
	public final void setMetascore(Float value)
	{
		privateMetascore = value;
	}

	private boolean privateIsUnidentified;
	public final boolean getIsUnidentified()
	{
		return privateIsUnidentified;
	}
	public final void setIsUnidentified(boolean value)
	{
		privateIsUnidentified = value;
	}

	private Integer privateAnimeSeriesIndex = new Integer();
	public final Integer getAnimeSeriesIndex()
	{
		return privateAnimeSeriesIndex;
	}
	public final void setAnimeSeriesIndex(Integer value)
	{
		privateAnimeSeriesIndex = value;
	}

	/** 
	 Gets or sets the DVD season number.
	 
	 <value>The DVD season number.</value>
	*/
	private Integer privateDvdSeasonNumber = new Integer();
	public final Integer getDvdSeasonNumber()
	{
		return privateDvdSeasonNumber;
	}
	public final void setDvdSeasonNumber(Integer value)
	{
		privateDvdSeasonNumber = value;
	}
	/** 
	 Gets or sets the DVD episode number.
	 
	 <value>The DVD episode number.</value>
	*/
	private Float privateDvdEpisodeNumber = new Float();
	public final Float getDvdEpisodeNumber()
	{
		return privateDvdEpisodeNumber;
	}
	public final void setDvdEpisodeNumber(Float value)
	{
		privateDvdEpisodeNumber = value;
	}

	/** 
	 Gets or sets the name of the sort.
	 
	 <value>The name of the sort.</value>
	*/
	private String privateSortName;
	public final String getSortName()
	{
		return privateSortName;
	}
	public final void setSortName(String value)
	{
		privateSortName = value;
	}
	private String privateForcedSortName;
	public final String getForcedSortName()
	{
		return privateForcedSortName;
	}
	public final void setForcedSortName(String value)
	{
		privateForcedSortName = value;
	}

	/** 
	 Gets or sets the video3 D format.
	 
	 <value>The video3 D format.</value>
	*/
	private Video3DFormat privateVideo3DFormat = new Video3DFormat();
	public final Video3DFormat getVideo3DFormat()
	{
		return privateVideo3DFormat;
	}
	public final void setVideo3DFormat(Video3DFormat value)
	{
		privateVideo3DFormat = value;
	}

	/** 
	 Gets or sets the premiere date.
	 
	 <value>The premiere date.</value>
	*/
	private java.util.Date privatePremiereDate = new java.util.Date();
	public final java.util.Date getPremiereDate()
	{
		return privatePremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		privatePremiereDate = value;
	}

	/** 
	 Gets or sets the external urls.
	 
	 <value>The external urls.</value>
	*/
	private ExternalUrl[] privateExternalUrls;
	public final ExternalUrl[] getExternalUrls()
	{
		return privateExternalUrls;
	}
	public final void setExternalUrls(ExternalUrl[] value)
	{
		privateExternalUrls = value;
	}

	/** 
	 Gets or sets the media versions.
	 
	 <value>The media versions.</value>
	*/
	private java.util.ArrayList<MediaSourceInfo> privateMediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return privateMediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		privateMediaSources = value;
	}

	/** 
	 Gets or sets the critic rating.
	 
	 <value>The critic rating.</value>
	*/
	private Float privateCriticRating = new Float();
	public final Float getCriticRating()
	{
		return privateCriticRating;
	}
	public final void setCriticRating(Float value)
	{
		privateCriticRating = value;
	}

	/** 
	 Gets or sets the game system.
	 
	 <value>The game system.</value>
	*/
	private String privateGameSystem;
	public final String getGameSystem()
	{
		return privateGameSystem;
	}
	public final void setGameSystem(String value)
	{
		privateGameSystem = value;
	}

	/** 
	 Gets or sets the critic rating summary.
	 
	 <value>The critic rating summary.</value>
	*/
	private String privateCriticRatingSummary;
	public final String getCriticRatingSummary()
	{
		return privateCriticRatingSummary;
	}
	public final void setCriticRatingSummary(String value)
	{
		privateCriticRatingSummary = value;
	}

	private java.util.ArrayList<String> privateMultiPartGameFiles;
	public final java.util.ArrayList<String> getMultiPartGameFiles()
	{
		return privateMultiPartGameFiles;
	}
	public final void setMultiPartGameFiles(java.util.ArrayList<String> value)
	{
		privateMultiPartGameFiles = value;
	}

	/** 
	 Gets or sets the path.
	 
	 <value>The path.</value>
	*/
	private String privatePath;
	public final String getPath()
	{
		return privatePath;
	}
	public final void setPath(String value)
	{
		privatePath = value;
	}

	/** 
	 Gets or sets the official rating.
	 
	 <value>The official rating.</value>
	*/
	private String privateOfficialRating;
	public final String getOfficialRating()
	{
		return privateOfficialRating;
	}
	public final void setOfficialRating(String value)
	{
		privateOfficialRating = value;
	}

	/** 
	 Gets or sets the custom rating.
	 
	 <value>The custom rating.</value>
	*/
	private String privateCustomRating;
	public final String getCustomRating()
	{
		return privateCustomRating;
	}
	public final void setCustomRating(String value)
	{
		privateCustomRating = value;
	}

	/** 
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String privateOverview;
	public final String getOverview()
	{
		return privateOverview;
	}
	public final void setOverview(String value)
	{
		privateOverview = value;
	}

	/** 
	 Gets or sets the name of the TMDB collection.
	 
	 <value>The name of the TMDB collection.</value>
	*/
	private String privateTmdbCollectionName;
	public final String getTmdbCollectionName()
	{
		return privateTmdbCollectionName;
	}
	public final void setTmdbCollectionName(String value)
	{
		privateTmdbCollectionName = value;
	}

	/** 
	 Gets or sets the taglines.
	 
	 <value>The taglines.</value>
	*/
	private java.util.ArrayList<String> privateTaglines;
	public final java.util.ArrayList<String> getTaglines()
	{
		return privateTaglines;
	}
	public final void setTaglines(java.util.ArrayList<String> value)
	{
		privateTaglines = value;
	}

	/** 
	 Gets or sets the genres.
	 
	 <value>The genres.</value>
	*/
	private java.util.ArrayList<String> privateGenres;
	public final java.util.ArrayList<String> getGenres()
	{
		return privateGenres;
	}
	public final void setGenres(java.util.ArrayList<String> value)
	{
		privateGenres = value;
	}

	/** 
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Float privateCommunityRating = new Float();
	public final Float getCommunityRating()
	{
		return privateCommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		privateCommunityRating = value;
	}

	/** 
	 Gets or sets the vote count.
	 
	 <value>The vote count.</value>
	*/
	private Integer privateVoteCount = new Integer();
	public final Integer getVoteCount()
	{
		return privateVoteCount;
	}
	public final void setVoteCount(Integer value)
	{
		privateVoteCount = value;
	}

	/** 
	 Gets or sets the cumulative run time ticks.
	 
	 <value>The cumulative run time ticks.</value>
	*/
	private Long privateCumulativeRunTimeTicks = new Long();
	public final Long getCumulativeRunTimeTicks()
	{
		return privateCumulativeRunTimeTicks;
	}
	public final void setCumulativeRunTimeTicks(Long value)
	{
		privateCumulativeRunTimeTicks = value;
	}

	/** 
	 Gets or sets the original run time ticks.
	 
	 <value>The original run time ticks.</value>
	*/
	private Long privateOriginalRunTimeTicks = new Long();
	public final Long getOriginalRunTimeTicks()
	{
		return privateOriginalRunTimeTicks;
	}
	public final void setOriginalRunTimeTicks(Long value)
	{
		privateOriginalRunTimeTicks = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long privateRunTimeTicks = new Long();
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	/** 
	 Gets or sets the play access.
	 
	 <value>The play access.</value>
	*/
	private PlayAccess privatePlayAccess = getPlayAccess().values()[0];
	public final PlayAccess getPlayAccess()
	{
		return privatePlayAccess;
	}
	public final void setPlayAccess(PlayAccess value)
	{
		privatePlayAccess = value;
	}

	/** 
	 Gets or sets the aspect ratio.
	 
	 <value>The aspect ratio.</value>
	*/
	private String privateAspectRatio;
	public final String getAspectRatio()
	{
		return privateAspectRatio;
	}
	public final void setAspectRatio(String value)
	{
		privateAspectRatio = value;
	}

	/** 
	 Gets or sets the production year.
	 
	 <value>The production year.</value>
	*/
	private Integer privateProductionYear = new Integer();
	public final Integer getProductionYear()
	{
		return privateProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		privateProductionYear = value;
	}

	/** 
	 Gets or sets the season count.
	 
	 <value>The season count.</value>
	*/
	private Integer privateSeasonCount = new Integer();
	public final Integer getSeasonCount()
	{
		return privateSeasonCount;
	}
	public final void setSeasonCount(Integer value)
	{
		privateSeasonCount = value;
	}

	/** 
	 Gets or sets the players supported by a game.
	 
	 <value>The players.</value>
	*/
	private Integer privatePlayers = new Integer();
	public final Integer getPlayers()
	{
		return privatePlayers;
	}
	public final void setPlayers(Integer value)
	{
		privatePlayers = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is place holder.
	 
	 <value><c>null</c> if [is place holder] contains no value, <c>true</c> if [is place holder]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsPlaceHolder = new Boolean();
	public final Boolean getIsPlaceHolder()
	{
		return privateIsPlaceHolder;
	}
	public final void setIsPlaceHolder(Boolean value)
	{
		privateIsPlaceHolder = value;
	}

	/** 
	 Gets or sets the index number.
	 
	 <value>The index number.</value>
	*/
	private Integer privateIndexNumber = new Integer();
	public final Integer getIndexNumber()
	{
		return privateIndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		privateIndexNumber = value;
	}

	/** 
	 Gets or sets the index number end.
	 
	 <value>The index number end.</value>
	*/
	private Integer privateIndexNumberEnd = new Integer();
	public final Integer getIndexNumberEnd()
	{
		return privateIndexNumberEnd;
	}
	public final void setIndexNumberEnd(Integer value)
	{
		privateIndexNumberEnd = value;
	}

	/** 
	 Gets or sets the parent index number.
	 
	 <value>The parent index number.</value>
	*/
	private Integer privateParentIndexNumber = new Integer();
	public final Integer getParentIndexNumber()
	{
		return privateParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		privateParentIndexNumber = value;
	}

	/** 
	 Gets or sets the trailer urls.
	 
	 <value>The trailer urls.</value>
	*/
	private java.util.ArrayList<MediaUrl> privateRemoteTrailers;
	public final java.util.ArrayList<MediaUrl> getRemoteTrailers()
	{
		return privateRemoteTrailers;
	}
	public final void setRemoteTrailers(java.util.ArrayList<MediaUrl> value)
	{
		privateRemoteTrailers = value;
	}

	/** 
	 Gets or sets the soundtrack ids.
	 
	 <value>The soundtrack ids.</value>
	*/
	private String[] privateSoundtrackIds;
	public final String[] getSoundtrackIds()
	{
		return privateSoundtrackIds;
	}
	public final void setSoundtrackIds(String[] value)
	{
		privateSoundtrackIds = value;
	}

	/** 
	 Gets or sets the provider ids.
	 
	 <value>The provider ids.</value>
	*/
	private java.util.HashMap<String, String> privateProviderIds;
	public final java.util.HashMap<String, String> getProviderIds()
	{
		return privateProviderIds;
	}
	public final void setProviderIds(java.util.HashMap<String, String> value)
	{
		privateProviderIds = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is HD.
	 
	 <value><c>null</c> if [is HD] contains no value, <c>true</c> if [is HD]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsHD = new Boolean();
	public final Boolean getIsHD()
	{
		return privateIsHD;
	}
	public final void setIsHD(Boolean value)
	{
		privateIsHD = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is folder.
	 
	 <value><c>true</c> if this instance is folder; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsFolder;
	public final boolean getIsFolder()
	{
		return privateIsFolder;
	}
	public final void setIsFolder(boolean value)
	{
		privateIsFolder = value;
	}

	/** 
	 Gets or sets the parent id.
	 
	 <value>The parent id.</value>
	*/
	private String privateParentId;
	public final String getParentId()
	{
		return privateParentId;
	}
	public final void setParentId(String value)
	{
		privateParentId = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the people.
	 
	 <value>The people.</value>
	*/
	private BaseItemPerson[] privatePeople;
	public final BaseItemPerson[] getPeople()
	{
		return privatePeople;
	}
	public final void setPeople(BaseItemPerson[] value)
	{
		privatePeople = value;
	}

	/** 
	 Gets or sets the studios.
	 
	 <value>The studios.</value>
	*/
	private StudioDto[] privateStudios;
	public final StudioDto[] getStudios()
	{
		return privateStudios;
	}
	public final void setStudios(StudioDto[] value)
	{
		privateStudios = value;
	}

	/** 
	 If the item does not have a logo, this will hold the Id of the Parent that has one.
	 
	 <value>The parent logo item id.</value>
	*/
	private String privateParentLogoItemId;
	public final String getParentLogoItemId()
	{
		return privateParentLogoItemId;
	}
	public final void setParentLogoItemId(String value)
	{
		privateParentLogoItemId = value;
	}

	/** 
	 If the item does not have any backdrops, this will hold the Id of the Parent that has one.
	 
	 <value>The parent backdrop item id.</value>
	*/
	private String privateParentBackdropItemId;
	public final String getParentBackdropItemId()
	{
		return privateParentBackdropItemId;
	}
	public final void setParentBackdropItemId(String value)
	{
		privateParentBackdropItemId = value;
	}

	/** 
	 Gets or sets the parent backdrop image tags.
	 
	 <value>The parent backdrop image tags.</value>
	*/
	private java.util.ArrayList<String> privateParentBackdropImageTags;
	public final java.util.ArrayList<String> getParentBackdropImageTags()
	{
		return privateParentBackdropImageTags;
	}
	public final void setParentBackdropImageTags(java.util.ArrayList<String> value)
	{
		privateParentBackdropImageTags = value;
	}

	/** 
	 Gets or sets the local trailer count.
	 
	 <value>The local trailer count.</value>
	*/
	private Integer privateLocalTrailerCount = new Integer();
	public final Integer getLocalTrailerCount()
	{
		return privateLocalTrailerCount;
	}
	public final void setLocalTrailerCount(Integer value)
	{
		privateLocalTrailerCount = value;
	}

	/** 
	 User data for this item based on the user it's being requested for
	 
	 <value>The user data.</value>
	*/
	private UserItemDataDto privateUserData;
	public final UserItemDataDto getUserData()
	{
		return privateUserData;
	}
	public final void setUserData(UserItemDataDto value)
	{
		privateUserData = value;
	}

	/** 
	 Gets or sets the recently added item count.
	 
	 <value>The recently added item count.</value>
	*/
	private Integer privateRecentlyAddedItemCount = new Integer();
	public final Integer getRecentlyAddedItemCount()
	{
		return privateRecentlyAddedItemCount;
	}
	public final void setRecentlyAddedItemCount(Integer value)
	{
		privateRecentlyAddedItemCount = value;
	}

	/** 
	 Gets or sets the played percentage.
	 
	 <value>The played percentage.</value>
	*/
	private Double privatePlayedPercentage = new Double();
	public final Double getPlayedPercentage()
	{
		return privatePlayedPercentage;
	}
	public final void setPlayedPercentage(Double value)
	{
		privatePlayedPercentage = value;
	}

	/** 
	 Gets or sets the recursive item count.
	 
	 <value>The recursive item count.</value>
	*/
	private Integer privateRecursiveItemCount = new Integer();
	public final Integer getRecursiveItemCount()
	{
		return privateRecursiveItemCount;
	}
	public final void setRecursiveItemCount(Integer value)
	{
		privateRecursiveItemCount = value;
	}

	/** 
	 Gets or sets the recursive unplayed item count.
	 
	 <value>The recursive unplayed item count.</value>
	*/
	private Integer privateRecursiveUnplayedItemCount = new Integer();
	public final Integer getRecursiveUnplayedItemCount()
	{
		return privateRecursiveUnplayedItemCount;
	}
	public final void setRecursiveUnplayedItemCount(Integer value)
	{
		privateRecursiveUnplayedItemCount = value;
	}

	/** 
	 Gets or sets the child count.
	 
	 <value>The child count.</value>
	*/
	private Integer privateChildCount = new Integer();
	public final Integer getChildCount()
	{
		return privateChildCount;
	}
	public final void setChildCount(Integer value)
	{
		privateChildCount = value;
	}

	/** 
	 Gets or sets the name of the series.
	 
	 <value>The name of the series.</value>
	*/
	private String privateSeriesName;
	public final String getSeriesName()
	{
		return privateSeriesName;
	}
	public final void setSeriesName(String value)
	{
		privateSeriesName = value;
	}

	/** 
	 Gets or sets the series id.
	 
	 <value>The series id.</value>
	*/
	private String privateSeriesId;
	public final String getSeriesId()
	{
		return privateSeriesId;
	}
	public final void setSeriesId(String value)
	{
		privateSeriesId = value;
	}

	/** 
	 Gets or sets the season identifier.
	 
	 <value>The season identifier.</value>
	*/
	private String privateSeasonId;
	public final String getSeasonId()
	{
		return privateSeasonId;
	}
	public final void setSeasonId(String value)
	{
		privateSeasonId = value;
	}

	/** 
	 Gets or sets the special feature count.
	 
	 <value>The special feature count.</value>
	*/
	private Integer privateSpecialFeatureCount = new Integer();
	public final Integer getSpecialFeatureCount()
	{
		return privateSpecialFeatureCount;
	}
	public final void setSpecialFeatureCount(Integer value)
	{
		privateSpecialFeatureCount = value;
	}

	/** 
	 Gets or sets the display preferences id.
	 
	 <value>The display preferences id.</value>
	*/
	private String privateDisplayPreferencesId;
	public final String getDisplayPreferencesId()
	{
		return privateDisplayPreferencesId;
	}
	public final void setDisplayPreferencesId(String value)
	{
		privateDisplayPreferencesId = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SeriesStatus privateStatus = new SeriesStatus();
	public final SeriesStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(SeriesStatus value)
	{
		privateStatus = value;
	}

	/** 
	 Gets or sets the air time.
	 
	 <value>The air time.</value>
	*/
	private String privateAirTime;
	public final String getAirTime()
	{
		return privateAirTime;
	}
	public final void setAirTime(String value)
	{
		privateAirTime = value;
	}

	/** 
	 Gets or sets the air days.
	 
	 <value>The air days.</value>
	*/
	private java.util.ArrayList<DayOfWeek> privateAirDays;
	public final java.util.ArrayList<DayOfWeek> getAirDays()
	{
		return privateAirDays;
	}
	public final void setAirDays(java.util.ArrayList<DayOfWeek> value)
	{
		privateAirDays = value;
	}

	/** 
	 Gets or sets the index options.
	 
	 <value>The index options.</value>
	*/
	private String[] privateIndexOptions;
	public final String[] getIndexOptions()
	{
		return privateIndexOptions;
	}
	public final void setIndexOptions(String[] value)
	{
		privateIndexOptions = value;
	}

	/** 
	 Gets or sets the tags.
	 
	 <value>The tags.</value>
	*/
	private java.util.ArrayList<String> privateTags;
	public final java.util.ArrayList<String> getTags()
	{
		return privateTags;
	}
	public final void setTags(java.util.ArrayList<String> value)
	{
		privateTags = value;
	}

	/** 
	 Gets or sets the keywords.
	 
	 <value>The keywords.</value>
	*/
	private java.util.ArrayList<String> privateKeywords;
	public final java.util.ArrayList<String> getKeywords()
	{
		return privateKeywords;
	}
	public final void setKeywords(java.util.ArrayList<String> value)
	{
		privateKeywords = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio, after image enhancements.
	 
	 <value>The primary image aspect ratio.</value>
	*/
	private Double privatePrimaryImageAspectRatio = new Double();
	public final Double getPrimaryImageAspectRatio()
	{
		return privatePrimaryImageAspectRatio;
	}
	public final void setPrimaryImageAspectRatio(Double value)
	{
		privatePrimaryImageAspectRatio = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio, before image enhancements.
	 
	 <value>The original primary image aspect ratio.</value>
	*/
	private Double privateOriginalPrimaryImageAspectRatio = new Double();
	public final Double getOriginalPrimaryImageAspectRatio()
	{
		return privateOriginalPrimaryImageAspectRatio;
	}
	public final void setOriginalPrimaryImageAspectRatio(Double value)
	{
		privateOriginalPrimaryImageAspectRatio = value;
	}

	/** 
	 Gets or sets the artists.
	 
	 <value>The artists.</value>
	*/
	private java.util.ArrayList<String> privateArtists;
	public final java.util.ArrayList<String> getArtists()
	{
		return privateArtists;
	}
	public final void setArtists(java.util.ArrayList<String> value)
	{
		privateArtists = value;
	}

	/** 
	 Gets or sets the album.
	 
	 <value>The album.</value>
	*/
	private String privateAlbum;
	public final String getAlbum()
	{
		return privateAlbum;
	}
	public final void setAlbum(String value)
	{
		privateAlbum = value;
	}

	/** 
	 Gets or sets the type of the collection.
	 
	 <value>The type of the collection.</value>
	*/
	private String privateCollectionType;
	public final String getCollectionType()
	{
		return privateCollectionType;
	}
	public final void setCollectionType(String value)
	{
		privateCollectionType = value;
	}

	/** 
	 Gets or sets the display order.
	 
	 <value>The display order.</value>
	*/
	private String privateDisplayOrder;
	public final String getDisplayOrder()
	{
		return privateDisplayOrder;
	}
	public final void setDisplayOrder(String value)
	{
		privateDisplayOrder = value;
	}

	/** 
	 Gets or sets the album id.
	 
	 <value>The album id.</value>
	*/
	private String privateAlbumId;
	public final String getAlbumId()
	{
		return privateAlbumId;
	}
	public final void setAlbumId(String value)
	{
		privateAlbumId = value;
	}
	/** 
	 Gets or sets the album image tag.
	 
	 <value>The album image tag.</value>
	*/
	private String privateAlbumPrimaryImageTag;
	public final String getAlbumPrimaryImageTag()
	{
		return privateAlbumPrimaryImageTag;
	}
	public final void setAlbumPrimaryImageTag(String value)
	{
		privateAlbumPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the series primary image tag.
	 
	 <value>The series primary image tag.</value>
	*/
	private String privateSeriesPrimaryImageTag;
	public final String getSeriesPrimaryImageTag()
	{
		return privateSeriesPrimaryImageTag;
	}
	public final void setSeriesPrimaryImageTag(String value)
	{
		privateSeriesPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the album artist.
	 
	 <value>The album artist.</value>
	*/
	private String privateAlbumArtist;
	public final String getAlbumArtist()
	{
		return privateAlbumArtist;
	}
	public final void setAlbumArtist(String value)
	{
		privateAlbumArtist = value;
	}

	/** 
	 Gets or sets the media streams.
	 
	 <value>The media streams.</value>
	*/
	private java.util.ArrayList<MediaStream> privateMediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return privateMediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		privateMediaStreams = value;
	}

	/** 
	 Gets or sets the type of the video.
	 
	 <value>The type of the video.</value>
	*/
	private VideoType privateVideoType = new VideoType();
	public final VideoType getVideoType()
	{
		return privateVideoType;
	}
	public final void setVideoType(VideoType value)
	{
		privateVideoType = value;
	}

	/** 
	 Gets or sets the display type of the media.
	 
	 <value>The display type of the media.</value>
	*/
	private String privateDisplayMediaType;
	public final String getDisplayMediaType()
	{
		return privateDisplayMediaType;
	}
	public final void setDisplayMediaType(String value)
	{
		privateDisplayMediaType = value;
	}

	/** 
	 Gets or sets the part count.
	 
	 <value>The part count.</value>
	*/
	private Integer privatePartCount = new Integer();
	public final Integer getPartCount()
	{
		return privatePartCount;
	}
	public final void setPartCount(Integer value)
	{
		privatePartCount = value;
	}
	private Integer privateMediaSourceCount = new Integer();
	public final Integer getMediaSourceCount()
	{
		return privateMediaSourceCount;
	}
	public final void setMediaSourceCount(Integer value)
	{
		privateMediaSourceCount = value;
	}

	/** 
	 Determines whether the specified type is type.
	 
	 @param type The type.
	 @return <c>true</c> if the specified type is type; otherwise, <c>false</c>.
	*/
	public final boolean IsType(java.lang.Class type)
	{
		return IsType(type.getSimpleName());
	}

	/** 
	 Determines whether the specified type is type.
	 
	 @param type The type.
	 @return <c>true</c> if the specified type is type; otherwise, <c>false</c>.
	*/
	public final boolean IsType(String type)
	{
		return getType().equals(type, StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Gets or sets the image tags.
	 
	 <value>The image tags.</value>
	*/
	private java.util.HashMap<ImageType, String> privateImageTags;
	public final java.util.HashMap<ImageType, String> getImageTags()
	{
		return privateImageTags;
	}
	public final void setImageTags(java.util.HashMap<ImageType, String> value)
	{
		privateImageTags = value;
	}

	/** 
	 Gets or sets the backdrop image tags.
	 
	 <value>The backdrop image tags.</value>
	*/
	private java.util.ArrayList<String> privateBackdropImageTags;
	public final java.util.ArrayList<String> getBackdropImageTags()
	{
		return privateBackdropImageTags;
	}
	public final void setBackdropImageTags(java.util.ArrayList<String> value)
	{
		privateBackdropImageTags = value;
	}

	/** 
	 Gets or sets the screenshot image tags.
	 
	 <value>The screenshot image tags.</value>
	*/
	private java.util.ArrayList<String> privateScreenshotImageTags;
	public final java.util.ArrayList<String> getScreenshotImageTags()
	{
		return privateScreenshotImageTags;
	}
	public final void setScreenshotImageTags(java.util.ArrayList<String> value)
	{
		privateScreenshotImageTags = value;
	}

	/** 
	 Gets or sets the parent logo image tag.
	 
	 <value>The parent logo image tag.</value>
	*/
	private String privateParentLogoImageTag;
	public final String getParentLogoImageTag()
	{
		return privateParentLogoImageTag;
	}
	public final void setParentLogoImageTag(String value)
	{
		privateParentLogoImageTag = value;
	}

	/** 
	 If the item does not have a art, this will hold the Id of the Parent that has one.
	 
	 <value>The parent art item id.</value>
	*/
	private String privateParentArtItemId;
	public final String getParentArtItemId()
	{
		return privateParentArtItemId;
	}
	public final void setParentArtItemId(String value)
	{
		privateParentArtItemId = value;
	}

	/** 
	 Gets or sets the parent art image tag.
	 
	 <value>The parent art image tag.</value>
	*/
	private String privateParentArtImageTag;
	public final String getParentArtImageTag()
	{
		return privateParentArtImageTag;
	}
	public final void setParentArtImageTag(String value)
	{
		privateParentArtImageTag = value;
	}

	/** 
	 Gets or sets the series thumb image tag.
	 
	 <value>The series thumb image tag.</value>
	*/
	private String privateSeriesThumbImageTag;
	public final String getSeriesThumbImageTag()
	{
		return privateSeriesThumbImageTag;
	}
	public final void setSeriesThumbImageTag(String value)
	{
		privateSeriesThumbImageTag = value;
	}

	/** 
	 Gets or sets the series studio.
	 
	 <value>The series studio.</value>
	*/
	private String privateSeriesStudio;
	public final String getSeriesStudio()
	{
		return privateSeriesStudio;
	}
	public final void setSeriesStudio(String value)
	{
		privateSeriesStudio = value;
	}

	/** 
	 Gets or sets the parent thumb item id.
	 
	 <value>The parent thumb item id.</value>
	*/
	private String privateParentThumbItemId;
	public final String getParentThumbItemId()
	{
		return privateParentThumbItemId;
	}
	public final void setParentThumbItemId(String value)
	{
		privateParentThumbItemId = value;
	}

	/** 
	 Gets or sets the parent thumb image tag.
	 
	 <value>The parent thumb image tag.</value>
	*/
	private String privateParentThumbImageTag;
	public final String getParentThumbImageTag()
	{
		return privateParentThumbImageTag;
	}
	public final void setParentThumbImageTag(String value)
	{
		privateParentThumbImageTag = value;
	}

	/** 
	 Gets or sets the chapters.
	 
	 <value>The chapters.</value>
	*/
	private java.util.ArrayList<ChapterInfoDto> privateChapters;
	public final java.util.ArrayList<ChapterInfoDto> getChapters()
	{
		return privateChapters;
	}
	public final void setChapters(java.util.ArrayList<ChapterInfoDto> value)
	{
		privateChapters = value;
	}

	/** 
	 Gets or sets the type of the location.
	 
	 <value>The type of the location.</value>
	*/
	private LocationType privateLocationType = getLocationType().values()[0];
	public final LocationType getLocationType()
	{
		return privateLocationType;
	}
	public final void setLocationType(LocationType value)
	{
		privateLocationType = value;
	}

	/** 
	 Gets or sets the type of the iso.
	 
	 <value>The type of the iso.</value>
	*/
	private IsoType privateIsoType = new IsoType();
	public final IsoType getIsoType()
	{
		return privateIsoType;
	}
	public final void setIsoType(IsoType value)
	{
		privateIsoType = value;
	}

	/** 
	 Gets or sets the type of the media.
	 
	 <value>The type of the media.</value>
	*/
	private String privateMediaType;
	public final String getMediaType()
	{
		return privateMediaType;
	}
	public final void setMediaType(String value)
	{
		privateMediaType = value;
	}

	/** 
	 Gets or sets the overview HTML.
	 
	 <value>The overview HTML.</value>
	*/
	private String privateOverviewHtml;
	public final String getOverviewHtml()
	{
		return privateOverviewHtml;
	}
	public final void setOverviewHtml(String value)
	{
		privateOverviewHtml = value;
	}

	/** 
	 Gets or sets the end date.
	 
	 <value>The end date.</value>
	*/
	private java.util.Date privateEndDate = new java.util.Date();
	public final java.util.Date getEndDate()
	{
		return privateEndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		privateEndDate = value;
	}

	/** 
	 Gets or sets the home page URL.
	 
	 <value>The home page URL.</value>
	*/
	private String privateHomePageUrl;
	public final String getHomePageUrl()
	{
		return privateHomePageUrl;
	}
	public final void setHomePageUrl(String value)
	{
		privateHomePageUrl = value;
	}

	/** 
	 Gets or sets the production locations.
	 
	 <value>The production locations.</value>
	*/
	private java.util.ArrayList<String> privateProductionLocations;
	public final java.util.ArrayList<String> getProductionLocations()
	{
		return privateProductionLocations;
	}
	public final void setProductionLocations(java.util.ArrayList<String> value)
	{
		privateProductionLocations = value;
	}

	/** 
	 Gets or sets the budget.
	 
	 <value>The budget.</value>
	*/
	private Double privateBudget = new Double();
	public final Double getBudget()
	{
		return privateBudget;
	}
	public final void setBudget(Double value)
	{
		privateBudget = value;
	}

	/** 
	 Gets or sets the revenue.
	 
	 <value>The revenue.</value>
	*/
	private Double privateRevenue = new Double();
	public final Double getRevenue()
	{
		return privateRevenue;
	}
	public final void setRevenue(Double value)
	{
		privateRevenue = value;
	}

	/** 
	 Gets or sets the locked fields.
	 
	 <value>The locked fields.</value>
	*/
	private java.util.ArrayList<MetadataFields> privateLockedFields;
	public final java.util.ArrayList<MetadataFields> getLockedFields()
	{
		return privateLockedFields;
	}
	public final void setLockedFields(java.util.ArrayList<MetadataFields> value)
	{
		privateLockedFields = value;
	}

	private Integer privateAdultVideoCount = new Integer();
	public final Integer getAdultVideoCount()
	{
		return privateAdultVideoCount;
	}
	public final void setAdultVideoCount(Integer value)
	{
		privateAdultVideoCount = value;
	}
	/** 
	 Gets or sets the movie count.
	 
	 <value>The movie count.</value>
	*/
	private Integer privateMovieCount = new Integer();
	public final Integer getMovieCount()
	{
		return privateMovieCount;
	}
	public final void setMovieCount(Integer value)
	{
		privateMovieCount = value;
	}
	/** 
	 Gets or sets the series count.
	 
	 <value>The series count.</value>
	*/
	private Integer privateSeriesCount = new Integer();
	public final Integer getSeriesCount()
	{
		return privateSeriesCount;
	}
	public final void setSeriesCount(Integer value)
	{
		privateSeriesCount = value;
	}
	/** 
	 Gets or sets the episode count.
	 
	 <value>The episode count.</value>
	*/
	private Integer privateEpisodeCount = new Integer();
	public final Integer getEpisodeCount()
	{
		return privateEpisodeCount;
	}
	public final void setEpisodeCount(Integer value)
	{
		privateEpisodeCount = value;
	}
	/** 
	 Gets or sets the game count.
	 
	 <value>The game count.</value>
	*/
	private Integer privateGameCount = new Integer();
	public final Integer getGameCount()
	{
		return privateGameCount;
	}
	public final void setGameCount(Integer value)
	{
		privateGameCount = value;
	}
	/** 
	 Gets or sets the trailer count.
	 
	 <value>The trailer count.</value>
	*/
	private Integer privateTrailerCount = new Integer();
	public final Integer getTrailerCount()
	{
		return privateTrailerCount;
	}
	public final void setTrailerCount(Integer value)
	{
		privateTrailerCount = value;
	}
	/** 
	 Gets or sets the song count.
	 
	 <value>The song count.</value>
	*/
	private Integer privateSongCount = new Integer();
	public final Integer getSongCount()
	{
		return privateSongCount;
	}
	public final void setSongCount(Integer value)
	{
		privateSongCount = value;
	}
	/** 
	 Gets or sets the album count.
	 
	 <value>The album count.</value>
	*/
	private Integer privateAlbumCount = new Integer();
	public final Integer getAlbumCount()
	{
		return privateAlbumCount;
	}
	public final void setAlbumCount(Integer value)
	{
		privateAlbumCount = value;
	}
	/** 
	 Gets or sets the music video count.
	 
	 <value>The music video count.</value>
	*/
	private Integer privateMusicVideoCount = new Integer();
	public final Integer getMusicVideoCount()
	{
		return privateMusicVideoCount;
	}
	public final void setMusicVideoCount(Integer value)
	{
		privateMusicVideoCount = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable internet providers].
	 
	 <value><c>true</c> if [enable internet providers]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateLockData = new Boolean();
	public final Boolean getLockData()
	{
		return privateLockData;
	}
	public final void setLockData(Boolean value)
	{
		privateLockData = value;
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
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsVideo
	public final boolean getIsVideo()
	{
		return String.equals(getMediaType(), Entities.MediaType.Video, StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Gets a value indicating whether this instance is audio.
	 
	 <value><c>true</c> if this instance is audio; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsAudio
	public final boolean getIsAudio()
	{
		return String.equals(getMediaType(), Entities.MediaType.Audio, StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Gets a value indicating whether this instance is game.
	 
	 <value><c>true</c> if this instance is game; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsGame
	public final boolean getIsGame()
	{
		return String.equals(getMediaType(), Entities.MediaType.Game, StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Gets a value indicating whether this instance is person.
	 
	 <value><c>true</c> if this instance is person; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsPerson
	public final boolean getIsPerson()
	{
		return String.equals(getType(), "Person", StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Gets a value indicating whether this instance is root.
	 
	 <value><c>true</c> if this instance is root; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsRoot
	public final boolean getIsRoot()
	{
		return String.equals(getType(), "AggregateFolder", StringComparison.OrdinalIgnoreCase);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsMusicGenre
	public final boolean getIsMusicGenre()
	{
		return String.equals(getType(), "MusicGenre", StringComparison.OrdinalIgnoreCase);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsGameGenre
	public final boolean getIsGameGenre()
	{
		return String.equals(getType(), "GameGenre", StringComparison.OrdinalIgnoreCase);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsGenre
	public final boolean getIsGenre()
	{
		return String.equals(getType(), "Genre", StringComparison.OrdinalIgnoreCase);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsArtist
	public final boolean getIsArtist()
	{
		return String.equals(getType(), "Artist", StringComparison.OrdinalIgnoreCase);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool IsStudio
	public final boolean getIsStudio()
	{
		return String.equals(getType(), "Studio", StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Occurs when [property changed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;
}