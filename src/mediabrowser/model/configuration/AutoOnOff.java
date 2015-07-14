package mediabrowser.model.configuration;

public enum AutoOnOff
{
	Auto,
	Enabled,
	Disabled;

	public int getValue()
	{
		return this.ordinal();
	}

	public static AutoOnOff forValue(int value)
	{
		return values()[value];
	}
}