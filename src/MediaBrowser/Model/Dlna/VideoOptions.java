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
}