package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.android.profiles.Api16Builder;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.session.ClientCapabilities;

import java.util.concurrent.Semaphore;

public class CameraUploadProgress extends SyncProgress {

    private ILogger logger;
    private ServerInfo server;
    private SyncProgress progress;

    double maxPercentage;

    public CameraUploadProgress(ILogger logger, ServerInfo server, SyncProgress progress, double maxPercentage) {
        this.logger = logger;
        this.server = server;
        this.progress = progress;
        this.maxPercentage = maxPercentage;
    }

    @Override
    public void onProgress(Double percent) {

        logger.Info("Sync progress " + percent + " percent to server " + server.getName());
        progress.report(percent * maxPercentage);
    }

    @Override
    public void onComplete() {

        logger.Info("Sync complete to server " + server.getName());
        onAnyComplete();
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
        onAnyComplete();
    }

    @Override
    public void onFileUploadError(LocalFileInfo file, Exception ex) {

        logger.ErrorException("Error syncing to server " + server.getName(), ex);
        progress.onFileUploadError(file, ex);
    }

    public void onAnyComplete() {


    }
}
