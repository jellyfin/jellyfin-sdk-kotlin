package mediabrowser.model.apiclient;

public class ServerDiscoveryInfo
{
	/** 
	 Gets or sets the address.
	 
	 <value>The address.</value>
	*/
	private String Address;
	public final String getAddress()
	{
		return Address;
	}
	public final void setAddress(String value)
	{
		Address = value;
	}
	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
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
	 Gets or sets the endpoint address.
	 
	 <value>The endpoint address.</value>
	*/
	private String EndpointAddress;
	public final String getEndpointAddress()
	{
		return EndpointAddress;
	}
	public final void setEndpointAddress(String value)
	{
		EndpointAddress = value;
	}
}