package mediabrowser.model.querying;

public class QueryResult<T>
{
	/** 
	 Gets or sets the items.
	 
	 <value>The items.</value>
	*/
	private T[] Items;
	public final T[] getItems()
	{
		return Items;
	}
	public final void setItems(T[] value)
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
	 Initializes a new instance of the <see cref="ItemsResult" /> class.
	*/
	public QueryResult()
	{

	}
}