package mediabrowser.model.entities;

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