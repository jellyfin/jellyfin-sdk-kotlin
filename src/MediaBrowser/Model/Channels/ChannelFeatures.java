package MediaBrowser.Model.Channels;

public class ChannelFeatures
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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets a value indicating whether this instance can search.
	 
	 <value><c>true</c> if this instance can search; otherwise, <c>false</c>.</value>
	*/
	private boolean privateCanSearch;
	public final boolean getCanSearch()
	{
		return privateCanSearch;
	}
	public final void setCanSearch(boolean value)
	{
		privateCanSearch = value;
	}

	/** 
	 Gets or sets the media types.
	 
	 <value>The media types.</value>
	*/
	private java.util.ArrayList<ChannelMediaType> privateMediaTypes;
	public final java.util.ArrayList<ChannelMediaType> getMediaTypes()
	{
		return privateMediaTypes;
	}
	public final void setMediaTypes(java.util.ArrayList<ChannelMediaType> value)
	{
		privateMediaTypes = value;
	}

	/** 
	 Gets or sets the content types.
	 
	 <value>The content types.</value>
	*/
	private java.util.ArrayList<ChannelMediaContentType> privateContentTypes;
	public final java.util.ArrayList<ChannelMediaContentType> getContentTypes()
	{
		return privateContentTypes;
	}
	public final void setContentTypes(java.util.ArrayList<ChannelMediaContentType> value)
	{
		privateContentTypes = value;
	}

	/** 
	 Represents the maximum number of records the channel allows retrieving at a time
	*/
	private Integer privateMaxPageSize = new Integer();
	public final Integer getMaxPageSize()
	{
		return privateMaxPageSize;
	}
	public final void setMaxPageSize(Integer value)
	{
		privateMaxPageSize = value;
	}

	/** 
	 Gets or sets the default sort orders.
	 
	 <value>The default sort orders.</value>
	*/
	private java.util.ArrayList<ChannelItemSortField> privateDefaultSortFields;
	public final java.util.ArrayList<ChannelItemSortField> getDefaultSortFields()
	{
		return privateDefaultSortFields;
	}
	public final void setDefaultSortFields(java.util.ArrayList<ChannelItemSortField> value)
	{
		privateDefaultSortFields = value;
	}

	/** 
	 Indicates if a sort ascending/descending toggle is supported or not.
	*/
	private boolean privateSupportsSortOrderToggle;
	public final boolean getSupportsSortOrderToggle()
	{
		return privateSupportsSortOrderToggle;
	}
	public final void setSupportsSortOrderToggle(boolean value)
	{
		privateSupportsSortOrderToggle = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports latest media].
	 
	 <value><c>true</c> if [supports latest media]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSupportsLatestMedia;
	public final boolean getSupportsLatestMedia()
	{
		return privateSupportsLatestMedia;
	}
	public final void setSupportsLatestMedia(boolean value)
	{
		privateSupportsLatestMedia = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance can filter.
	 
	 <value><c>true</c> if this instance can filter; otherwise, <c>false</c>.</value>
	*/
	private boolean privateCanFilter;
	public final boolean getCanFilter()
	{
		return privateCanFilter;
	}
	public final void setCanFilter(boolean value)
	{
		privateCanFilter = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports content downloading].
	 
	 <value><c>true</c> if [supports content downloading]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSupportsContentDownloading;
	public final boolean getSupportsContentDownloading()
	{
		return privateSupportsContentDownloading;
	}
	public final void setSupportsContentDownloading(boolean value)
	{
		privateSupportsContentDownloading = value;
	}

	public ChannelFeatures()
	{
		setMediaTypes(new java.util.ArrayList<ChannelMediaType>());
		setContentTypes(new java.util.ArrayList<ChannelMediaContentType>());

		setDefaultSortFields(new java.util.ArrayList<ChannelItemSortField>());
	}
}