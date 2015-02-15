package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.connect.UserLinkType;
import mediabrowser.model.logging.ILogger;

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

            if (server.getUserLinkType() == null ||
                    server.getUserLinkType() != UserLinkType.Guest){

                server.setAccessToken(null);
                server.setExchangeToken(null);
                server.setUserId(null);
                servers.add(server);
            }
        }

        credentials.setServers(servers);
        credentialProvider.SaveCredentials(credentials);

        connectionManager.clearConnectUserAfterLogout();

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
