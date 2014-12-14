package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;

import java.util.ArrayList;

public class ApiClientLogoutResponse extends EmptyResponse {

    private ArrayList<EmptyResponse> doneList;
    private int count;
    private EmptyResponse response;

    public ApiClientLogoutResponse(ArrayList<EmptyResponse> doneList, int count, EmptyResponse response) {
        this.doneList = doneList;
        this.count = count;
        this.response = response;
    }

    @Override
    public void onResponse() {

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
