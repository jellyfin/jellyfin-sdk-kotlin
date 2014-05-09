package MediaBrowser.Model.LiveTv;

/** 
 Class ServiceInfo
*/
public class LiveTvServiceInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the home page URL.
	 
	 <value>The home page URL.</value>
	*/
	private String privateHomePageUrl;
	public final String getHomePageUrl()
	{
		return privateHomePageUrl;
	}
	public final void setHomePageUrl(String value)
	{
		privateHomePageUrl = value;
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

	/** 
	 Gets or sets the version.
	 
	 <value>The version.</value>
	*/
	private String privateVersion;
	public final String getVersion()
	{
		return privateVersion;
	}
	public final void setVersion(String value)
	{
		privateVersion = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has update available.
	 
	 <value><c>true</c> if this instance has update available; otherwise, <c>false</c>.</value>
	*/
	private boolean privateHasUpdateAvailable;
	public final boolean getHasUpdateAvailable()
	{
		return privateHasUpdateAvailable;
	}
	public final void setHasUpdateAvailable(boolean value)
	{
		privateHasUpdateAvailable = value;
	}

	private java.util.ArrayList<LiveTvTunerInfoDto> privateTuners;
	public final java.util.ArrayList<LiveTvTunerInfoDto> getTuners()
	{
		return privateTuners;
	}
	public final void setTuners(java.util.ArrayList<LiveTvTunerInfoDto> value)
	{
		privateTuners = value;
	}

	public LiveTvServiceInfo()
	{
		setTuners(new java.util.ArrayList<LiveTvTunerInfoDto>());
	}
}