package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ConnectionState;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

public class ServerSync {

    private ILogger logger;
    private IConnectionManager connectionManager;

    public ServerSync(IConnectionManager connectionManager, ILogger logger) {
        this.logger = logger;
        this.connectionManager = connectionManager;
    }

    public void Sync(final ServerInfo server, final CancellationToken cancellationToken, final IProgress progress){

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()) &&
                tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getExchangeToken()))
        {
            LogNoAuthentication(server);
            return;
        }

        connectionManager.Connect(server, new Response<ConnectionResult>() {

            @Override
            public void onResponse(ConnectionResult result) {

                if (result.getState() == ConnectionState.ConnectSignIn) {

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

    private void Sync(final ServerInfo server, ApiClient apiClient, CancellationToken cancellationToken, final IProgress progress){

        final CancellationTokenSource innerCancellationSource = new CancellationTokenSource();

        new ContentUploader(apiClient, logger).UploadImages(new Progress<Double>(){

            @Override
            public void onProgress(Double percent) {

                logger.Info("Sync progress " + percent + "% to server " + server.getName());
                progress.report(percent);
            }

            @Override
            public void onComplete() {

                logger.Info("Sync complete to server " + server.getName());
                progress.reportComplete();
            }

            @Override
            public void onCancelled() {

                logger.Info("Sync cancelled to server " + server.getName());
                progress.reportCancelled();
            }

            @Override
            public void onError(Exception ex) {

                logger.ErrorException("Error syncing to server " + server.getName(), ex);
                innerCancellationSource.cancel();
            }

        }, innerCancellationSource.getToken());
    }
}
