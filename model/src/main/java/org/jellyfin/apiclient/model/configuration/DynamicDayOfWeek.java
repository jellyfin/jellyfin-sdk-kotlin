package org.jellyfin.apiclient.model.configuration;

public enum DynamicDayOfWeek
{
	Sunday(0),
	Monday(1),
	Tuesday(2),
	Wednesday(3),
	Thursday(4),
	Friday(5),
	Saturday(6),
	Everyday(7),
	Weekday(8),
	Weekend(9);

	private int intValue;
	private static java.util.HashMap<Integer, DynamicDayOfWeek> mappings;
	private static java.util.HashMap<Integer, DynamicDayOfWeek> getMappings()
	{
		if (mappings == null)
		{
			synchronized (DynamicDayOfWeek.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, DynamicDayOfWeek>();
				}
			}
		}
		return mappings;
	}

	private DynamicDayOfWeek(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static DynamicDayOfWeek forValue(int value)
	{
		return getMappings().get(value);
	}
}