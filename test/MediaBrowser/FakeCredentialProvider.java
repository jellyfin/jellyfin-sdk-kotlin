package MediaBrowser;

import MediaBrowser.ApiInteraction.ICredentialProvider;
import MediaBrowser.ApiInteraction.ServerCredentialConfiguration;

public class FakeCredentialProvider implements ICredentialProvider {

    private ServerCredentialConfiguration config = new ServerCredentialConfiguration();

    @Override
    public ServerCredentialConfiguration GetCredentials() {
        return config;
    }

    @Override
    public void SaveCredentials(ServerCredentialConfiguration credentials) {
        config = credentials;
    }
}
