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
	private String H264Encoder;
	public final String getH264Encoder()
	{
		return H264Encoder;
	}
	public final void setH264Encoder(String value)
	{
		H264Encoder = value;
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

	public EncodingOptions()
	{
		setH264Encoder("libx264");
		setDownMixAudioBoost(2);
		setEnableThrottling(true);
		setThrottleThresholdInSeconds(120);
		setEncodingThreadCount(-1);
	}
}