package MediaBrowser.Model.Channels;

public class ChannelInfo
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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets the home page URL.
	 
	 <value>The home page URL.</value>
	*/
	private String HomePageUrl;
	public final String getHomePageUrl()
	{
		return HomePageUrl;
	}
	public final void setHomePageUrl(String value)
	{
		HomePageUrl = value;
	}

	/** 
	 Gets or sets the features.
	 
	 <value>The features.</value>
	*/
	private ChannelFeatures Features;
	public final ChannelFeatures getFeatures()
	{
		return Features;
	}
	public final void setFeatures(ChannelFeatures value)
	{
		Features = value;
	}
}