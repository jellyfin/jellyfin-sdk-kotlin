package mediabrowser.model.updates;

/** 
 Enum PackageType
*/
public enum PackageType
{
	/** 
	 All
	*/
	All,
	/** 
	 The system
	*/
	System,
	/** 
	 The user installed
	*/
	UserInstalled;

	public int getValue()
	{
		return this.ordinal();
	}

	public static PackageType forValue(int value)
	{
		return values()[value];
	}
}