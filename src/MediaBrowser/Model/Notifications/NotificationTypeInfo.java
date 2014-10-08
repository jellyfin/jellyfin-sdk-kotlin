package MediaBrowser.Model.Notifications;

public class NotificationTypeInfo
{
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
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

	private boolean Enabled;
	public final boolean getEnabled()
	{
		return Enabled;
	}
	public final void setEnabled(boolean value)
	{
		Enabled = value;
	}

	private String Category;
	public final String getCategory()
	{
		return Category;
	}
	public final void setCategory(String value)
	{
		Category = value;
	}

	private boolean IsBasedOnUserEvent;
	public final boolean getIsBasedOnUserEvent()
	{
		return IsBasedOnUserEvent;
	}
	public final void setIsBasedOnUserEvent(boolean value)
	{
		IsBasedOnUserEvent = value;
	}

	private String DefaultTitle;
	public final String getDefaultTitle()
	{
		return DefaultTitle;
	}
	public final void setDefaultTitle(String value)
	{
		DefaultTitle = value;
	}

	private String DefaultDescription;
	public final String getDefaultDescription()
	{
		return DefaultDescription;
	}
	public final void setDefaultDescription(String value)
	{
		DefaultDescription = value;
	}

	private java.util.ArrayList<String> Variables;
	public final java.util.ArrayList<String> getVariables()
	{
		return Variables;
	}
	public final void setVariables(java.util.ArrayList<String> value)
	{
		Variables = value;
	}

	public NotificationTypeInfo()
	{
		setVariables(new java.util.ArrayList<String>());
	}
}