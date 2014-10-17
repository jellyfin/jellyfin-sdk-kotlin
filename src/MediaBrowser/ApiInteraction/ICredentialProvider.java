package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerCredentials;

public interface ICredentialProvider {

    ServerCredentials GetCredentials();

    void SaveCredentials(ServerCredentials credentials);
}
