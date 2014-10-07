package MediaBrowser.ApiInteraction;

public interface ICredentialProvider {

    ServerCredentialConfiguration GetCredentials();

    void SaveCredentials(ServerCredentialConfiguration credentials);
}
