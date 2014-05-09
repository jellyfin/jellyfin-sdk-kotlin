package MediaBrowser.Model.Session;

/** 
 Class BrowseRequest
*/
public class BrowseRequest
{
	/** 
	 Artist, Genre, Studio, Person, or any kind of BaseItem
	 
	 <value>The type of the item.</value>
	*/
	private String privateItemType;
	public final String getItemType()
	{
		return privateItemType;
	}
	public final void setItemType(String value)
	{
		privateItemType = value;
	}

	/** 
	 Gets or sets the item id.
	 
	 <value>The item id.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	/** 
	 Gets or sets the name of the item.
	 
	 <value>The name of the item.</value>
	*/
	private String privateItemName;
	public final String getItemName()
	{
		return privateItemName;
	}
	public final void setItemName(String value)
	{
		privateItemName = value;
	}
}