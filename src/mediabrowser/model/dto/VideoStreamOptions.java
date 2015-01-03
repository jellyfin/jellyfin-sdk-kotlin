package mediabrowser.model.dto;

/** 
 Class VideoStreamOptions
*/
@Deprecated
public class VideoStreamOptions
{
	/** 
	 Gets or sets the audio bit rate.
	 
	 <value>The audio bit rate.</value>
	*/
	private Integer AudioBitRate = null;
	public final Integer getAudioBitRate()
	{
		return AudioBitRate;
	}
	public final void setAudioBitRate(Integer value)
	{
		AudioBitRate = value;
	}

	/** 
	 Gets or sets the audio codec.
	 Omit to copy the original stream
	 
	 <value>The audio encoding format.</value>
	*/
	private String AudioCodec;
	public final String getAudioCodec()
	{
		return AudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		AudioCodec = value;
	}

	/** 
	 Gets or sets the item id.
	 
	 <value>The item id.</value>
	*/
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}

	/** 
	 Gets or sets the max audio channels.
	 
	 <value>The max audio channels.</value>
	*/
	private Integer MaxAudioChannels = null;
	public final Integer getMaxAudioChannels()
	{
		return MaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		MaxAudioChannels = value;
	}

	/** 
	 Gets or sets the max audio sample rate.
	 
	 <value>The max audio sample rate.</value>
	*/
	private Integer MaxAudioSampleRate = null;
	public final Integer getMaxAudioSampleRate()
	{
		return MaxAudioSampleRate;
	}
	public final void setMaxAudioSampleRate(Integer value)
	{
		MaxAudioSampleRate = value;
	}

	/** 
	 Gets or sets the start time ticks.
	 
	 <value>The start time ticks.</value>
	*/
	private Long StartTimeTicks = null;
	public final Long getStartTimeTicks()
	{
		return StartTimeTicks;
	}
	public final void setStartTimeTicks(Long value)
	{
		StartTimeTicks = value;
	}

	/** 
	 Gets or sets a value indicating whether the original media should be served statically
	 Only used with progressive streaming
	 
	 <value><c>true</c> if static; otherwise, <c>false</c>.</value>
	*/
	private Boolean Static = null;
	public final Boolean getStatic()
	{
		return Static;
	}
	public final void setStatic(Boolean value)
	{
		Static = value;
	}

	/** 
	 Gets or sets the output file extension.
	 
	 <value>The output file extension.</value>
	*/
	private String OutputFileExtension;
	public final String getOutputFileExtension()
	{
		return OutputFileExtension;
	}
	public final void setOutputFileExtension(String value)
	{
		OutputFileExtension = value;
	}

	/** 
	 Gets or sets the device id.
	 
	 <value>The device id.</value>
	*/
	private String DeviceId;
	public final String getDeviceId()
	{
		return DeviceId;
	}
	public final void setDeviceId(String value)
	{
		DeviceId = value;
	}

	/** 
	 Gets or sets the video codec.
	 Omit to copy
	 
	 <value>The video codec.</value>
	*/
	private String VideoCodec;
	public final String getVideoCodec()
	{
		return VideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		VideoCodec = value;
	}

	/** 
	 Gets or sets the video bit rate.
	 
	 <value>The video bit rate.</value>
	*/
	private Integer VideoBitRate = null;
	public final Integer getVideoBitRate()
	{
		return VideoBitRate;
	}
	public final void setVideoBitRate(Integer value)
	{
		VideoBitRate = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer Width = null;
	public final Integer getWidth()
	{
		return Width;
	}
	public final void setWidth(Integer value)
	{
		Width = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer Height = null;
	public final Integer getHeight()
	{
		return Height;
	}
	public final void setHeight(Integer value)
	{
		Height = value;
	}

	/** 
	 Gets or sets the width of the max.
	 
	 <value>The width of the max.</value>
	*/
	private Integer MaxWidth = null;
	public final Integer getMaxWidth()
	{
		return MaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		MaxWidth = value;
	}

	/** 
	 Gets or sets the height of the max.
	 
	 <value>The height of the max.</value>
	*/
	private Integer MaxHeight = null;
	public final Integer getMaxHeight()
	{
		return MaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		MaxHeight = value;
	}

	/** 
	 Gets or sets the frame rate.
	 
	 <value>The frame rate.</value>
	*/
	private Double FrameRate = null;
	public final Double getFrameRate()
	{
		return FrameRate;
	}
	public final void setFrameRate(Double value)
	{
		FrameRate = value;
	}

	/** 
	 Gets or sets the index of the audio stream.
	 
	 <value>The index of the audio stream.</value>
	*/
	private Integer AudioStreamIndex = null;
	public final Integer getAudioStreamIndex()
	{
		return AudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		AudioStreamIndex = value;
	}

	/** 
	 Gets or sets the index of the video stream.
	 
	 <value>The index of the video stream.</value>
	*/
	private Integer VideoStreamIndex = null;
	public final Integer getVideoStreamIndex()
	{
		return VideoStreamIndex;
	}
	public final void setVideoStreamIndex(Integer value)
	{
		VideoStreamIndex = value;
	}

	/** 
	 Gets or sets the index of the subtitle stream.
	 
	 <value>The index of the subtitle stream.</value>
	*/
	private Integer SubtitleStreamIndex = null;
	public final Integer getSubtitleStreamIndex()
	{
		return SubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		SubtitleStreamIndex = value;
	}

	/** 
	 Gets or sets the profile.
	 
	 <value>The profile.</value>
	*/
	private String Profile;
	public final String getProfile()
	{
		return Profile;
	}
	public final void setProfile(String value)
	{
		Profile = value;
	}

	/** 
	 Gets or sets the level.
	 
	 <value>The level.</value>
	*/
	private String Level;
	public final String getLevel()
	{
		return Level;
	}
	public final void setLevel(String value)
	{
		Level = value;
	}

	/** 
	 Gets or sets the baseline stream audio bit rate.
	 
	 <value>The baseline stream audio bit rate.</value>
	*/
	private Integer BaselineStreamAudioBitRate = null;
	public final Integer getBaselineStreamAudioBitRate()
	{
		return BaselineStreamAudioBitRate;
	}
	public final void setBaselineStreamAudioBitRate(Integer value)
	{
		BaselineStreamAudioBitRate = value;
	}

	/** 
	 Gets or sets a value indicating whether [append baseline stream].
	 
	 <value><c>true</c> if [append baseline stream]; otherwise, <c>false</c>.</value>
	*/
	private boolean AppendBaselineStream;
	public final boolean getAppendBaselineStream()
	{
		return AppendBaselineStream;
	}
	public final void setAppendBaselineStream(boolean value)
	{
		AppendBaselineStream = value;
	}

	/** 
	 Gets or sets the time stamp offset ms. Only used with HLS.
	 
	 <value>The time stamp offset ms.</value>
	*/
	private Integer TimeStampOffsetMs = null;
	public final Integer getTimeStampOffsetMs()
	{
		return TimeStampOffsetMs;
	}
	public final void setTimeStampOffsetMs(Integer value)
	{
		TimeStampOffsetMs = value;
	}
}