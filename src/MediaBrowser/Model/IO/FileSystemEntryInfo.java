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
	 Gets or sets the path.
	 
	 <value>The path.</value>
	*/
	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private FileSystemEntryType Type = FileSystemEntryType.values()[0];
	public final FileSystemEntryType getType()
	{
		return Type;
	}
	public final void setType(FileSystemEntryType value)
	{
		Type = value;
	}
}