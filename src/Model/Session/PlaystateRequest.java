package MediaBrowser.Model.Session;

public class PlaystateRequest
{
	private PlaystateCommand privateCommand = PlaystateCommand.values()[0];
	public final PlaystateCommand getCommand()
	{
		return privateCommand;
	}
	public final void setCommand(PlaystateCommand value)
	{
		privateCommand = value;
	}

	private Long privateSeekPositionTicks;
	public final Long getSeekPositionTicks()
	{
		return privateSeekPositionTicks;
	}
	public final void setSeekPositionTicks(Long value)
	{
		privateSeekPositionTicks = value;
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