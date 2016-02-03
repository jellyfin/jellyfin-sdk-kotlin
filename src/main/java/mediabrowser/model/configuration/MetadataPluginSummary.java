package mediabrowser.model.configuration;

import mediabrowser.model.entities.*;

public class MetadataPluginSummary
{
	/** 
	 Gets or sets the type of the item.
	 
	 <value>The type of the item.</value>
	*/
	private String ItemType;
	public final String getItemType()
	{
		return ItemType;
	}
	public final void setItemType(String value)
	{
		ItemType = value;
	}

	/** 
	 Gets or sets the plugins.
	 
	 <value>The plugins.</value>
	*/
	private java.util.ArrayList<MetadataPlugin> Plugins;
	public final java.util.ArrayList<MetadataPlugin> getPlugins()
	{
		return Plugins;
	}
	public final void setPlugins(java.util.ArrayList<MetadataPlugin> value)
	{
		Plugins = value;
	}

	/** 
	 Gets or sets the supported image types.
	 
	 <value>The supported image types.</value>
	*/
	private java.util.ArrayList<ImageType> SupportedImageTypes;
	public final java.util.ArrayList<ImageType> getSupportedImageTypes()
	{
		return SupportedImageTypes;
	}
	public final void setSupportedImageTypes(java.util.ArrayList<ImageType> value)
	{
		SupportedImageTypes = value;
	}

	public MetadataPluginSummary()
	{
		setSupportedImageTypes(new java.util.ArrayList<ImageType>());
		setPlugins(new java.util.ArrayList<MetadataPlugin>());
	}
}