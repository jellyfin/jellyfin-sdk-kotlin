package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
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

        connectionManager.Connect(server, new Response<ConnectionResult>() {

            @Override
            public void onResponse(ConnectionResult result) {

                if (result.getState() == ConnectionState.SignedIn) {

                    Sync(server, result.getApiClient(), cancellationToken, progress);

                } else {

                    LogNoAuthentication(server);

                    // Done. Nothing to do.
                    progress.reportComplete();
                }
            }

        });
    }

    private void LogNoAuthentication(ServerInfo server){

        logger.Info("Skipping sync process for server " + server.getName() + ". No server authentication information available.");
    }

    private void Sync(final ServerInfo server, ApiClient apiClient, CancellationToken cancellationToken, final SyncProgress progress){

        final CancellationTokenSource innerCancellationSource = new CancellationTokenSource();

        new ContentUploader(apiClient, logger).UploadImages(new SyncProgress(){

            @Override
            public void onProgress(Double percent) {

                logger.Info("Sync progress " + percent + " percent to server " + server.getName());
                progress.report(percent);
            }

            @Override
            public void onComplete() {

                logger.Info("Sync complete to server " + server.getName());
                progress.reportComplete();
            }

            @Override
            public void onFileUploaded(LocalFileInfo file) {

                progress.onFileUploaded(file);
            }

            @Override
            public void onCancelled() {

                logger.Info("Sync cancelled to server " + server.getName());
                progress.reportCancelled();
            }

            @Override
            public void onError(Exception ex) {

                logger.ErrorException("Error syncing to server " + server.getName(), ex);
                progress.reportError(ex);
            }

            @Override
            public void onFileUploadError(LocalFileInfo file, Exception ex) {

                logger.ErrorException("Error syncing to server " + server.getName(), ex);
                progress.onFileUploadError(file, ex);
            }

        }, innerCancellationSource.getToken());
    }
}
