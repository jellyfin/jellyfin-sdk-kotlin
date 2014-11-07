package mediabrowser.model.channels;

public enum ChannelMediaContentType
{
	Clip(0),

	Podcast(1),

	Trailer(2),

	Movie(3),

	Episode(4),

	Song(5),

	MovieExtra(6),

	TvExtra(7),

	GameExtra(8);

	private int intValue;
	private static java.util.HashMap<Integer, ChannelMediaContentType> mappings;
	private static java.util.HashMap<Integer, ChannelMediaContentType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ChannelMediaContentType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ChannelMediaContentType>();
				}
			}
		}
		return mappings;
	}

	private ChannelMediaContentType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ChannelMediaContentType forValue(int value)
	{
		return getMappings().get(value);
	}
}