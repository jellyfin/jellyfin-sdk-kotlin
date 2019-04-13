package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ApiClient;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.users.AuthenticationResult;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Luke on 2/26/2015.
 */
public class AuthenticatedObserver implements Observer {

    private  ConnectionManager connectionManager;
    private ApiClient apiClient;

    public AuthenticatedObserver(ConnectionManager connectionManager, ApiClient apiClient) {
        this.connectionManager = connectionManager;
        this.apiClient = apiClient;
    }

    @Override
    public void update(Observable observable, Object o) {
        connectionManager.OnAuthenticated(apiClient, (AuthenticationResult) o, new ConnectionOptions(), true);
    }
}
