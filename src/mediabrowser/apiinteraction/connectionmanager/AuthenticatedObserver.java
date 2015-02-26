package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.users.AuthenticationResult;

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
