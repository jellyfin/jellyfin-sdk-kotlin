package MediaBrowser.Model.LiveTv;

public class GuideInfo
{
	/** 
	 Gets or sets the start date.
	 
	 <value>The start date.</value>
	*/
	private java.util.Date StartDate = new java.util.Date(0);
	public final java.util.Date getStartDate()
	{
		return StartDate;
	}
	public final void setStartDate(java.util.Date value)
	{
		StartDate = value;
	}

	/** 
	 Gets or sets the end date.
	 
	 <value>The end date.</value>
	*/
	private java.util.Date EndDate = new java.util.Date(0);
	public final java.util.Date getEndDate()
	{
		return EndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		EndDate = value;
	}
}