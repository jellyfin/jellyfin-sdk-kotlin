package MediaBrowser.Model.Activity;

import MediaBrowser.Model.Logging.*;

public class ActivityLogEntry
{
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
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String privateOverview;
	public final String getOverview()
	{
		return privateOverview;
	}
	public final void setOverview(String value)
	{
		privateOverview = value;
	}

	/** 
	 Gets or sets the short overview.
	 
	 <value>The short overview.</value>
	*/
	private String privateShortOverview;
	public final String getShortOverview()
	{
		return privateShortOverview;
	}
	public final void setShortOverview(String value)
	{
		privateShortOverview = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	/** 
	 Gets or sets the date.
	 
	 <value>The date.</value>
	*/
	private java.util.Date privateDate = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return privateDate;
	}
	public final void setDate(java.util.Date value)
	{
		privateDate = value;
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
	 Gets or sets the log severity.
	 
	 <value>The log severity.</value>
	*/
	private LogSeverity privateSeverity = LogSeverity.values()[0];
	public final LogSeverity getSeverity()
	{
		return privateSeverity;
	}
	public final void setSeverity(LogSeverity value)
	{
		privateSeverity = value;
	}
}