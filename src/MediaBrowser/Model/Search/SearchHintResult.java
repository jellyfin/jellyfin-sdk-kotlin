package MediaBrowser.Model.Search;

/** 
 Class SearchHintResult
*/
public class SearchHintResult
{
	/** 
	 Gets or sets the search hints.
	 
	 <value>The search hints.</value>
	*/
	private SearchHint[] SearchHints;
	public final SearchHint[] getSearchHints()
	{
		return SearchHints;
	}
	public final void setSearchHints(SearchHint[] value)
	{
		SearchHints = value;
	}

	/** 
	 Gets or sets the total record count.
	 
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
}