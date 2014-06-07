package MediaBrowser.Model.MediaInfo;

public enum TransportStreamTimestamp
{
	None,
	Zero,
	Valid;

	public int getValue()
	{
		return this.ordinal();
	}

	public static TransportStreamTimestamp forValue(int value)
	{
		return values()[value];
	}
}