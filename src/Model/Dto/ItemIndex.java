package MediaBrowser.Model.Dto;

/** 
 Class ItemIndex
*/
public class ItemIndex
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
	 Gets or sets the item count.
	 
	 <value>The item count.</value>
	*/
	private int privateItemCount;
	public final int getItemCount()
	{
		return privateItemCount;
	}
	public final void setItemCount(int value)
	{
		privateItemCount = value;
	}
}