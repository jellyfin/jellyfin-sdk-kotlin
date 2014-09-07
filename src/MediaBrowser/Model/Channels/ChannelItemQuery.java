package MediaBrowser.Model.Channels;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Querying.*;

public class ChannelItemQuery
{
	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String privateChannelId;
	public final String getChannelId()
	{
		return privateChannelId;
	}
	public final void setChannelId(String value)
	{
		privateChannelId = value;
	}

	/** 
	 Gets or sets the category identifier.
	 
	 <value>The category identifier.</value>
	*/
	private String privateFolderId;
	public final String getFolderId()
	{
		return privateFolderId;
	}
	public final void setFolderId(String value)
	{
		privateFolderId = value;
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

	private SortOrder privateSortOrder;
	public final SortOrder getSortOrder()
	{
		return privateSortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		privateSortOrder = value;
	}
	private String[] privateSortBy;
	public final String[] getSortBy()
	{
		return privateSortBy;
	}
	public final void setSortBy(String[] value)
	{
		privateSortBy = value;
	}
	private ItemFilter[] privateFilters;
	public final ItemFilter[] getFilters()
	{
		return privateFilters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		privateFilters = value;
	}
	private ItemFields[] privateFields;
	public final ItemFields[] getFields()
	{
		return privateFields;
	}
	public final void setFields(ItemFields[] value)
	{
		privateFields = value;
	}

	public ChannelItemQuery()
	{
		setFilters(new ItemFilter[] { });
		setSortBy(new String[] { });
		setFields(new ItemFields[] { });
	}
}