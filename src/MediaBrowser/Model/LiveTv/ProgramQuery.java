package MediaBrowser.Model.LiveTv;

/** 
 Class ProgramQuery.
*/
public class ProgramQuery
{
	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String[] ChannelIdList;
	public final String[] getChannelIdList()
	{
		return ChannelIdList;
	}
	public final void setChannelIdList(String[] value)
	{
		ChannelIdList = value;
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

	private java.util.Date MinStartDate = null;
	public final java.util.Date getMinStartDate()
	{
		return MinStartDate;
	}
	public final void setMinStartDate(java.util.Date value)
	{
		MinStartDate = value;
	}

	private java.util.Date MaxStartDate = null;
	public final java.util.Date getMaxStartDate()
	{
		return MaxStartDate;
	}
	public final void setMaxStartDate(java.util.Date value)
	{
		MaxStartDate = value;
	}

	private java.util.Date MinEndDate = null;
	public final java.util.Date getMinEndDate()
	{
		return MinEndDate;
	}
	public final void setMinEndDate(java.util.Date value)
	{
		MinEndDate = value;
	}

	private java.util.Date MaxEndDate = null;
	public final java.util.Date getMaxEndDate()
	{
		return MaxEndDate;
	}
	public final void setMaxEndDate(java.util.Date value)
	{
		MaxEndDate = value;
	}

	public ProgramQuery()
	{
		setChannelIdList(new String[] { });
	}
}