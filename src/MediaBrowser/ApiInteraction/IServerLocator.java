package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;

public interface IServerLocator {
    void FindServers(Response<ServerDiscoveryInfo[]> response);
}
