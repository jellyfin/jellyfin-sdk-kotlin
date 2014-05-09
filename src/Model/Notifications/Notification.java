package MediaBrowser.Model.Notifications;

public class Notification
{
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	private java.util.Date privateDate = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return privateDate;
	}
	public final void setDate(java.util.Date value)
	{
		privateDate = value;
	}

	private boolean privateIsRead;
	public final boolean getIsRead()
	{
		return privateIsRead;
	}
	public final void setIsRead(boolean value)
	{
		privateIsRead = value;
	}

	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	private String privateDescription;
	public final String getDescription()
	{
		return privateDescription;
	}
	public final void setDescription(String value)
	{
		privateDescription = value;
	}

	private String privateUrl;
	public final String getUrl()
	{
		return privateUrl;
	}
	public final void setUrl(String value)
	{
		privateUrl = value;
	}

	private NotificationLevel privateLevel = NotificationLevel.values()[0];
	public final NotificationLevel getLevel()
	{
		return privateLevel;
	}
	public final void setLevel(NotificationLevel value)
	{
		privateLevel = value;
	}

	public Notification()
	{
		setDate(java.util.Date.UtcNow);
	}
}