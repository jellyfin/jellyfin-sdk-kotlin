package org.jellyfin.apiclient.model.querying;

import org.jellyfin.apiclient.model.entities.*;

public class NextUpQuery
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
	 Gets or sets the parent identifier.
	 
	 <value>The parent identifier.</value>
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
	 Gets or sets the series id.
	 
	 <value>The series id.</value>
	*/
	private String SeriesId;
	public final String getSeriesId()
	{
		return SeriesId;
	}
	public final void setSeriesId(String value)
	{
		SeriesId = value;
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

	public NextUpQuery()
	{
		setEnableImageTypes(new ImageType[] {});
	}
}