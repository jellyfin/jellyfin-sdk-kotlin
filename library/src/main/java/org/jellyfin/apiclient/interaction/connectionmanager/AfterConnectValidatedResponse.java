package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.system.PublicSystemInfo;

/**
 * Created by Luke on 2/26/2015.
 */
public class AfterConnectValidatedResponse extends EmptyResponse {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private PublicSystemInfo systemInfo;
    private ConnectionOptions options;
    private Response<ConnectionResult> response;

    public AfterConnectValidatedResponse(ConnectionManager connectionManager, ServerInfo server, PublicSystemInfo systemInfo, ConnectionOptions options, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.systemInfo = systemInfo;
        this.options = options;
        this.response = response;
    }

    @Override
    public void onResponse() {
        connectionManager.AfterConnectValidated(server, systemInfo, false, options, response);
    }
}
