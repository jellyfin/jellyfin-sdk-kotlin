package MediaBrowser.Model.Plugins;

import MediaBrowser.Model.Updates.*;

/** 
 This is a serializable stub class that is used by the api to provide information about installed plugins.
*/
public class PluginInfo
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
	 Gets or sets the configuration date last modified.
	 
	 <value>The configuration date last modified.</value>
	*/
	private java.util.Date privateConfigurationDateLastModified = new java.util.Date(0);
	public final java.util.Date getConfigurationDateLastModified()
	{
		return privateConfigurationDateLastModified;
	}
	public final void setConfigurationDateLastModified(java.util.Date value)
	{
		privateConfigurationDateLastModified = value;
	}

	/** 
	 Gets or sets the version.
	 
	 <value>The version.</value>
	*/
	private String privateVersion;
	public final String getVersion()
	{
		return privateVersion;
	}
	public final void setVersion(String value)
	{
		privateVersion = value;
	}

	/** 
	 Gets or sets the name of the assembly file.
	 
	 <value>The name of the assembly file.</value>
	*/
	private String privateAssemblyFileName;
	public final String getAssemblyFileName()
	{
		return privateAssemblyFileName;
	}
	public final void setAssemblyFileName(String value)
	{
		privateAssemblyFileName = value;
	}

	/** 
	 Gets or sets the name of the configuration file.
	 
	 <value>The name of the configuration file.</value>
	*/
	private String privateConfigurationFileName;
	public final String getConfigurationFileName()
	{
		return privateConfigurationFileName;
	}
	public final void setConfigurationFileName(String value)
	{
		privateConfigurationFileName = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String privateDescription;
	public final String getDescription()
	{
		return privateDescription;
	}
	public final void setDescription(String value)
	{
		privateDescription = value;
	}

	/** 
	 Gets or sets the unique id.
	 
	 <value>The unique id.</value>
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
	 Whether or not this plug-in should be automatically updated when a
	 compatible new version is released
	 
	 <value><c>true</c> if [enable auto update]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableAutoUpdate;
	public final boolean getEnableAutoUpdate()
	{
		return privateEnableAutoUpdate;
	}
	public final void setEnableAutoUpdate(boolean value)
	{
		privateEnableAutoUpdate = value;
	}

	/** 
	 The classification of updates to which to subscribe.
	 Options are: Dev, Beta or Release
	 
	 <value>The update class.</value>
	*/
	private PackageVersionClass privateUpdateClass = PackageVersionClass.values()[0];
	public final PackageVersionClass getUpdateClass()
	{
		return privateUpdateClass;
	}
	public final void setUpdateClass(PackageVersionClass value)
	{
		privateUpdateClass = value;
	}

	/** 
	 Gets or sets the minimum required UI version.
	 
	 <value>The minimum required UI version.</value>
	*/
	private String privateMinimumRequiredUIVersion;
	public final String getMinimumRequiredUIVersion()
	{
		return privateMinimumRequiredUIVersion;
	}
	public final void setMinimumRequiredUIVersion(String value)
	{
		privateMinimumRequiredUIVersion = value;
	}
}