package MediaBrowser.Model.Tasks;

/** 
 Enum TaskCompletionStatus
*/
public enum TaskCompletionStatus
{
	/** 
	 The completed
	*/
	Completed,

	/** 
	 The failed
	*/
	Failed,

	/** 
	 Manually cancelled by the user
	*/
	Cancelled,

	/** 
	 Aborted due to a system failure or shutdown
	*/
	Aborted;

	public int getValue()
	{
		return this.ordinal();
	}

	public static TaskCompletionStatus forValue(int value)
	{
		return values()[value];
	}
}