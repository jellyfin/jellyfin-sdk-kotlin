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
	private BaseItemDto[] privateItems;
	public final BaseItemDto[] getItems()
	{
		return privateItems;
	}
	public final void setItems(BaseItemDto[] value)
	{
		privateItems = value;
	}

	/** 
	 The total number of records available
	 
	 <value>The total record count.</value>
	*/
	private int privateTotalRecordCount;
	public final int getTotalRecordCount()
	{
		return privateTotalRecordCount;
	}
	public final void setTotalRecordCount(int value)
	{
		privateTotalRecordCount = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ItemsResult"/> class.
	*/
	public ItemsResult()
	{
		setItems(new BaseItemDto[] { });
	}
}