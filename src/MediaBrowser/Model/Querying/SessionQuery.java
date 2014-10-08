package MediaBrowser.Model.Querying;

/** 
 Class SessionQuery
*/
public class SessionQuery
{
	/** 
	 Filter by sessions that are allowed to be controlled by a given user
	*/
	private String ControllableByUserId;
	public final String getControllableByUserId()
	{
		return ControllableByUserId;
	}
	public final void setControllableByUserId(String value)
	{
		ControllableByUserId = value;
	}
}