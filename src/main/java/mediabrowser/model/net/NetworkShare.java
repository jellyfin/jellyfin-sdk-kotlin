package mediabrowser.model.net;

public class NetworkShare
{
	/** 
	 The name of the computer that this share belongs to
	*/
	private String Server;
	public final String getServer()
	{
		return Server;
	}
	public final void setServer(String value)
	{
		Server = value;
	}

	/** 
	 Share name
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
	 Local path
	*/
	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
	}

	/** 
	 Share type
	*/
	private NetworkShareType ShareType = NetworkShareType.values()[0];
	public final NetworkShareType getShareType()
	{
		return ShareType;
	}
	public final void setShareType(NetworkShareType value)
	{
		ShareType = value;
	}

	/** 
	 Comment
	*/
	private String Remark;
	public final String getRemark()
	{
		return Remark;
	}
	public final void setRemark(String value)
	{
		Remark = value;
	}
}