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
}