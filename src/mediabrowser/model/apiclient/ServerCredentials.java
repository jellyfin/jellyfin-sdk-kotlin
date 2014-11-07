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

		java.util.ArrayList<Object> list = getServers().ToList();

		int index = FindIndex(list, server.getId());

		if (index != -1)
		{
			Object existing = list.get(index);

			// Merge the data
			existing.DateLastAccessed = new Object[] {existing.DateLastAccessed, server.getDateLastAccessed()}.Max();

			existing.UserLinkType = server.getUserLinkType();

			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()))
			{
				existing.AccessToken = server.getAccessToken();
				existing.UserId = server.getUserId();
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getExchangeToken()))
			{
				existing.ExchangeToken = server.getExchangeToken();
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getRemoteAddress()))
			{
				existing.RemoteAddress = server.getRemoteAddress();
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getLocalAddress()))
			{
				existing.LocalAddress = server.getLocalAddress();
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getName()))
			{
				existing.Name = server.getName();
			}
			if (server.getWakeOnLanInfos() != null && server.getWakeOnLanInfos().size() > 0)
			{
				existing.WakeOnLanInfos = server.getWakeOnLanInfos().ToList();
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
			if (StringHelper.equals(id, server.getId()))
			{
				return index;
			}

			index++;
		}

		return -1;
	}
}