package mediabrowser.apiinteraction.android.sync.server;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.android.sync.SyncProgress;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ConnectionState;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.session.ClientCapabilities;

public class ServerSyncConnectionResponse extends Response<ConnectionResult> {

    private ServerSync serverSync;
    private ServerInfo server;
    private CancellationToken cancellationToken;
    private SyncProgress progress;
    private ClientCapabilities clientCapabilities;
    private boolean uploadPhotos;

    public ServerSyncConnectionResponse(ServerSync serverSync, ServerInfo server, boolean uploadPhotos, ClientCapabilities clientCapabilities, CancellationToken cancellationToken, SyncProgress progress) {
        this.serverSync = serverSync;
        this.server = server;
        this.cancellationToken = cancellationToken;
        this.progress = progress;
        this.clientCapabilities = clientCapabilities;
        this.uploadPhotos = uploadPhotos;
    }

    @Override
    public void onResponse(ConnectionResult result) {

        if (result.getState() == ConnectionState.SignedIn) {

            serverSync.Sync(server, result.getApiClient(), uploadPhotos, clientCapabilities, cancellationToken, progress);

        } else {

            serverSync.LogNoAuthentication(server);

            // Done. Nothing to do.
            progress.reportComplete();
        }
    }
}
