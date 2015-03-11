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
 * Created by Luke on 3/11/2015.
 */
public class EnsureConnectUserResponse extends EmptyResponse {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private ServerCredentials credentials;
    private PublicSystemInfo systemInfo;
    private ConnectionMode connectionMode;
    private ConnectionOptions connectionOptions;
    private Response<ConnectionResult> response;

    public EnsureConnectUserResponse(ConnectionManager connectionManager, ServerInfo server, ServerCredentials credentials, PublicSystemInfo systemInfo, ConnectionMode connectionMode, ConnectionOptions connectionOptions, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.credentials = credentials;
        this.systemInfo = systemInfo;
        this.connectionMode = connectionMode;
        this.connectionOptions = connectionOptions;
        this.response = response;
    }

    void onEnsureConnectUserDone(){
        connectionManager.AfterConnectValidated(server, credentials, systemInfo, connectionMode, true, connectionOptions, response);
    }

    @Override
    public void onResponse() {

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getExchangeToken())){
            connectionManager.AddAuthenticationInfoFromConnect(server, connectionMode, credentials, new AddAuthenticationInfoFromConnectResponse(this));
        }
        else{
            onEnsureConnectUserDone();
        }
    }

}
