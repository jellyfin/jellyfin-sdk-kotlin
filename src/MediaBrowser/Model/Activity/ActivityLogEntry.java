package MediaBrowser.Model.Activity;

import MediaBrowser.Model.Logging.*;

public class ActivityLogEntry
{
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
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
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
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String Overview;
	public final String getOverview()
	{
		return Overview;
	}
	public final void setOverview(String value)
	{
		Overview = value;
	}

	/** 
	 Gets or sets the short overview.
	 
	 <value>The short overview.</value>
	*/
	private String ShortOverview;
	public final String getShortOverview()
	{
		return ShortOverview;
	}
	public final void setShortOverview(String value)
	{
		ShortOverview = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}

	/** 
	 Gets or sets the date.
	 
	 <value>The date.</value>
	*/
	private java.util.Date Date = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return Date;
	}
	public final void setDate(java.util.Date value)
	{
		Date = value;
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
	 Gets or sets the user primary image tag.
	 
	 <value>The user primary image tag.</value>
	*/
	private String UserPrimaryImageTag;
	public final String getUserPrimaryImageTag()
	{
		return UserPrimaryImageTag;
	}
	public final void setUserPrimaryImageTag(String value)
	{
		UserPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the log severity.
	 
	 <value>The log severity.</value>
	*/
	private LogSeverity Severity = LogSeverity.values()[0];
	public final LogSeverity getSeverity()
	{
		return Severity;
	}
	public final void setSeverity(LogSeverity value)
	{
		Severity = value;
	}
}