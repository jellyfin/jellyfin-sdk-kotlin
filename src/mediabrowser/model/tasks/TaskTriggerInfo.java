package mediabrowser.model.tasks;

/** 
 Class TaskTriggerInfo
*/
public class TaskTriggerInfo
{
	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the time of day.
	 
	 <value>The time of day.</value>
	*/
	private Long TimeOfDayTicks = null;
	public final Long getTimeOfDayTicks()
	{
		return TimeOfDayTicks;
	}
	public final void setTimeOfDayTicks(Long value)
	{
		TimeOfDayTicks = value;
	}

	/** 
	 Gets or sets the interval.
	 
	 <value>The interval.</value>
	*/
	private Long IntervalTicks = null;
	public final Long getIntervalTicks()
	{
		return IntervalTicks;
	}
	public final void setIntervalTicks(Long value)
	{
		IntervalTicks = value;
	}

	/** 
	 Gets or sets the system event.
	 
	 <value>The system event.</value>
	*/
	private SystemEvent SystemEvent = null;
	public final SystemEvent getSystemEvent()
	{
		return SystemEvent;
	}
	public final void setSystemEvent(SystemEvent value)
	{
		SystemEvent = value;
	}

	/** 
	 Gets or sets the day of week.
	 
	 <value>The day of week.</value>
	*/
	private DayOfWeek DayOfWeek = null;
	public final DayOfWeek getDayOfWeek()
	{
		return DayOfWeek;
	}
	public final void setDayOfWeek(DayOfWeek value)
	{
		DayOfWeek = value;
	}
}