package org.jellyfin.apiclient.model.session;

public enum RepeatMode
{
	RepeatNone(0),
	RepeatAll(1),
	RepeatOne(2);

	private int intValue;
	private static java.util.HashMap<Integer, RepeatMode> mappings;
	private static java.util.HashMap<Integer, RepeatMode> getMappings()
	{
		if (mappings == null)
		{
			synchronized (RepeatMode.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, RepeatMode>();
				}
			}
		}
		return mappings;
	}

	private RepeatMode(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static RepeatMode forValue(int value)
	{
		return getMappings().get(value);
	}
}