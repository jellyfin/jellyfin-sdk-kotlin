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
	private SearchHint[] privateSearchHints;
	public final SearchHint[] getSearchHints()
	{
		return privateSearchHints;
	}
	public final void setSearchHints(SearchHint[] value)
	{
		privateSearchHints = value;
	}

	/** 
	 Gets or sets the total record count.
	 
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
}