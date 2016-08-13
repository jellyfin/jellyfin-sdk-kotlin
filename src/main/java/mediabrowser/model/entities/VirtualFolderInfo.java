package mediabrowser.model.entities;

import mediabrowser.model.configuration.*;

/** 
 Used to hold information about a user's list of configured virtual folders
*/
public class VirtualFolderInfo
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
	 Gets or sets the locations.
	 
	 <value>The locations.</value>
	*/
	private java.util.ArrayList<String> Locations;
	public final java.util.ArrayList<String> getLocations()
	{
		return Locations;
	}
	public final void setLocations(java.util.ArrayList<String> value)
	{
		Locations = value;
	}

	/** 
	 Gets or sets the type of the collection.
	 
	 <value>The type of the collection.</value>
	*/
	private String CollectionType;
	public final String getCollectionType()
	{
		return CollectionType;
	}
	public final void setCollectionType(String value)
	{
		CollectionType = value;
	}

	private LibraryOptions LibraryOptions;
	public final LibraryOptions getLibraryOptions()
	{
		return LibraryOptions;
	}
	public final void setLibraryOptions(LibraryOptions value)
	{
		LibraryOptions = value;
	}

	/** 
	 Initializes a new instance of the <see cref="VirtualFolderInfo"/> class.
	*/
	public VirtualFolderInfo()
	{
		setLocations(new java.util.ArrayList<String>());
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}

	/** 
	 Gets or sets the primary image item identifier.
	 
	 <value>The primary image item identifier.</value>
	*/
	private String PrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return PrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		PrimaryImageItemId = value;
	}
}