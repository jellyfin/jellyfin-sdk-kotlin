package MediaBrowser.ApiInteraction.Network;

import MediaBrowser.ApiInteraction.EmptyResponse;
import MediaBrowser.ApiInteraction.NetworkStatus;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public interface INetworkConnection {

    void SendWakeOnLan(String macAddress, int port, EmptyResponse response) throws IOException;

    void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response) throws IOException;

    NetworkStatus getNetworkStatus();
}
