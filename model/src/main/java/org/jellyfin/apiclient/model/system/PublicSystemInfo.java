package org.jellyfin.apiclient.model.system;

public class PublicSystemInfo
{
	/** 
	 Gets or sets the local address.
	 
	 <value>The local address.</value>
	*/
	private String LocalAddress;
	public final String getLocalAddress()
	{
		return LocalAddress;
	}
	public final void setLocalAddress(String value)
	{
		LocalAddress = value;
	}

	/** 
	 Gets or sets the wan address.
	 
	 <value>The wan address.</value>
	*/
	private String WanAddress;
	public final String getWanAddress()
	{
		return WanAddress;
	}
	public final void setWanAddress(String value)
	{
		WanAddress = value;
	}

	/** 
	 Gets or sets the name of the server.
	 
	 <value>The name of the server.</value>
	*/
	private String ServerName;
	public final String getServerName()
	{
		return ServerName;
	}
	public final void setServerName(String value)
	{
		ServerName = value;
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
	 Gets or sets the operating sytem.
	 
	 <value>The operating sytem.</value>
	*/
	private String OperatingSystem;
	public final String getOperatingSystem()
	{
		return OperatingSystem;
	}
	public final void setOperatingSystem(String value)
	{
		OperatingSystem = value;
	}

	/** 
	 Gets or sets the id.
	 
	 <value>The id.</value>
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
}