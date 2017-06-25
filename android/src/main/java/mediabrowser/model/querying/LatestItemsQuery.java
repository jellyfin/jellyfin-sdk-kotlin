package mediabrowser.model.querying;

import mediabrowser.model.entities.*;

public class LatestItemsQuery
{
	/** 
	 The user to localize search results for
	 
	 <value>The user id.</value>
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
	 Specify this to localize the search to a specific item or folder. Omit to use the root.
	 
	 <value>The parent id.</value>
	*/
	private String ParentId;
	public final String getParentId()
	{
		return ParentId;
	}
	public final void setParentId(String value)
	{
		ParentId = value;
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

	/** 
	 Gets or sets the include item types.
	 
	 <value>The include item types.</value>
	*/
	private String[] IncludeItemTypes;
	public final String[] getIncludeItemTypes()
	{
		return IncludeItemTypes;
	}
	public final void setIncludeItemTypes(String[] value)
	{
		IncludeItemTypes = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is played.
	 
	 <value><c>null</c> if [is played] contains no value, <c>true</c> if [is played]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsPlayed;
	public final Boolean getIsPlayed()
	{
		return IsPlayed;
	}
	public final void setIsPlayed(Boolean value)
	{
		IsPlayed = value;
	}

	/** 
	 Gets or sets a value indicating whether [group items].
	 
	 <value><c>true</c> if [group items]; otherwise, <c>false</c>.</value>
	*/
	private boolean GroupItems;
	public final boolean getGroupItems()
	{
		return GroupItems;
	}
	public final void setGroupItems(boolean value)
	{
		GroupItems = value;
	}
	/** 
	 Gets or sets a value indicating whether [enable images].
	 
	 <value><c>null</c> if [enable images] contains no value, <c>true</c> if [enable images]; otherwise, <c>false</c>.</value>
	*/
	private Boolean EnableImages;
	public final Boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(Boolean value)
	{
		EnableImages = value;
	}
	/** 
	 Gets or sets the image type limit.
	 
	 <value>The image type limit.</value>
	*/
	private Integer ImageTypeLimit;
	public final Integer getImageTypeLimit()
	{
		return ImageTypeLimit;
	}
	public final void setImageTypeLimit(Integer value)
	{
		ImageTypeLimit = value;
	}
	/** 
	 Gets or sets the enable image types.
	 
	 <value>The enable image types.</value>
	*/
	private ImageType[] EnableImageTypes;
	public final ImageType[] getEnableImageTypes()
	{
		return EnableImageTypes;
	}
	public final void setEnableImageTypes(ImageType[] value)
	{
		EnableImageTypes = value;
	}

	public LatestItemsQuery()
	{
		setEnableImageTypes(new ImageType[] {});
	}
}