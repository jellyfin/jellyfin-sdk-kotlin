package MediaBrowser.Model.Notifications;

import MediaBrowser.Model.Configuration.*;

public class NotificationRequest
{
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

	private java.util.ArrayList<String> privateUserIds;
	public final java.util.ArrayList<String> getUserIds()
	{
		return privateUserIds;
	}
	public final void setUserIds(java.util.ArrayList<String> value)
	{
		privateUserIds = value;
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

	/** 
	 The corresponding type name used in configuration. Not for display.
	*/
	private String privateNotificationType;
	public final String getNotificationType()
	{
		return privateNotificationType;
	}
	public final void setNotificationType(String value)
	{
		privateNotificationType = value;
	}

	private java.util.HashMap<String, String> privateVariables;
	public final java.util.HashMap<String, String> getVariables()
	{
		return privateVariables;
	}
	public final void setVariables(java.util.HashMap<String, String> value)
	{
		privateVariables = value;
	}

	private SendToUserType privateSendToUserMode = new SendToUserType();
	public final SendToUserType getSendToUserMode()
	{
		return privateSendToUserMode;
	}
	public final void setSendToUserMode(SendToUserType value)
	{
		privateSendToUserMode = value;
	}

	private java.util.ArrayList<String> privateExcludeUserIds;
	public final java.util.ArrayList<String> getExcludeUserIds()
	{
		return privateExcludeUserIds;
	}
	public final void setExcludeUserIds(java.util.ArrayList<String> value)
	{
		privateExcludeUserIds = value;
	}

	public NotificationRequest()
	{
		setUserIds(new java.util.ArrayList<String>());
		setDate(java.util.Date.UtcNow);

		setVariables(new java.util.HashMap<String, String>(StringComparer.OrdinalIgnoreCase));

		setExcludeUserIds(new java.util.ArrayList<String>());
	}
}