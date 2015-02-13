package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ConnectionState;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;

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

        connectionManager.Connect(server, options, new ServerSyncConnectionResponse(this, server, cancellationToken, progress));
    }

    void LogNoAuthentication(ServerInfo server){

        logger.Info("Skipping sync process for server " + server.getName() + ". No server authentication information available.");
    }

    void Sync(final ServerInfo server, ApiClient apiClient, CancellationToken cancellationToken, final SyncProgress progress){

        final CancellationTokenSource innerCancellationSource = new CancellationTokenSource();

        new ContentUploader(apiClient, logger).UploadImages(new ServerSyncProgress(logger, server, progress), innerCancellationSource.getToken());
    }
}
