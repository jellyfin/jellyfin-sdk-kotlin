package mediabrowser.apiinteraction.android.sync.server.mediasync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

/**
 * Created by Luke on 4/20/2015.
 */
public class GetNewMediaProgress extends Progress<Double> {

    private ILogger logger;
    private ApiClient apiClient;
    private ServerInfo serverInfo;
    private Progress<Double> outerProgress;
    private MediaSync mediaSync;

    public GetNewMediaProgress(ILogger logger, ApiClient apiClient, ServerInfo serverInfo, Progress<Double> outerProgress, MediaSync mediaSync) {
        this.logger = logger;
        this.apiClient = apiClient;
        this.serverInfo = serverInfo;
        this.outerProgress = outerProgress;
        this.mediaSync = mediaSync;
    }

    @Override
    public void onCancelled() {

        outerProgress.reportCancelled();
    }

    @Override
    public void onComplete() {

        outerProgress.report(99.0);

        // Do the data sync twice so the server knows what was removed from the device
        mediaSync.SyncData(apiClient, serverInfo, true, new SecondSyncDataResponse(logger, serverInfo, outerProgress));
    }

    @Override
    public void onError(Exception ex) {
        outerProgress.reportError(ex);
    }

    @Override
    public void onProgress(Double val) {
        outerProgress.report(3 + .96 * val);
    }
}
