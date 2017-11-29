package mediabrowser.model.session;

import mediabrowser.model.dto.MediaSourceInfo;

/**
 Class PlayRequest
*/
public class PlayRequest
{
	/** 
	 Gets or sets the item ids.
	 
	 <value>The item ids.</value>
	*/
	private String[] ItemIds;
	public final String[] getItemIds()
	{
		return ItemIds;
	}
	public final void setItemIds(String[] value)
	{
		ItemIds = value;
	}

	/** 
	 Gets or sets the start position ticks that the first item should be played at
	 
	 <value>The start position ticks.</value>
	*/
	private Long StartPositionTicks;
	public final Long getStartPositionTicks()
	{
		return StartPositionTicks;
	}
	public final void setStartPositionTicks(Long value)
	{
		StartPositionTicks = value;
	}

	/**
	 Gets or sets the audio stream index for the first item

	 <value>The audio.</value>
	 */
	private Long AudioStreamIndex;
	public final Long getAudioStreamIndex()
	{
		return AudioStreamIndex;
	}
	public final void setAudioStreamIndex(Long value)
	{
		AudioStreamIndex = value;
	}

	/**
	 Gets or sets the subtitle stream index for the first item

	 <value>The subtitle.</value>
	 */
	private Long SubtitleStreamIndex;
	public final Long getSubtitleStreamIndex()
	{
		return SubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Long value)
	{
		SubtitleStreamIndex = value;
	}

	/**
	 Gets or sets the media source ID for the first item

	 <value>The start position ticks.</value>
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
	 Gets or sets the play command.
	 
	 <value>The play command.</value>
	*/
	private PlayCommand PlayCommand = getPlayCommand().values()[0];
	public final PlayCommand getPlayCommand()
	{
		return PlayCommand;
	}
	public final void setPlayCommand(PlayCommand value)
	{
		PlayCommand = value;
	}

	/** 
	 Gets or sets the controlling user identifier.
	 
	 <value>The controlling user identifier.</value>
	*/
	private String ControllingUserId;
	public final String getControllingUserId()
	{
		return ControllingUserId;
	}
	public final void setControllingUserId(String value)
	{
		ControllingUserId = value;
	}
}