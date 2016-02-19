package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ConnectionMode;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.system.PublicSystemInfo;

import java.util.ArrayList;

/**
 * Created by Luke on 2/21/2015.
 */
public class TryConnectRetryResponse extends Response<PublicSystemInfo> {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private ArrayList<ConnectionMode> tests;
    private ConnectionMode mode;
    private ConnectionOptions options;
    private int index;
    private boolean isLocalNetworkAvailable;
    private long wakeOnLanSendTime;
    private Response<ConnectionResult> response;

    public TryConnectRetryResponse(ConnectionManager connectionManager, ServerInfo server, ArrayList<ConnectionMode> tests, ConnectionMode mode, ConnectionOptions options, int index, boolean isLocalNetworkAvailable, long wakeOnLanSendTime, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.tests = tests;
        this.mode = mode;
        this.options = options;
        this.index = index;
        this.isLocalNetworkAvailable = isLocalNetworkAvailable;
        this.wakeOnLanSendTime = wakeOnLanSendTime;
        this.response = response;
    }

    @Override
    public void onResponse(PublicSystemInfo result) {

        connectionManager.OnSuccessfulConnection(server, result, mode, options, response);
    }

    @Override
    public void onError(Exception ex) {

        connectionManager.TestNextConnectionMode(tests, index + 1, isLocalNetworkAvailable, server, wakeOnLanSendTime, options, response);
    }

}
