package MediaBrowser.ApiInteraction.Network;

import MediaBrowser.ApiInteraction.EmptyResponse;
import MediaBrowser.ApiInteraction.INetworkConnection;
import MediaBrowser.ApiInteraction.NetworkStatus;

public class NetworkConnection implements INetworkConnection {

    @Override
    public void SendWakeOnLan(String macAddress, int port, EmptyResponse response) {

        // Send WOL to broadcast address
        // TODO: Implement
    }

    @Override
    public void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response) {

        // Send WOL to specific Ip address
        // TODO: Implement
    }

    @Override
    public NetworkStatus getNetworkStatus() {

        NetworkStatus status = new NetworkStatus();

        // TODO: set these values
        status.setIsNetworkAvailable(true);
        status.setIsLocalNetworkAvailable(true);
        status.setIsRemoteNetworkAvailable(true);

        return status;
    }
}
