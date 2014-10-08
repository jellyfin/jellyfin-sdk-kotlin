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
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	/** 
	 Gets or sets the home page URL.
	 
	 <value>The home page URL.</value>
	*/
	private String HomePageUrl;
	public final String getHomePageUrl()
	{
		return HomePageUrl;
	}
	public final void setHomePageUrl(String value)
	{
		HomePageUrl = value;
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

	/** 
	 Gets or sets the version.
	 
	 <value>The version.</value>
	*/
	private String Version;
	public final String getVersion()
	{
		return Version;
	}
	public final void setVersion(String value)
	{
		Version = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has update available.
	 
	 <value><c>true</c> if this instance has update available; otherwise, <c>false</c>.</value>
	*/
	private boolean HasUpdateAvailable;
	public final boolean getHasUpdateAvailable()
	{
		return HasUpdateAvailable;
	}
	public final void setHasUpdateAvailable(boolean value)
	{
		HasUpdateAvailable = value;
	}

	private java.util.ArrayList<LiveTvTunerInfoDto> Tuners;
	public final java.util.ArrayList<LiveTvTunerInfoDto> getTuners()
	{
		return Tuners;
	}
	public final void setTuners(java.util.ArrayList<LiveTvTunerInfoDto> value)
	{
		Tuners = value;
	}

	public LiveTvServiceInfo()
	{
		setTuners(new java.util.ArrayList<LiveTvTunerInfoDto>());
	}
}