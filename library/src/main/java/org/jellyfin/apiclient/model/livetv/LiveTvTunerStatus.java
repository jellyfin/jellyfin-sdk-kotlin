package org.jellyfin.apiclient.model.livetv;

public enum LiveTvTunerStatus
{
	Available(0),
	Disabled(1),
	RecordingTv(2),
	LiveTv(3);

	private int intValue;
	private static java.util.HashMap<Integer, LiveTvTunerStatus> mappings;
	private static java.util.HashMap<Integer, LiveTvTunerStatus> getMappings()
	{
		if (mappings == null)
		{
			synchronized (LiveTvTunerStatus.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, LiveTvTunerStatus>();
				}
			}
		}
		return mappings;
	}

	private LiveTvTunerStatus(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static LiveTvTunerStatus forValue(int value)
	{
		return getMappings().get(value);
	}
}