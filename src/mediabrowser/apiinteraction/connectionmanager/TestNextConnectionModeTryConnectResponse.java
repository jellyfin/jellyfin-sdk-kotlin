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
    private  boolean finalEnableRetry;
    private ILogger logger;
    private Response<ConnectionResult> response;

    public TestNextConnectionModeTryConnectResponse(ConnectionManager connectionManager, ServerInfo server, ArrayList<ConnectionMode> tests, ConnectionMode mode, String address, int finalTimeout, ConnectionOptions options, int index, boolean isLocalNetworkAvailable, long wakeOnLanSendTime, boolean finalEnableRetry, ILogger logger, Response<ConnectionResult> response) {
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
        this.finalEnableRetry = finalEnableRetry;
        this.logger = logger;
        this.response = response;
    }

    @Override
    public void onResponse(PublicSystemInfo result) {

        connectionManager.OnSuccessfulConnection(server, result, mode, options, response);
    }

    @Override
    public void onError(Exception ex) {

        if (finalEnableRetry){
            long sleepTime = 10000 - (System.currentTimeMillis() - wakeOnLanSendTime);

            if (sleepTime > 0){
                try {
                    Thread.sleep(sleepTime, 0);
                } catch (InterruptedException e) {
                    logger.ErrorException("Error in Thread.Sleep", e);
                }
            }

            connectionManager.TryConnect(address, finalTimeout, new TryConnectRetryResponse(connectionManager, server, tests, mode, options, index, isLocalNetworkAvailable, wakeOnLanSendTime, response));
        }
        else{
            connectionManager.TestNextConnectionMode(tests, index + 1, isLocalNetworkAvailable, server, wakeOnLanSendTime, options, response);
        }
    }

}
