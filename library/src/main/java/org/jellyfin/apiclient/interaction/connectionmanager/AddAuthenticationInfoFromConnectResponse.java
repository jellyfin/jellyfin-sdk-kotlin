package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;

/**
 * Created by Luke on 3/11/2015.
 */
public class AddAuthenticationInfoFromConnectResponse extends EmptyResponse {

    private EnsureConnectUserResponse parentResponse;

    public AddAuthenticationInfoFromConnectResponse(EnsureConnectUserResponse parentResponse) {
        this.parentResponse = parentResponse;
    }

    @Override
    public void onResponse() {

        parentResponse.onEnsureConnectUserDone();
    }

}
