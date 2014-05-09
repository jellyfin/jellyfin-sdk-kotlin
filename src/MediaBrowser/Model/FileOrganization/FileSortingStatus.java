package MediaBrowser.Model.FileOrganization;

public enum FileSortingStatus
{
	Success,
	Failure,
	SkippedExisting;

	public int getValue()
	{
		return this.ordinal();
	}

	public static FileSortingStatus forValue(int value)
	{
		return values()[value];
	}
}