package MediaBrowser.Model.LiveTv;

/** 
 Class RecordingQuery.
*/
public class RecordingQuery
{
	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String privateChannelId;
	public final String getChannelId()
	{
		return privateChannelId;
	}
	public final void setChannelId(String value)
	{
		privateChannelId = value;
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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	/** 
	 Gets or sets the group identifier.
	 
	 <value>The group identifier.</value>
	*/
	private String privateGroupId;
	public final String getGroupId()
	{
		return privateGroupId;
	}
	public final void setGroupId(String value)
	{
		privateGroupId = value;
	}

	/** 
	 Skips over a given number of items within the results. Use for paging.
	 
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
	 The maximum number of items to return
	 
	 <value>The limit.</value>
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
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private RecordingStatus privateStatus;
	public final RecordingStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(RecordingStatus value)
	{
		privateStatus = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is in progress.
	 
	 <value><c>null</c> if [is in progress] contains no value, <c>true</c> if [is in progress]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsInProgress;
	public final Boolean getIsInProgress()
	{
		return privateIsInProgress;
	}
	public final void setIsInProgress(Boolean value)
	{
		privateIsInProgress = value;
	}

	/** 
	 Gets or sets the series timer identifier.
	 
	 <value>The series timer identifier.</value>
	*/
	private String privateSeriesTimerId;
	public final String getSeriesTimerId()
	{
		return privateSeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		privateSeriesTimerId = value;
	}
}