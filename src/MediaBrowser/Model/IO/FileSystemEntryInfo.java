package MediaBrowser.Model.IO;

/** 
 Class FileSystemEntryInfo
*/
public class FileSystemEntryInfo
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
	 Gets or sets the path.
	 
	 <value>The path.</value>
	*/
	private String privatePath;
	public final String getPath()
	{
		return privatePath;
	}
	public final void setPath(String value)
	{
		privatePath = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private FileSystemEntryType privateType = FileSystemEntryType.values()[0];
	public final FileSystemEntryType getType()
	{
		return privateType;
	}
	public final void setType(FileSystemEntryType value)
	{
		privateType = value;
	}
}