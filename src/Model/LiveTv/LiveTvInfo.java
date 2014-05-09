package MediaBrowser.Model.LiveTv;

public class LiveTvInfo
{
	/** 
	 Gets or sets the services.
	 
	 <value>The services.</value>
	*/
	private java.util.ArrayList<LiveTvServiceInfo> privateServices;
	public final java.util.ArrayList<LiveTvServiceInfo> getServices()
	{
		return privateServices;
	}
	public final void setServices(java.util.ArrayList<LiveTvServiceInfo> value)
	{
		privateServices = value;
	}

	/** 
	 Gets or sets the name of the active service.
	 
	 <value>The name of the active service.</value>
	*/
	private String privateActiveServiceName;
	public final String getActiveServiceName()
	{
		return privateActiveServiceName;
	}
	public final void setActiveServiceName(String value)
	{
		privateActiveServiceName = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is enabled.
	 
	 <value><c>true</c> if this instance is enabled; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsEnabled;
	public final boolean getIsEnabled()
	{
		return privateIsEnabled;
	}
	public final void setIsEnabled(boolean value)
	{
		privateIsEnabled = value;
	}

	/** 
	 Gets or sets the enabled users.
	 
	 <value>The enabled users.</value>
	*/
	private java.util.ArrayList<String> privateEnabledUsers;
	public final java.util.ArrayList<String> getEnabledUsers()
	{
		return privateEnabledUsers;
	}
	public final void setEnabledUsers(java.util.ArrayList<String> value)
	{
		privateEnabledUsers = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private LiveTvServiceStatus privateStatus = LiveTvServiceStatus.values()[0];
	public final LiveTvServiceStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(LiveTvServiceStatus value)
	{
		privateStatus = value;
	}

	/** 
	 Gets or sets the status message.
	 
	 <value>The status message.</value>
	*/
	private String privateStatusMessage;
	public final String getStatusMessage()
	{
		return privateStatusMessage;
	}
	public final void setStatusMessage(String value)
	{
		privateStatusMessage = value;
	}

	public LiveTvInfo()
	{
		setServices(new java.util.ArrayList<LiveTvServiceInfo>());
		setEnabledUsers(new java.util.ArrayList<String>());
	}
}