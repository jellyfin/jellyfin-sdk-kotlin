package mediabrowser.apiinteraction.discovery;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.ApiClient.ServerDiscoveryInfo;

public interface IServerLocator {
    void FindServers(Response<ServerDiscoveryInfo[]> response);
}
