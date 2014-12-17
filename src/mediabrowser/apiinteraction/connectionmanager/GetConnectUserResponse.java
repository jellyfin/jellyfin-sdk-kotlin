package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.connect.ConnectUser;

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

        response.onError(ex);
    }
}
