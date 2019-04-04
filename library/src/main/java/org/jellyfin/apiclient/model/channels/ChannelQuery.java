package org.jellyfin.apiclient.model.channels;

import org.jellyfin.apiclient.model.entities.*;
import org.jellyfin.apiclient.model.querying.*;

public class ChannelQuery
{
	/** 
	 Fields to return within the items, in addition to basic information
	 
	 <value>The fields.</value>
	*/
	private ItemFields[] Fields;
	public final ItemFields[] getFields()
	{
		return Fields;
	}
	public final void setFields(ItemFields[] value)
	{
		Fields = value;
	}
	private Boolean EnableImages;
	public final Boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(Boolean value)
	{
		EnableImages = value;
	}
	private Integer ImageTypeLimit;
	public final Integer getImageTypeLimit()
	{
		return ImageTypeLimit;
	}
	public final void setImageTypeLimit(Integer value)
	{
		ImageTypeLimit = value;
	}
	private ImageType[] EnableImageTypes;
	public final ImageType[] getEnableImageTypes()
	{
		return EnableImageTypes;
	}
	public final void setEnableImageTypes(ImageType[] value)
	{
		EnableImageTypes = value;
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