package mediabrowser.model.session;

import mediabrowser.model.entities.*;

/** 
 Class PlaybackProgressInfo.
*/
public class PlaybackProgressInfo
{
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
	 Gets or sets the item.
	 
	 <value>The item.</value>
	*/
	private BaseItemInfo Item;
	public final BaseItemInfo getItem()
	{
		return Item;
	}
	public final void setItem(BaseItemInfo value)
	{
		Item = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
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
	 Gets or sets the session id.
	 
	 <value>The session id.</value>
	*/
	private String SessionId;
	public final String getSessionId()
	{
		return SessionId;
	}
	public final void setSessionId(String value)
	{
		SessionId = value;
	}

	/** 
	 Gets or sets the media version identifier.
	 
	 <value>The media version identifier.</value>
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
	 Gets or sets the index of the audio stream.
	 
	 <value>The index of the audio stream.</value>
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
	 Gets or sets the index of the subtitle stream.
	 
	 <value>The index of the subtitle stream.</value>
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
	 Gets or sets the position ticks.
	 
	 <value>The position ticks.</value>
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
	 Gets or sets the play method.
	 
	 <value>The play method.</value>
	*/
	private PlayMethod PlayMethod = getPlayMethod().values()[0];
	public final PlayMethod getPlayMethod()
	{
		return PlayMethod;
	}
	public final void setPlayMethod(PlayMethod value)
	{
		PlayMethod = value;
	}
	/** 
	 Gets or sets the live stream identifier.
	 
	 <value>The live stream identifier.</value>
	*/
	private String LiveStreamId;
	public final String getLiveStreamId()
	{
		return LiveStreamId;
	}
	public final void setLiveStreamId(String value)
	{
		LiveStreamId = value;
	}
}