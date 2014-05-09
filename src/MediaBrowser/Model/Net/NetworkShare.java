package MediaBrowser.Model.Net;

public class NetworkShare
{
	/** 
	 The name of the computer that this share belongs to
	*/
	private String privateServer;
	public final String getServer()
	{
		return privateServer;
	}
	public final void setServer(String value)
	{
		privateServer = value;
	}

	/** 
	 Share name
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
	 Local path
	*/
	private String privatePath;
	public final String getPath()
	{
		return privatePath;
	}
	public final void setPath(String value)
	{
		privatePath = value;
	}

	/** 
	 Share type
	*/
	private NetworkShareType privateShareType = NetworkShareType.values()[0];
	public final NetworkShareType getShareType()
	{
		return privateShareType;
	}
	public final void setShareType(NetworkShareType value)
	{
		privateShareType = value;
	}

	/** 
	 Comment
	*/
	private String privateRemark;
	public final String getRemark()
	{
		return privateRemark;
	}
	public final void setRemark(String value)
	{
		privateRemark = value;
	}
}