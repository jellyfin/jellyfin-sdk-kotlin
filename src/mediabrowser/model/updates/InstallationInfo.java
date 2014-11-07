package mediabrowser.model.updates;

/** 
 Class InstallationInfo
*/
public class InstallationInfo
{
	/** 
	 Gets or sets the id.
	 
	 <value>The id.</value>
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
	 Gets or sets the assembly guid.
	 
	 <value>The guid of the assembly.</value>
	*/
	private String AssemblyGuid;
	public final String getAssemblyGuid()
	{
		return AssemblyGuid;
	}
	public final void setAssemblyGuid(String value)
	{
		AssemblyGuid = value;
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
	 Gets or sets the update class.
	 
	 <value>The update class.</value>
	*/
	private PackageVersionClass UpdateClass = PackageVersionClass.values()[0];
	public final PackageVersionClass getUpdateClass()
	{
		return UpdateClass;
	}
	public final void setUpdateClass(PackageVersionClass value)
	{
		UpdateClass = value;
	}

	/** 
	 Gets or sets the percent complete.
	 
	 <value>The percent complete.</value>
	*/
	private Double PercentComplete = null;
	public final Double getPercentComplete()
	{
		return PercentComplete;
	}
	public final void setPercentComplete(Double value)
	{
		PercentComplete = value;
	}
}