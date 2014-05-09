package MediaBrowser.Model.Notifications;

public enum NotificationLevel
{
	Normal(0),
	Warning(1),
	Error(2);

	private int intValue;
	private static java.util.HashMap<Integer, NotificationLevel> mappings;
	private static java.util.HashMap<Integer, NotificationLevel> getMappings()
	{
		if (mappings == null)
		{
			synchronized (NotificationLevel.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, NotificationLevel>();
				}
			}
		}
		return mappings;
	}

	private NotificationLevel(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static NotificationLevel forValue(int value)
	{
		return getMappings().get(value);
	}
}