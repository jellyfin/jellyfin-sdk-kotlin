package mediabrowser.model.apiclient;

import mediabrowser.model.extensions.*;

public class ServerCredentials
{
	private java.util.ArrayList<ServerInfo> Servers;
	public final java.util.ArrayList<ServerInfo> getServers()
	{
		return Servers;
	}
	public final void setServers(java.util.ArrayList<ServerInfo> value)
	{
		Servers = value;
	}

	private String ConnectUserId;
	public final String getConnectUserId()
	{
		return ConnectUserId;
	}
	public final void setConnectUserId(String value)
	{
		ConnectUserId = value;
	}
	private String ConnectAccessToken;
	public final String getConnectAccessToken()
	{
		return ConnectAccessToken;
	}
	public final void setConnectAccessToken(String value)
	{
		ConnectAccessToken = value;
	}

	public ServerCredentials()
	{
		setServers(new java.util.ArrayList<ServerInfo>());
	}

	public final void AddOrUpdateServer(ServerInfo server)
	{
		if (server == null)
		{
			throw new IllegalArgumentException("server");
		}

		// Clone the existing list of servers
		java.util.ArrayList<ServerInfo> list = new java.util.ArrayList<ServerInfo>();
		for (ServerInfo serverInfo : getServers())
		{
			list.add(serverInfo);
		}

		int index = FindIndex(list, server.getId());

		if (index != -1)
		{
			ServerInfo existing = list.get(index);

			// Take the most recent DateLastAccessed
			if (server.getDateLastAccessed().compareTo(existing.getDateLastAccessed()) > 0)
			{
				existing.setDateLastAccessed(server.getDateLastAccessed());
			}

			existing.setUserLinkType(server.getUserLinkType());

			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()))
			{
				existing.setAccessToken(server.getAccessToken());
				existing.setUserId(server.getUserId());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getExchangeToken()))
			{
				existing.setExchangeToken(server.getExchangeToken());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getRemoteAddress()))
			{
				existing.setRemoteAddress(server.getRemoteAddress());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getLocalAddress()))
			{
				existing.setLocalAddress(server.getLocalAddress());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getManualAddress()))
			{
				existing.setLocalAddress(server.getManualAddress());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getName()))
			{
				existing.setName(server.getName());
			}
			if (server.getWakeOnLanInfos() != null && server.getWakeOnLanInfos().size() > 0)
			{
				existing.setWakeOnLanInfos(new java.util.ArrayList<WakeOnLanInfo>());
				for (WakeOnLanInfo info : server.getWakeOnLanInfos())
				{
					existing.getWakeOnLanInfos().add(info);
				}
			}
			if (server.getLastConnectionMode() != null)
			{
				existing.setLastConnectionMode(server.getLastConnectionMode());
			}
			for (ServerUserInfo user : server.getUsers())
			{
				existing.AddOrUpdate(user);
			}
		}
		else
		{
			list.add(server);
		}

		setServers(list);
	}

	private int FindIndex(java.util.ArrayList<ServerInfo> servers, String id)
	{
		int index = 0;

		for (ServerInfo server : servers)
		{
			if (StringHelper.EqualsIgnoreCase(id, server.getId()))
			{
				return index;
			}

			index++;
		}

		return -1;
	}

	public final ServerInfo GetServer(String id)
	{
		for (ServerInfo server : getServers())
		{
			if (StringHelper.EqualsIgnoreCase(id, server.getId()))
			{
				return server;
			}
		}

		return null;
	}
}