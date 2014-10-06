package MediaBrowser.Model.Channels;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Querying.*;

public class AllChannelMediaQuery
{
	/** 
	 Gets or sets the channel ids.
	 
	 <value>The channel ids.</value>
	*/
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

	/** 
	 Gets or sets the extra types.
	 
	 <value>The extra types.</value>
	*/
	private ExtraType[] privateExtraTypes;
	public final ExtraType[] getExtraTypes()
	{
		return privateExtraTypes;
	}
	public final void setExtraTypes(ExtraType[] value)
	{
		privateExtraTypes = value;
	}
	private TrailerType[] privateTrailerTypes;
	public final TrailerType[] getTrailerTypes()
	{
		return privateTrailerTypes;
	}
	public final void setTrailerTypes(TrailerType[] value)
	{
		privateTrailerTypes = value;
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

	private ItemFilter[] privateFilters;
	public final ItemFilter[] getFilters()
	{
		return privateFilters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		privateFilters = value;
	}
	private java.util.ArrayList<ItemFields> privateFields;
	public final java.util.ArrayList<ItemFields> getFields()
	{
		return privateFields;
	}
	public final void setFields(java.util.ArrayList<ItemFields> value)
	{
		privateFields = value;
	}
}