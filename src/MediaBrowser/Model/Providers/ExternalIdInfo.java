package MediaBrowser.Model.Providers;

public class ExternalIdInfo
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
	 Gets or sets the key.
	 
	 <value>The key.</value>
	*/
	private String privateKey;
	public final String getKey()
	{
		return privateKey;
	}
	public final void setKey(String value)
	{
		privateKey = value;
	}

	/** 
	 Gets or sets the URL format string.
	 
	 <value>The URL format string.</value>
	*/
	private String privateUrlFormatString;
	public final String getUrlFormatString()
	{
		return privateUrlFormatString;
	}
	public final void setUrlFormatString(String value)
	{
		privateUrlFormatString = value;
	}
}