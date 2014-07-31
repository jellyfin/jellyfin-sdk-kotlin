package MediaBrowser.Model.Users;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Session.*;

public class AuthenticationResult
{
	/** 
	 Gets or sets the user.
	 
	 <value>The user.</value>
	*/
	private UserDto privateUser;
	public final UserDto getUser()
	{
		return privateUser;
	}
	public final void setUser(UserDto value)
	{
		privateUser = value;
	}

	/** 
	 Gets or sets the session information.
	 
	 <value>The session information.</value>
	*/
	private SessionInfoDto privateSessionInfo;
	public final SessionInfoDto getSessionInfo()
	{
		return privateSessionInfo;
	}
	public final void setSessionInfo(SessionInfoDto value)
	{
		privateSessionInfo = value;
	}

	/** 
	 Gets or sets the authentication token.
	 
	 <value>The authentication token.</value>
	*/
	private String privateAccessToken;
	public final String getAccessToken()
	{
		return privateAccessToken;
	}
	public final void setAccessToken(String value)
	{
		privateAccessToken = value;
	}

	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String privateServerId;
	public final String getServerId()
	{
		return privateServerId;
	}
	public final void setServerId(String value)
	{
		privateServerId = value;
	}
}