package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ConnectionState;

/**
 * Created by Luke on 2/21/2015.
 */
public class ConnectToSingleServerListResponse extends Response<ConnectionResult> {

    private Response<ConnectionResult> response;

    public ConnectToSingleServerListResponse(Response<ConnectionResult> response) {
        this.response = response;
    }

    @Override
    public void onResponse(ConnectionResult result) {

        if (result.getState() == ConnectionState.Unavailable) {
            result.setState((result.getConnectUser() == null ? ConnectionState.ConnectSignIn : ConnectionState.ServerSelection));
        }
        response.onResponse(result);
    }
}
