package mediabrowser.model.entities;

public enum TrailerType
{
	ComingSoonToTheaters(1),
	ComingSoonToDvd(2),
	ComingSoonToStreaming(3),
	Archive(4),
	LocalTrailer(5);

	private int intValue;
	private static java.util.HashMap<Integer, TrailerType> mappings;
	private static java.util.HashMap<Integer, TrailerType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (TrailerType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, TrailerType>();
				}
			}
		}
		return mappings;
	}

	private TrailerType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static TrailerType forValue(int value)
	{
		return getMappings().get(value);
	}
}