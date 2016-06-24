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
	private boolean EnableThrottling;
	public final boolean getEnableThrottling()
	{
		return EnableThrottling;
	}
	public final void setEnableThrottling(boolean value)
	{
		EnableThrottling = value;
	}
	private int ThrottleDelaySeconds;
	public final int getThrottleDelaySeconds()
	{
		return ThrottleDelaySeconds;
	}
	public final void setThrottleDelaySeconds(int value)
	{
		ThrottleDelaySeconds = value;
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
	private String EncoderAppPath;
	public final String getEncoderAppPath()
	{
		return EncoderAppPath;
	}
	public final void setEncoderAppPath(String value)
	{
		EncoderAppPath = value;
	}

	public EncodingOptions()
	{
		setDownMixAudioBoost(2);
		setEnableThrottling(true);
		setThrottleDelaySeconds(180);
		setEncodingThreadCount(-1);
	}
}