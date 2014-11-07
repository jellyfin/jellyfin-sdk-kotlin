package mediabrowser.model.themes;

public class ThemeImage
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
	 Gets or sets the cache tag.
	 
	 <value>The cache tag.</value>
	*/
	private String CacheTag;
	public final String getCacheTag()
	{
		return CacheTag;
	}
	public final void setCacheTag(String value)
	{
		CacheTag = value;
	}
}