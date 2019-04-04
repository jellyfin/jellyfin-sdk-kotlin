package org.jellyfin.apiclient.model.channels;

public enum ChannelItemSortField
{
	Name(0),
	CommunityRating(1),
	PremiereDate(2),
	DateCreated(3),
	Runtime(4),
	PlayCount(5),
	CommunityPlayCount(6);

	private int intValue;
	private static java.util.HashMap<Integer, ChannelItemSortField> mappings;
	private static java.util.HashMap<Integer, ChannelItemSortField> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ChannelItemSortField.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ChannelItemSortField>();
				}
			}
		}
		return mappings;
	}

	private ChannelItemSortField(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ChannelItemSortField forValue(int value)
	{
		return getMappings().get(value);
	}
}