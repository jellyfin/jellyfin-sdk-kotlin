package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.sync.data.NullAssetManager;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.session.PlaybackProgressInfo;
import mediabrowser.model.session.PlaybackStartInfo;

public class PlaybackManager {

    private ILocalAssetManager localAssetManager;
    private ILogger logger;
    private IDevice device;

    public PlaybackManager(ILocalAssetManager localAssetManager, ILogger logger, IDevice device) {
        this.localAssetManager = localAssetManager;
        this.logger = logger;
        this.device = device;
    }

    public PlaybackManager(ILogger logger, IDevice device) {
        this.localAssetManager = new NullAssetManager();
        this.logger = logger;
        this.device = device;
    }

    public void reportPlaybackStart(PlaybackStartInfo info, boolean isOffline, ApiClient apiClient, EmptyResponse response)
    {
        if (!isOffline)
        {
            apiClient.ReportPlaybackStartAsync(info, response);
        }
    }

    public void reportPlaybackProgress(PlaybackProgressInfo info, boolean isOffline, ApiClient apiClient, EmptyResponse response)
    {
        if (!isOffline)
        {
            apiClient.ReportPlaybackProgressAsync(info, response);
        }
    }
}
