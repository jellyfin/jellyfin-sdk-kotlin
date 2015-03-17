package mediabrowser.model.querying;

import mediabrowser.model.entities.*;

/** 
 Contains all the possible parameters that can be used to query for items
*/
public class ItemQuery
{
	/** 
	 The user to localize search results for
	 
	 <value>The user id.</value>
	*/
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	/** 
	 Specify this to localize the search to a specific item or folder. Omit to use the root.
	 
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
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer StartIndex = null;
	public final Integer getStartIndex()
	{
		return StartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		StartIndex = value;
	}

	/** 
	 The maximum number of items to return
	 
	 <value>The limit.</value>
	*/
	private Integer Limit = null;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}

	/** 
	 What to sort the results by
	 
	 <value>The sort by.</value>
	*/
	private String[] SortBy;
	public final String[] getSortBy()
	{
		return SortBy;
	}
	public final void setSortBy(String[] value)
	{
		SortBy = value;
	}

	/** 
	 Gets or sets the artist ids.
	 
	 <value>The artist ids.</value>
	*/
	private String[] ArtistIds;
	public final String[] getArtistIds()
	{
		return ArtistIds;
	}
	public final void setArtistIds(String[] value)
	{
		ArtistIds = value;
	}

	/** 
	 The sort order to return results with
	 
	 <value>The sort order.</value>
	*/
	private SortOrder SortOrder = null;
	public final SortOrder getSortOrder()
	{
		return SortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		SortOrder = value;
	}

	/** 
	 Filters to apply to the results
	 
	 <value>The filters.</value>
	*/
	private ItemFilter[] Filters;
	public final ItemFilter[] getFilters()
	{
		return Filters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		Filters = value;
	}

	/** 
	 Fields to return within the items, in addition to basic information
	 
	 <value>The fields.</value>
	*/
	private ItemFields[] Fields;
	public final ItemFields[] getFields()
	{
		return Fields;
	}
	public final void setFields(ItemFields[] value)
	{
		Fields = value;
	}

	/** 
	 Gets or sets the media types.
	 
	 <value>The media types.</value>
	*/
	private String[] MediaTypes;
	public final String[] getMediaTypes()
	{
		return MediaTypes;
	}
	public final void setMediaTypes(String[] value)
	{
		MediaTypes = value;
	}

	/** 
	 Gets or sets the video formats.
	 
	 <value>The video formats.</value>
	*/
	private Boolean Is3D = null;
	public final Boolean getIs3D()
	{
		return Is3D;
	}
	public final void setIs3D(Boolean value)
	{
		Is3D = value;
	}

	/** 
	 Gets or sets the video types.
	 
	 <value>The video types.</value>
	*/
	private VideoType[] VideoTypes;
	public final VideoType[] getVideoTypes()
	{
		return VideoTypes;
	}
	public final void setVideoTypes(VideoType[] value)
	{
		VideoTypes = value;
	}

	/** 
	 Whether or not to perform the query recursively
	 
	 <value><c>true</c> if recursive; otherwise, <c>false</c>.</value>
	*/
	private boolean Recursive;
	public final boolean getRecursive()
	{
		return Recursive;
	}
	public final void setRecursive(boolean value)
	{
		Recursive = value;
	}

	/** 
	 Limit results to items containing specific genres
	 
	 <value>The genres.</value>
	*/
	private String[] Genres;
	public final String[] getGenres()
	{
		return Genres;
	}
	public final void setGenres(String[] value)
	{
		Genres = value;
	}

	/** 
	 Gets or sets the studio ids.
	 
	 <value>The studio ids.</value>
	*/
	private String[] StudioIds;
	public final String[] getStudioIds()
	{
		return StudioIds;
	}
	public final void setStudioIds(String[] value)
	{
		StudioIds = value;
	}

	/** 
	 Gets or sets the exclude item types.
	 
	 <value>The exclude item types.</value>
	*/
	private String[] ExcludeItemTypes;
	public final String[] getExcludeItemTypes()
	{
		return ExcludeItemTypes;
	}
	public final void setExcludeItemTypes(String[] value)
	{
		ExcludeItemTypes = value;
	}

	/** 
	 Gets or sets the include item types.
	 
	 <value>The include item types.</value>
	*/
	private String[] IncludeItemTypes;
	public final String[] getIncludeItemTypes()
	{
		return IncludeItemTypes;
	}
	public final void setIncludeItemTypes(String[] value)
	{
		IncludeItemTypes = value;
	}

	/** 
	 Limit results to items containing specific years
	 
	 <value>The years.</value>
	*/
	private int[] Years;
	public final int[] getYears()
	{
		return Years;
	}
	public final void setYears(int[] value)
	{
		Years = value;
	}

	/** 
	 Limit results to items containing a specific person
	 
	 <value>The person.</value>
	*/
	private String Person;
	public final String getPerson()
	{
		return Person;
	}
	public final void setPerson(String value)
	{
		Person = value;
	}

	/** 
	 If the Person filter is used, this can also be used to restrict to a specific person type
	 
	 <value>The type of the person.</value>
	*/
	private String[] PersonTypes;
	public final String[] getPersonTypes()
	{
		return PersonTypes;
	}
	public final void setPersonTypes(String[] value)
	{
		PersonTypes = value;
	}

	/** 
	 Search characters used to find items
	 
	 <value>The index by.</value>
	*/
	private String SearchTerm;
	public final String getSearchTerm()
	{
		return SearchTerm;
	}
	public final void setSearchTerm(String value)
	{
		SearchTerm = value;
	}

	/** 
	 Gets or sets the image types.
	 
	 <value>The image types.</value>
	*/
	private ImageType[] ImageTypes;
	public final ImageType[] getImageTypes()
	{
		return ImageTypes;
	}
	public final void setImageTypes(ImageType[] value)
	{
		ImageTypes = value;
	}

	/** 
	 Gets or sets the air days.
	 
	 <value>The air days.</value>
	*/
	private String[] AirDays;
	public final String[] getAirDays()
	{
		return AirDays;
	}
	public final void setAirDays(String[] value)
	{
		AirDays = value;
	}

	/** 
	 Gets or sets the series status.
	 
	 <value>The series status.</value>
	*/
	private SeriesStatus[] SeriesStatuses;
	public final SeriesStatus[] getSeriesStatuses()
	{
		return SeriesStatuses;
	}
	public final void setSeriesStatuses(SeriesStatus[] value)
	{
		SeriesStatuses = value;
	}

	/** 
	 Gets or sets the ids, which are specific items to retrieve
	 
	 <value>The ids.</value>
	*/
	private String[] Ids;
	public final String[] getIds()
	{
		return Ids;
	}
	public final void setIds(String[] value)
	{
		Ids = value;
	}

	/** 
	 Gets or sets the min official rating.
	 
	 <value>The min official rating.</value>
	*/
	private String MinOfficialRating;
	public final String getMinOfficialRating()
	{
		return MinOfficialRating;
	}
	public final void setMinOfficialRating(String value)
	{
		MinOfficialRating = value;
	}

	/** 
	 Gets or sets the max official rating.
	 
	 <value>The max official rating.</value>
	*/
	private String MaxOfficialRating;
	public final String getMaxOfficialRating()
	{
		return MaxOfficialRating;
	}
	public final void setMaxOfficialRating(String value)
	{
		MaxOfficialRating = value;
	}

	/** 
	 Gets or sets the min index number.
	 
	 <value>The min index number.</value>
	*/
	private Integer MinIndexNumber = null;
	public final Integer getMinIndexNumber()
	{
		return MinIndexNumber;
	}
	public final void setMinIndexNumber(Integer value)
	{
		MinIndexNumber = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has parental rating.
	 
	 <value><c>null</c> if [has parental rating] contains no value, <c>true</c> if [has parental rating]; otherwise, <c>false</c>.</value>
	*/
	private Boolean HasParentalRating = null;
	public final Boolean getHasParentalRating()
	{
		return HasParentalRating;
	}
	public final void setHasParentalRating(Boolean value)
	{
		HasParentalRating = value;
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
	 Gets or sets the min players.
	 
	 <value>The min players.</value>
	*/
	private Integer MinPlayers = null;
	public final Integer getMinPlayers()
	{
		return MinPlayers;
	}
	public final void setMinPlayers(Integer value)
	{
		MinPlayers = value;
	}

	/** 
	 Gets or sets the max players.
	 
	 <value>The max players.</value>
	*/
	private Integer MaxPlayers = null;
	public final Integer getMaxPlayers()
	{
		return MaxPlayers;
	}
	public final void setMaxPlayers(Integer value)
	{
		MaxPlayers = value;
	}

	/** 
	 Gets or sets the name starts with or greater.
	 
	 <value>The name starts with or greater.</value>
	*/
	private String NameStartsWithOrGreater;
	public final String getNameStartsWithOrGreater()
	{
		return NameStartsWithOrGreater;
	}
	public final void setNameStartsWithOrGreater(String value)
	{
		NameStartsWithOrGreater = value;
	}

	/** 
	 Gets or sets the name starts with.
	 
	 <value>The name starts with or greater.</value>
	*/
	private String NameStartsWith;
	public final String getNameStartsWith()
	{
		return NameStartsWith;
	}
	public final void setNameStartsWith(String value)
	{
		NameStartsWith = value;
	}

	/** 
	 Gets or sets the name starts with.
	 
	 <value>The name lessthan.</value>
	*/
	private String NameLessThan;
	public final String getNameLessThan()
	{
		return NameLessThan;
	}
	public final void setNameLessThan(String value)
	{
		NameLessThan = value;
	}

	/** 
	 Gets or sets the album artist starts with or greater.
	 
	 <value>The album artist starts with or greater.</value>
	*/
	private String AlbumArtistStartsWithOrGreater;
	public final String getAlbumArtistStartsWithOrGreater()
	{
		return AlbumArtistStartsWithOrGreater;
	}
	public final void setAlbumArtistStartsWithOrGreater(String value)
	{
		AlbumArtistStartsWithOrGreater = value;
	}

	/** 
	 Gets or sets a value indicating whether [include index containers].
	 
	 <value><c>true</c> if [include index containers]; otherwise, <c>false</c>.</value>
	*/
	private boolean IncludeIndexContainers;
	public final boolean getIncludeIndexContainers()
	{
		return IncludeIndexContainers;
	}
	public final void setIncludeIndexContainers(boolean value)
	{
		IncludeIndexContainers = value;
	}

	/** 
	 Gets or sets the location types.
	 
	 <value>The location types.</value>
	*/
	private LocationType[] LocationTypes;
	public final LocationType[] getLocationTypes()
	{
		return LocationTypes;
	}
	public final void setLocationTypes(LocationType[] value)
	{
		LocationTypes = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is missing episode.
	 
	 <value><c>null</c> if [is missing episode] contains no value, <c>true</c> if [is missing episode]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsMissing = null;
	public final Boolean getIsMissing()
	{
		return IsMissing;
	}
	public final void setIsMissing(Boolean value)
	{
		IsMissing = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is unaired episode.
	 
	 <value><c>null</c> if [is unaired episode] contains no value, <c>true</c> if [is unaired episode]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsUnaired = null;
	public final Boolean getIsUnaired()
	{
		return IsUnaired;
	}
	public final void setIsUnaired(Boolean value)
	{
		IsUnaired = value;
	}

	private Boolean IsVirtualUnaired = null;
	public final Boolean getIsVirtualUnaired()
	{
		return IsVirtualUnaired;
	}
	public final void setIsVirtualUnaired(Boolean value)
	{
		IsVirtualUnaired = value;
	}

	private Boolean IsInBoxSet = null;
	public final Boolean getIsInBoxSet()
	{
		return IsInBoxSet;
	}
	public final void setIsInBoxSet(Boolean value)
	{
		IsInBoxSet = value;
	}

	private Boolean CollapseBoxSetItems = null;
	public final Boolean getCollapseBoxSetItems()
	{
		return CollapseBoxSetItems;
	}
	public final void setCollapseBoxSetItems(Boolean value)
	{
		CollapseBoxSetItems = value;
	}

	private Boolean IsPlayed = null;
	public final Boolean getIsPlayed()
	{
		return IsPlayed;
	}
	public final void setIsPlayed(Boolean value)
	{
		IsPlayed = value;
	}

	/** 
	 Gets or sets the exclude location types.
	 
	 <value>The exclude location types.</value>
	*/
	private LocationType[] ExcludeLocationTypes;
	public final LocationType[] getExcludeLocationTypes()
	{
		return ExcludeLocationTypes;
	}
	public final void setExcludeLocationTypes(LocationType[] value)
	{
		ExcludeLocationTypes = value;
	}

	private Double MinCommunityRating = null;
	public final Double getMinCommunityRating()
	{
		return MinCommunityRating;
	}
	public final void setMinCommunityRating(Double value)
	{
		MinCommunityRating = value;
	}
	private Double MinCriticRating = null;
	public final Double getMinCriticRating()
	{
		return MinCriticRating;
	}
	public final void setMinCriticRating(Double value)
	{
		MinCriticRating = value;
	}

	private Integer AiredDuringSeason = null;
	public final Integer getAiredDuringSeason()
	{
		return AiredDuringSeason;
	}
	public final void setAiredDuringSeason(Integer value)
	{
		AiredDuringSeason = value;
	}

	private java.util.Date MinPremiereDate = null;
	public final java.util.Date getMinPremiereDate()
	{
		return MinPremiereDate;
	}
	public final void setMinPremiereDate(java.util.Date value)
	{
		MinPremiereDate = value;
	}

	private java.util.Date MaxPremiereDate = null;
	public final java.util.Date getMaxPremiereDate()
	{
		return MaxPremiereDate;
	}
	public final void setMaxPremiereDate(java.util.Date value)
	{
		MaxPremiereDate = value;
	}

	private Boolean EnableImages = null;
	public final Boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(Boolean value)
	{
		EnableImages = value;
	}
	private Integer ImageTypeLimit = null;
	public final Integer getImageTypeLimit()
	{
		return ImageTypeLimit;
	}
	public final void setImageTypeLimit(Integer value)
	{
		ImageTypeLimit = value;
	}
	private ImageType[] EnableImageTypes;
	public final ImageType[] getEnableImageTypes()
	{
		return EnableImageTypes;
	}
	public final void setEnableImageTypes(ImageType[] value)
	{
		EnableImageTypes = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ItemQuery" /> class.
	*/
	public ItemQuery()
	{
		setLocationTypes(new LocationType[] { });
		setExcludeLocationTypes(new LocationType[] { });

		setSortBy(new String[] { });

		setFilters(new ItemFilter[] { });

		setFields(new ItemFields[] { });

		setMediaTypes(new String[] { });

		setVideoTypes(new VideoType[] { });

		setGenres(new String[] { });
		setStudioIds(new String[] { });
		setIncludeItemTypes(new String[] { });
		setExcludeItemTypes(new String[] { });
		setYears(new int[] { });
		setPersonTypes(new String[] { });
		setIds(new String[] { });
		setArtistIds(new String[] { });

		setImageTypes(new ImageType[] { });
		setAirDays(new String[] { });
		setSeriesStatuses(new SeriesStatus[] { });
		setEnableImageTypes(new ImageType[] { });
	}
}