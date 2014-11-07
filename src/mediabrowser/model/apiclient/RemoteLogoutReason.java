package mediabrowser.model.apiclient;

public enum RemoteLogoutReason
{
	GeneralAccesError(0),
	ParentalControlRestriction(1);

	private int intValue;
	private static java.util.HashMap<Integer, RemoteLogoutReason> mappings;
	private static java.util.HashMap<Integer, RemoteLogoutReason> getMappings()
	{
		if (mappings == null)
		{
			synchronized (RemoteLogoutReason.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, RemoteLogoutReason>();
				}
			}
		}
		return mappings;
	}

	private RemoteLogoutReason(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static RemoteLogoutReason forValue(int value)
	{
		return getMappings().get(value);
	}
}