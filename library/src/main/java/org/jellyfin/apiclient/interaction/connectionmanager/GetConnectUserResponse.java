package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.connect.ConnectUser;

public class GetConnectUserResponse extends Response<ConnectUser> {

    private ConnectionManager connectionManager;
    private EmptyResponse response;

    public GetConnectUserResponse(ConnectionManager connectionManager, EmptyResponse response) {
        this.connectionManager = connectionManager;
        this.response = response;
    }

    @Override
    public void onResponse(ConnectUser user) {

        connectionManager.OnConnectUserSignIn(user);
        response.onResponse();
    }

    @Override
    public void onError(Exception ex) {

        // logged at lower levels
        response.onResponse();
    }
}
