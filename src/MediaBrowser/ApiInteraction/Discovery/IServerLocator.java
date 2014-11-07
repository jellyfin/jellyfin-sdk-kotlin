package MediaBrowser.apiinteraction.discovery;

import MediaBrowser.apiinteraction.Response;
import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;

public interface IServerLocator {
    void FindServers(Response<ServerDiscoveryInfo[]> response);
}
