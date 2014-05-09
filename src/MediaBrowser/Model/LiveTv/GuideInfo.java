package MediaBrowser.Model.LiveTv;

public class GuideInfo
{
	/** 
	 Gets or sets the start date.
	 
	 <value>The start date.</value>
	*/
	private java.util.Date privateStartDate = new java.util.Date(0);
	public final java.util.Date getStartDate()
	{
		return privateStartDate;
	}
	public final void setStartDate(java.util.Date value)
	{
		privateStartDate = value;
	}

	/** 
	 Gets or sets the end date.
	 
	 <value>The end date.</value>
	*/
	private java.util.Date privateEndDate = new java.util.Date(0);
	public final java.util.Date getEndDate()
	{
		return privateEndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		privateEndDate = value;
	}
}