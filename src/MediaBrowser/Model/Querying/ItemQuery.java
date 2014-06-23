package MediaBrowser.Model.Querying;

import MediaBrowser.Model.Entities.*;

/** 
 Contains all the possible parameters that can be used to query for items
*/
public class ItemQuery
{
	/** 
	 The user to localize search results for
	 
	 <value>The user id.</value>
	*/
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	/** 
	 Specify this to localize the search to a specific item or folder. Omit to use the root.
	 
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
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer privateStartIndex = null;
	public final Integer getStartIndex()
	{
		return privateStartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		privateStartIndex = value;
	}

	/** 
	 The maximum number of items to return
	 
	 <value>The limit.</value>
	*/
	private Integer privateLimit = null;
	public final Integer getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(Integer value)
	{
		privateLimit = value;
	}

	/** 
	 What to sort the results by
	 
	 <value>The sort by.</value>
	*/
	private String[] privateSortBy;
	public final String[] getSortBy()
	{
		return privateSortBy;
	}
	public final void setSortBy(String[] value)
	{
		privateSortBy = value;
	}

	/** 
	 Filter by artists
	 
	 <value>The artists.</value>
	*/
	private String[] privateArtists;
	public final String[] getArtists()
	{
		return privateArtists;
	}
	public final void setArtists(String[] value)
	{
		privateArtists = value;
	}

	/** 
	 The sort order to return results with
	 
	 <value>The sort order.</value>
	*/
	private SortOrder privateSortOrder = null;
	public final SortOrder getSortOrder()
	{
		return privateSortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		privateSortOrder = value;
	}

	/** 
	 Filters to apply to the results
	 
	 <value>The filters.</value>
	*/
	private ItemFilter[] privateFilters;
	public final ItemFilter[] getFilters()
	{
		return privateFilters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		privateFilters = value;
	}

	/** 
	 Fields to return within the items, in addition to basic information
	 
	 <value>The fields.</value>
	*/
	private ItemFields[] privateFields;
	public final ItemFields[] getFields()
	{
		return privateFields;
	}
	public final void setFields(ItemFields[] value)
	{
		privateFields = value;
	}

	/** 
	 Gets or sets the media types.
	 
	 <value>The media types.</value>
	*/
	private String[] privateMediaTypes;
	public final String[] getMediaTypes()
	{
		return privateMediaTypes;
	}
	public final void setMediaTypes(String[] value)
	{
		privateMediaTypes = value;
	}

	/** 
	 Gets or sets the video formats.
	 
	 <value>The video formats.</value>
	*/
	private Boolean privateIs3D = null;
	public final Boolean getIs3D()
	{
		return privateIs3D;
	}
	public final void setIs3D(Boolean value)
	{
		privateIs3D = value;
	}

	/** 
	 Gets or sets the video types.
	 
	 <value>The video types.</value>
	*/
	private VideoType[] privateVideoTypes;
	public final VideoType[] getVideoTypes()
	{
		return privateVideoTypes;
	}
	public final void setVideoTypes(VideoType[] value)
	{
		privateVideoTypes = value;
	}

	/** 
	 Whether or not to perform the query recursively
	 
	 <value><c>true</c> if recursive; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRecursive;
	public final boolean getRecursive()
	{
		return privateRecursive;
	}
	public final void setRecursive(boolean value)
	{
		privateRecursive = value;
	}

	/** 
	 Limit results to items containing specific genres
	 
	 <value>The genres.</value>
	*/
	private String[] privateGenres;
	public final String[] getGenres()
	{
		return privateGenres;
	}
	public final void setGenres(String[] value)
	{
		privateGenres = value;
	}

	/** 
	 Limit results to items containing specific genres
	 
	 <value>The genres.</value>
	*/
	private String[] privateAllGenres;
	public final String[] getAllGenres()
	{
		return privateAllGenres;
	}
	public final void setAllGenres(String[] value)
	{
		privateAllGenres = value;
	}

	/** 
	 Limit results to items containing specific studios
	 
	 <value>The studios.</value>
	*/
	private String[] privateStudios;
	public final String[] getStudios()
	{
		return privateStudios;
	}
	public final void setStudios(String[] value)
	{
		privateStudios = value;
	}

