package MediaBrowser.Model.Themes;

public class ThemeImage
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
	 Gets or sets the cache tag.
	 
	 <value>The cache tag.</value>
	*/
	private String privateCacheTag;
	public final String getCacheTag()
	{
		return privateCacheTag;
	}
	public final void setCacheTag(String value)
	{
		privateCacheTag = value;
	}
}