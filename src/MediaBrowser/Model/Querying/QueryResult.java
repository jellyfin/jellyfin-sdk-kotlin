package MediaBrowser.Model.Querying;

public class QueryResult<T>
{
	/** 
	 Gets or sets the items.
	 
	 <value>The items.</value>
	*/
	private T[] privateItems;
	public final T[] getItems()
	{
		return privateItems;
	}
	public final void setItems(T[] value)
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
	 Initializes a new instance of the <see cref="ItemsResult" /> class.
	*/
	public QueryResult()
	{
		setItems(new T[] { });
	}
}