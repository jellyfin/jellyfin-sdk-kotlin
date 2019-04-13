package org.jellyfin.apiclient.interaction.network;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.model.apiclient.NetworkStatus;

public interface INetworkConnection {

    void SendWakeOnLan(String macAddress, int port, EmptyResponse response);

    void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response);

    NetworkStatus getNetworkStatus();
}
