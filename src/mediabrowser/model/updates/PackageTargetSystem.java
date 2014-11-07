package mediabrowser.model.updates;

/** 
 Enum PackageType
*/
public enum PackageTargetSystem
{
	/** 
	 Server
	*/
	Server,
	/** 
	 MB Theater
	*/
	MBTheater,
	/** 
	 MB Classic
	*/
	MBClassic;

	public int getValue()
	{
		return this.ordinal();
	}

	public static PackageTargetSystem forValue(int value)
	{
		return values()[value];
	}
}