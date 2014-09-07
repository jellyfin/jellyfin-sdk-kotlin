package MediaBrowser.Model.Querying;

public class LatestItemsQuery
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
	 Gets or sets a value indicating whether this instance is played.
	 
	 <value><c>null</c> if [is played] contains no value, <c>true</c> if [is played]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsPlayed;
	public final Boolean getIsPlayed()
	{
		return privateIsPlayed;
	}
	public final void setIsPlayed(Boolean value)
	{
		privateIsPlayed = value;
	}

	/** 
	 Gets or sets a value indicating whether [group items].
	 
	 <value><c>true</c> if [group items]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateGroupItems;
	public final boolean getGroupItems()
	{
		return privateGroupItems;
	}
	public final void setGroupItems(boolean value)
	{
		privateGroupItems = value;
	}
}