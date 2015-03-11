package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

/**
 * Created by Luke on 3/11/2015.
 */
public class MediaSync {

    private ILocalAssetManager localAssetManager;
    private ILogger logger;

    public void sync(ApiClient apiClient,
                     ServerInfo serverInfo,
                     Progress<Double> progress,
                     CancellationToken cancellationToken) {


        progress.reportComplete();
    }
}
