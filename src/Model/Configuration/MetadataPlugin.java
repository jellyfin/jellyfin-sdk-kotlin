package MediaBrowser.Model.Configuration;

public class MetadataPlugin
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
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private MetadataPluginType privateType = MetadataPluginType.values()[0];
	public final MetadataPluginType getType()
	{
		return privateType;
	}
	public final void setType(MetadataPluginType value)
	{
		privateType = value;
	}
}