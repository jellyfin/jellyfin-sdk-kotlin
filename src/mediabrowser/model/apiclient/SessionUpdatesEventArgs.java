package mediabrowser.model.apiclient;

import mediabrowser.model.session.*;

/** 
 Class SessionUpdatesEventArgs
*/
public class SessionUpdatesEventArgs
{
	private SessionInfoDto[] Sessions;
	public final SessionInfoDto[] getSessions()
	{
		return Sessions;
	}
	public final void setSessions(SessionInfoDto[] value)
	{
		Sessions = value;
	}
}