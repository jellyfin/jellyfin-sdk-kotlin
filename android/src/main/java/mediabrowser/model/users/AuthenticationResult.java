package mediabrowser.model.users;

import mediabrowser.model.dto.*;
import mediabrowser.model.session.*;

public class AuthenticationResult
{
	/** 
	 Gets or sets the user.
	 
	 <value>The user.</value>
	*/
	private UserDto User;
	public final UserDto getUser()
	{
		return User;
	}
	public final void setUser(UserDto value)
	{
		User = value;
	}

	/** 
	 Gets or sets the session information.
	 
	 <value>The session information.</value>
	*/
	private SessionInfoDto SessionInfo;
	public final SessionInfoDto getSessionInfo()
	{
		return SessionInfo;
	}
	public final void setSessionInfo(SessionInfoDto value)
	{
		SessionInfo = value;
	}

	/** 
	 Gets or sets the authentication token.
	 
	 <value>The authentication token.</value>
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

	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}
}