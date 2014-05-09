package MediaBrowser.Model.Session;

public class PlayerStateInfo
{
	/** 
	 Gets or sets the now playing position ticks.
	 
	 <value>The now playing position ticks.</value>
	*/
	private Long privatePositionTicks;
	public final Long getPositionTicks()
	{
		return privatePositionTicks;
	}
	public final void setPositionTicks(Long value)
	{
		privatePositionTicks = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance can seek.
	 
	 <value><c>true</c> if this instance can seek; otherwise, <c>false</c>.</value>
	*/
	private boolean privateCanSeek;
	public final boolean getCanSeek()
	{
		return privateCanSeek;
	}
	public final void setCanSeek(boolean value)
	{
		privateCanSeek = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is paused.
	 
	 <value><c>true</c> if this instance is paused; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsPaused;
	public final boolean getIsPaused()
	{
		return privateIsPaused;
	}
	public final void setIsPaused(boolean value)
	{
		privateIsPaused = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is muted.
	 
	 <value><c>true</c> if this instance is muted; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsMuted;
	public final boolean getIsMuted()
	{
		return privateIsMuted;
	}
	public final void setIsMuted(boolean value)
	{
		privateIsMuted = value;
	}

	/** 
	 Gets or sets the volume level.
	 
	 <value>The volume level.</value>
	*/
	private Integer privateVolumeLevel;
	public final Integer getVolumeLevel()
	{
		return privateVolumeLevel;
	}
	public final void setVolumeLevel(Integer value)
	{
		privateVolumeLevel = value;
	}

	/** 
	 Gets or sets the index of the now playing audio stream.
	 
	 <value>The index of the now playing audio stream.</value>
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
	 Gets or sets the index of the now playing subtitle stream.
	 
	 <value>The index of the now playing subtitle stream.</value>
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
	 Gets or sets the now playing media version identifier.
	 
	 <value>The now playing media version identifier.</value>
	*/
	private String privateMediaSourceId;
	public final String getMediaSourceId()
	{
		return privateMediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		privateMediaSourceId = value;
	}

	/** 
	 Gets or sets the play method.
	 
	 <value>The play method.</value>
	*/
	private PlayMethod privatePlayMethod;
	public final PlayMethod getPlayMethod()
	{
		return privatePlayMethod;
	}
	public final void setPlayMethod(PlayMethod value)
	{
		privatePlayMethod = value;
	}
}