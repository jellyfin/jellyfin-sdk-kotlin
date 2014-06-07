package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Entities.*;

public class MetadataPluginSummary
{
	/** 
	 Gets or sets the type of the item.
	 
	 <value>The type of the item.</value>
	*/
	private String privateItemType;
	public final String getItemType()
	{
		return privateItemType;
	}
	public final void setItemType(String value)
	{
		privateItemType = value;
	}

	/** 
	 Gets or sets the plugins.
	 
	 <value>The plugins.</value>
	*/
	private java.util.ArrayList<MetadataPlugin> privatePlugins;
	public final java.util.ArrayList<MetadataPlugin> getPlugins()
	{
		return privatePlugins;
	}
	public final void setPlugins(java.util.ArrayList<MetadataPlugin> value)
	{
		privatePlugins = value;
	}

	/** 
	 Gets or sets the supported image types.
	 
	 <value>The supported image types.</value>
	*/
	private java.util.ArrayList<ImageType> privateSupportedImageTypes;
	public final java.util.ArrayList<ImageType> getSupportedImageTypes()
	{
		return privateSupportedImageTypes;
	}
	public final void setSupportedImageTypes(java.util.ArrayList<ImageType> value)
	{
		privateSupportedImageTypes = value;
	}

	public MetadataPluginSummary()
	{
		setSupportedImageTypes(new java.util.ArrayList<ImageType>());
		setPlugins(new java.util.ArrayList<MetadataPlugin>());
	}
}