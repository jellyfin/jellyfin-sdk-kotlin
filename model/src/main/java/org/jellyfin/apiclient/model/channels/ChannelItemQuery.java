package org.jellyfin.apiclient.model.channels;

import org.jellyfin.apiclient.model.entities.*;
import org.jellyfin.apiclient.model.querying.*;

public class ChannelItemQuery
{
	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String ChannelId;
	public final String getChannelId()
	{
		return ChannelId;
	}
	public final void setChannelId(String value)
	{
		ChannelId = value;
	}

	/** 
	 Gets or sets the category identifier.
	 
	 <value>The category identifier.</value>
	*/
	private String FolderId;
	public final String getFolderId()
	{
		return FolderId;
	}
	public final void setFolderId(String value)
	{
		FolderId = value;
	}

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

	private SortOrder SortOrder;
	public final SortOrder getSortOrder()
	{
		return SortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		SortOrder = value;
	}
	private String[] SortBy;
	public final String[] getSortBy()
	{
		return SortBy;
	}
	public final void setSortBy(String[] value)
	{
		SortBy = value;
	}
	private ItemFilter[] Filters;
	public final ItemFilter[] getFilters()
	{
		return Filters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		Filters = value;
	}
	private ItemFields[] Fields;
	public final ItemFields[] getFields()
	{
		return Fields;
	}
	public final void setFields(ItemFields[] value)
	{
		Fields = value;
	}

	public ChannelItemQuery()
	{
		setFilters(new ItemFilter[] { });
		setSortBy(new String[] { });
		setFields(new ItemFields[] { });
	}
}