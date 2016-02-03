package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ConnectionMode;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.system.PublicSystemInfo;

/**
 * Created by Luke on 2/26/2015.
 */
public class AfterConnectValidatedResponse extends EmptyResponse {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private ServerCredentials credentials;
    private PublicSystemInfo systemInfo;
    private ConnectionMode connectionMode;
    private ConnectionOptions options;
    private Response<ConnectionResult> response;

    public AfterConnectValidatedResponse(ConnectionManager connectionManager, ServerInfo server, ServerCredentials credentials, PublicSystemInfo systemInfo, ConnectionMode connectionMode, ConnectionOptions options, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.credentials = credentials;
        this.systemInfo = systemInfo;
        this.connectionMode = connectionMode;
        this.options = options;
        this.response = response;
    }

    @Override
    public void onResponse() {

        connectionManager.AfterConnectValidated(server, credentials, systemInfo, connectionMode, false, options, response);
    }

}
