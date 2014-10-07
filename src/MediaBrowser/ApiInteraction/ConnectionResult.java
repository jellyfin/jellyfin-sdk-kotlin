package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerInfo;

public class ConnectionResult {

    private ConnectionState state = ConnectionState.Unavailable;
    public final ConnectionState getState()
    {
        return state;
    }
    public final void setState(ConnectionState value)
    {
        state = value;
    }

    private ServerInfo serverInfo;
    public final ServerInfo getServerInfo()
    {
        return serverInfo;
    }
    public final void setServerInfo(ServerInfo value)
    {
        serverInfo = value;
    }

    private ApiClient apiClient;
    public final ApiClient getApiClient()
    {
        return apiClient;
    }
    public final void setApiClient(ApiClient value)
    {
        apiClient = value;
    }

}
