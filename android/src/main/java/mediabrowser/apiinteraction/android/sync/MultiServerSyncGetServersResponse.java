package mediabrowser.apiinteraction.android.sync;

import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ServerInfo;

import java.util.ArrayList;

public class MultiServerSyncGetServersResponse extends Response<ArrayList<ServerInfo>> {

    private MultiServerSync multiServerSync;
    private SyncProgress progress;
    private CancellationToken cancellationToken;

    public MultiServerSyncGetServersResponse(MultiServerSync multiServerSync, SyncProgress progress, CancellationToken cancellationToken) {
        this.multiServerSync = multiServerSync;
        this.progress = progress;
        this.cancellationToken = cancellationToken;
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> servers) {

        multiServerSync.SyncNext(servers, 0, cancellationToken, progress);
    }

    @Override
    public void onError(Exception ex) {

        progress.reportError(ex);
    }
}
