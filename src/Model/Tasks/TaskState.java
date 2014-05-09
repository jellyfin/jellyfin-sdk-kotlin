package MediaBrowser.Model.Tasks;

/** 
 Enum TaskState
*/
public enum TaskState
{
	/** 
	 The idle
	*/
	Idle,
	/** 
	 The cancelling
	*/
	Cancelling,
	/** 
	 The running
	*/
	Running;

	public int getValue()
	{
		return this.ordinal();
	}

	public static TaskState forValue(int value)
	{
		return values()[value];
	}
}