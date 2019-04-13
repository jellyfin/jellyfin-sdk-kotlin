package org.jellyfin.apiclient.model.channels;

public enum ChannelMediaType
{
	Audio(0),

	Video(1),

	Photo(2);

	private int intValue;
	private static java.util.HashMap<Integer, ChannelMediaType> mappings;
	private static java.util.HashMap<Integer, ChannelMediaType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ChannelMediaType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ChannelMediaType>();
				}
			}
		}
		return mappings;
	}

	private ChannelMediaType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ChannelMediaType forValue(int value)
	{
		return getMappings().get(value);
	}
}