package mediabrowser.model.channels;

public class ChannelQuery
{
	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
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
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer StartIndex;
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
	private Integer Limit;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports latest items].
	 
	 <value><c>true</c> if [supports latest items]; otherwise, <c>false</c>.</value>
	*/
	private Boolean SupportsLatestItems;
	public final Boolean getSupportsLatestItems()
	{
		return SupportsLatestItems;
	}
	public final void setSupportsLatestItems(Boolean value)
	{
		SupportsLatestItems = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is favorite.
	 
	 <value><c>null</c> if [is favorite] contains no value, <c>true</c> if [is favorite]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsFavorite;
	public final Boolean getIsFavorite()
	{
		return IsFavorite;
	}
	public final void setIsFavorite(Boolean value)
	{
		IsFavorite = value;
	}
}