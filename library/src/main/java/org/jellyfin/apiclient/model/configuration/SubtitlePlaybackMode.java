package org.jellyfin.apiclient.model.configuration;

public enum SubtitlePlaybackMode
{
	Default(0),
	Always(1),
	OnlyForced(2),
	None(3),
	Smart(4);

	private int intValue;
	private static java.util.HashMap<Integer, SubtitlePlaybackMode> mappings;
	private static java.util.HashMap<Integer, SubtitlePlaybackMode> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SubtitlePlaybackMode.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SubtitlePlaybackMode>();
				}
			}
		}
		return mappings;
	}

	private SubtitlePlaybackMode(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SubtitlePlaybackMode forValue(int value)
	{
		return getMappings().get(value);
	}
}