	/** 
	 Gets or sets the exclude item types.
	 
	 <value>The exclude item types.</value>
	*/
	private String[] privateExcludeItemTypes;
	public final String[] getExcludeItemTypes()
	{
		return privateExcludeItemTypes;
	}
	public final void setExcludeItemTypes(String[] value)
	{
		privateExcludeItemTypes = value;
	}

	/** 
	 Gets or sets the include item types.
	 
	 <value>The include item types.</value>
	*/
	private String[] privateIncludeItemTypes;
	public final String[] getIncludeItemTypes()
	{
		return privateIncludeItemTypes;
	}
	public final void setIncludeItemTypes(String[] value)
	{
		privateIncludeItemTypes = value;
	}

	/** 
	 Limit results to items containing specific years
	 
	 <value>The years.</value>
	*/
	private int[] privateYears;
	public final int[] getYears()
	{
		return privateYears;
	}
	public final void setYears(int[] value)
	{
		privateYears = value;
	}

	/** 
	 Limit results to items containing a specific person
	 
	 <value>The person.</value>
	*/
	private String privatePerson;
	public final String getPerson()
	{
		return privatePerson;
	}
	public final void setPerson(String value)
	{
		privatePerson = value;
	}

	/** 
	 If the Person filter is used, this can also be used to restrict to a specific person type
	 
	 <value>The type of the person.</value>
	*/
	private String[] privatePersonTypes;
	public final String[] getPersonTypes()
	{
		return privatePersonTypes;
	}
	public final void setPersonTypes(String[] value)
	{
		privatePersonTypes = value;
	}

	/** 
	 Search characters used to find items
	 
	 <value>The index by.</value>
	*/
	private String privateSearchTerm;
	public final String getSearchTerm()
	{
		return privateSearchTerm;
	}
	public final void setSearchTerm(String value)
	{
		privateSearchTerm = value;
	}

	/** 
	 Gets or sets the image types.
	 
	 <value>The image types.</value>
	*/
	private ImageType[] privateImageTypes;
	public final ImageType[] getImageTypes()
	{
		return privateImageTypes;
	}
	public final void setImageTypes(ImageType[] value)
	{
		privateImageTypes = value;
	}

	/** 
	 Gets or sets the air days.
	 
	 <value>The air days.</value>
	*/
	private DayOfWeek[] privateAirDays;
	public final DayOfWeek[] getAirDays()
	{
		return privateAirDays;
	}
	public final void setAirDays(DayOfWeek[] value)
	{
		privateAirDays = value;
	}

	/** 
	 Gets or sets the series status.
	 
	 <value>The series status.</value>
	*/
	private SeriesStatus[] privateSeriesStatuses;
	public final SeriesStatus[] getSeriesStatuses()
	{
		return privateSeriesStatuses;
	}
	public final void setSeriesStatuses(SeriesStatus[] value)
	{
		privateSeriesStatuses = value;
	}

	/** 
	 Gets or sets the ids, which are specific items to retrieve
	 
	 <value>The ids.</value>
	*/
	private String[] privateIds;
	public final String[] getIds()
	{
		return privateIds;
	}
	public final void setIds(String[] value)
	{
		privateIds = value;
	}

	/** 
	 Gets or sets the min official rating.
	 
	 <value>The min official rating.</value>
	*/
	private String privateMinOfficialRating;
	public final String getMinOfficialRating()
	{
		return privateMinOfficialRating;
	}
	public final void setMinOfficialRating(String value)
	{
		privateMinOfficialRating = value;
	}

	/** 
	 Gets or sets the max official rating.
	 
	 <value>The max official rating.</value>
	*/
	private String privateMaxOfficialRating;
	public final String getMaxOfficialRating()
	{
		return privateMaxOfficialRating;
	}
	public final void setMaxOfficialRating(String value)
	{
		privateMaxOfficialRating = value;
	}

