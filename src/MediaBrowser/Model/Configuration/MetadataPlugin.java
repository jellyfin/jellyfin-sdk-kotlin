package MediaBrowser.Model.Configuration;

public class MetadataPlugin
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
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private MetadataPluginType Type = MetadataPluginType.values()[0];
	public final MetadataPluginType getType()
	{
		return Type;
	}
	public final void setType(MetadataPluginType value)
	{
		Type = value;
	}
}