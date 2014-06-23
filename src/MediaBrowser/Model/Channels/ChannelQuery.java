package MediaBrowser.Model.Channels;

import MediaBrowser.Model.Querying.*;

public class ChannelQuery
{
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
	 Gets or sets a value indicating whether [supports latest items].
	 
	 <value><c>true</c> if [supports latest items]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateSupportsLatestItems = null;
	public final Boolean getSupportsLatestItems()
	{
		return privateSupportsLatestItems;
	}
	public final void setSupportsLatestItems(Boolean value)
	{
		privateSupportsLatestItems = value;
	}
}