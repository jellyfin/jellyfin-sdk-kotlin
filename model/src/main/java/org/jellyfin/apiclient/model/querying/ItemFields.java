package org.jellyfin.apiclient.model.querying;

/** 
 Used to control the data that gets attached to DtoBaseItems
*/
public enum ItemFields
{
	/** 
	 The air time
	*/
	AirTime,

	/** 
	 The alternate episode numbers
	*/
	AlternateEpisodeNumbers,

	/** 
	 The awards summary
	*/
	AwardSummary,

	/** 
	 The can delete
	*/
	CanDelete,

	/** 
	 The can download
	*/
	CanDownload,

	/** 
	 The channel information
	*/
	ChannelInfo,

	/** 
	 The chapters
	*/
	Chapters,

	ChildCount,

	/** 
	 The critic rating summary
	*/
	CriticRatingSummary,

	/** 
	 The cumulative run time ticks
	*/
	CumulativeRunTimeTicks,

	/** 
	 The custom rating
	*/
	CustomRating,

	/** 
	 The date created of the item
	*/
	DateCreated,

	/** 
	 The date last media added
	*/
	DateLastMediaAdded,

	/** 
	 Item display preferences
	*/
	DisplayPreferencesId,

	/** 
	 The display media type
	*/
	DisplayMediaType,

	/** 
	 The etag
	*/
	Etag,

	/** 
	 The external urls
	*/
	ExternalUrls,

	/** 
	 Genres
	*/
	Genres,

	/** 
	 The home page URL
	*/
	HomePageUrl,

	/** 
	 The fields that the server supports indexing on
	*/
	IndexOptions,

	/** 
	 The item counts
	*/
	ItemCounts,

	/** 
	 The keywords
	*/
	Keywords,

	/** 
	 The media source count
	*/
	MediaSourceCount,

	/** 
	 The media versions
	*/
	MediaSources,

	/** 
	 The metascore
	*/
	Metascore,

	OfficialRatingDescription,

	OriginalTitle,

	/** 
	 The item overview
	*/
	Overview,

	/** 
	 The id of the item's parent
	*/
	ParentId,

	/** 
	 The physical path of the item
	*/
	Path,

	/** 
	 The list of people for the item
	*/
	People,

	PlayAccess,

	/** 
	 The production locations
	*/
	ProductionLocations,

	/** 
	 Imdb, tmdb, etc
	*/
	ProviderIds,

	/** 
	 The aspect ratio of the primary image
	*/
	PrimaryImageAspectRatio,

	RecursiveItemCount,

	/** 
	 The season name
	*/
	SeasonName,

	/** 
	 The settings
	*/
	Settings,

	/** 
	 The screenshot image tags
	*/
	ScreenshotImageTags,

	/** 
	 The series genres
	*/
	SeriesGenres,

	SeriesPrimaryImage,

	/** 
	 The series studio
	*/
	SeriesStudio,

	/** 
	 The sort name of the item
	*/
	SortName,

	/** 
	 The special episode numbers
	*/
	SpecialEpisodeNumbers,

	/** 
	 The studios of the item
	*/
	Studios,

	BasicSyncInfo,
	/** 
	 The synchronize information
	*/
	SyncInfo,

	/** 
	 The taglines of the item
	*/
	Taglines,

	/** 
	 The tags
	*/
	Tags,

	/** 
	 The vote count
	*/
	VoteCount,

	/** 
	 The trailer url of the item
	*/
	RemoteTrailers,

	/** 
	 The media streams
	*/
	MediaStreams,

	/** 
	 The season user data
	*/
	SeasonUserData,

	/** 
	 The service name
	*/
	ServiceName,
	ThemeSongIds,
	ThemeVideoIds;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ItemFields forValue(int value)
	{
		return values()[value];
	}
}