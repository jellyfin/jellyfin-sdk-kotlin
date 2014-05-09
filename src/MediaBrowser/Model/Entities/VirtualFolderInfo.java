package MediaBrowser.Model.Entities;

/** 
 Used to hold information about a user's list of configured virtual folders
*/
public class VirtualFolderInfo
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
	 Gets or sets the locations.
	 
	 <value>The locations.</value>
	*/
	private java.util.ArrayList<String> privateLocations;
	public final java.util.ArrayList<String> getLocations()
	{
		return privateLocations;
	}
	public final void setLocations(java.util.ArrayList<String> value)
	{
		privateLocations = value;
	}

	/** 
	 Gets or sets the type of the collection.
	 
	 <value>The type of the collection.</value>
	*/
	private String privateCollectionType;
	public final String getCollectionType()
	{
		return privateCollectionType;
	}
	public final void setCollectionType(String value)
	{
		privateCollectionType = value;
	}

	/** 
	 Initializes a new instance of the <see cref="VirtualFolderInfo"/> class.
	*/
	public VirtualFolderInfo()
	{
		setLocations(new java.util.ArrayList<String>());
	}
}