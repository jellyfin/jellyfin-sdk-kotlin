package MediaBrowser.Model.Sync;

public enum SyncLimitType
{
	ItemCount(0);

	private int intValue;
	private static java.util.HashMap<Integer, SyncLimitType> mappings;
	private static java.util.HashMap<Integer, SyncLimitType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncLimitType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncLimitType>();
				}
			}
		}
		return mappings;
	}

	private SyncLimitType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncLimitType forValue(int value)
	{
		return getMappings().get(value);
	}
}