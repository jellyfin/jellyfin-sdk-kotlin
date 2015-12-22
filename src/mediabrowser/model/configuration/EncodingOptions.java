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
	private int ThrottleThresholdSeconds;
	public final int getThrottleThresholdSeconds()
	{
		return ThrottleThresholdSeconds;
	}
	public final void setThrottleThresholdSeconds(int value)
	{
		ThrottleThresholdSeconds = value;
	}
	private String HardwareAccelerationType;
	public final String getHardwareAccelerationType()
	{
		return HardwareAccelerationType;
	}
	public final void setHardwareAccelerationType(String value)
	{
		HardwareAccelerationType = value;
	}

	public EncodingOptions()
	{
		setDownMixAudioBoost(2);
		setEnableThrottling(true);
		setThrottleThresholdSeconds(110);
		setEncodingThreadCount(-1);
	}
}