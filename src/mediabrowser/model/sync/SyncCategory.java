package mediabrowser.model.sync;

public enum SyncCategory
{
	/** 
	 The latest
	*/
	Latest(0),
	/** 
	 The next up
	*/
	NextUp(1),
	/** 
	 The resume
	*/
	Resume(2);

	private int intValue;
	private static java.util.HashMap<Integer, SyncCategory> mappings;
	private static java.util.HashMap<Integer, SyncCategory> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncCategory.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncCategory>();
				}
			}
		}
		return mappings;
	}

	private SyncCategory(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncCategory forValue(int value)
	{
		return getMappings().get(value);
	}
}