package MediaBrowser.Model.Querying;

import MediaBrowser.Model.Entities.*;

/** 
 Class ItemsByNameQuery
*/
public class ItemsByNameQuery
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
	 Gets or sets the start index.
	 
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
	 Gets or sets the size of the page.
	 
	 <value>The size of the page.</value>
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
	 Gets or sets a value indicating whether this <see cref="ItemsByNameQuery" /> is recursive.
	 
	 <value><c>true</c> if recursive; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRecursive;
	public final boolean getRecursive()
	{
		return privateRecursive;
	}
	public final void setRecursive(boolean value)
	{
		privateRecursive = value;
	}
	/** 
	 Gets or sets the sort order.
	 
	 <value>The sort order.</value>
	*/
	private SortOrder privateSortOrder;
	public final SortOrder getSortOrder()
	{
		return privateSortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		privateSortOrder = value;
	}
	/** 
	 Gets or sets the parent id.
	 
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
	 Gets or sets the filters.
	 
	 <value>The filters.</value>
	*/
	private ItemFilter[] privateFilters;
	public final ItemFilter[] getFilters()
	{
		return privateFilters;
	}
	public final void setFilters(ItemFilter[] value)
	{
		privateFilters = value;
	}

	/** 
	 Gets or sets the exclude item types.
	 
	 <value>The exclude item types.</value>
	*/
	private String[] privateExcludeItemTypes;
	public final String[] getExcludeItemTypes()
	{
		return privateExcludeItemTypes;
	}
	public final void setExcludeItemTypes(String[] value)
	{
		privateExcludeItemTypes = value;
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
	 Gets or sets the media types.
	 
	 <value>The media types.</value>
	*/
	private String[] privateMediaTypes;
	public final String[] getMediaTypes()
	{
		return privateMediaTypes;
	}
	public final void setMediaTypes(String[] value)
	{
		privateMediaTypes = value;
	}

	/** 
	 What to sort the results by
	 
	 <value>The sort by.</value>
	*/
	private String[] privateSortBy;
	public final String[] getSortBy()
	{
		return privateSortBy;
	}
	public final void setSortBy(String[] value)
	{
		privateSortBy = value;
	}

	/** 
	 Gets or sets the image types.
	 
	 <value>The image types.</value>
	*/
	private ImageType[] privateImageTypes;
	public final ImageType[] getImageTypes()
	{
		return privateImageTypes;
	}
	public final void setImageTypes(ImageType[] value)
	{
		privateImageTypes = value;
	}

	/** 
	 Gets or sets the name starts with or greater.
	 
	 <value>The name starts with or greater.</value>
	*/
	private String privateNameStartsWithOrGreater;
	public final String getNameStartsWithOrGreater()
	{
		return privateNameStartsWithOrGreater;
	}
	public final void setNameStartsWithOrGreater(String value)
	{
		privateNameStartsWithOrGreater = value;
	}

	/** 
	 Gets or sets the name starts with
	 
	 <value>The name starts with or greater.</value>
	*/
	private String privateNameStartsWith;
	public final String getNameStartsWith()
	{
		return privateNameStartsWith;
	}
	public final void setNameStartsWith(String value)
	{
		privateNameStartsWith = value;
	}
	/** 
	 Gets or sets the name less than.
	 
	 <value>The name less than.</value>
	*/
	private String privateNameLessThan;
	public final String getNameLessThan()
	{
		return privateNameLessThan;
	}
	public final void setNameLessThan(String value)
	{
		privateNameLessThan = value;
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
	}
}