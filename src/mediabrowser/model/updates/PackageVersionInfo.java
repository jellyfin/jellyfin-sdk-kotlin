package mediabrowser.model.updates;

/** 
 Class PackageVersionInfo
*/
public class PackageVersionInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String name;
	public final String getname()
	{
		return name;
	}
	public final void setname(String value)
	{
		name = value;
	}

	/** 
	 Gets or sets the guid.
	 
	 <value>The guid.</value>
	*/
	private String guid;
	public final String getguid()
	{
		return guid;
	}
	public final void setguid(String value)
	{
		guid = value;
	}

	/** 
	 Gets or sets the version STR.
	 
	 <value>The version STR.</value>
	*/
	private String versionStr;
	public final String getversionStr()
	{
		return versionStr;
	}
	public final void setversionStr(String value)
	{
		versionStr = value;
	}

	/** 
	 The _version
	*/
	private String _version;
	/** 
	 Gets or sets the version.
	 Had to make this an interpreted property since Protobuf can't handle Version
	 
	 <value>The version.</value>
	*/
    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //ORIGINAL LINE: [IgnoreDataMember] public Version version
	public final String getversion()
	{
		return _version;
	}

	/** 
	 Values the or default.
	 
	 @param str The STR.
	 @param def The def.
	 @return System.String.
	*/
	private static String ValueOrDefault(String str, String def)
	{
		return tangible.DotNetToJavaStringHelper.isNullOrEmpty(str) ? def : str;
	}

	/** 
	 Gets or sets the classification.
	 
	 <value>The classification.</value>
	*/
	private PackageVersionClass classification = PackageVersionClass.values()[0];
	public final PackageVersionClass getclassification()
	{
		return classification;
	}
	public final void setclassification(PackageVersionClass value)
	{
		classification = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String description;
	public final String getdescription()
	{
		return description;
	}
	public final void setdescription(String value)
	{
		description = value;
	}

	/** 
	 Gets or sets the required version STR.
	 
	 <value>The required version STR.</value>
	*/
	private String requiredVersionStr;
	public final String getrequiredVersionStr()
	{
		return requiredVersionStr;
	}
	public final void setrequiredVersionStr(String value)
	{
		requiredVersionStr = value;
	}

	/** 
	 Gets or sets the source URL.
	 
	 <value>The source URL.</value>
	*/
	private String sourceUrl;
	public final String getsourceUrl()
	{
		return sourceUrl;
	}
	public final void setsourceUrl(String value)
	{
		sourceUrl = value;
	}

	/** 
	 Gets or sets the source URL.
	 
	 <value>The source URL.</value>
	*/
	private String checksum;
	public final String getchecksum()
	{
		return checksum;
	}
	public final void setchecksum(String value)
	{
		checksum = value;
	}

	/** 
	 Gets or sets the target filename.
	 
	 <value>The target filename.</value>
	*/
	private String targetFilename;
	public final String gettargetFilename()
	{
		return targetFilename;
	}
	public final void settargetFilename(String value)
	{
		targetFilename = value;
	}
}