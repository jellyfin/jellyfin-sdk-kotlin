package org.jellyfin.apiclient.model.channels;

import org.jellyfin.apiclient.model.entities.*;
import org.jellyfin.apiclient.model.querying.*;

public class AllChannelMediaQuery
{
	/** 
	 Gets or sets the channel ids.
	 
	 <value>The channel ids.</value>
	*/
	private String[] ChannelIds;
	public final String[] getChannelIds()
	{
		return ChannelIds;
	}
	public final void setChannelIds(String[] value)
	{
		ChannelIds = value;
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
	private Integer StartIndex = null;
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
	private Integer Limit = null;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}

	/** 
	 Gets or sets the content types.
	 
	 <value>The content types.</value>
	*/
	private ChannelMediaContentType[] ContentTypes;
	public final ChannelMediaContentType[] getContentTypes()
	{
		return ContentTypes;
	}
	public final void setContentTypes(ChannelMediaContentType[] value)
	{
		ContentTypes = value;
	}

	/** 
	 Gets or sets the extra types.
	 
	 <value>The extra types.</value>
	*/
	private ExtraType[] ExtraTypes;
	public final ExtraType[] getExtraTypes()
	{
		return ExtraTypes;
	}
	public final void setExtraTypes(ExtraType[] value)
	{
		ExtraTypes = value;
	}
	private TrailerType[] TrailerTypes;
	public final TrailerType[] getTrailerTypes()
	{
		return TrailerTypes;
	}
	public final void setTrailerTypes(TrailerType[] value)
	{
		TrailerTypes = value;
	}

	public AllChannelMediaQuery()
	{
		setChannelIds(new String[] { });

		setContentTypes(new ChannelMediaContentType[] { });
		setExtraTypes(new ExtraType[] { });
		setTrailerTypes(new TrailerType[] { });

		setFilters(new ItemFilter[] { });
		setFields(new java.util.ArrayList<ItemFields>());
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
	private java.util.ArrayList<ItemFields> Fields;
	public final java.util.ArrayList<ItemFields> getFields()
	{
		return Fields;
	}
	public final void setFields(java.util.ArrayList<ItemFields> value)
	{
		Fields = value;
	}
}