package MediaBrowser.Model.LiveTv;

/** 
 Class ChannelQuery.
*/
public class ChannelQuery
{
	/** 
	 Gets or sets the type of the channel.
	 
	 <value>The type of the channel.</value>
	*/
	private ChannelType privateChannelType;
	public final ChannelType getChannelType()
	{
		return privateChannelType;
	}
	public final void setChannelType(ChannelType value)
	{
		privateChannelType = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is favorite.
	 
	 <value><c>null</c> if [is favorite] contains no value, <c>true</c> if [is favorite]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsFavorite;
	public final Boolean getIsFavorite()
	{
		return privateIsFavorite;
	}
	public final void setIsFavorite(Boolean value)
	{
		privateIsFavorite = value;
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
}