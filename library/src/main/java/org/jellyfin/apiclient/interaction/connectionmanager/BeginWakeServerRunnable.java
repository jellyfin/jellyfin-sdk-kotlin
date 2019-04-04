package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;

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
