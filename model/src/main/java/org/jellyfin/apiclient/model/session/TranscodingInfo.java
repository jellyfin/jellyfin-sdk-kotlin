package org.jellyfin.apiclient.model.session;

public class TranscodingInfo
{
	private String AudioCodec;
	public final String getAudioCodec()
	{
		return AudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		AudioCodec = value;
	}
	private String VideoCodec;
	public final String getVideoCodec()
	{
		return VideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		VideoCodec = value;
	}
	private String Container;
	public final String getContainer()
	{
		return Container;
	}
	public final void setContainer(String value)
	{
		Container = value;
	}
	private boolean IsVideoDirect;
	public final boolean getIsVideoDirect()
	{
		return IsVideoDirect;
	}
	public final void setIsVideoDirect(boolean value)
	{
		IsVideoDirect = value;
	}
	private boolean IsAudioDirect;
	public final boolean getIsAudioDirect()
	{
		return IsAudioDirect;
	}
	public final void setIsAudioDirect(boolean value)
	{
		IsAudioDirect = value;
	}
	private Integer Bitrate;
	public final Integer getBitrate()
	{
		return Bitrate;
	}
	public final void setBitrate(Integer value)
	{
		Bitrate = value;
	}

	private Float Framerate;
	public final Float getFramerate()
	{
		return Framerate;
	}
	public final void setFramerate(Float value)
	{
		Framerate = value;
	}
	private Double CompletionPercentage;
	public final Double getCompletionPercentage()
	{
		return CompletionPercentage;
	}
	public final void setCompletionPercentage(Double value)
	{
		CompletionPercentage = value;
	}

	private Integer Width;
	public final Integer getWidth()
	{
		return Width;
	}
	public final void setWidth(Integer value)
	{
		Width = value;
	}
	private Integer Height;
	public final Integer getHeight()
	{
		return Height;
	}
	public final void setHeight(Integer value)
	{
		Height = value;
	}
	private Integer AudioChannels;
	public final Integer getAudioChannels()
	{
		return AudioChannels;
	}
	public final void setAudioChannels(Integer value)
	{
		AudioChannels = value;
	}
}