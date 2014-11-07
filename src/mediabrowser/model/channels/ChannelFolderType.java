package mediabrowser.model.channels;

public enum ChannelFolderType
{
	Container(0),

	MusicAlbum(1),

	PhotoAlbum(2);

	private int intValue;
	private static java.util.HashMap<Integer, ChannelFolderType> mappings;
	private static java.util.HashMap<Integer, ChannelFolderType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ChannelFolderType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ChannelFolderType>();
				}
			}
		}
		return mappings;
	}

	private ChannelFolderType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ChannelFolderType forValue(int value)
	{
		return getMappings().get(value);
	}
}