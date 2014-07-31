package MediaBrowser.Model.ApiClient;

public class ServerDiscoveryInfo
{
	/** 
	 Gets or sets the address.
	 
	 <value>The address.</value>
	*/
	private String privateAddress;
	public final String getAddress()
	{
		return privateAddress;
	}
	public final void setAddress(String value)
	{
		privateAddress = value;
	}
	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
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
}