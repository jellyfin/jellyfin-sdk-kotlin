package MediaBrowser.Model.ApiClient;

import MediaBrowser.Model.Session.*;

/** 
 Class SessionUpdatesEventArgs
*/
public class SessionUpdatesEventArgs extends EventArgs
{
	private SessionInfoDto[] privateSessions;
	public final SessionInfoDto[] getSessions()
	{
		return privateSessions;
	}
	public final void setSessions(SessionInfoDto[] value)
	{
		privateSessions = value;
	}
}