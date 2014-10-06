package MediaBrowser.Model.System;

import MediaBrowser.Model.Updates.*;

public class PublicSystemInfo
{
	/** 
	 Gets or sets the local address.
	 
	 <value>The local address.</value>
	*/
	private String privateLocalAddress;
	public final String getLocalAddress()
	{
		return privateLocalAddress;
	}
	public final void setLocalAddress(String value)
	{
		privateLocalAddress = value;
	}

	/** 
	 Gets or sets the wan address.
	 
	 <value>The wan address.</value>
	*/
	private String privateWanAddress;
	public final String getWanAddress()
	{
		return privateWanAddress;
	}
	public final void setWanAddress(String value)
	{
		privateWanAddress = value;
	}

	/** 
	 Gets or sets the name of the server.
	 
	 <value>The name of the server.</value>
	*/
	private String privateServerName;
	public final String getServerName()
	{
		return privateServerName;
	}
	public final void setServerName(String value)
	{
		privateServerName = value;
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
	 Gets or sets the id.
	 
	 <value>The id.</value>
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
}