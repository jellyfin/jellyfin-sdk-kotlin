package mediabrowser.apiinteraction.android.sync.server.mediasync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.results.ReadySyncItemsResult;

/**
 * Created by Luke on 4/20/2015.
 */
public class GetReadySyncItemsResponse extends Response<ReadySyncItemsResult> {

    private ILogger logger;
    private ApiClient apiClient;
    private ServerInfo serverInfo;
    private CancellationToken cancellationToken;
    private IProgress<Double> progress;
    private MediaSync mediaSync;

    public GetReadySyncItemsResponse(ILogger logger, ApiClient apiClient, ServerInfo serverInfo, CancellationToken cancellationToken, IProgress<Double> progress, MediaSync mediaSync) {
        this.logger = logger;
        this.apiClient = apiClient;
        this.serverInfo = serverInfo;
        this.cancellationToken = cancellationToken;
        this.progress = progress;
        this.mediaSync = mediaSync;
    }

    @Override
    public void onResponse(ReadySyncItemsResult jobItems) {

        logger.Debug("Ready sync items response returned %s items", jobItems.size());

        mediaSync.GetNextItem(jobItems, 0, apiClient, serverInfo, cancellationToken, progress);
    }

    @Override
    public void onError(Exception ex) {

        progress.reportError(ex);
    }

}
