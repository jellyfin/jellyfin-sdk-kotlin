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
	private String[] privateChannelIdList;
	public final String[] getChannelIdList()
	{
		return privateChannelIdList;
	}
	public final void setChannelIdList(String[] value)
	{
		privateChannelIdList = value;
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

	private java.util.Date privateMinStartDate = null;
	public final java.util.Date getMinStartDate()
	{
		return privateMinStartDate;
	}
	public final void setMinStartDate(java.util.Date value)
	{
		privateMinStartDate = value;
	}

	private java.util.Date privateMaxStartDate = null;
	public final java.util.Date getMaxStartDate()
	{
		return privateMaxStartDate;
	}
	public final void setMaxStartDate(java.util.Date value)
	{
		privateMaxStartDate = value;
	}

	private java.util.Date privateMinEndDate = null;
	public final java.util.Date getMinEndDate()
	{
		return privateMinEndDate;
	}
	public final void setMinEndDate(java.util.Date value)
	{
		privateMinEndDate = value;
	}

	private java.util.Date privateMaxEndDate = null;
	public final java.util.Date getMaxEndDate()
	{
		return privateMaxEndDate;
	}
	public final void setMaxEndDate(java.util.Date value)
	{
		privateMaxEndDate = value;
	}

	public ProgramQuery()
	{
		setChannelIdList(new String[] { });
	}
}