package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.util.ArrayList;

public class FindServersRunnable implements Runnable {

    private AndroidConnectionManager connectionManager;
    private Response<ArrayList<ServerInfo>> response;

    public FindServersRunnable(AndroidConnectionManager connectionManager, Response<ArrayList<ServerInfo>> response) {
        this.connectionManager = connectionManager;
        this.response = response;
    }

    @Override
    public void run() {
        connectionManager.FindServersAndroid(response);
    }
}
