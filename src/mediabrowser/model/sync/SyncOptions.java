package mediabrowser.model.sync;

public enum SyncOptions
{
	Name(0),
	Quality(1),
	UnwatchedOnly(2),
	SyncNewContent(3),
	ItemLimit(4);

	private int intValue;
	private static java.util.HashMap<Integer, SyncOptions> mappings;
	private static java.util.HashMap<Integer, SyncOptions> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncOptions.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncOptions>();
				}
			}
		}
		return mappings;
	}

	private SyncOptions(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncOptions forValue(int value)
	{
		return getMappings().get(value);
	}
}