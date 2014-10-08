package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerInfo;
import MediaBrowser.Model.Extensions.StringHelper;

public class ServerCredentialConfiguration {

    private String lastServerId;
    public final String getLastServerId()
    {
        return lastServerId;
    }
    public void setLastServerId(String value)
    {
        lastServerId = value;
    }

    private java.util.ArrayList<ServerInfo> servers;
    public final java.util.ArrayList<ServerInfo> getServers()
    {
        return servers;
    }
    public final void setServers(java.util.ArrayList<ServerInfo> value)
    {
        servers = value;
    }

    public void AddOrUpdateServer(ServerInfo server)
    {
        if (server == null)
        {
            throw new IllegalArgumentException("server");
        }

        java.util.ArrayList<ServerInfo> list = getServers();

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

        for(ServerInfo server : servers)
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
