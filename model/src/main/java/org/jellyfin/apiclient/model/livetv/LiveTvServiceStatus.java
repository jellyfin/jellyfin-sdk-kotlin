package org.jellyfin.apiclient.model.livetv;

public enum LiveTvServiceStatus
{
	Ok(0),
	Unavailable(1);

	private int intValue;
	private static java.util.HashMap<Integer, LiveTvServiceStatus> mappings;
	private static java.util.HashMap<Integer, LiveTvServiceStatus> getMappings()
	{
		if (mappings == null)
		{
			synchronized (LiveTvServiceStatus.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, LiveTvServiceStatus>();
				}
			}
		}
		return mappings;
	}

	private LiveTvServiceStatus(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static LiveTvServiceStatus forValue(int value)
	{
		return getMappings().get(value);
	}
}