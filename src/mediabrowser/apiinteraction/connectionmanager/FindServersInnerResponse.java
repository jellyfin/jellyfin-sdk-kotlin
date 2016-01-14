package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerDiscoveryInfo;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.extensions.IntHelper;

import java.util.ArrayList;
import java.util.Date;

public class FindServersInnerResponse extends Response<ArrayList<ServerDiscoveryInfo>> {

    private ConnectionManager connectionManager;
    private Response<ArrayList<ServerInfo>> response;

    public FindServersInnerResponse(ConnectionManager connectionManager, Response<ArrayList<ServerInfo>> response) {
        this.connectionManager = connectionManager;
        this.response = response;
    }

    @Override
    public void onResponse(ArrayList<ServerDiscoveryInfo> foundServers) {

        ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();

        for (int i = 0; i < foundServers.size(); i++) {

            ServerInfo server = new ServerInfo();
            ServerDiscoveryInfo foundServer = foundServers.get(i);

            server.setId(foundServer.getId());
            server.setName(foundServer.getName());

            String localAddress = ConvertEndpointAddressToManualAddress(foundServer);
            if (localAddress == null) {
                localAddress = foundServer.getAddress();
            }
            server.setLocalAddress(localAddress);
            server.setDateLastLocalConnection(new Date().getTime());

            servers.add(server);
        }

        response.onResponse(servers);
    }

    @Override
    public void onError(Exception ex) {

        ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();

        response.onResponse(servers);
    }

    private String ConvertEndpointAddressToManualAddress(ServerDiscoveryInfo info)
    {
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(info.getAddress()) && !tangible.DotNetToJavaStringHelper.isNullOrEmpty(info.getEndpointAddress()))
        {
            String address = info.getEndpointAddress().split(":")[0];

            // Determine the port, if any
            String[] parts = info.getAddress().split(":");
            if (parts.length > 1)
            {
                String portString = parts[parts.length-1];

                int port = 0;
                tangible.RefObject<Integer> tempRef_expected = new tangible.RefObject<Integer>(port);
                if (IntHelper.TryParseCultureInvariant(portString, tempRef_expected))
                {
                    address += ":" + portString;
                }
            }

            return connectionManager.NormalizeAddress(address);
        }

        return null;
    }
}
