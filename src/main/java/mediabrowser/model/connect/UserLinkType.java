package mediabrowser.model.connect;

public enum UserLinkType
{
	/** 
	 The linked user
	*/
	LinkedUser(0),
	/** 
	 The guest
	*/
	Guest(1);

	private int intValue;
	private static java.util.HashMap<Integer, UserLinkType> mappings;
	private static java.util.HashMap<Integer, UserLinkType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (UserLinkType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, UserLinkType>();
				}
			}
		}
		return mappings;
	}

	private UserLinkType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static UserLinkType forValue(int value)
	{
		return getMappings().get(value);
	}
}