package mediabrowser.apiinteraction;

import mediabrowser.model.ApiClient.ServerCredentials;

public interface ICredentialProvider {

    ServerCredentials GetCredentials();

    void SaveCredentials(ServerCredentials credentials);
}
