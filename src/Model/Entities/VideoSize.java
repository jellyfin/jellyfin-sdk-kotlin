package MediaBrowser.Model.Entities;

public enum VideoSize
{
	StandardDefinition,
	HighDefinition;

	public int getValue()
	{
		return this.ordinal();
	}

	public static VideoSize forValue(int value)
	{
		return values()[value];
	}
}