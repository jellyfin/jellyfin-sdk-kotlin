package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;

import java.util.ArrayList;

public class ApiClientLogoutResponse extends EmptyResponse {

    private ArrayList<Integer> doneList;
    private int count;
    private EmptyResponse response;
    private ConnectionManager connectionManager;
    private ApiClient apiClient;

    public ApiClientLogoutResponse(ArrayList<Integer> doneList, int count, EmptyResponse response, ConnectionManager connectionManager, ApiClient apiClient) {
        this.doneList = doneList;
        this.count = count;
        this.response = response;
        this.connectionManager = connectionManager;
        this.apiClient = apiClient;
    }

    @Override
    public void onResponse() {

        onResponse(true);
    }

    public void onResponse(boolean wasSignedOut) {

        if (wasSignedOut) {
            connectionManager.OnLocalUserSignout(apiClient);
        }
        synchronized (doneList) {

            doneList.add(0);

            if (doneList.size() >= count) {
                response.onResponse();
            }
        }

    }

    @Override
    public void onError(Exception ex) {

        onResponse();
    }
}
