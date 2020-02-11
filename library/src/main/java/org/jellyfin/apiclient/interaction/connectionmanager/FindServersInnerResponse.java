package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerDiscoveryInfo;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.util.ArrayList;

public class FindServersInnerResponse extends Response<ArrayList<ServerDiscoveryInfo>> {

    private ConnectionManager connectionManager;
    private Response<ArrayList<ServerInfo>> response;

    public FindServersInnerResponse(ConnectionManager connectionManager, Response<ArrayList<ServerInfo>> response) {
        this.connectionManager = connectionManager;
        this.response = response;
    }

    @Override
    public void onResponse(ArrayList<ServerDiscoveryInfo> foundServers) {
        ArrayList<ServerInfo> servers = new ArrayList<>();
        for (int i = 0; i < foundServers.size(); i++) {
            ServerInfo server = new ServerInfo();
            ServerDiscoveryInfo foundServer = foundServers.get(i);

            server.setId(foundServer.getId());
            server.setName(foundServer.getName());

            String localAddress = ConvertEndpointAddressToManualAddress(foundServer);
            if (localAddress != null) {
                server.setAddress(localAddress);
            } else {
                server.setAddress(foundServer.getAddress());
            }

            servers.add(server);
        }

        response.onResponse(servers);
    }

    @Override
    public void onError(Exception ex) {
        ArrayList<ServerInfo> servers = new ArrayList<>();
        response.onResponse(servers);
    }

    private String ConvertEndpointAddressToManualAddress(ServerDiscoveryInfo info) throws NumberFormatException {
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(info.getAddress()) && !tangible.DotNetToJavaStringHelper.isNullOrEmpty(info.getEndpointAddress())) {
            String address = info.getEndpointAddress().split(":")[0];

            // Determine the port
            String[] parts = info.getAddress().split(":");
            if (parts.length > 1) {
                String portString = parts[parts.length - 1];
                address += ":" + Integer.parseInt(portString);
            }

            return address;
        }

        return null;
    }
}
