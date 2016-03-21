package mediabrowser.model.querying;

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
	 The budget
	*/
	Budget,

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

	/** 
	 The revenue
	*/
	Revenue,

	/** 
	 The season name
	*/
	SeasonName,

	/** 
	 The settings
	*/
	Settings,

	/** 
	 The short overview
	*/
	ShortOverview,

	/** 
	 The screenshot image tags
	*/
	ScreenshotImageTags,

	/** 
	 The series genres
	*/
	SeriesGenres,

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
	ServiceName;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ItemFields forValue(int value)
	{
		return values()[value];
	}
}