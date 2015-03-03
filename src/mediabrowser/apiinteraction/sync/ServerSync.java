package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.session.ClientCapabilities;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class ServerSync {

    private ILogger logger;
    private IConnectionManager connectionManager;
    private ILocalAssetManager localAssetManager;

    public ServerSync(IConnectionManager connectionManager, ILogger logger, ILocalAssetManager localAssetManager) {
        this.logger = logger;
        this.connectionManager = connectionManager;
        this.localAssetManager = localAssetManager;
    }

    public void Sync(final ServerInfo server, final CancellationToken cancellationToken, final SyncProgress progress){

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()) &&
                tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getExchangeToken()))
        {
            LogNoAuthentication(server);
            return;
        }

        ConnectionOptions options = new ConnectionOptions();
        options.setEnableWebSocket(false);
        options.setReportCapabilities(false);
        options.setUpdateDateLastAccessed(false);

        connectionManager.Connect(server, options, new ServerSyncConnectionResponse(this, server, connectionManager.getClientCapabilities(), cancellationToken, progress));
    }

    void LogNoAuthentication(ServerInfo server){

        logger.Info("Skipping sync process for server " + server.getName() + ". No server authentication information available.");
    }

    void Sync(final ServerInfo server, ApiClient apiClient, ClientCapabilities clientCapabilities, CancellationToken cancellationToken, final SyncProgress progress){

        Semaphore semaphore = GetLock(server.getId());

        // TODO: Implement with semaphore
/*        try {

            semaphore.acquire();

            SyncInternal(server, apiClient, clientCapabilities, cancellationToken, progress);

        }
        catch (InterruptedException e) {

            logger.ErrorException("InterruptedException in ServerSync", e);
            progress.reportError(e);
        }*/

        SyncInternal(server, apiClient, clientCapabilities, cancellationToken, progress);
    }

    private void SyncInternal(final ServerInfo server,
                              final ApiClient apiClient,
                              final ClientCapabilities clientCapabilities,
                              final CancellationToken cancellationToken,
                              final SyncProgress progress){

        final double cameraUploadTotalPercentage = .25;

        new ContentUploader(apiClient, logger).UploadImages(new CameraUploadProgress(logger, server, progress, cameraUploadTotalPercentage){

            @Override
            public void onAnyComplete(){

                UpdateOfflineUsersResponse offlineUserResponse = new UpdateOfflineUsersResponse(progress);

                if (cancellationToken.isCancellationRequested()){
                    progress.reportCancelled();
                }
                else if (clientCapabilities.getSupportsOfflineAccess()){
                    new OfflineUsersSync(logger, localAssetManager).UpdateOfflineUsers(server, apiClient, cancellationToken, offlineUserResponse);
                }
                else {
                    offlineUserResponse.onResponse();
                }

            }

        }, cancellationToken);
    }

    private static HashMap<String, Semaphore> SemaphoreLocks = new HashMap<String, Semaphore>();
    private static Semaphore GetLock(String serverId)
    {
        if (!SemaphoreLocks.containsKey(serverId))
        {
            SemaphoreLocks.put(serverId, new Semaphore(1));
        }

        return SemaphoreLocks.get(serverId);
    }
}
