package org.jellyfin.apiclient.model.session;

/** 
 Class BrowseRequest
*/
public class BrowseRequest
{
	/** 
	 Artist, Genre, Studio, Person, or any kind of BaseItem
	 
	 <value>The type of the item.</value>
	*/
	private String ItemType;
	public final String getItemType()
	{
		return ItemType;
	}
	public final void setItemType(String value)
	{
		ItemType = value;
	}

	/** 
	 Gets or sets the item id.
	 
	 <value>The item id.</value>
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
	 Gets or sets the name of the item.
	 
	 <value>The name of the item.</value>
	*/
	private String ItemName;
	public final String getItemName()
	{
		return ItemName;
	}
	public final void setItemName(String value)
	{
		ItemName = value;
	}
}