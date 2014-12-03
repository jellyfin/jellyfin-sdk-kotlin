package mediabrowser.apiinteraction.discovery;

import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ServerDiscoveryInfo;

import java.util.ArrayList;

public interface IServerLocator {
    void FindServers(int timeoutMs, Response<ArrayList<ServerDiscoveryInfo>> response);
}
