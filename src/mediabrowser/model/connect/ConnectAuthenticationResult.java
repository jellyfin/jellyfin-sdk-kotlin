package mediabrowser.model.connect;

public class ConnectAuthenticationResult
{
	/** 
	 Gets or sets the user.
	 
	 <value>The user.</value>
	*/
	private ConnectUser User;
	public final ConnectUser getUser()
	{
		return User;
	}
	public final void setUser(ConnectUser value)
	{
		User = value;
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