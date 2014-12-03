package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.connect.ConnectAuthenticationResult;


public class LoginToConnectResponse extends Response<ConnectAuthenticationResult> {

    private ConnectionManager connectionManager;
    private ICredentialProvider credentialProvider;
    private EmptyResponse response;

    public LoginToConnectResponse(ConnectionManager connectionManager, ICredentialProvider credentialProvider, EmptyResponse response) {
        this.connectionManager = connectionManager;
        this.credentialProvider = credentialProvider;
        this.response = response;
    }

    @Override
    public void onResponse(ConnectAuthenticationResult result) {

        ServerCredentials credentials = credentialProvider.GetCredentials();

        credentials.setConnectAccessToken(result.getAccessToken());
        credentials.setConnectUserId(result.getUser().getId());

        credentialProvider.SaveCredentials(credentials);

        connectionManager.OnConnectUserSignIn(result.getUser());

        response.onResponse();
    }

    @Override
    public void onError(Exception ex) {

        response.onError(ex);
    }
}
