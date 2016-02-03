package mediabrowser.model.configuration;

public enum ImageSavingConvention
{
	Legacy,
	Compatible;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ImageSavingConvention forValue(int value)
	{
		return values()[value];
	}
}