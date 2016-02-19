package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;

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
