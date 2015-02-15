package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;

import java.util.ArrayList;

public class ApiClientLogoutResponse extends EmptyResponse {

    private ArrayList<EmptyResponse> doneList;
    private int count;
    private EmptyResponse response;
    private ConnectionManager connectionManager;
    private ApiClient apiClient;

    public ApiClientLogoutResponse(ArrayList<EmptyResponse> doneList, int count, EmptyResponse response, ConnectionManager connectionManager, ApiClient apiClient) {
        this.doneList = doneList;
        this.count = count;
        this.response = response;
        this.connectionManager = connectionManager;
        this.apiClient = apiClient;
    }

    @Override
    public void onResponse() {

        connectionManager.OnLocalUserSignout(apiClient);

        synchronized (doneList) {

            doneList.add(new EmptyResponse());

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
