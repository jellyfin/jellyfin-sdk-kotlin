package MediaBrowser.Model.LiveTv;

public class LiveTvTunerInfoDto
{
	/** 
	 Gets or sets the type of the source.
	 
	 <value>The type of the source.</value>
	*/
	private String SourceType;
	public final String getSourceType()
	{
		return SourceType;
	}
	public final void setSourceType(String value)
	{
		SourceType = value;
	}

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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private LiveTvTunerStatus Status = LiveTvTunerStatus.values()[0];
	public final LiveTvTunerStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(LiveTvTunerStatus value)
	{
		Status = value;
	}

	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String ChannelId;
	public final String getChannelId()
	{
		return ChannelId;
	}
	public final void setChannelId(String value)
	{
		ChannelId = value;
	}

	/** 
	 Gets or sets the name of the channel.
	 
	 <value>The name of the channel.</value>
	*/
	private String ChannelName;
	public final String getChannelName()
	{
		return ChannelName;
	}
	public final void setChannelName(String value)
	{
		ChannelName = value;
	}

	/** 
	 Gets or sets the recording identifier.
	 
	 <value>The recording identifier.</value>
	*/
	private String RecordingId;
	public final String getRecordingId()
	{
		return RecordingId;
	}
	public final void setRecordingId(String value)
	{
		RecordingId = value;
	}

	/** 
	 Gets or sets the name of the program.
	 
	 <value>The name of the program.</value>
	*/
	private String ProgramName;
	public final String getProgramName()
	{
		return ProgramName;
	}
	public final void setProgramName(String value)
	{
		ProgramName = value;
	}

	/** 
	 Gets or sets the clients.
	 
	 <value>The clients.</value>
	*/
	private java.util.ArrayList<String> Clients;
	public final java.util.ArrayList<String> getClients()
	{
		return Clients;
	}
	public final void setClients(java.util.ArrayList<String> value)
	{
		Clients = value;
	}

	public LiveTvTunerInfoDto()
	{
		setClients(new java.util.ArrayList<String>());
	}
}