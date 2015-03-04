package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ConnectionResult;
import mediabrowser.apiinteraction.Response;
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

    public ServerSyncConnectionResponse(ServerSync serverSync, ServerInfo server, ClientCapabilities clientCapabilities, CancellationToken cancellationToken, SyncProgress progress) {
        this.serverSync = serverSync;
        this.server = server;
        this.cancellationToken = cancellationToken;
        this.progress = progress;
        this.clientCapabilities = clientCapabilities;
    }

    @Override
    public void onResponse(ConnectionResult result) {

        if (result.getState() == ConnectionState.SignedIn) {

            serverSync.Sync(server, result.getApiClient(), clientCapabilities, cancellationToken, progress);

        } else {

            serverSync.LogNoAuthentication(server);

            // Done. Nothing to do.
            progress.reportComplete();
        }
    }
}
