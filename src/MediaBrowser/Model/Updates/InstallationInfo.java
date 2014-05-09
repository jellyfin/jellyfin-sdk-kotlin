package MediaBrowser.Model.Updates;

/** 
 Class InstallationInfo
*/
public class InstallationInfo
{
	/** 
	 Gets or sets the id.
	 
	 <value>The id.</value>
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
	 Gets or sets the assembly guid.
	 
	 <value>The guid of the assembly.</value>
	*/
	private String privateAssemblyGuid;
	public final String getAssemblyGuid()
	{
		return privateAssemblyGuid;
	}
	public final void setAssemblyGuid(String value)
	{
		privateAssemblyGuid = value;
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
	 Gets or sets the update class.
	 
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
	 Gets or sets the percent complete.
	 
	 <value>The percent complete.</value>
	*/
	private Double privatePercentComplete = new Double();
	public final Double getPercentComplete()
	{
		return privatePercentComplete;
	}
	public final void setPercentComplete(Double value)
	{
		privatePercentComplete = value;
	}
}