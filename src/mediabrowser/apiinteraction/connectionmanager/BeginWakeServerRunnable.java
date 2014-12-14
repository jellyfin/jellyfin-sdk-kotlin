package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.model.apiclient.ServerInfo;
import org.apache.maven.settings.Server;

public class BeginWakeServerRunnable implements Runnable {

    private ConnectionManager connectionManager;
    private ServerInfo info;

    public BeginWakeServerRunnable(ConnectionManager connectionManager, ServerInfo info) {
        this.connectionManager = connectionManager;
        this.info = info;
    }

    @Override
    public void run() {
        connectionManager.WakeServer(info, new EmptyResponse());
    }
}
