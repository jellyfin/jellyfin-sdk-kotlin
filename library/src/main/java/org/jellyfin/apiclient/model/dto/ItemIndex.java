package org.jellyfin.apiclient.model.dto;

/** 
 Class ItemIndex
*/
public class ItemIndex
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
	 Gets or sets the item count.
	 
	 <value>The item count.</value>
	*/
	private int ItemCount;
	public final int getItemCount()
	{
		return ItemCount;
	}
	public final void setItemCount(int value)
	{
		ItemCount = value;
	}
}