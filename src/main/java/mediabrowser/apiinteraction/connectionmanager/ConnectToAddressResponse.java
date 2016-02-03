package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ConnectionMode;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.system.PublicSystemInfo;

/**
 * Created by Luke on 2/16/2015.
 */
public class ConnectToAddressResponse extends Response<PublicSystemInfo> {

    private ConnectionManager connectionManager;
    private String normalizedAddress;
    private Response<ConnectionResult> response;

    public ConnectToAddressResponse(ConnectionManager connectionManager, String normalizedAddress, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.normalizedAddress = normalizedAddress;
        this.response = response;
    }

    @Override
    public void onResponse(PublicSystemInfo result) {

        ServerInfo server = new ServerInfo();

        server.setManualAddress(normalizedAddress);
        server.setLastConnectionMode(ConnectionMode.Manual);
        server.ImportInfo(result);

        connectionManager.Connect(server, new ConnectionOptions(), response);
    }

    @Override
    public void onError(Exception ex) {

        connectionManager.OnFailedConnection(response);
    }

}
