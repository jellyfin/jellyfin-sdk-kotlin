package org.jellyfin.apiclient.model.session;

public enum PlayMethod
{
	Transcode(0),
	DirectStream(1),
	DirectPlay(2);

	private int intValue;
	private static java.util.HashMap<Integer, PlayMethod> mappings;
	private static java.util.HashMap<Integer, PlayMethod> getMappings()
	{
		if (mappings == null)
		{
			synchronized (PlayMethod.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, PlayMethod>();
				}
			}
		}
		return mappings;
	}

	private PlayMethod(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static PlayMethod forValue(int value)
	{
		return getMappings().get(value);
	}
}