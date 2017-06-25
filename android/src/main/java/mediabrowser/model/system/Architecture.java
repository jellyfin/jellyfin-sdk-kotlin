package mediabrowser.model.system;

public enum Architecture
{
	X86(0),
	X64(1),
	Arm(2),
	Arm64(3);

	private int intValue;
	private static java.util.HashMap<Integer, Architecture> mappings;
	private static java.util.HashMap<Integer, Architecture> getMappings()
	{
		if (mappings == null)
		{
			synchronized (Architecture.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, Architecture>();
				}
			}
		}
		return mappings;
	}

	private Architecture(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static Architecture forValue(int value)
	{
		return getMappings().get(value);
	}
}