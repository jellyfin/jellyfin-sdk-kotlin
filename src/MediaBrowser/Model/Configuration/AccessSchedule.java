package MediaBrowser.Model.Configuration;

public class AccessSchedule
{
	/** 
	 Gets or sets the day of week.
	 
	 <value>The day of week.</value>
	*/
	private DynamicDayOfWeek DayOfWeek = DynamicDayOfWeek.values()[0];
	public final DynamicDayOfWeek getDayOfWeek()
	{
		return DayOfWeek;
	}
	public final void setDayOfWeek(DynamicDayOfWeek value)
	{
		DayOfWeek = value;
	}
	/** 
	 Gets or sets the start hour.
	 
	 <value>The start hour.</value>
	*/
	private double StartHour;
	public final double getStartHour()
	{
		return StartHour;
	}
	public final void setStartHour(double value)
	{
		StartHour = value;
	}
	/** 
	 Gets or sets the end hour.
	 
	 <value>The end hour.</value>
	*/
	private double EndHour;
	public final double getEndHour()
	{
		return EndHour;
	}
	public final void setEndHour(double value)
	{
		EndHour = value;
	}
}