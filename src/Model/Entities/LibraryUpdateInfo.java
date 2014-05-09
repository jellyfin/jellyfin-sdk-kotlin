package MediaBrowser.Model.Entities;

/** 
 Class LibraryUpdateInfo
*/
public class LibraryUpdateInfo
{
	/** 
	 Gets or sets the folders added to.
	 
	 <value>The folders added to.</value>
	*/
	private java.util.ArrayList<String> privateFoldersAddedTo;
	public final java.util.ArrayList<String> getFoldersAddedTo()
	{
		return privateFoldersAddedTo;
	}
	public final void setFoldersAddedTo(java.util.ArrayList<String> value)
	{
		privateFoldersAddedTo = value;
	}
	/** 
	 Gets or sets the folders removed from.
	 
	 <value>The folders removed from.</value>
	*/
	private java.util.ArrayList<String> privateFoldersRemovedFrom;
	public final java.util.ArrayList<String> getFoldersRemovedFrom()
	{
		return privateFoldersRemovedFrom;
	}
	public final void setFoldersRemovedFrom(java.util.ArrayList<String> value)
	{
		privateFoldersRemovedFrom = value;
	}

	/** 
	 Gets or sets the items added.
	 
	 <value>The items added.</value>
	*/
	private java.util.ArrayList<String> privateItemsAdded;
	public final java.util.ArrayList<String> getItemsAdded()
	{
		return privateItemsAdded;
	}
	public final void setItemsAdded(java.util.ArrayList<String> value)
	{
		privateItemsAdded = value;
	}

	/** 
	 Gets or sets the items removed.
	 
	 <value>The items removed.</value>
	*/
	private java.util.ArrayList<String> privateItemsRemoved;
	public final java.util.ArrayList<String> getItemsRemoved()
	{
		return privateItemsRemoved;
	}
	public final void setItemsRemoved(java.util.ArrayList<String> value)
	{
		privateItemsRemoved = value;
	}

	/** 
	 Gets or sets the items updated.
	 
	 <value>The items updated.</value>
	*/
	private java.util.ArrayList<String> privateItemsUpdated;
	public final java.util.ArrayList<String> getItemsUpdated()
	{
		return privateItemsUpdated;
	}
	public final void setItemsUpdated(java.util.ArrayList<String> value)
	{
		privateItemsUpdated = value;
	}

	/** 
	 Initializes a new instance of the <see cref="LibraryUpdateInfo"/> class.
	*/
	public LibraryUpdateInfo()
	{
		setFoldersAddedTo(new java.util.ArrayList<String>());
		setFoldersRemovedFrom(new java.util.ArrayList<String>());
		setItemsAdded(new java.util.ArrayList<String>());
		setItemsRemoved(new java.util.ArrayList<String>());
		setItemsUpdated(new java.util.ArrayList<String>());
	}
}