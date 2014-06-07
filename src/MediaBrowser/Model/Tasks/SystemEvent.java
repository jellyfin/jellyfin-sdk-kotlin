package MediaBrowser.Model.Tasks;

/** 
 Enum SystemEvent
*/
public enum SystemEvent
{
	/** 
	 The wake from sleep
	*/
	WakeFromSleep(0);

	private int intValue;
	private static java.util.HashMap<Integer, SystemEvent> mappings;
	private static java.util.HashMap<Integer, SystemEvent> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SystemEvent.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SystemEvent>();
				}
			}
		}
		return mappings;
	}

	private SystemEvent(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SystemEvent forValue(int value)
	{
		return getMappings().get(value);
	}
}