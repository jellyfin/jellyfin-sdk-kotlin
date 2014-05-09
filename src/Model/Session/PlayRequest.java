package MediaBrowser.Model.Session;

/** 
 Class PlayRequest
*/
public class PlayRequest
{
	/** 
	 Gets or sets the item ids.
	 
	 <value>The item ids.</value>
	*/
	private String[] privateItemIds;
	public final String[] getItemIds()
	{
		return privateItemIds;
	}
	public final void setItemIds(String[] value)
	{
		privateItemIds = value;
	}

	/** 
	 Gets or sets the start position ticks that the first item should be played at
	 
	 <value>The start position ticks.</value>
	*/
	private Long privateStartPositionTicks;
	public final Long getStartPositionTicks()
	{
		return privateStartPositionTicks;
	}
	public final void setStartPositionTicks(Long value)
	{
		privateStartPositionTicks = value;
	}

	/** 
	 Gets or sets the play command.
	 
	 <value>The play command.</value>
	*/
	private PlayCommand privatePlayCommand = getPlayCommand().values()[0];
	public final PlayCommand getPlayCommand()
	{
		return privatePlayCommand;
	}
	public final void setPlayCommand(PlayCommand value)
	{
		privatePlayCommand = value;
	}

	/** 
	 Gets or sets the controlling user identifier.
	 
	 <value>The controlling user identifier.</value>
	*/
	private String privateControllingUserId;
	public final String getControllingUserId()
	{
		return privateControllingUserId;
	}
	public final void setControllingUserId(String value)
	{
		privateControllingUserId = value;
	}
}