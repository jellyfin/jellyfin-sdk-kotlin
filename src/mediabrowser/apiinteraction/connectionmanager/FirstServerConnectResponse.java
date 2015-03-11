package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ConnectionState;
import mediabrowser.model.apiclient.ServerInfo;

import java.util.ArrayList;

/**
 * Created by Luke on 3/11/2015.
 */
public class FirstServerConnectResponse extends Response<ConnectionResult> {

    private ConnectionManager connectionManager;
    private ArrayList<ServerInfo> servers;
    private Response<ConnectionResult> response;

    public FirstServerConnectResponse(ConnectionManager connectionManager, ArrayList<ServerInfo> servers, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.servers = servers;
        this.response = response;
    }

    @Override
    public void onResponse(ConnectionResult result) {

        if (result.getState() == ConnectionState.SignedIn) {
            response.onResponse(result);
        } else {
            connectionManager.OnFailedConnection(response, servers);
        }
    }
}
