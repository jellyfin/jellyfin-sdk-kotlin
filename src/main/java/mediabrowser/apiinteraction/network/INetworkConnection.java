package mediabrowser.apiinteraction.network;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.model.apiclient.NetworkStatus;

public interface INetworkConnection {

    void SendWakeOnLan(String macAddress, int port, EmptyResponse response);

    void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response);

    NetworkStatus getNetworkStatus();
}
