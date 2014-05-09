package MediaBrowser.Model.Querying;

public class UpcomingEpisodesQuery
{
	/** 
	 Gets or sets the user id.
	 
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
	 Gets or sets the parent identifier.
	 
	 <value>The parent identifier.</value>
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
}