package MediaBrowser.Model.Dto;

/** 
 Class VideoStreamOptions
*/
public class VideoStreamOptions extends StreamOptions
{
	/** 
	 Gets or sets the video codec.
	 Omit to copy
	 
	 <value>The video codec.</value>
	*/
	private String privateVideoCodec;
	public final String getVideoCodec()
	{
		return privateVideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		privateVideoCodec = value;
	}

	/** 
	 Gets or sets the video bit rate.
	 
	 <value>The video bit rate.</value>
	*/
	private Integer privateVideoBitRate;
	public final Integer getVideoBitRate()
	{
		return privateVideoBitRate;
	}
	public final void setVideoBitRate(Integer value)
	{
		privateVideoBitRate = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer privateWidth;
	public final Integer getWidth()
	{
		return privateWidth;
	}
	public final void setWidth(Integer value)
	{
		privateWidth = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer privateHeight;
	public final Integer getHeight()
	{
		return privateHeight;
	}
	public final void setHeight(Integer value)
	{
		privateHeight = value;
	}

	/** 
	 Gets or sets the width of the max.
	 
	 <value>The width of the max.</value>
	*/
	private Integer privateMaxWidth;
	public final Integer getMaxWidth()
	{
		return privateMaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		privateMaxWidth = value;
	}

	/** 
	 Gets or sets the height of the max.
	 
	 <value>The height of the max.</value>
	*/
	private Integer privateMaxHeight;
	public final Integer getMaxHeight()
	{
		return privateMaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		privateMaxHeight = value;
	}

	/** 
	 Gets or sets the frame rate.
	 
	 <value>The frame rate.</value>
	*/
	private Double privateFrameRate;
	public final Double getFrameRate()
	{
		return privateFrameRate;
	}
	public final void setFrameRate(Double value)
	{
		privateFrameRate = value;
	}

	/** 
	 Gets or sets the index of the audio stream.
	 
	 <value>The index of the audio stream.</value>
	*/
	private Integer privateAudioStreamIndex;
	public final Integer getAudioStreamIndex()
	{
		return privateAudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		privateAudioStreamIndex = value;
	}

	/** 
	 Gets or sets the index of the video stream.
	 
	 <value>The index of the video stream.</value>
	*/
	private Integer privateVideoStreamIndex;
	public final Integer getVideoStreamIndex()
	{
		return privateVideoStreamIndex;
	}
	public final void setVideoStreamIndex(Integer value)
	{
		privateVideoStreamIndex = value;
	}

	/** 
	 Gets or sets the index of the subtitle stream.
	 
	 <value>The index of the subtitle stream.</value>
	*/
	private Integer privateSubtitleStreamIndex;
	public final Integer getSubtitleStreamIndex()
	{
		return privateSubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		privateSubtitleStreamIndex = value;
	}

	/** 
	 Gets or sets the profile.
	 
	 <value>The profile.</value>
	*/
	private String privateProfile;
	public final String getProfile()
	{
		return privateProfile;
	}
	public final void setProfile(String value)
	{
		privateProfile = value;
	}

	/** 
	 Gets or sets the level.
	 
	 <value>The level.</value>
	*/
	private String privateLevel;
	public final String getLevel()
	{
		return privateLevel;
	}
	public final void setLevel(String value)
	{
		privateLevel = value;
	}

	/** 
	 Gets or sets the baseline stream audio bit rate.
	 
	 <value>The baseline stream audio bit rate.</value>
	*/
	private Integer privateBaselineStreamAudioBitRate;
	public final Integer getBaselineStreamAudioBitRate()
	{
		return privateBaselineStreamAudioBitRate;
	}
	public final void setBaselineStreamAudioBitRate(Integer value)
	{
		privateBaselineStreamAudioBitRate = value;
	}

	/** 
	 Gets or sets a value indicating whether [append baseline stream].
	 
	 <value><c>true</c> if [append baseline stream]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateAppendBaselineStream;
	public final boolean getAppendBaselineStream()
	{
		return privateAppendBaselineStream;
	}
	public final void setAppendBaselineStream(boolean value)
	{
		privateAppendBaselineStream = value;
	}

	/** 
	 Gets or sets the time stamp offset ms. Only used with HLS.
	 
	 <value>The time stamp offset ms.</value>
	*/
	private Integer privateTimeStampOffsetMs;
	public final Integer getTimeStampOffsetMs()
	{
		return privateTimeStampOffsetMs;
	}
	public final void setTimeStampOffsetMs(Integer value)
	{
		privateTimeStampOffsetMs = value;
	}
}