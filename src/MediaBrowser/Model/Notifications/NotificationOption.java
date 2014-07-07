package MediaBrowser.Model.Notifications;

import MediaBrowser.Model.Configuration.*;

public class NotificationOption
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

	/** 
	 User Ids to not monitor (it's opt out)
	*/
	private String[] privateDisabledMonitorUsers;
	public final String[] getDisabledMonitorUsers()
	{
		return privateDisabledMonitorUsers;
	}
	public final void setDisabledMonitorUsers(String[] value)
	{
		privateDisabledMonitorUsers = value;
	}

	/** 
	 User Ids to send to (if SendToUserMode == Custom)
	*/
	private String[] privateSendToUsers;
	public final String[] getSendToUsers()
	{
		return privateSendToUsers;
	}
	public final void setSendToUsers(String[] value)
	{
		privateSendToUsers = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="NotificationOption"/> is enabled.
	 
	 <value><c>true</c> if enabled; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnabled;
	public final boolean getEnabled()
	{
		return privateEnabled;
	}
	public final void setEnabled(boolean value)
	{
		privateEnabled = value;
	}

	/** 
	 Gets or sets the title format string.
	 
	 <value>The title format string.</value>
	*/
	private String privateTitle;
	public final String getTitle()
	{
		return privateTitle;
	}
	public final void setTitle(String value)
	{
		privateTitle = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String privateDescription;
	public final String getDescription()
	{
		return privateDescription;
	}
	public final void setDescription(String value)
	{
		privateDescription = value;
	}

	/** 
	 Gets or sets the disabled services.
	 
	 <value>The disabled services.</value>
	*/
	private String[] privateDisabledServices;
	public final String[] getDisabledServices()
	{
		return privateDisabledServices;
	}
	public final void setDisabledServices(String[] value)
	{
		privateDisabledServices = value;
	}

	/** 
	 Gets or sets the send to user mode.
	 
	 <value>The send to user mode.</value>
	*/
	private SendToUserType privateSendToUserMode = SendToUserType.values()[0];
	public final SendToUserType getSendToUserMode()
	{
		return privateSendToUserMode;
	}
	public final void setSendToUserMode(SendToUserType value)
	{
		privateSendToUserMode = value;
	}

	public NotificationOption()
	{
		setDisabledServices(new String[] { });
		setDisabledMonitorUsers(new String[] { });
		setSendToUsers(new String[] { });
	}
}