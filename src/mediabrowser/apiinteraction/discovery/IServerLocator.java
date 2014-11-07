package mediabrowser.apiinteraction.discovery;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerDiscoveryInfo;

public interface IServerLocator {
    void FindServers(Response<ServerDiscoveryInfo[]> response);
}
