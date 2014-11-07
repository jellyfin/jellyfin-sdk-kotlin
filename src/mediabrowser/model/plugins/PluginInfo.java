package mediabrowser.model.plugins;

import mediabrowser.model.updates.*;

/** 
 This is a serializable stub class that is used by the api to provide information about installed plugins.
*/
public class PluginInfo
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
	 Gets or sets the configuration date last modified.
	 
	 <value>The configuration date last modified.</value>
	*/
	private java.util.Date ConfigurationDateLastModified = new java.util.Date(0);
	public final java.util.Date getConfigurationDateLastModified()
	{
		return ConfigurationDateLastModified;
	}
	public final void setConfigurationDateLastModified(java.util.Date value)
	{
		ConfigurationDateLastModified = value;
	}

	/** 
	 Gets or sets the version.
	 
	 <value>The version.</value>
	*/
	private String Version;
	public final String getVersion()
	{
		return Version;
	}
	public final void setVersion(String value)
	{
		Version = value;
	}

	/** 
	 Gets or sets the name of the assembly file.
	 
	 <value>The name of the assembly file.</value>
	*/
	private String AssemblyFileName;
	public final String getAssemblyFileName()
	{
		return AssemblyFileName;
	}
	public final void setAssemblyFileName(String value)
	{
		AssemblyFileName = value;
	}

	/** 
	 Gets or sets the name of the configuration file.
	 
	 <value>The name of the configuration file.</value>
	*/
	private String ConfigurationFileName;
	public final String getConfigurationFileName()
	{
		return ConfigurationFileName;
	}
	public final void setConfigurationFileName(String value)
	{
		ConfigurationFileName = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}

	/** 
	 Gets or sets the unique id.
	 
	 <value>The unique id.</value>
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
}