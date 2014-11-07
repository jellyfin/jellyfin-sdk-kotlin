package mediabrowser.model.providers;

public class ExternalIdInfo
{
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
	 Gets or sets the key.
	 
	 <value>The key.</value>
	*/
	private String Key;
	public final String getKey()
	{
		return Key;
	}
	public final void setKey(String value)
	{
		Key = value;
	}

	/** 
	 Gets or sets the URL format string.
	 
	 <value>The URL format string.</value>
	*/
	private String UrlFormatString;
	public final String getUrlFormatString()
	{
		return UrlFormatString;
	}
	public final void setUrlFormatString(String value)
	{
		UrlFormatString = value;
	}
}