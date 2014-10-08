package MediaBrowser.ApiInteraction.Discovery;

import MediaBrowser.ApiInteraction.Response;
import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;

public interface IServerLocator {
    void FindServers(Response<ServerDiscoveryInfo[]> response);
}
