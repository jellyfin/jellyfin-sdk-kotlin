package mediabrowser.model.livetv;

/** 
 Enum ChannelType
*/
public enum ChannelType
{
	/** 
	 The TV
	*/
	TV,

	/** 
	 The radio
	*/
	Radio;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ChannelType forValue(int value)
	{
		return values()[value];
	}
}