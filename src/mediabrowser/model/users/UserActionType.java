package mediabrowser.model.users;

public enum UserActionType
{
	PlayedItem(0);

	private int intValue;
	private static java.util.HashMap<Integer, UserActionType> mappings;
	private static java.util.HashMap<Integer, UserActionType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (UserActionType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, UserActionType>();
				}
			}
		}
		return mappings;
	}

	private UserActionType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static UserActionType forValue(int value)
	{
		return getMappings().get(value);
	}
}