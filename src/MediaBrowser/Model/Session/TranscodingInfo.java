package MediaBrowser.Model.Session;

public class TranscodingInfo
{
	private String privateAudioCodec;
	public final String getAudioCodec()
	{
		return privateAudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		privateAudioCodec = value;
	}
	private String privateVideoCodec;
	public final String getVideoCodec()
	{
		return privateVideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		privateVideoCodec = value;
	}
	private String privateContainer;
	public final String getContainer()
	{
		return privateContainer;
	}
	public final void setContainer(String value)
	{
		privateContainer = value;
	}
	private Integer privateBitrate;
	public final Integer getBitrate()
	{
		return privateBitrate;
	}
	public final void setBitrate(Integer value)
	{
		privateBitrate = value;
	}

	private Float privateFramerate;
	public final Float getFramerate()
	{
		return privateFramerate;
	}
	public final void setFramerate(Float value)
	{
		privateFramerate = value;
	}
	private Double privateCompletionPercentage;
	public final Double getCompletionPercentage()
	{
		return privateCompletionPercentage;
	}
	public final void setCompletionPercentage(Double value)
	{
		privateCompletionPercentage = value;
	}

	private Integer privateWidth;
	public final Integer getWidth()
	{
		return privateWidth;
	}
	public final void setWidth(Integer value)
	{
		privateWidth = value;
	}
	private Integer privateHeight;
	public final Integer getHeight()
	{
		return privateHeight;
	}
	public final void setHeight(Integer value)
	{
		privateHeight = value;
	}
	private Integer privateAudioChannels;
	public final Integer getAudioChannels()
	{
		return privateAudioChannels;
	}
	public final void setAudioChannels(Integer value)
	{
		privateAudioChannels = value;
	}
}