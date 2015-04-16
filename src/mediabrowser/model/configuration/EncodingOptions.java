package mediabrowser.model.configuration;

public class EncodingOptions
{
	private EncodingQuality EncodingQuality = getEncodingQuality().values()[0];
	public final EncodingQuality getEncodingQuality()
	{
		return EncodingQuality;
	}
	public final void setEncodingQuality(EncodingQuality value)
	{
		EncodingQuality = value;
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
	private int ThrottleThresholdSeconds;
	public final int getThrottleThresholdSeconds()
	{
		return ThrottleThresholdSeconds;
	}
	public final void setThrottleThresholdSeconds(int value)
	{
		ThrottleThresholdSeconds = value;
	}

	public EncodingOptions()
	{
		setH264Encoder("libx264");
		setDownMixAudioBoost(2);
		setEncodingQuality(EncodingQuality.Auto);
		setEnableThrottling(true);
		setThrottleThresholdSeconds(90);
	}
}