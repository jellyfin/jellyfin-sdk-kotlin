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
	private java.util.ArrayList<String> FoldersAddedTo;
	public final java.util.ArrayList<String> getFoldersAddedTo()
	{
		return FoldersAddedTo;
	}
	public final void setFoldersAddedTo(java.util.ArrayList<String> value)
	{
		FoldersAddedTo = value;
	}
	/** 
	 Gets or sets the folders removed from.
	 
	 <value>The folders removed from.</value>
	*/
	private java.util.ArrayList<String> FoldersRemovedFrom;
	public final java.util.ArrayList<String> getFoldersRemovedFrom()
	{
		return FoldersRemovedFrom;
	}
	public final void setFoldersRemovedFrom(java.util.ArrayList<String> value)
	{
		FoldersRemovedFrom = value;
	}

	/** 
	 Gets or sets the items added.
	 
	 <value>The items added.</value>
	*/
	private java.util.ArrayList<String> ItemsAdded;
	public final java.util.ArrayList<String> getItemsAdded()
	{
		return ItemsAdded;
	}
	public final void setItemsAdded(java.util.ArrayList<String> value)
	{
		ItemsAdded = value;
	}

	/** 
	 Gets or sets the items removed.
	 
	 <value>The items removed.</value>
	*/
	private java.util.ArrayList<String> ItemsRemoved;
	public final java.util.ArrayList<String> getItemsRemoved()
	{
		return ItemsRemoved;
	}
	public final void setItemsRemoved(java.util.ArrayList<String> value)
	{
		ItemsRemoved = value;
	}

	/** 
	 Gets or sets the items updated.
	 
	 <value>The items updated.</value>
	*/
	private java.util.ArrayList<String> ItemsUpdated;
	public final java.util.ArrayList<String> getItemsUpdated()
	{
		return ItemsUpdated;
	}
	public final void setItemsUpdated(java.util.ArrayList<String> value)
	{
		ItemsUpdated = value;
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