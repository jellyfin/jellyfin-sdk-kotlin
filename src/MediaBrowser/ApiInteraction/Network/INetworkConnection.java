package MediaBrowser.apiinteraction.network;

import MediaBrowser.apiinteraction.EmptyResponse;
import MediaBrowser.apiinteraction.NetworkStatus;

import java.io.IOException;

public interface INetworkConnection {

    void SendWakeOnLan(String macAddress, int port, EmptyResponse response);

    void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response);

    NetworkStatus getNetworkStatus();
}
