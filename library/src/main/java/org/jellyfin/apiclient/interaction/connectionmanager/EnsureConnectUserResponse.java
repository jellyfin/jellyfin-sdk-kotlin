package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ConnectionMode;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.system.PublicSystemInfo;

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
