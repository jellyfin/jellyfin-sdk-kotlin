package mediabrowser.model.sync;

public enum SyncJobOption
{
	Name(0),
	Quality(1),
	UnwatchedOnly(2),
	SyncNewContent(3),
	ItemLimit(4);

	private int intValue;
	private static java.util.HashMap<Integer, SyncJobOption> mappings;
	private static java.util.HashMap<Integer, SyncJobOption> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncJobOption.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncJobOption>();
				}
			}
		}
		return mappings;
	}

	private SyncJobOption(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncJobOption forValue(int value)
	{
		return getMappings().get(value);
	}
}