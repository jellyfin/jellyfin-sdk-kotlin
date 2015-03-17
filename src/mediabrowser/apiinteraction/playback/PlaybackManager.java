package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.sync.data.NullAssetManager;
import mediabrowser.model.dlna.*;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.entities.MediaStream;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.mediainfo.LiveMediaInfoResult;
import mediabrowser.model.session.PlayMethod;
import mediabrowser.model.session.PlaybackProgressInfo;
import mediabrowser.model.session.PlaybackStartInfo;
import mediabrowser.model.session.PlaybackStopInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.users.UserAction;
import mediabrowser.model.users.UserActionType;

import java.util.ArrayList;
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

    public PlaybackManager(IDevice device, ILogger logger)
    {
        this.localAssetManager = new NullAssetManager();
        this.device = device;
        this.logger = logger;
        this.localPlayer = new NullLocalPlayer();
    }

    public ArrayList<MediaStream> getPrePlaybackSelectableAudioStreams(String serverId, VideoOptions options)
    {
        StreamInfo info = getVideoStreamInfoInternal(serverId, options);

        return info.GetSelectableAudioStreams();
    }

    public ArrayList<MediaStream> getPrePlaybackSelectableSubtitleStreams(String serverId, VideoOptions options)
    {
        StreamInfo info = getVideoStreamInfoInternal(serverId, options);

        return info.GetSelectableSubtitleStreams();
    }

    public ArrayList<MediaStream> getInPlaybackSelectableAudioStreams(StreamInfo info)
    {
        return info.GetSelectableAudioStreams();
    }

    public ArrayList<MediaStream> getInPlaybackSelectableSubtitleStreams(StreamInfo info)
    {
        return info.GetSelectableSubtitleStreams();
    }

    public void getAudioStreamInfo(String serverId, AudioOptions options, boolean isOffline, ApiClient apiClient, Response<StreamInfo> response)
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

                    StreamInfo result = streamBuilder.BuildAudioItem(options);
                    result.setPlayMethod(PlayMethod.DirectPlay);
                    response.onResponse(result);
                    return;
                }
            }
        }

        response.onResponse(streamBuilder.BuildAudioItem(options));
    }

    public void getVideoStreamInfo(final String serverId, final VideoOptions options, boolean isOffline, ApiClient apiClient, final Response<StreamInfo> response)
    {
        if (!isOffline)
        {
            apiClient.GetPlaybackInfo(options.getItemId(), apiClient.getCurrentUserId(), new Response<LiveMediaInfoResult>(response){

                @Override
                public void onResponse(LiveMediaInfoResult result) {

                    if (result.getErrorCode() != null){

                        PlaybackException error = new PlaybackException();
                        error.setErrorCode(result.getErrorCode());
                        response.onError(error);
                        return;
                    }

                    options.setMediaSources(result.getMediaSources());
                    response.onResponse(getVideoStreamInfoInternal(serverId, options));
                }

            });
            return;
        }

        response.onResponse(getVideoStreamInfoInternal(serverId, options));
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
            return;
        }

        response.onResponse();
    }

    public void reportPlaybackProgress(PlaybackProgressInfo info, boolean isOffline, ApiClient apiClient, EmptyResponse response)
    {
        if (!isOffline)
        {
            apiClient.ReportPlaybackProgressAsync(info, response);
            return;
        }

        response.onResponse();
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
            response.onResponse();
            return;
        }

        apiClient.ReportPlaybackStoppedAsync(info, new ReportPlaybackStopResponse(logger, device, apiClient, streamInfo, response));
    }
}
