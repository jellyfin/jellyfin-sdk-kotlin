package org.jellyfin.apiclient.model.apiclient;

import org.jellyfin.apiclient.model.session.*;

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