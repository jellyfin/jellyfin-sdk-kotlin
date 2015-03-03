package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.EmptyResponse;

/**
 * Created by Luke on 3/3/2015.
 */
public class UpdateOfflineUsersResponse extends EmptyResponse {

    private SyncProgress progress;

    public UpdateOfflineUsersResponse(SyncProgress progress) {

        this.progress = progress;
    }

    @Override
    public void onError(Exception ex){

        progress.reportError(ex);
    }

    @Override
    public void onResponse(){

        progress.reportComplete();
    }

}
