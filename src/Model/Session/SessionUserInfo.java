package MediaBrowser.Model.Session;

/** 
 Class SessionUserInfo.
*/
public class SessionUserInfo
{
	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
	*/
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}
	/** 
	 Gets or sets the name of the user.
	 
	 <value>The name of the user.</value>
	*/
	private String privateUserName;
	public final String getUserName()
	{
		return privateUserName;
	}
	public final void setUserName(String value)
	{
		privateUserName = value;
	}
}