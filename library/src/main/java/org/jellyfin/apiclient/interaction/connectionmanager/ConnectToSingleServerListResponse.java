package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ConnectionState;

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
            result.setState((ConnectionState.ServerSelection));
        }
        response.onResponse(result);
    }
}
