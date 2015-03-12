package mediabrowser.apiinteraction.playback;

import android.filterpacks.videosrc.MediaSource;
import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.sync.data.NullAssetManager;
import mediabrowser.model.dlna.*;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.session.PlayMethod;
import mediabrowser.model.session.PlaybackProgressInfo;
import mediabrowser.model.session.PlaybackStartInfo;
import mediabrowser.model.session.PlaybackStopInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.users.UserAction;
import mediabrowser.model.users.UserActionType;

import java.util.Date;

public class PlaybackManager {

    private ILocalAssetManager localAssetManager;
    private ILogger logger;
    private IDevice device;
    private ILocalPlayer localPlayer;

    public PlaybackManager(ILocalAssetManager localAssetManager, IDevice device, ILogger logger, ILocalPlayer localPlayer)
    {
        this.localAssetManager = localAssetManager;
        this.device = device;
        this.logger = logger;
        this.localPlayer = localPlayer;
    }

    public PlaybackManager(ILocalAssetManager localAssetManager, IDevice device, ILogger logger)
    {
        this.localAssetManager = localAssetManager;
        this.device = device;
        this.logger = logger;
        this.localPlayer = new NullLocalPlayer();
    }

    public PlaybackManager(IDevice device, ILogger logger, ILocalPlayer localPlayer)
    {
        this.localAssetManager = new NullAssetManager();
        this.device = device;
        this.logger = logger;
        this.localPlayer = localPlayer;
    }

    private StreamInfo getVideoStreamInfoInternal(String serverId, VideoOptions options)
    {
        StreamBuilder streamBuilder = new StreamBuilder(localPlayer);

        LocalItem localItem = localAssetManager.getLocalItem(serverId, options.getItemId());

        if (localItem != null)
        {
            MediaSourceInfo localMediaSource = localItem.getItem().getMediaSources().get(0);

            // Use the local media source, unless a specific server media source was requested
            if (options.getMediaSourceId() == null || StringHelper.EqualsIgnoreCase(localMediaSource.getId(), options.getMediaSourceId()))
            {
                // Finally, check to make sure the local file is actually available at this time
                boolean fileExists = localAssetManager.fileExists(localMediaSource.getPath());

                if (fileExists)
                {
                    options.setMediaSources(localItem.getItem().getMediaSources());

                    StreamInfo result = streamBuilder.BuildVideoItem(options);
                    result.setPlayMethod(PlayMethod.DirectPlay);
                    return result;
                }
            }
        }

        return streamBuilder.BuildVideoItem(options);
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

    public void reportPlaybackStopped(PlaybackStopInfo info, final StreamInfo streamInfo, final String serverId, String userId, boolean isOffline, final ApiClient apiClient, final EmptyResponse response)
    {
        if (isOffline)
        {
            UserAction action = new UserAction();
            action.setDate(new Date());
            action.setItemId(info.getItemId());
            action.setPositionTicks(info.getPositionTicks());
            action.setServerId(serverId);
            action.setType(UserActionType.PlayedItem);
            action.setUserId(userId);

            localAssetManager.recordUserAction(action);
            return;
        }

        apiClient.ReportPlaybackStoppedAsync(info, new EmptyResponse(){

            private void onAny(){

                if (streamInfo.getMediaType() == DlnaProfileType.Video)
                {
                    apiClient.StopTranscodingProcesses(device.getDeviceId(), response);
                }
                else{
                    response.onResponse();
                }
            }

            @Override
            public void onResponse(){
                onAny();
            }

            @Override
            public void onError(Exception ex) {

                logger.ErrorException("Error reporting playback stopped", ex);
                onAny();
            }
        });
    }
}
