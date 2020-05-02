package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.util.ArrayList;

public class GetAvailableServersResponse extends Response<ArrayList<ServerInfo>> {

    private ILogger logger;
    private ConnectionManager connectionManager;
    private Response<ConnectionResult> response;

    public GetAvailableServersResponse(ILogger logger, ConnectionManager connectionManager, Response<ConnectionResult> response) {
        this.logger = logger;
        this.connectionManager = connectionManager;
        this.response = response;
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> servers) {
        logger.debug("Looping through server list");
        connectionManager.Connect(servers, response);
    }
}
