package MediaBrowser.Model.Channels;

public class ChannelInfo
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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets the home page URL.
	 
	 <value>The home page URL.</value>
	*/
	private String privateHomePageUrl;
	public final String getHomePageUrl()
	{
		return privateHomePageUrl;
	}
	public final void setHomePageUrl(String value)
	{
		privateHomePageUrl = value;
	}

	/** 
	 Gets or sets the features.
	 
	 <value>The features.</value>
	*/
	private ChannelFeatures privateFeatures;
	public final ChannelFeatures getFeatures()
	{
		return privateFeatures;
	}
	public final void setFeatures(ChannelFeatures value)
	{
		privateFeatures = value;
	}
}