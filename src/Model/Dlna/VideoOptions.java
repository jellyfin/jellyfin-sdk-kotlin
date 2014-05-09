package MediaBrowser.Model.Dlna;

/** 
 Class VideoOptions.
*/
public class VideoOptions extends AudioOptions
{
	private Integer privateAudioStreamIndex;
	public final Integer getAudioStreamIndex()
	{
		return privateAudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		privateAudioStreamIndex = value;
	}
	private Integer privateSubtitleStreamIndex;
	public final Integer getSubtitleStreamIndex()
	{
		return privateSubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		privateSubtitleStreamIndex = value;
	}
	private Integer privateMaxAudioTranscodingBitrate;
	public final Integer getMaxAudioTranscodingBitrate()
	{
		return privateMaxAudioTranscodingBitrate;
	}
	public final void setMaxAudioTranscodingBitrate(Integer value)
	{
		privateMaxAudioTranscodingBitrate = value;
	}

	public VideoOptions()
	{
		setMaxAudioTranscodingBitrate(128000);
	}
}