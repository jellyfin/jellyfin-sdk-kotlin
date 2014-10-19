package MediaBrowser.Model.Connect;

public class ConnectAuthenticationExchangeResult
{
	/** 
	 Gets or sets the local user identifier.
	 
	 <value>The local user identifier.</value>
	*/
	private String LocalUserId;
	public final String getLocalUserId()
	{
		return LocalUserId;
	}
	public final void setLocalUserId(String value)
	{
		LocalUserId = value;
	}
	/** 
	 Gets or sets the access token.
	 
	 <value>The access token.</value>
	*/
	private String AccessToken;
	public final String getAccessToken()
	{
		return AccessToken;
	}
	public final void setAccessToken(String value)
	{
		AccessToken = value;
	}
}