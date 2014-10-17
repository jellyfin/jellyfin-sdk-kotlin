package MediaBrowser.Model.MediaInfo;

public enum MediaProtocol
{
	File(0),
	Http(1),
	Rtmp(2),
	Rtsp(3);

	private int intValue;
	private static java.util.HashMap<Integer, MediaProtocol> mappings;
	private static java.util.HashMap<Integer, MediaProtocol> getMappings()
	{
		if (mappings == null)
		{
			synchronized (MediaProtocol.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, MediaProtocol>();
				}
			}
		}
		return mappings;
	}

	private MediaProtocol(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static MediaProtocol forValue(int value)
	{
		return getMappings().get(value);
	}
}