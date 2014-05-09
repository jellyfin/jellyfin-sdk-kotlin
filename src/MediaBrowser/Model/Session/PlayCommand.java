package MediaBrowser.Model.Session;

/** 
 Enum PlayCommand
*/
public enum PlayCommand
{
	/** 
	 The play now
	*/
	PlayNow(0),
	/** 
	 The play next
	*/
	PlayNext(1),
	/** 
	 The play last
	*/
	PlayLast(2),
	/** 
	 The play instant mix
	*/
	PlayInstantMix(3),
	/** 
	 The play shuffle
	*/
	PlayShuffle(4);

	private int intValue;
	private static java.util.HashMap<Integer, PlayCommand> mappings;
	private static java.util.HashMap<Integer, PlayCommand> getMappings()
	{
		if (mappings == null)
		{
			synchronized (PlayCommand.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, PlayCommand>();
				}
			}
		}
		return mappings;
	}

	private PlayCommand(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static PlayCommand forValue(int value)
	{
		return getMappings().get(value);
	}
}