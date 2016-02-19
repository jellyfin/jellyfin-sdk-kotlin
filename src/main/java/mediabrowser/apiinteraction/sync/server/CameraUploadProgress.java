package mediabrowser.apiinteraction.sync.server;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.sync.SyncProgress;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.session.ClientCapabilities;

import java.util.concurrent.Semaphore;

public class CameraUploadProgress extends SyncProgress {

    private ILogger logger;
    private ServerInfo server;
    private SyncProgress progress;
    private ApiClient apiClient;
    private ClientCapabilities clientCapabilities;
    private ILocalAssetManager localAssetManager;
    private CancellationToken cancellationToken;
    private double maxPercentage;

    public CameraUploadProgress(ILogger logger, ServerInfo server, SyncProgress progress, ApiClient apiClient, ClientCapabilities clientCapabilities, ILocalAssetManager localAssetManager, CancellationToken cancellationToken, double maxPercentage) {
        this.logger = logger;
        this.server = server;
        this.progress = progress;
        this.apiClient = apiClient;
        this.clientCapabilities = clientCapabilities;
        this.localAssetManager = localAssetManager;
        this.cancellationToken = cancellationToken;
        this.maxPercentage = maxPercentage;
    }

    @Override
    public void onProgress(Double percent) {

        logger.Info("Camera upload progress " + percent + " percent to server " + server.getName());
        progress.report(percent * maxPercentage);
    }

    @Override
    public void onComplete() {

        logger.Info("Camera upload complete to server " + server.getName());
        onAnyComplete();
    }

    @Override
    public void onFileUploaded(LocalFileInfo file) {

        progress.onFileUploaded(file);
    }

    @Override
    public void onCancelled() {

        logger.Info("Camera upload cancelled to server " + server.getName());
        progress.reportCancelled();
    }

    @Override
    public void onError(Exception ex) {

        logger.ErrorException("Error syncing to server " + server.getName(), ex);
        onAnyComplete();
    }

    @Override
    public void onFileUploadError(LocalFileInfo file, Exception ex) {

        logger.ErrorException("Error syncing to server " + server.getName(), ex);
        progress.onFileUploadError(file, ex);
    }

    public void onAnyComplete() {

        UpdateOfflineUsersResponse offlineUserResponse = new UpdateOfflineUsersResponse(progress, apiClient, server, localAssetManager, logger, cancellationToken, maxPercentage);

        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
        }
        else {

            new OfflineUsersSync(logger, localAssetManager).UpdateOfflineUsers(server, apiClient, cancellationToken, offlineUserResponse);
        }
    }
}
