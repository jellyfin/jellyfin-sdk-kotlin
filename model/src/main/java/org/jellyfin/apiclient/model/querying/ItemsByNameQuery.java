package org.jellyfin.apiclient.model.querying;

import org.jellyfin.apiclient.model.entities.*;

/** 
 Class ItemsByNameQuery
*/
public class ItemsByNameQuery
{
	/** 
	 Gets or sets the user id.
	 
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
	 Gets or sets the start index.
	 
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
	 Gets or sets the size of the page.
	 
	 <value>The size of the page.</value>
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
	 Gets or sets a value indicating whether this <see cref="ItemsByNameQuery" /> is recursive.
	 
	 <value><c>true</c> if recursive; otherwise, <c>false</c>.</value>
	*/
	private boolean Recursive;
	public final boolean getRecursive()
	{
		return Recursive;
	}
	public final void setRecursive(boolean value)
	{
		Recursive = value;
	}
	/** 
	 Gets or sets the sort order.
	 
	 <value>The sort order.</value>
	*/
	private SortOrder SortOrder;
	public final SortOrder getSortOrder()
	{
		return SortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		SortOrder = value;
	}
	/** 
	 Gets or sets the parent id.
	 
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
	 Gets or sets the filters.
	 
	 <value>The filters.</value>
	*/
	private ItemFilter[] Filters;
	public final ItemFilter[] getFilters()
	{
		return Filters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		Filters = value;
	}

	/** 
	 Gets or sets the exclude item types.
	 
	 <value>The exclude item types.</value>
	*/
	private String[] ExcludeItemTypes;
	public final String[] getExcludeItemTypes()
	{
		return ExcludeItemTypes;
	}
	public final void setExcludeItemTypes(String[] value)
	{
		ExcludeItemTypes = value;
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
	 Gets or sets the media types.
	 
	 <value>The media types.</value>
	*/
	private String[] MediaTypes;
	public final String[] getMediaTypes()
	{
		return MediaTypes;
	}
	public final void setMediaTypes(String[] value)
	{
		MediaTypes = value;
	}

	/** 
	 What to sort the results by
	 
	 <value>The sort by.</value>
	*/
	private String[] SortBy;
	public final String[] getSortBy()
	{
		return SortBy;
	}
	public final void setSortBy(String[] value)
	{
		SortBy = value;
	}

	/** 
	 Gets or sets the image types.
	 
	 <value>The image types.</value>
	*/
	private ImageType[] ImageTypes;
	public final ImageType[] getImageTypes()
	{
		return ImageTypes;
	}
	public final void setImageTypes(ImageType[] value)
	{
		ImageTypes = value;
	}

	/** 
	 Gets or sets the name starts with or greater.
	 
	 <value>The name starts with or greater.</value>
	*/
	private String NameStartsWithOrGreater;
	public final String getNameStartsWithOrGreater()
	{
		return NameStartsWithOrGreater;
	}
	public final void setNameStartsWithOrGreater(String value)
	{
		NameStartsWithOrGreater = value;
	}

	/** 
	 Gets or sets the name starts with
	 
	 <value>The name starts with or greater.</value>
	*/
	private String NameStartsWith;
	public final String getNameStartsWith()
	{
		return NameStartsWith;
	}
	public final void setNameStartsWith(String value)
	{
		NameStartsWith = value;
	}
	/** 
	 Gets or sets the name less than.
	 
	 <value>The name less than.</value>
	*/
	private String NameLessThan;
	public final String getNameLessThan()
	{
		return NameLessThan;
	}
	public final void setNameLessThan(String value)
	{
		NameLessThan = value;
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

	/** 
	 Initializes a new instance of the <see cref="ItemsByNameQuery" /> class.
	*/
	public ItemsByNameQuery()
	{
		setImageTypes(new ImageType[] { });
		setFilters(new ItemFilter[] { });
		setFields(new ItemFields[] { });
		setRecursive(true);
		setMediaTypes(new String[] { });
		setSortBy(new String[] { });
		setExcludeItemTypes(new String[] { });
		setIncludeItemTypes(new String[] { });
		setEnableImageTypes(new ImageType[] { });
	}
}