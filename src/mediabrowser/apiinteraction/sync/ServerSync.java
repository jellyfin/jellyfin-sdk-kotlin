package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class ServerSync {

    private ILogger logger;
    private IConnectionManager connectionManager;

    public ServerSync(IConnectionManager connectionManager, ILogger logger) {
        this.logger = logger;
        this.connectionManager = connectionManager;
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

        connectionManager.Connect(server, options, new ServerSyncConnectionResponse(this, server, cancellationToken, progress));
    }

    void LogNoAuthentication(ServerInfo server){

        logger.Info("Skipping sync process for server " + server.getName() + ". No server authentication information available.");
    }

    void Sync(final ServerInfo server, ApiClient apiClient, CancellationToken cancellationToken, final SyncProgress progress){

        Semaphore semaphore = GetLock(server.getId());

        try {

            semaphore.acquire();

            SyncInternal(server, apiClient, cancellationToken, semaphore, progress);

        } catch (InterruptedException e) {

            logger.ErrorException("InterruptedException in ServerSync", e);
            progress.reportError(e);
        }
    }

    private void SyncInternal(final ServerInfo server, ApiClient apiClient, CancellationToken cancellationToken, Semaphore semaphore, final SyncProgress progress){

        new ContentUploader(apiClient, logger).UploadImages(new ServerSyncProgress(logger, server, progress, semaphore), cancellationToken);
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
