package org.jellyfin.apiclient.interaction.discovery;

import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerDiscoveryInfo;

import java.util.ArrayList;

public interface IServerLocator {
    void FindServers(int timeoutMs, Response<ArrayList<ServerDiscoveryInfo>> response);
}
