package MediaBrowser.Model.Dlna;

public enum ProfileConditionValue
{
	AudioChannels(0),
	AudioBitrate(1),
	AudioProfile(2),
	Width(3),
	Height(4),
	Has64BitOffsets(5),
	PacketLength(6),
	VideoBitDepth(7),
	VideoBitrate(8),
	VideoFramerate(9),
	VideoLevel(10),
	VideoProfile(11),
	VideoTimestamp(12),
	IsAnamorphic(13);

	private int intValue;
	private static java.util.HashMap<Integer, ProfileConditionValue> mappings;
	private static java.util.HashMap<Integer, ProfileConditionValue> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ProfileConditionValue.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ProfileConditionValue>();
				}
			}
		}
		return mappings;
	}

	private ProfileConditionValue(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ProfileConditionValue forValue(int value)
	{
		return getMappings().get(value);
	}
}