package org.jellyfin.apiclient.model.livetv;

import org.jellyfin.apiclient.model.entities.*;
import org.jellyfin.apiclient.model.querying.*;

/** 
 Class ProgramQuery.
*/
public class ProgramQuery
{
	public ProgramQuery()
	{
		setChannelIds(new String[] { });
		setSortBy(new String[] { });
		setGenres(new String[] { });
		setEnableTotalRecordCount(true);
		setEnableUserData(true);
	}

	private boolean EnableTotalRecordCount;
	public final boolean getEnableTotalRecordCount()
	{
		return EnableTotalRecordCount;
	}
	public final void setEnableTotalRecordCount(boolean value)
	{
		EnableTotalRecordCount = value;
	}
	private boolean EnableUserData;
	public final boolean getEnableUserData()
	{
		return EnableUserData;
	}
	public final void setEnableUserData(boolean value)
	{
		EnableUserData = value;
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
	private Boolean EnableImages = null;
	public final Boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(Boolean value)
	{
		EnableImages = value;
	}
	private Integer ImageTypeLimit = null;
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
	private String SeriesTimerId;
	public final String getSeriesTimerId()
	{
		return SeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		SeriesTimerId = value;
	}
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	/** 
	 The earliest date for which a program starts to return
	*/
	private java.util.Date MinStartDate = null;
	public final java.util.Date getMinStartDate()
	{
		return MinStartDate;
	}
	public final void setMinStartDate(java.util.Date value)
	{
		MinStartDate = value;
	}

	/** 
	 The latest date for which a program starts to return
	*/
	private java.util.Date MaxStartDate = null;
	public final java.util.Date getMaxStartDate()
	{
		return MaxStartDate;
	}
	public final void setMaxStartDate(java.util.Date value)
	{
		MaxStartDate = value;
	}

	/** 
	 The earliest date for which a program ends to return
	*/
	private java.util.Date MinEndDate = null;
	public final java.util.Date getMinEndDate()
	{
		return MinEndDate;
	}
	public final void setMinEndDate(java.util.Date value)
	{
		MinEndDate = value;
	}

	/** 
	 The latest date for which a program ends to return
	*/
	private java.util.Date MaxEndDate = null;
	public final java.util.Date getMaxEndDate()
	{
		return MaxEndDate;
	}
	public final void setMaxEndDate(java.util.Date value)
	{
		MaxEndDate = value;
	}

	/** 
	 Used to specific whether to return news or not
	 
	 If set to null, all programs will be returned
	*/
	private Boolean IsNews = null;
	public final Boolean getIsNews()
	{
		return IsNews;
	}
	public final void setIsNews(Boolean value)
	{
		IsNews = value;
	}

	/** 
	 Used to specific whether to return movies or not
	 
	 If set to null, all programs will be returned
	*/
	private Boolean IsMovie = null;
	public final Boolean getIsMovie()
	{
		return IsMovie;
	}
	public final void setIsMovie(Boolean value)
	{
		IsMovie = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is kids.
	 
	 <value><c>null</c> if [is kids] contains no value, <c>true</c> if [is kids]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsKids = null;
	public final Boolean getIsKids()
	{
		return IsKids;
	}
	public final void setIsKids(Boolean value)
	{
		IsKids = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is sports.
	 
	 <value><c>null</c> if [is sports] contains no value, <c>true</c> if [is sports]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsSports = null;
	public final Boolean getIsSports()
	{
		return IsSports;
	}
	public final void setIsSports(Boolean value)
	{
		IsSports = value;
	}

	/** 
	 Skips over a given number of items within the results. Use for paging.
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
	private Boolean IsSeries = null;
	public final Boolean getIsSeries()
	{
		return IsSeries;
	}
	public final void setIsSeries(Boolean value)
	{
		IsSeries = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has aired.
	 
	 <value><c>null</c> if [has aired] contains no value, <c>true</c> if [has aired]; otherwise, <c>false</c>.</value>
	*/
	private Boolean HasAired = null;
	public final Boolean getHasAired()
	{
		return HasAired;
	}
	public final void setHasAired(Boolean value)
	{
		HasAired = value;
	}

	/** 
	 The maximum number of items to return
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
	 The sort order to return results with
	 
	 <value>The sort order.</value>
	*/
	private SortOrder SortOrder = null;
	public final SortOrder getSortOrder()
	{
		return SortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		SortOrder = value;
	}

	/** 
	 Limit results to items containing specific genres
	 
	 <value>The genres.</value>
	*/
	private String[] Genres;
	public final String[] getGenres()
	{
		return Genres;
	}
	public final void setGenres(String[] value)
	{
		Genres = value;
	}
}