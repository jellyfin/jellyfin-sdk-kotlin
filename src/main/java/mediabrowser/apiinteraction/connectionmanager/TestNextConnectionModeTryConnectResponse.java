package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ConnectionMode;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.system.PublicSystemInfo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luke on 2/21/2015.
 */
public class TestNextConnectionModeTryConnectResponse extends Response<PublicSystemInfo> {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private ArrayList<ConnectionMode> tests;
    private ConnectionMode mode;
    private String address;
    private int finalTimeout;
    private ConnectionOptions options;
    private int index;
    private boolean isLocalNetworkAvailable;
    private long wakeOnLanSendTime;
    private ILogger logger;
    private Response<ConnectionResult> response;

    public TestNextConnectionModeTryConnectResponse(ConnectionManager connectionManager, ServerInfo server, ArrayList<ConnectionMode> tests, ConnectionMode mode, String address, int finalTimeout, ConnectionOptions options, int index, boolean isLocalNetworkAvailable, long wakeOnLanSendTime, ILogger logger, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.tests = tests;
        this.mode = mode;
        this.address = address;
        this.finalTimeout = finalTimeout;
        this.options = options;
        this.index = index;
        this.isLocalNetworkAvailable = isLocalNetworkAvailable;
        this.wakeOnLanSendTime = wakeOnLanSendTime;
        this.logger = logger;
        this.response = response;
    }

    @Override
    public void onResponse(PublicSystemInfo result) {

        if (result == null){
            connectionManager.OnSuccessfulConnection(server, result, mode, options, response);
        }

        else{

            logger.Error("Somehow we got into TestNextConnectionModeTryConnectResponse.onResponse with a null response.");
            onError(new Exception());
        }
    }

    @Override
    public void onError(Exception ex) {

        connectionManager.TestNextConnectionMode(tests, index + 1, isLocalNetworkAvailable, server, wakeOnLanSendTime, options, response);
    }

}
