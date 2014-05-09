package MediaBrowser.Model.Notifications;

public class NotificationTypeInfo
{
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
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

	private boolean privateEnabled;
	public final boolean getEnabled()
	{
		return privateEnabled;
	}
	public final void setEnabled(boolean value)
	{
		privateEnabled = value;
	}

	private String privateCategory;
	public final String getCategory()
	{
		return privateCategory;
	}
	public final void setCategory(String value)
	{
		privateCategory = value;
	}

	private boolean privateIsBasedOnUserEvent;
	public final boolean getIsBasedOnUserEvent()
	{
		return privateIsBasedOnUserEvent;
	}
	public final void setIsBasedOnUserEvent(boolean value)
	{
		privateIsBasedOnUserEvent = value;
	}

	private String privateDefaultTitle;
	public final String getDefaultTitle()
	{
		return privateDefaultTitle;
	}
	public final void setDefaultTitle(String value)
	{
		privateDefaultTitle = value;
	}

	private String privateDefaultDescription;
	public final String getDefaultDescription()
	{
		return privateDefaultDescription;
	}
	public final void setDefaultDescription(String value)
	{
		privateDefaultDescription = value;
	}

	private java.util.ArrayList<String> privateVariables;
	public final java.util.ArrayList<String> getVariables()
	{
		return privateVariables;
	}
	public final void setVariables(java.util.ArrayList<String> value)
	{
		privateVariables = value;
	}

	public NotificationTypeInfo()
	{
		setVariables(new java.util.ArrayList<String>());
	}
}