package MediaBrowser.ApiInteraction;

public interface INetworkConnection {

    void SendWakeOnLan(String macAddress, int port, EmptyResponse response);

    void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response);

    NetworkStatus getNetworkStatus();
}
