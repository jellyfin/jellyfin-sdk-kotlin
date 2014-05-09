package MediaBrowser.Model.Dto;

/** 
 Class StreamOptions
*/
public class StreamOptions
{
	/** 
	 Gets or sets the audio bit rate.
	 
	 <value>The audio bit rate.</value>
	*/
	private Integer privateAudioBitRate;
	public final Integer getAudioBitRate()
	{
		return privateAudioBitRate;
	}
	public final void setAudioBitRate(Integer value)
	{
		privateAudioBitRate = value;
	}

	/** 
	 Gets or sets the audio codec.
	 Omit to copy the original stream
	 
	 <value>The audio encoding format.</value>
	*/
	private String privateAudioCodec;
	public final String getAudioCodec()
	{
		return privateAudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		privateAudioCodec = value;
	}

	/** 
	 Gets or sets the item id.
	 
	 <value>The item id.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	/** 
	 Gets or sets the max audio channels.
	 
	 <value>The max audio channels.</value>
	*/
	private Integer privateMaxAudioChannels;
	public final Integer getMaxAudioChannels()
	{
		return privateMaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		privateMaxAudioChannels = value;
	}

	/** 
	 Gets or sets the max audio sample rate.
	 
	 <value>The max audio sample rate.</value>
	*/
	private Integer privateMaxAudioSampleRate;
	public final Integer getMaxAudioSampleRate()
	{
		return privateMaxAudioSampleRate;
	}
	public final void setMaxAudioSampleRate(Integer value)
	{
		privateMaxAudioSampleRate = value;
	}

	/** 
	 Gets or sets the start time ticks.
	 
	 <value>The start time ticks.</value>
	*/
	private Long privateStartTimeTicks;
	public final Long getStartTimeTicks()
	{
		return privateStartTimeTicks;
	}
	public final void setStartTimeTicks(Long value)
	{
		privateStartTimeTicks = value;
	}

	/** 
	 Gets or sets a value indicating whether the original media should be served statically
	 Only used with progressive streaming
	 
	 <value><c>true</c> if static; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateStatic;
	public final Boolean getStatic()
	{
		return privateStatic;
	}
	public final void setStatic(Boolean value)
	{
		privateStatic = value;
	}

	/** 
	 Gets or sets the output file extension.
	 
	 <value>The output file extension.</value>
	*/
	private String privateOutputFileExtension;
	public final String getOutputFileExtension()
	{
		return privateOutputFileExtension;
	}
	public final void setOutputFileExtension(String value)
	{
		privateOutputFileExtension = value;
	}

	/** 
	 Gets or sets the device id.
	 
	 <value>The device id.</value>
	*/
	private String privateDeviceId;
	public final String getDeviceId()
	{
		return privateDeviceId;
	}
	public final void setDeviceId(String value)
	{
		privateDeviceId = value;
	}
}