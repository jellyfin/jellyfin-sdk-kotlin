package mediabrowser.model.dto;

/** 
 Class StreamOptions
*/
@Deprecated
public class StreamOptions
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
}