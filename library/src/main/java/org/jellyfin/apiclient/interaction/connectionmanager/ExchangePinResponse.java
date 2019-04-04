package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ICredentialProvider;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.connect.PinExchangeResult;

public class ExchangePinResponse extends Response<PinExchangeResult> {

    private ICredentialProvider credentialProvider;
    private Response<PinExchangeResult> response;

    public ExchangePinResponse(ICredentialProvider credentialProvider, Response<PinExchangeResult> response) {
        this.credentialProvider = credentialProvider;
        this.response = response;
    }

    @Override
    public void onResponse(PinExchangeResult result) {

        ServerCredentials credentials = credentialProvider.GetCredentials();

        credentials.setConnectAccessToken(result.getAccessToken());
        credentials.setConnectUserId(result.getUserId());

        credentialProvider.SaveCredentials(credentials);

        response.onResponse(result);
    }

    @Override
    public void onError(Exception ex) {

        response.onError(ex);
    }
}
