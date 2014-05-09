package MediaBrowser.Model.Entities;

/** 
 Enum LocationType
*/
public enum LocationType
{
	/** 
	 The file system
	*/
	FileSystem(0),
	/** 
	 The remote
	*/
	Remote(1),
	/** 
	 The virtual
	*/
	Virtual(2),
	/** 
	 The offline
	*/
	Offline(3);

	private int intValue;
	private static java.util.HashMap<Integer, LocationType> mappings;
	private static java.util.HashMap<Integer, LocationType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (LocationType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, LocationType>();
				}
			}
		}
		return mappings;
	}

	private LocationType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static LocationType forValue(int value)
	{
		return getMappings().get(value);
	}
}