package MediaBrowser.Model.ApiClient;

import MediaBrowser.Model.Extensions.StringHelper;

import java.util.ArrayList;

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

        ArrayList<ServerInfo> list = (ArrayList) getServers().clone();

        int index = FindIndex(list, server.getId());

        if (index != -1)
        {
            list.set(index, server);
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
}