	/** 
	 Gets or sets the min index number.
	 
	 <value>The min index number.</value>
	*/
	private Integer privateMinIndexNumber = null;
	public final Integer getMinIndexNumber()
	{
		return privateMinIndexNumber;
	}
	public final void setMinIndexNumber(Integer value)
	{
		privateMinIndexNumber = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has parental rating.
	 
	 <value><c>null</c> if [has parental rating] contains no value, <c>true</c> if [has parental rating]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateHasParentalRating = null;
	public final Boolean getHasParentalRating()
	{
		return privateHasParentalRating;
	}
	public final void setHasParentalRating(Boolean value)
	{
		privateHasParentalRating = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is HD.
	 
	 <value><c>null</c> if [is HD] contains no value, <c>true</c> if [is HD]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsHD = null;
	public final Boolean getIsHD()
	{
		return privateIsHD;
	}
	public final void setIsHD(Boolean value)
	{
		privateIsHD = value;
	}

	/** 
	 Gets or sets the parent index number.
	 
	 <value>The parent index number.</value>
	*/
	private Integer privateParentIndexNumber = null;
	public final Integer getParentIndexNumber()
	{
		return privateParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		privateParentIndexNumber = value;
	}

	/** 
	 Gets or sets the min players.
	 
	 <value>The min players.</value>
	*/
	private Integer privateMinPlayers = null;
	public final Integer getMinPlayers()
	{
		return privateMinPlayers;
	}
	public final void setMinPlayers(Integer value)
	{
		privateMinPlayers = value;
	}

	/** 
	 Gets or sets the max players.
	 
	 <value>The max players.</value>
	*/
	private Integer privateMaxPlayers = null;
	public final Integer getMaxPlayers()
	{
		return privateMaxPlayers;
	}
	public final void setMaxPlayers(Integer value)
	{
		privateMaxPlayers = value;
	}

	/** 
	 Gets or sets the name starts with or greater.
	 
	 <value>The name starts with or greater.</value>
	*/
	private String privateNameStartsWithOrGreater;
	public final String getNameStartsWithOrGreater()
	{
		return privateNameStartsWithOrGreater;
	}
	public final void setNameStartsWithOrGreater(String value)
	{
		privateNameStartsWithOrGreater = value;
	}

	/** 
	 Gets or sets the name starts with.
	 
	 <value>The name starts with or greater.</value>
	*/
	private String privateNameStartsWith;
	public final String getNameStartsWith()
	{
		return privateNameStartsWith;
	}
	public final void setNameStartsWith(String value)
	{
		privateNameStartsWith = value;
	}

	/** 
	 Gets or sets the name starts with.
	 
	 <value>The name lessthan.</value>
	*/
	private String privateNameLessThan;
	public final String getNameLessThan()
	{
		return privateNameLessThan;
	}
	public final void setNameLessThan(String value)
	{
		privateNameLessThan = value;
	}

	/** 
	 Gets or sets the album artist starts with or greater.
	 
	 <value>The album artist starts with or greater.</value>
	*/
	private String privateAlbumArtistStartsWithOrGreater;
	public final String getAlbumArtistStartsWithOrGreater()
	{
		return privateAlbumArtistStartsWithOrGreater;
	}
	public final void setAlbumArtistStartsWithOrGreater(String value)
	{
		privateAlbumArtistStartsWithOrGreater = value;
	}

	/** 
	 Gets or sets a value indicating whether [include index containers].
	 
	 <value><c>true</c> if [include index containers]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIncludeIndexContainers;
	public final boolean getIncludeIndexContainers()
	{
		return privateIncludeIndexContainers;
	}
	public final void setIncludeIndexContainers(boolean value)
	{
		privateIncludeIndexContainers = value;
	}

	/** 
	 Gets or sets the location types.
	 
	 <value>The location types.</value>
	*/
	private LocationType[] privateLocationTypes;
	public final LocationType[] getLocationTypes()
	{
		return privateLocationTypes;
	}
	public final void setLocationTypes(LocationType[] value)
	{
		privateLocationTypes = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is missing episode.
	 
	 <value><c>null</c> if [is missing episode] contains no value, <c>true</c> if [is missing episode]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsMissing = null;
	public final Boolean getIsMissing()
	{
		return privateIsMissing;
	}
	public final void setIsMissing(Boolean value)
	{
		privateIsMissing = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is unaired episode.
	 
	 <value><c>null</c> if [is unaired episode] contains no value, <c>true</c> if [is unaired episode]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsUnaired = null;
	public final Boolean getIsUnaired()
	{
		return privateIsUnaired;
	}
	public final void setIsUnaired(Boolean value)
	{
		privateIsUnaired = value;
	}

	private Boolean privateIsVirtualUnaired = null;
	public final Boolean getIsVirtualUnaired()
	{
		return privateIsVirtualUnaired;
	}
	public final void setIsVirtualUnaired(Boolean value)
	{
		privateIsVirtualUnaired = value;
	}

	private Boolean privateIsInBoxSet = null;
	public final Boolean getIsInBoxSet()
	{
		return privateIsInBoxSet;
	}
	public final void setIsInBoxSet(Boolean value)
	{
		privateIsInBoxSet = value;
	}

	private Boolean privateCollapseBoxSetItems = null;
	public final Boolean getCollapseBoxSetItems()
	{
		return privateCollapseBoxSetItems;
	}
	public final void setCollapseBoxSetItems(Boolean value)
	{
		privateCollapseBoxSetItems = value;
	}

	private Boolean privateIsPlayed = null;
	public final Boolean getIsPlayed()
	{
		return privateIsPlayed;
	}
	public final void setIsPlayed(Boolean value)
	{
		privateIsPlayed = value;
	}

	/** 
	 Gets or sets the exclude location types.
	 
	 <value>The exclude location types.</value>
	*/
	private LocationType[] privateExcludeLocationTypes;
	public final LocationType[] getExcludeLocationTypes()
	{
		return privateExcludeLocationTypes;
	}
	public final void setExcludeLocationTypes(LocationType[] value)
	{
		privateExcludeLocationTypes = value;
	}

	private Double privateMinCommunityRating = null;
	public final Double getMinCommunityRating()
	{
		return privateMinCommunityRating;
	}
	public final void setMinCommunityRating(Double value)
	{
		privateMinCommunityRating = value;
	}
	private Double privateMinCriticRating = null;
	public final Double getMinCriticRating()
	{
		return privateMinCriticRating;
	}
	public final void setMinCriticRating(Double value)
	{
		privateMinCriticRating = value;
	}

	private Integer privateAiredDuringSeason = null;
	public final Integer getAiredDuringSeason()
	{
		return privateAiredDuringSeason;
	}
	public final void setAiredDuringSeason(Integer value)
	{
		privateAiredDuringSeason = value;
	}

	private java.util.Date privateMinPremiereDate = null;
	public final java.util.Date getMinPremiereDate()
	{
		return privateMinPremiereDate;
	}
	public final void setMinPremiereDate(java.util.Date value)
	{
		privateMinPremiereDate = value;
	}

	private java.util.Date privateMaxPremiereDate = null;
	public final java.util.Date getMaxPremiereDate()
	{
		return privateMaxPremiereDate;
	}
	public final void setMaxPremiereDate(java.util.Date value)
	{
		privateMaxPremiereDate = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ItemQuery" /> class.
	*/
	public ItemQuery()
	{
		setLocationTypes(new LocationType[] { });
		setExcludeLocationTypes(new LocationType[] { });

		setSortBy(new String[] { });

		setFilters(new ItemFilter[] {});

		setFields(new ItemFields[] {});

		setMediaTypes(new String[] {});

		setVideoTypes(new VideoType[] {});

		setGenres(new String[] { });
		setStudios(new String[] { });
		setIncludeItemTypes(new String[] { });
		setExcludeItemTypes(new String[] { });
		setYears(new int[] { });
		setPersonTypes(new String[] { });
		setIds(new String[] { });
		setArtists(new String[] { });

		setImageTypes(new ImageType[] { });
		setAirDays(new DayOfWeek[] { });
		setSeriesStatuses(new SeriesStatus[] { });
	}
}