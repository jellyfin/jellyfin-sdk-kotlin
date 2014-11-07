package mediabrowser.model.entities;

public enum Video3DFormat
{
	HalfSideBySide,
	FullSideBySide,
	FullTopAndBottom,
	HalfTopAndBottom;

	public int getValue()
	{
		return this.ordinal();
	}

	public static Video3DFormat forValue(int value)
	{
		return values()[value];
	}
}