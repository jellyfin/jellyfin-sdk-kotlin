package MediaBrowser.Model.Querying;

import MediaBrowser.Model.Dto.*;

/** 
 Represents the result of a query for items
*/
public class ItemsResult
{
	/** 
	 The set of items returned based on sorting, paging, etc
	 
	 <value>The items.</value>
	*/
	private BaseItemDto[] Items;
	public final BaseItemDto[] getItems()
	{
		return Items;
	}
	public final void setItems(BaseItemDto[] value)
	{
		Items = value;
	}

	/** 
	 The total number of records available
	 
	 <value>The total record count.</value>
	*/
	private int TotalRecordCount;
	public final int getTotalRecordCount()
	{
		return TotalRecordCount;
	}
	public final void setTotalRecordCount(int value)
	{
		TotalRecordCount = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ItemsResult"/> class.
	*/
	public ItemsResult()
	{
		setItems(new BaseItemDto[] { });
	}
}