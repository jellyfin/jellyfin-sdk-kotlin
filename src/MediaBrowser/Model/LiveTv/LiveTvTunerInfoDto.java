package MediaBrowser.Model.LiveTv;

public class LiveTvTunerInfoDto
{
	/** 
	 Gets or sets the type of the source.
	 
	 <value>The type of the source.</value>
	*/
	private String privateSourceType;
	public final String getSourceType()
	{
		return privateSourceType;
	}
	public final void setSourceType(String value)
	{
		privateSourceType = value;
	}

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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private LiveTvTunerStatus privateStatus = LiveTvTunerStatus.values()[0];
	public final LiveTvTunerStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(LiveTvTunerStatus value)
	{
		privateStatus = value;
	}

	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String privateChannelId;
	public final String getChannelId()
	{
		return privateChannelId;
	}
	public final void setChannelId(String value)
	{
		privateChannelId = value;
	}

	/** 
	 Gets or sets the name of the channel.
	 
	 <value>The name of the channel.</value>
	*/
	private String privateChannelName;
	public final String getChannelName()
	{
		return privateChannelName;
	}
	public final void setChannelName(String value)
	{
		privateChannelName = value;
	}

	/** 
	 Gets or sets the recording identifier.
	 
	 <value>The recording identifier.</value>
	*/
	private String privateRecordingId;
	public final String getRecordingId()
	{
		return privateRecordingId;
	}
	public final void setRecordingId(String value)
	{
		privateRecordingId = value;
	}

	/** 
	 Gets or sets the name of the program.
	 
	 <value>The name of the program.</value>
	*/
	private String privateProgramName;
	public final String getProgramName()
	{
		return privateProgramName;
	}
	public final void setProgramName(String value)
	{
		privateProgramName = value;
	}

	/** 
	 Gets or sets the clients.
	 
	 <value>The clients.</value>
	*/
	private java.util.ArrayList<String> privateClients;
	public final java.util.ArrayList<String> getClients()
	{
		return privateClients;
	}
	public final void setClients(java.util.ArrayList<String> value)
	{
		privateClients = value;
	}

	public LiveTvTunerInfoDto()
	{
		setClients(new java.util.ArrayList<String>());
	}
}