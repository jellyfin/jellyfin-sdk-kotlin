package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

import java.util.ArrayList;

public class GetAvailableServersResponse extends Response<ArrayList<ServerInfo>> {

    private ILogger logger;
    private ConnectionManager connectionManager;
    private Response<ConnectionResult> response;

    public GetAvailableServersResponse(ILogger logger, ConnectionManager connectionManager, Response<ConnectionResult> response) {
        this.logger = logger;
        this.connectionManager = connectionManager;
        this.response = response;
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> servers) {

        logger.Debug("Looping through server list");
        connectionManager.Connect(servers, response);
    }
}
