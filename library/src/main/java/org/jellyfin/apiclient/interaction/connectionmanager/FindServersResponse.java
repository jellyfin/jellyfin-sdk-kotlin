package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.util.ArrayList;

public class FindServersResponse extends Response<ArrayList<ServerInfo>> {

    private ConnectionManager connectionManager;
    private ServerCredentials credentials;
    private ArrayList<ServerInfo> foundServers;
    private int[] numTasksCompleted;
    private int numTasks;
    private Response<ArrayList<ServerInfo>> response;

    public FindServersResponse(ConnectionManager connectionManager, ServerCredentials credentials, ArrayList<ServerInfo> foundServers, int[] numTasksCompleted, int numTasks, Response<ArrayList<ServerInfo>> response) {
        this.connectionManager = connectionManager;
        this.credentials = credentials;
        this.foundServers = foundServers;
        this.numTasksCompleted = numTasksCompleted;
        this.numTasks = numTasks;
        this.response = response;
    }

    private void OnAny(ArrayList<ServerInfo> servers){

        synchronized (credentials){

            foundServers.addAll(servers);

            numTasksCompleted[0]++;

            if (numTasksCompleted[0] >= numTasks) {
                connectionManager.OnGetServerResponse(credentials, foundServers, response);
            }
        }
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> response) {
        OnAny(response);
    }

    @Override
    public void onError(Exception ex) {
        OnAny(new ArrayList<ServerInfo>());
    }
}
