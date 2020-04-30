package org.jellyfin.apiclient.model.entities;

public enum ExtraType
{
	Clip(1),
	Trailer(2),
	BehindTheScenes(3),
	DeletedScene(4),
	Interview(5),
	Scene(6),
	Sample(7),
	ThemeSong(8),
	ThemeVideo(9);

	private int intValue;
	private static java.util.HashMap<Integer, ExtraType> mappings;
	private static java.util.HashMap<Integer, ExtraType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ExtraType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ExtraType>();
				}
			}
		}
		return mappings;
	}

	private ExtraType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ExtraType forValue(int value)
	{
		return getMappings().get(value);
	}
}