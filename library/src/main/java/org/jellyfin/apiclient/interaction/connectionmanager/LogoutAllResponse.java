package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.ICredentialProvider;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.logging.ILogger;

import java.util.ArrayList;

public class LogoutAllResponse extends EmptyResponse {

    private ICredentialProvider credentialProvider;
    private ILogger logger;
    private EmptyResponse response;
    private ConnectionManager connectionManager;

    public LogoutAllResponse(ICredentialProvider credentialProvider, ILogger logger, EmptyResponse response, ConnectionManager connectionManager) {
        this.credentialProvider = credentialProvider;
        this.logger = logger;
        this.response = response;
        this.connectionManager = connectionManager;
    }

    private void OnSuccessOrFail() {
        logger.Debug("Updating saved credentials for all servers");
        ServerCredentials credentials = credentialProvider.GetCredentials();

        ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();

        for (ServerInfo server : credentials.getServers()) {
            server.setAccessToken(null);
            server.setUserId(null);

            servers.add(server);
        }

        credentials.setServers(servers);
        credentialProvider.SaveCredentials(credentials);

        response.onResponse();
    }

    @Override
    public void onResponse() {
        OnSuccessOrFail();
    }

    @Override
    public void onError(Exception ex) {
        OnSuccessOrFail();
    }
}
