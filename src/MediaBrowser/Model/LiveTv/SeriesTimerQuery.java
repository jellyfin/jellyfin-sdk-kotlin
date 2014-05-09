package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Entities.*;

public class SeriesTimerQuery
{
	/** 
	 Gets or sets the sort by - SortName, Priority
	 
	 <value>The sort by.</value>
	*/
	private String privateSortBy;
	public final String getSortBy()
	{
		return privateSortBy;
	}
	public final void setSortBy(String value)
	{
		privateSortBy = value;
	}

	/** 
	 Gets or sets the sort order.
	 
	 <value>The sort order.</value>
	*/
	private SortOrder privateSortOrder = getSortOrder().values()[0];
	public final SortOrder getSortOrder()
	{
		return privateSortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		privateSortOrder = value;
	}
}