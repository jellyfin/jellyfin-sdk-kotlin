package mediabrowser.apiinteraction.sync;

import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;

public class ServerSyncProgress extends SyncProgress {

    private ILogger logger;
    private ServerInfo server;
    private SyncProgress progress;

    public ServerSyncProgress(ILogger logger, ServerInfo server, SyncProgress progress) {
        this.logger = logger;
        this.server = server;
        this.progress = progress;
    }

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
}
