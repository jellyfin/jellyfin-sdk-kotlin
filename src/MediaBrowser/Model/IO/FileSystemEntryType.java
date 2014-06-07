package MediaBrowser.Model.IO;

/** 
 Enum FileSystemEntryType
*/
public enum FileSystemEntryType
{
	/** 
	 The file
	*/
	File,
	/** 
	 The directory
	*/
	Directory,
	/** 
	 The network computer
	*/
	NetworkComputer,
	/** 
	 The network share
	*/
	NetworkShare;

	public int getValue()
	{
		return this.ordinal();
	}

	public static FileSystemEntryType forValue(int value)
	{
		return values()[value];
	}
}