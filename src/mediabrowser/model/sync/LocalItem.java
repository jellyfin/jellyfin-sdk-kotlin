package mediabrowser.model.sync;

import mediabrowser.model.dto.*;

public class LocalItem
{
	/** 
	 Gets or sets the item.
	 
	 <value>The item.</value>
	*/
	private BaseItemDto Item;
	public final BaseItemDto getItem()
	{
		return Item;
	}
	public final void setItem(BaseItemDto value)
	{
		Item = value;
	}
	/** 
	 Gets or sets the local path.
	 
	 <value>The local path.</value>
	*/
	private String LocalPath;
	public final String getLocalPath()
	{
		return LocalPath;
	}
	public final void setLocalPath(String value)
	{
		LocalPath = value;
	}
	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}
	/** 
	 Gets or sets the unique identifier.
	 
	 <value>The unique identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
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
}