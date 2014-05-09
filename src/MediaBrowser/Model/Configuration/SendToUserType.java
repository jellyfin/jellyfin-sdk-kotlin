package MediaBrowser.Model.Configuration;

public enum SendToUserType
{
	All(0),
	Admins(1),
	Custom(2);

	private int intValue;
	private static java.util.HashMap<Integer, SendToUserType> mappings;
	private static java.util.HashMap<Integer, SendToUserType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SendToUserType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SendToUserType>();
				}
			}
		}
		return mappings;
	}

	private SendToUserType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SendToUserType forValue(int value)
	{
		return getMappings().get(value);
	}
}