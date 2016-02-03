package mediabrowser.apiinteraction;

import mediabrowser.model.apiclient.ServerCredentials;

public interface ICredentialProvider {

    ServerCredentials GetCredentials();

    void SaveCredentials(ServerCredentials credentials);
}
