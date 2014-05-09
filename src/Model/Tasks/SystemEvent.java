package MediaBrowser.Model.Tasks;

/** 
 Enum SystemEvent
*/
public enum SystemEvent
{
	/** 
	 The wake from sleep
	*/
	WakeFromSleep;

	public int getValue()
	{
		return this.ordinal();
	}

	public static SystemEvent forValue(int value)
	{
		return values()[value];
	}
}