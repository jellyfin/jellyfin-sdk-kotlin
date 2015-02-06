package mediabrowser.model.apiclient;

public enum ConnectionState
{
	Unavailable(1),
	ServerSignIn(2),
	SignedIn(3),
	ServerSelection(4),
	ConnectSignIn(5),
	OfflineSignIn(6),
	OfflineSignedIn(7);

	private int intValue;
	private static java.util.HashMap<Integer, ConnectionState> mappings;
	private static java.util.HashMap<Integer, ConnectionState> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ConnectionState.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ConnectionState>();
				}
			}
		}
		return mappings;
	}

	private ConnectionState(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ConnectionState forValue(int value)
	{
		return getMappings().get(value);
	}
}