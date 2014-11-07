package mediabrowser.model.livetv;

import mediabrowser.model.entities.*;

public class SeriesTimerQuery
{
	/** 
	 Gets or sets the sort by - SortName, Priority
	 
	 <value>The sort by.</value>
	*/
	private String SortBy;
	public final String getSortBy()
	{
		return SortBy;
	}
	public final void setSortBy(String value)
	{
		SortBy = value;
	}

	/** 
	 Gets or sets the sort order.
	 
	 <value>The sort order.</value>
	*/
	private SortOrder SortOrder = getSortOrder().values()[0];
	public final SortOrder getSortOrder()
	{
		return SortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		SortOrder = value;
	}
}