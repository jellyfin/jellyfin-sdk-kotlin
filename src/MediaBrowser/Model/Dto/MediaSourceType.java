package MediaBrowser.Model.Dto;

public enum MediaSourceType
{
	Default(0),
	Grouping(1),
	Cache(2);

	private int intValue;
	private static java.util.HashMap<Integer, MediaSourceType> mappings;
	private static java.util.HashMap<Integer, MediaSourceType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (MediaSourceType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, MediaSourceType>();
				}
			}
		}
		return mappings;
	}

	private MediaSourceType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static MediaSourceType forValue(int value)
	{
		return getMappings().get(value);
	}
}