package mediabrowser.model.channels;

public class ChannelFeatures
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

	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets a value indicating whether this instance can search.
	 
	 <value><c>true</c> if this instance can search; otherwise, <c>false</c>.</value>
	*/
	private boolean CanSearch;
	public final boolean getCanSearch()
	{
		return CanSearch;
	}
	public final void setCanSearch(boolean value)
	{
		CanSearch = value;
	}

	/** 
	 Gets or sets the media types.
	 
	 <value>The media types.</value>
	*/
	private java.util.ArrayList<ChannelMediaType> MediaTypes;
	public final java.util.ArrayList<ChannelMediaType> getMediaTypes()
	{
		return MediaTypes;
	}
	public final void setMediaTypes(java.util.ArrayList<ChannelMediaType> value)
	{
		MediaTypes = value;
	}

	/** 
	 Gets or sets the content types.
	 
	 <value>The content types.</value>
	*/
	private java.util.ArrayList<ChannelMediaContentType> ContentTypes;
	public final java.util.ArrayList<ChannelMediaContentType> getContentTypes()
	{
		return ContentTypes;
	}
	public final void setContentTypes(java.util.ArrayList<ChannelMediaContentType> value)
	{
		ContentTypes = value;
	}

	/** 
	 Represents the maximum number of records the channel allows retrieving at a time
	*/
	private Integer MaxPageSize = null;
	public final Integer getMaxPageSize()
	{
		return MaxPageSize;
	}
	public final void setMaxPageSize(Integer value)
	{
		MaxPageSize = value;
	}

	/** 
	 Gets or sets the automatic refresh levels.
	 
	 <value>The automatic refresh levels.</value>
	*/
	private Integer AutoRefreshLevels = null;
	public final Integer getAutoRefreshLevels()
	{
		return AutoRefreshLevels;
	}
	public final void setAutoRefreshLevels(Integer value)
	{
		AutoRefreshLevels = value;
	}

	/** 
	 Gets or sets the default sort orders.
	 
	 <value>The default sort orders.</value>
	*/
	private java.util.ArrayList<ChannelItemSortField> DefaultSortFields;
	public final java.util.ArrayList<ChannelItemSortField> getDefaultSortFields()
	{
		return DefaultSortFields;
	}
	public final void setDefaultSortFields(java.util.ArrayList<ChannelItemSortField> value)
	{
		DefaultSortFields = value;
	}

	/** 
	 Indicates if a sort ascending/descending toggle is supported or not.
	*/
	private boolean SupportsSortOrderToggle;
	public final boolean getSupportsSortOrderToggle()
	{
		return SupportsSortOrderToggle;
	}
	public final void setSupportsSortOrderToggle(boolean value)
	{
		SupportsSortOrderToggle = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports latest media].
	 
	 <value><c>true</c> if [supports latest media]; otherwise, <c>false</c>.</value>
	*/
	private boolean SupportsLatestMedia;
	public final boolean getSupportsLatestMedia()
	{
		return SupportsLatestMedia;
	}
	public final void setSupportsLatestMedia(boolean value)
	{
		SupportsLatestMedia = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance can filter.
	 
	 <value><c>true</c> if this instance can filter; otherwise, <c>false</c>.</value>
	*/
	private boolean CanFilter;
	public final boolean getCanFilter()
	{
		return CanFilter;
	}
	public final void setCanFilter(boolean value)
	{
		CanFilter = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports content downloading].
	 
	 <value><c>true</c> if [supports content downloading]; otherwise, <c>false</c>.</value>
	*/
	private boolean SupportsContentDownloading;
	public final boolean getSupportsContentDownloading()
	{
		return SupportsContentDownloading;
	}
	public final void setSupportsContentDownloading(boolean value)
	{
		SupportsContentDownloading = value;
	}

	public ChannelFeatures()
	{
		setMediaTypes(new java.util.ArrayList<ChannelMediaType>());
		setContentTypes(new java.util.ArrayList<ChannelMediaContentType>());

		setDefaultSortFields(new java.util.ArrayList<ChannelItemSortField>());
	}
}