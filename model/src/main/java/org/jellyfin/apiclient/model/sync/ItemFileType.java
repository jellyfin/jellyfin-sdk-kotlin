package org.jellyfin.apiclient.model.sync;

public enum ItemFileType
{
	/** 
	 The media
	*/
	Media(0),
	/** 
	 The image
	*/
	Image(1),
	/** 
	 The subtitles
	*/
	Subtitles(2);

	private int intValue;
	private static java.util.HashMap<Integer, ItemFileType> mappings;
	private static java.util.HashMap<Integer, ItemFileType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ItemFileType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ItemFileType>();
				}
			}
		}
		return mappings;
	}

	private ItemFileType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ItemFileType forValue(int value)
	{
		return getMappings().get(value);
	}
}