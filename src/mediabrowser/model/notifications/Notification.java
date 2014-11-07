package mediabrowser.model.notifications;

public class Notification
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	private java.util.Date Date = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return Date;
	}
	public final void setDate(java.util.Date value)
	{
		Date = value;
	}

	private boolean IsRead;
	public final boolean getIsRead()
	{
		return IsRead;
	}
	public final void setIsRead(boolean value)
	{
		IsRead = value;
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

	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}

	private String Url;
	public final String getUrl()
	{
		return Url;
	}
	public final void setUrl(String value)
	{
		Url = value;
	}

	private NotificationLevel Level = NotificationLevel.values()[0];
	public final NotificationLevel getLevel()
	{
		return Level;
	}
	public final void setLevel(NotificationLevel value)
	{
		Level = value;
	}
}