package mediabrowser.model.livetv;

public enum RecordingStatus
{
	New,
	Scheduled,
	InProgress,
	Completed,
	Aborted,
	Cancelled,
	ConflictedOk,
	ConflictedNotOk,
	Error;

	public int getValue()
	{
		return this.ordinal();
	}

	public static RecordingStatus forValue(int value)
	{
		return values()[value];
	}
}