package mediabrowser.model.apiclient;

import mediabrowser.model.connect.*;
import mediabrowser.model.extensions.*;
import mediabrowser.model.system.*;

public class ServerInfo
{
	private java.util.ArrayList<ServerUserInfo> Users;
	public final java.util.ArrayList<ServerUserInfo> getUsers()
	{
		return Users;
	}
	public final void setUsers(java.util.ArrayList<ServerUserInfo> value)
	{
		Users = value;
	}

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
	private java.util.ArrayList<WakeOnLanInfo> WakeOnLanInfos;
	public final java.util.ArrayList<WakeOnLanInfo> getWakeOnLanInfos()
	{
		return WakeOnLanInfos;
	}
	public final void setWakeOnLanInfos(java.util.ArrayList<WakeOnLanInfo> value)
	{
		WakeOnLanInfos = value;
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
	private long DateLastLocalConnection = 0;
	public final long getDateLastLocalConnection()
	{
		return DateLastLocalConnection;
	}
	public final void setDateLastLocalConnection(long value)
	{
		DateLastLocalConnection = value;
	}
	private String ExchangeToken;
	public final String getExchangeToken()
	{
		return ExchangeToken;
	}
	public final void setExchangeToken(String value)
	{
		ExchangeToken = value;
	}
	private UserLinkType UserLinkType = null;
	public final UserLinkType getUserLinkType()
	{
		return UserLinkType;
	}
	public final void setUserLinkType(UserLinkType value)
	{
		UserLinkType = value;
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
		setWakeOnLanInfos(new java.util.ArrayList<WakeOnLanInfo>());
		setUsers(new java.util.ArrayList<ServerUserInfo>());
	}

	public final void ImportInfo(PublicSystemInfo systemInfo)
	{
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

		SystemInfo fullSystemInfo = (SystemInfo)((systemInfo instanceof SystemInfo) ? systemInfo : null);

		if (fullSystemInfo != null)
		{
			setWakeOnLanInfos(new java.util.ArrayList<WakeOnLanInfo>());

			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(fullSystemInfo.getMacAddress()))
			{
				WakeOnLanInfo tempVar = new WakeOnLanInfo();
				tempVar.setMacAddress(fullSystemInfo.getMacAddress());
				getWakeOnLanInfos().add(tempVar);
			}
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

	public final void AddOrUpdate(ServerUserInfo user)
	{
		if (user == null)
		{
			throw new IllegalArgumentException("user");
		}

		// Clone the existing list of users
		java.util.ArrayList<ServerUserInfo> list = new java.util.ArrayList<ServerUserInfo>();
		for (ServerUserInfo serverUserInfo : getUsers())
		{
			list.add(serverUserInfo);
		}

		int index = FindIndex(list, user.getId());

		if (index != -1)
		{
			ServerUserInfo existing = list.get(index);

			// Merge the data
			existing.setIsSignedInOffline(user.getIsSignedInOffline());
		}
		else
		{
			list.add(user);
		}

		setUsers(list);
	}

	private int FindIndex(java.util.ArrayList<ServerUserInfo> users, String id)
	{
		int index = 0;

		for (ServerUserInfo user : users)
		{
			if (StringHelper.EqualsIgnoreCase(id, user.getId()))
			{
				return index;
			}

			index++;
		}

		return -1;
	}
}