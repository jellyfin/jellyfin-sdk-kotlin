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
	private String VaapiDevice;
	public final String getVaapiDevice()
	{
		return VaapiDevice;
	}
	public final void setVaapiDevice(String value)
	{
		VaapiDevice = value;
	}
	private int H264Crf;
	public final int getH264Crf()
	{
		return H264Crf;
	}
	public final void setH264Crf(int value)
	{
		H264Crf = value;
	}
	private String H264Preset;
	public final String getH264Preset()
	{
		return H264Preset;
	}
	public final void setH264Preset(String value)
	{
		H264Preset = value;
	}

	public EncodingOptions()
	{
		setDownMixAudioBoost(2);
		setEnableThrottling(true);
		setThrottleDelaySeconds(180);
		setEncodingThreadCount(-1);
		setVaapiDevice("/dev/dri/card0");
		setH264Crf(23);
	}
}