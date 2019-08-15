package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.util.ArrayList;

public class FindServersResponse extends Response<ArrayList<ServerInfo>> {

    private ConnectionManager connectionManager;
    private ServerCredentials credentials;
    private ArrayList<ServerInfo> foundServers;
    private Response<ArrayList<ServerInfo>> response;

    public FindServersResponse(ConnectionManager connectionManager, ServerCredentials credentials, ArrayList<ServerInfo> foundServers, Response<ArrayList<ServerInfo>> response) {
        this.connectionManager = connectionManager;
        this.credentials = credentials;
        this.foundServers = foundServers;
        this.response = response;
    }

    private void OnAny(ArrayList<ServerInfo> servers) {
        synchronized (credentials) {
            foundServers.addAll(servers);
            connectionManager.OnGetServerResponse(credentials, foundServers, response);
        }
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> response) {
        OnAny(response);
    }

    @Override
    public void onError(Exception ex) {
        OnAny(new ArrayList<>());
    }
}
