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
}