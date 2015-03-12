package mediabrowser.apiinteraction.sync.server;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.sync.MediaSync;
import mediabrowser.apiinteraction.sync.SyncProgress;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

/**
 * Created by Luke on 3/3/2015.
 */
public class UpdateOfflineUsersResponse extends EmptyResponse {

    private SyncProgress progress;
    private ApiClient apiClient;
    private ServerInfo server;
    private ILocalAssetManager localAssetManager;
    private ILogger logger;

    private CancellationToken cancellationToken;
    private double initialProgressPercent;

    public UpdateOfflineUsersResponse(SyncProgress progress, ApiClient apiClient, ServerInfo server, ILocalAssetManager localAssetManager, ILogger logger, CancellationToken cancellationToken, double initialProgressPercent) {

        this.progress = progress;
        this.apiClient = apiClient;
        this.server = server;
        this.localAssetManager = localAssetManager;
        this.logger = logger;
        this.cancellationToken = cancellationToken;
        this.initialProgressPercent = initialProgressPercent;
    }

    @Override
    public void onError(Exception ex){

        progress.reportError(ex);
    }

    @Override
    public void onResponse(){

        startMediaSync();
    }

    public void startMediaSync(){

        new MediaSync(localAssetManager, logger).sync(apiClient, server, new MediaSyncProgress(progress, initialProgressPercent), cancellationToken);

        progress.reportComplete();
    }
}
