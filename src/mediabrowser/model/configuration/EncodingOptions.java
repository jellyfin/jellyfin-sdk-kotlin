package mediabrowser.model.configuration;

public class EncodingOptions
{
	private int EncodingThreadCount;
	public final int getEncodingThreadCount()
	{
		return EncodingThreadCount;
	}
	public final void setEncodingThreadCount(int value)
	{
		EncodingThreadCount = value;
	}
	private String TranscodingTempPath;
	public final String getTranscodingTempPath()
	{
		return TranscodingTempPath;
	}
	public final void setTranscodingTempPath(String value)
	{
		TranscodingTempPath = value;
	}
	private double DownMixAudioBoost;
	public final double getDownMixAudioBoost()
	{
		return DownMixAudioBoost;
	}
	public final void setDownMixAudioBoost(double value)
	{
		DownMixAudioBoost = value;
	}
	private boolean EnableDebugLogging;
	public final boolean getEnableDebugLogging()
	{
		return EnableDebugLogging;
	}
	public final void setEnableDebugLogging(boolean value)
	{
		EnableDebugLogging = value;
	}
	private boolean EnableThrottling;
	public final boolean getEnableThrottling()
	{
		return EnableThrottling;
	}
	public final void setEnableThrottling(boolean value)
	{
		EnableThrottling = value;
	}
	private int ThrottleThresholdInSeconds;
	public final int getThrottleThresholdInSeconds()
	{
		return ThrottleThresholdInSeconds;
	}
	public final void setThrottleThresholdInSeconds(int value)
	{
		ThrottleThresholdInSeconds = value;
	}
	private String HardwareVideoDecoder;
	public final String getHardwareVideoDecoder()
	{
		return HardwareVideoDecoder;
	}
	public final void setHardwareVideoDecoder(String value)
	{
		HardwareVideoDecoder = value;
	}

	public EncodingOptions()
	{
		setDownMixAudioBoost(2);
		setEnableThrottling(true);
		setThrottleThresholdInSeconds(120);
		setEncodingThreadCount(-1);
	}
}