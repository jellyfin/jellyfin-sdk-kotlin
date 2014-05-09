package MediaBrowser.Model.LiveTv;

public class TimerQuery
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