package org.jellyfin.apiclient.model.library;

public enum PlayAccess
{
	Full(0),
	None(1);

	private int intValue;
	private static java.util.HashMap<Integer, PlayAccess> mappings;
	private static java.util.HashMap<Integer, PlayAccess> getMappings()
	{
		if (mappings == null)
		{
			synchronized (PlayAccess.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, PlayAccess>();
				}
			}
		}
		return mappings;
	}

	private PlayAccess(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static PlayAccess forValue(int value)
	{
		return getMappings().get(value);
	}
}