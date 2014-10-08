package MediaBrowser.Model.LiveTv;

public class LiveTvInfo
{
	/** 
	 Gets or sets the services.
	 
	 <value>The services.</value>
	*/
	private java.util.ArrayList<LiveTvServiceInfo> Services;
	public final java.util.ArrayList<LiveTvServiceInfo> getServices()
	{
		return Services;
	}
	public final void setServices(java.util.ArrayList<LiveTvServiceInfo> value)
	{
		Services = value;
	}

	/** 
	 Gets or sets the name of the active service.
	 
	 <value>The name of the active service.</value>
	*/
	private String ActiveServiceName;
	public final String getActiveServiceName()
	{
		return ActiveServiceName;
	}
	public final void setActiveServiceName(String value)
	{
		ActiveServiceName = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is enabled.
	 
	 <value><c>true</c> if this instance is enabled; otherwise, <c>false</c>.</value>
	*/
	private boolean IsEnabled;
	public final boolean getIsEnabled()
	{
		return IsEnabled;
	}
	public final void setIsEnabled(boolean value)
	{
		IsEnabled = value;
	}

	/** 
	 Gets or sets the enabled users.
	 
	 <value>The enabled users.</value>
	*/
	private java.util.ArrayList<String> EnabledUsers;
	public final java.util.ArrayList<String> getEnabledUsers()
	{
		return EnabledUsers;
	}
	public final void setEnabledUsers(java.util.ArrayList<String> value)
	{
		EnabledUsers = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private LiveTvServiceStatus Status = LiveTvServiceStatus.values()[0];
	public final LiveTvServiceStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(LiveTvServiceStatus value)
	{
		Status = value;
	}

	/** 
	 Gets or sets the status message.
	 
	 <value>The status message.</value>
	*/
	private String StatusMessage;
	public final String getStatusMessage()
	{
		return StatusMessage;
	}
	public final void setStatusMessage(String value)
	{
		StatusMessage = value;
	}

	public LiveTvInfo()
	{
		setServices(new java.util.ArrayList<LiveTvServiceInfo>());
		setEnabledUsers(new java.util.ArrayList<String>());
	}
}