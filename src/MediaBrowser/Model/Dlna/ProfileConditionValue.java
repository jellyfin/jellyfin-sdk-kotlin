package MediaBrowser.Model.Dlna;

public enum ProfileConditionValue
{
	AudioChannels,
	AudioBitrate,
	AudioProfile,
	Width,
	Height,
	Has64BitOffsets,
	PacketLength,
	VideoBitDepth,
	VideoBitrate,
	VideoFramerate,
	VideoLevel,
	VideoProfile,
	VideoTimestamp;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ProfileConditionValue forValue(int value)
	{
		return values()[value];
	}
}