package mediabrowser.model.sync;

public enum SyncQuality
{
	/** 
	 The good
	*/
	Low(0),

	/** 
	 The better
	*/
	Medium(1),

	/** 
	 The best
	*/
	High(2),
	/** 
	 The original
	*/
	Original(3);

	private int intValue;
	private static java.util.HashMap<Integer, SyncQuality> mappings;
	private static java.util.HashMap<Integer, SyncQuality> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncQuality.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncQuality>();
				}
			}
		}
		return mappings;
	}

	private SyncQuality(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncQuality forValue(int value)
	{
		return getMappings().get(value);
	}
}