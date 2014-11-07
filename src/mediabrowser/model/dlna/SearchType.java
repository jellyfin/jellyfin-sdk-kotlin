package mediabrowser.model.dlna;

public enum SearchType
{
	Unknown(0),
	Audio(1),
	Image(2),
	Video(3),
	Playlist(4),
	MusicAlbum(5);

	private int intValue;
	private static java.util.HashMap<Integer, SearchType> mappings;
	private static java.util.HashMap<Integer, SearchType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SearchType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SearchType>();
				}
			}
		}
		return mappings;
	}

	private SearchType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SearchType forValue(int value)
	{
		return getMappings().get(value);
	}
}