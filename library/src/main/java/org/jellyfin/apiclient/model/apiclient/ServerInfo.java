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
	private String LocalAddress;
	public final String getLocalAddress()
	{
		return LocalAddress;
	}
	public final void setLocalAddress(String value)
	{
		LocalAddress = value;
	}
	private String RemoteAddress;
	public final String getRemoteAddress()
	{
		return RemoteAddress;
	}
	public final void setRemoteAddress(String value)
	{
		RemoteAddress = value;
	}
	private String ManualAddress;
	public final String getManualAddress()
	{
		return ManualAddress;
	}
	public final void setManualAddress(String value)
	{
		ManualAddress = value;
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
	private ConnectionMode LastConnectionMode = null;
	public final ConnectionMode getLastConnectionMode()
	{
		return LastConnectionMode;
	}
	public final void setLastConnectionMode(ConnectionMode value)
	{
		LastConnectionMode = value;
	}

	public ServerInfo()
	{
	}

	public final void ImportInfo(PublicSystemInfo systemInfo)
	{
		if (systemInfo == null) {
			throw new IllegalArgumentException();
		}
		setName(systemInfo.getServerName());
		setId(systemInfo.getId());

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(systemInfo.getLocalAddress()))
		{
			setLocalAddress(systemInfo.getLocalAddress());
		}

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(systemInfo.getWanAddress()))
		{
			setRemoteAddress(systemInfo.getWanAddress());
		}
	}

	public final String GetAddress(ConnectionMode mode)
	{
		switch (mode)
		{
			case Local:
				return getLocalAddress();
			case Manual:
				return getManualAddress();
			case Remote:
				return getRemoteAddress();
			default:
				throw new IllegalArgumentException("Unexpected ConnectionMode");
		}
	}
}