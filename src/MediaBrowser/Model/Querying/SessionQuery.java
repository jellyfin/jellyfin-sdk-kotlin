package MediaBrowser.Model.Querying;

/** 
 Class SessionQuery
*/
public class SessionQuery
{
	/** 
	 Filter by sessions that are allowed to be controlled by a given user
	*/
	private String privateControllableByUserId;
	public final String getControllableByUserId()
	{
		return privateControllableByUserId;
	}
	public final void setControllableByUserId(String value)
	{
		privateControllableByUserId = value;
	}

	/** 
	 Filter by sessions that either do or do not support remote control. Default returns all sessions.
	*/
	private Boolean privateSupportsRemoteControl;
	public final Boolean getSupportsRemoteControl()
	{
		return privateSupportsRemoteControl;
	}
	public final void setSupportsRemoteControl(Boolean value)
	{
		privateSupportsRemoteControl = value;
	}
}