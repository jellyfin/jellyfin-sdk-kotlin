package MediaBrowser.Model.Configuration;

public enum EncodingQuality
{
	Auto,
	HighSpeed,
	HighQuality,
	MaxQuality;

	public int getValue()
	{
		return this.ordinal();
	}

	public static EncodingQuality forValue(int value)
	{
		return values()[value];
	}
}