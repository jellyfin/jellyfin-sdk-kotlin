package mediabrowser.model.session;

public class PlaystateRequest
{
	private PlaystateCommand Command = PlaystateCommand.values()[0];
	public final PlaystateCommand getCommand()
	{
		return Command;
	}
	public final void setCommand(PlaystateCommand value)
	{
		Command = value;
	}

	private Long SeekPositionTicks;
	public final Long getSeekPositionTicks()
	{
		return SeekPositionTicks;
	}
	public final void setSeekPositionTicks(Long value)
	{
		SeekPositionTicks = value;
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