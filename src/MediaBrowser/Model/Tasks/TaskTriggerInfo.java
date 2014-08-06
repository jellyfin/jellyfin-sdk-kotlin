package MediaBrowser.Model.Tasks;

/** 
 Class TaskTriggerInfo
*/
public class TaskTriggerInfo
{
	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the time of day.
	 
	 <value>The time of day.</value>
	*/
	private Long privateTimeOfDayTicks = null;
	public final Long getTimeOfDayTicks()
	{
		return privateTimeOfDayTicks;
	}
	public final void setTimeOfDayTicks(Long value)
	{
		privateTimeOfDayTicks = value;
	}

	/** 
	 Gets or sets the interval.
	 
	 <value>The interval.</value>
	*/
	private Long privateIntervalTicks = null;
	public final Long getIntervalTicks()
	{
		return privateIntervalTicks;
	}
	public final void setIntervalTicks(Long value)
	{
		privateIntervalTicks = value;
	}

	/** 
	 Gets or sets the system event.
	 
	 <value>The system event.</value>
	*/
	private SystemEvent privateSystemEvent = null;
	public final SystemEvent getSystemEvent()
	{
		return privateSystemEvent;
	}
	public final void setSystemEvent(SystemEvent value)
	{
		privateSystemEvent = value;
	}

	/** 
	 Gets or sets the day of week.
	 
	 <value>The day of week.</value>
	*/
	private String privateDayOfWeek = null;
	public final String getDayOfWeek()
	{
		return privateDayOfWeek;
	}
	public final void setDayOfWeek(String value)
	{
		privateDayOfWeek = value;
	}
}