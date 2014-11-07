package MediaBrowser;

import MediaBrowser.apiinteraction.ICredentialProvider;
import MediaBrowser.Model.ApiClient.ServerCredentials;

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
