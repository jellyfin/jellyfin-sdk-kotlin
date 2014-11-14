package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;

import java.util.ArrayList;

public class WakeServerResponse extends EmptyResponse {

    private ArrayList<EmptyResponse> doneList;
    private int count;
    private EmptyResponse innerResponse;

    public WakeServerResponse(ArrayList<EmptyResponse> doneList, EmptyResponse innerResponse) {
        this.doneList = doneList;
        this.count = doneList.size();
        this.innerResponse = innerResponse;
    }

    private void OnServerDone(){

        synchronized(doneList) {

            doneList.add(new EmptyResponse());

            if (doneList.size() >= count){
                innerResponse.onResponse();
            }
        }
    }

    @Override
    public void onResponse() {

        OnServerDone();
    }

    @Override
    public void onError(Exception ex) {

        OnServerDone();
    }

}
