package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.system.PublicSystemInfo;

public class TryConnectResponse extends Response<PublicSystemInfo> {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private ConnectionOptions options;
    private ILogger logger;
    private Response<ConnectionResult> response;

    public TryConnectResponse(ConnectionManager connectionManager, ServerInfo server, ConnectionOptions options, ILogger logger, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.options = options;
        this.logger = logger;
        this.response = response;
    }

    @Override
    public void onResponse(PublicSystemInfo result) {
        if (this.server.getId() != null && !this.server.getId().equals(result.getId())) {
            onError(new Exception("Invalid server"));
        } else {
            connectionManager.OnSuccessfulConnection(server, result, options, response);
        }
    }

    @Override
    public void onError(Exception ex) {
        connectionManager.OnFailedConnection(response);
    }
}
