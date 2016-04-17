package mediabrowser.model.notifications;

public class NotificationOption
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

	/** 
	 User Ids to not monitor (it's opt out)
	*/
	private String[] DisabledMonitorUsers;
	public final String[] getDisabledMonitorUsers()
	{
		return DisabledMonitorUsers;
	}
	public final void setDisabledMonitorUsers(String[] value)
	{
		DisabledMonitorUsers = value;
	}

	/** 
	 User Ids to send to (if SendToUserMode == Custom)
	*/
	private String[] SendToUsers;
	public final String[] getSendToUsers()
	{
		return SendToUsers;
	}
	public final void setSendToUsers(String[] value)
	{
		SendToUsers = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="NotificationOption"/> is enabled.
	 
	 <value><c>true</c> if enabled; otherwise, <c>false</c>.</value>
	*/
	private boolean Enabled;
	public final boolean getEnabled()
	{
		return Enabled;
	}
	public final void setEnabled(boolean value)
	{
		Enabled = value;
	}

	/** 
	 Gets or sets the title format string.
	 
	 <value>The title format string.</value>
	*/
	private String Title;
	public final String getTitle()
	{
		return Title;
	}
	public final void setTitle(String value)
	{
		Title = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}

	/** 
	 Gets or sets the disabled services.
	 
	 <value>The disabled services.</value>
	*/
	private String[] DisabledServices;
	public final String[] getDisabledServices()
	{
		return DisabledServices;
	}
	public final void setDisabledServices(String[] value)
	{
		DisabledServices = value;
	}

	/** 
	 Gets or sets the send to user mode.
	 
	 <value>The send to user mode.</value>
	*/
	private SendToUserType SendToUserMode = SendToUserType.values()[0];
	public final SendToUserType getSendToUserMode()
	{
		return SendToUserMode;
	}
	public final void setSendToUserMode(SendToUserType value)
	{
		SendToUserMode = value;
	}

	public NotificationOption()
	{
		setDisabledServices(new String[] { });
		setDisabledMonitorUsers(new String[] { });
		setSendToUsers(new String[] { });
	}
}