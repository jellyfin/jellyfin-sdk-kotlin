package org.jellyfin.apiclient.model.apiclient;

import org.jellyfin.apiclient.model.system.*;

public class ServerInfo
{
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String Address;
	public final String getAddress()
	{
		return Address;
	}
	public final void setAddress(String value)
	{
		Address = value;
	}
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}
	private String AccessToken;
	public final String getAccessToken()
	{
		return AccessToken;
	}
	public final void setAccessToken(String value)
	{
		AccessToken = value;
	}
	private java.util.Date DateLastAccessed = new java.util.Date(0);
	public final java.util.Date getDateLastAccessed()
	{
		return DateLastAccessed;
	}
	public final void setDateLastAccessed(java.util.Date value)
	{
		DateLastAccessed = value;
	}

	public final void ImportInfo(PublicSystemInfo systemInfo)
	{
		if (systemInfo == null) {
			throw new IllegalArgumentException();
		}
		setName(systemInfo.getServerName());
		setId(systemInfo.getId());
	}
}