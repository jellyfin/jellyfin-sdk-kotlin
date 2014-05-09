package MediaBrowser.Model.Providers;

public class ExternalUrl
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
	 Gets or sets the type of the item.
	 
	 <value>The type of the item.</value>
	*/
	private String privateUrl;
	public final String getUrl()
	{
		return privateUrl;
	}
	public final void setUrl(String value)
	{
		privateUrl = value;
	}
}