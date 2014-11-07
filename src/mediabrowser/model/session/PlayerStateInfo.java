package mediabrowser.model.session;

public class PlayerStateInfo
{
	/** 
	 Gets or sets the now playing position ticks.
	 
	 <value>The now playing position ticks.</value>
	*/
	private Long PositionTicks;
	public final Long getPositionTicks()
	{
		return PositionTicks;
	}
	public final void setPositionTicks(Long value)
	{
		PositionTicks = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance can seek.
	 
	 <value><c>true</c> if this instance can seek; otherwise, <c>false</c>.</value>
	*/
	private boolean CanSeek;
	public final boolean getCanSeek()
	{
		return CanSeek;
	}
	public final void setCanSeek(boolean value)
	{
		CanSeek = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is paused.
	 
	 <value><c>true</c> if this instance is paused; otherwise, <c>false</c>.</value>
	*/
	private boolean IsPaused;
	public final boolean getIsPaused()
	{
		return IsPaused;
	}
	public final void setIsPaused(boolean value)
	{
		IsPaused = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is muted.
	 
	 <value><c>true</c> if this instance is muted; otherwise, <c>false</c>.</value>
	*/
	private boolean IsMuted;
	public final boolean getIsMuted()
	{
		return IsMuted;
	}
	public final void setIsMuted(boolean value)
	{
		IsMuted = value;
	}

	/** 
	 Gets or sets the volume level.
	 
	 <value>The volume level.</value>
	*/
	private Integer VolumeLevel;
	public final Integer getVolumeLevel()
	{
		return VolumeLevel;
	}
	public final void setVolumeLevel(Integer value)
	{
		VolumeLevel = value;
	}

	/** 
	 Gets or sets the index of the now playing audio stream.
	 
	 <value>The index of the now playing audio stream.</value>
	*/
	private Integer AudioStreamIndex;
	public final Integer getAudioStreamIndex()
	{
		return AudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		AudioStreamIndex = value;
	}

	/** 
	 Gets or sets the index of the now playing subtitle stream.
	 
	 <value>The index of the now playing subtitle stream.</value>
	*/
	private Integer SubtitleStreamIndex;
	public final Integer getSubtitleStreamIndex()
	{
		return SubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		SubtitleStreamIndex = value;
	}

	/** 
	 Gets or sets the now playing media version identifier.
	 
	 <value>The now playing media version identifier.</value>
	*/
	private String MediaSourceId;
	public final String getMediaSourceId()
	{
		return MediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		MediaSourceId = value;
	}

	/** 
	 Gets or sets the play method.
	 
	 <value>The play method.</value>
	*/
	private PlayMethod PlayMethod;
	public final PlayMethod getPlayMethod()
	{
		return PlayMethod;
	}
	public final void setPlayMethod(PlayMethod value)
	{
		PlayMethod = value;
	}
}