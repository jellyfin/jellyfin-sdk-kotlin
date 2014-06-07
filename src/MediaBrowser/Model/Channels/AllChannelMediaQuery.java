package MediaBrowser.Model.Channels;

public class AllChannelMediaQuery
{
	private String[] privateChannelIds;
	public final String[] getChannelIds()
	{
		return privateChannelIds;
	}
	public final void setChannelIds(String[] value)
	{
		privateChannelIds = value;
	}

	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
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
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer privateStartIndex;
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
	private Integer privateLimit;
	public final Integer getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(Integer value)
	{
		privateLimit = value;
	}

	/** 
	 Gets or sets the content types.
	 
	 <value>The content types.</value>
	*/
	private ChannelMediaContentType[] privateContentTypes;
	public final ChannelMediaContentType[] getContentTypes()
	{
		return privateContentTypes;
	}
	public final void setContentTypes(ChannelMediaContentType[] value)
	{
		privateContentTypes = value;
	}

	public AllChannelMediaQuery()
	{
		setChannelIds(new String[] { });

		setContentTypes(new ChannelMediaContentType[] { });
	}
}