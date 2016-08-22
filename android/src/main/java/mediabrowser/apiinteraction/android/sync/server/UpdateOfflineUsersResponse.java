package mediabrowser.apiinteraction.android.sync.server;

import android.app.Service;
import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.android.mediabrowser.IMediaRes;
import mediabrowser.apiinteraction.android.sync.SyncProgress;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.android.sync.server.mediasync.MediaSync;
import mediabrowser.apiinteraction.android.sync.server.mediasync.MediaSyncProgress;
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
    private Service mService;

    private IMediaRes mediaRes;

    private CancellationToken cancellationToken;
    private double initialProgressPercent;

    public UpdateOfflineUsersResponse(SyncProgress progress, ApiClient apiClient, ServerInfo server, ILocalAssetManager localAssetManager, ILogger logger, Service mService, IMediaRes mediaRes, CancellationToken cancellationToken, double initialProgressPercent) {

        this.progress = progress;
        this.apiClient = apiClient;
        this.server = server;
        this.localAssetManager = localAssetManager;
        this.logger = logger;
        this.mService = mService;
        this.mediaRes = mediaRes;
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

        new MediaSync(localAssetManager, logger, mService, mediaRes).sync(apiClient, server, new MediaSyncProgress(progress, initialProgressPercent), cancellationToken);
    }
}
