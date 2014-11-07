package mediabrowser;

import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.model.apiclient.ServerCredentials;

public class FakeCredentialProvider implements ICredentialProvider {

    private ServerCredentials config = new ServerCredentials();

    @Override
    public ServerCredentials GetCredentials() {
        return config;
    }

    @Override
    public void SaveCredentials(ServerCredentials credentials) {
        config = credentials;
    }
}
