package org.jellyfin.apiclient.model.dlna;

public enum PlaybackErrorCode
{
	NotAllowed(0),
	NoCompatibleStream(1),
	RateLimitExceeded(2);

	private int intValue;
	private static java.util.HashMap<Integer, PlaybackErrorCode> mappings;
	private static java.util.HashMap<Integer, PlaybackErrorCode> getMappings()
	{
		if (mappings == null)
		{
			synchronized (PlaybackErrorCode.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, PlaybackErrorCode>();
				}
			}
		}
		return mappings;
	}

	private PlaybackErrorCode(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static PlaybackErrorCode forValue(int value)
	{
		return getMappings().get(value);
	}
}