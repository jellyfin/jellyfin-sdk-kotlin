package mediabrowser.model.livetv;

public class TimerQuery
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

	private Boolean IsActive;
	public final Boolean getIsActive()
	{
		return IsActive;
	}
	public final void setIsActive(Boolean value)
	{
		IsActive = value;
	}

	private Boolean IsScheduled;
	public final Boolean getIsScheduled()
	{
		return IsScheduled;
	}
	public final void setIsScheduled(Boolean value)
	{
		IsScheduled = value;
	}
}