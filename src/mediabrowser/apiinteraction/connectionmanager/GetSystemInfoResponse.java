package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.system.SystemInfo;
import mediabrowser.model.users.AuthenticationResult;

import java.util.Date;

public class GetSystemInfoResponse extends Response<SystemInfo> {

    private ConnectionManager connectionManager;
    private ApiClient apiClient;
    private ICredentialProvider credentialProvider;
    private AuthenticationResult result;
    private boolean saveCredentials;

    public GetSystemInfoResponse(ConnectionManager connectionManager, ApiClient apiClient, ICredentialProvider credentialProvider, AuthenticationResult result, boolean saveCredentials) {
        this.connectionManager = connectionManager;
        this.apiClient = apiClient;
        this.credentialProvider = credentialProvider;
        this.result = result;
        this.saveCredentials = saveCredentials;
    }

    @Override
    public void onResponse(SystemInfo info) {

        ServerInfo server = apiClient.getServerInfo();
        server.ImportInfo(info);

        ServerCredentials credentials = credentialProvider.GetCredentials();

        server.setDateLastAccessed(new Date());

        if (saveCredentials)
        {
            server.setUserId(result.getUser().getId());
            server.setAccessToken(result.getAccessToken());
        }
        else {
            server.setUserId(null);
            server.setAccessToken(null);
        }

        credentials.AddOrUpdateServer(server);
        credentialProvider.SaveCredentials(credentials);

        connectionManager.EnsureWebSocketIfConfigured(apiClient);

        connectionManager.OnLocalUserSignIn(result.getUser());
    }
}
