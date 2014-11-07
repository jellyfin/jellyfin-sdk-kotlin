package mediabrowser.model.livetv;

public enum ProgramAudio
{
	Mono,
	Stereo,
	Dolby,
	DolbyDigital,
	Thx;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ProgramAudio forValue(int value)
	{
		return values()[value];
	}
}