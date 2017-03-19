package mediabrowser.model.livetv;

import mediabrowser.model.entities.*;
import mediabrowser.model.querying.*;

/** 
 Class RecordingQuery.
*/
public class RecordingQuery
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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	/** 
	 Gets or sets the group identifier.
	 
	 <value>The group identifier.</value>
	*/
	private String GroupId;
	public final String getGroupId()
	{
		return GroupId;
	}
	public final void setGroupId(String value)
	{
		GroupId = value;
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
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private RecordingStatus Status;
	public final RecordingStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(RecordingStatus value)
	{
		Status = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is in progress.
	 
	 <value><c>null</c> if [is in progress] contains no value, <c>true</c> if [is in progress]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsInProgress;
	public final Boolean getIsInProgress()
	{
		return IsInProgress;
	}
	public final void setIsInProgress(Boolean value)
	{
		IsInProgress = value;
	}

	/** 
	 Gets or sets the series timer identifier.
	 
	 <value>The series timer identifier.</value>
	*/
	private String SeriesTimerId;
	public final String getSeriesTimerId()
	{
		return SeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		SeriesTimerId = value;
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
	private Boolean EnableImages;
	public final Boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(Boolean value)
	{
		EnableImages = value;
	}
	private Boolean IsLibraryItem;
	public final Boolean getIsLibraryItem()
	{
		return IsLibraryItem;
	}
	public final void setIsLibraryItem(Boolean value)
	{
		IsLibraryItem = value;
	}
	private Boolean IsNews;
	public final Boolean getIsNews()
	{
		return IsNews;
	}
	public final void setIsNews(Boolean value)
	{
		IsNews = value;
	}
	private Boolean IsMovie;
	public final Boolean getIsMovie()
	{
		return IsMovie;
	}
	public final void setIsMovie(Boolean value)
	{
		IsMovie = value;
	}
	private Boolean IsSeries;
	public final Boolean getIsSeries()
	{
		return IsSeries;
	}
	public final void setIsSeries(Boolean value)
	{
		IsSeries = value;
	}
	private Boolean IsKids;
	public final Boolean getIsKids()
	{
		return IsKids;
	}
	public final void setIsKids(Boolean value)
	{
		IsKids = value;
	}
	private Boolean IsSports;
	public final Boolean getIsSports()
	{
		return IsSports;
	}
	public final void setIsSports(Boolean value)
	{
		IsSports = value;
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

	private boolean EnableTotalRecordCount;
	public final boolean getEnableTotalRecordCount()
	{
		return EnableTotalRecordCount;
	}
	public final void setEnableTotalRecordCount(boolean value)
	{
		EnableTotalRecordCount = value;
	}

	public RecordingQuery()
	{
		setEnableTotalRecordCount(true);
	}
}