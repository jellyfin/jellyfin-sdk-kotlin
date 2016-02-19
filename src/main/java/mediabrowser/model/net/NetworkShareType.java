package mediabrowser.model.net;

/** 
 Enum NetworkShareType
*/
public enum NetworkShareType
{
	/** 
	 Disk share
	*/
	Disk,
	/** 
	 Printer share
	*/
	Printer,
	/** 
	 Device share
	*/
	Device,
	/** 
	 IPC share
	*/
	Ipc,
	/** 
	 Special share
	*/
	Special;

	public int getValue()
	{
		return this.ordinal();
	}

	public static NetworkShareType forValue(int value)
	{
		return values()[value];
	}
}