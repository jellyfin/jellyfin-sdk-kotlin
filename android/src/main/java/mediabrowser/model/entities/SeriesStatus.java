package mediabrowser.model.entities;

/** 
 Enum SeriesStatus
*/
public enum SeriesStatus
{
	/** 
	 The continuing
	*/
	Continuing,
	/** 
	 The ended
	*/
	Ended;

	public int getValue()
	{
		return this.ordinal();
	}

	public static SeriesStatus forValue(int value)
	{
		return values()[value];
	}
}