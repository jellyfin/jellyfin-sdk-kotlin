package mediabrowser.model.session;

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