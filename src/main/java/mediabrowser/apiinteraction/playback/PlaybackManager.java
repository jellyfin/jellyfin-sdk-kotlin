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
import mediabrowser.model.mediainfo.PlaybackInfoRequest;
import mediabrowser.model.session.PlayMethod;
import mediabrowser.model.session.PlaybackProgressInfo;
import mediabrowser.model.session.PlaybackStartInfo;
import mediabrowser.model.session.PlaybackStopInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.users.UserAction;
import mediabrowser.model.users.UserActionType;
import mediabrowser.model.users.UserPolicy;

import java.util.ArrayList;
import java.util.Date;

public class PlaybackManager {

    private ILocalAssetManager localAssetManager;
    private ILogger logger;
    private IDevice device;

    public PlaybackManager(ILocalAssetManager localAssetManager, IDevice device, ILogger logger)
    {
        this.localAssetManager = localAssetManager;
        this.device = device;
        this.logger = logger;
    }

    public PlaybackManager(IDevice device, ILogger logger)
    {
        this.localAssetManager = new NullAssetManager();
        this.device = device;
        this.logger = logger;
    }

    public ArrayList<MediaStream> getPrePlaybackSelectableAudioStreams(String serverId, VideoOptions options)
    {
        Normalize(options);

        StreamInfo info = getVideoStreamInfoInternal(serverId, options);

        return info.GetSelectableAudioStreams();
    }

    public ArrayList<MediaStream> getPrePlaybackSelectableSubtitleStreams(String serverId, VideoOptions options)
    {
        Normalize(options);

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

    private void Normalize(AudioOptions options){
        options.setDeviceId(device.getDeviceId());
    }

    void SendResponse(Response<StreamInfo> response, StreamInfo info){

        if (info == null){
            PlaybackException error = new PlaybackException();
            error.setErrorCode(PlaybackErrorCode.NoCompatibleStream);
            response.onError(error);
        }
        else{
            response.onResponse(info);
        }
    }

    public void getAudioStreamInfo(String serverId, AudioOptions options, Long startPositionTicks, boolean isOffline, ApiClient apiClient, UserPolicy currentUserPolicy, Response<StreamInfo> response)
    {
        Normalize(options);
        StreamBuilder streamBuilder = new StreamBuilder(logger);

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
                    SendResponse(response, result);
                    return;
                }
            }
        }

        if (!isOffline)
        {
            PlaybackInfoRequest request = new PlaybackInfoRequest();
            request.setId(options.getItemId());
            request.setUserId(apiClient.getCurrentUserId());
            request.setMaxStreamingBitrate(options.getMaxBitrate());
            request.setMediaSourceId(options.getMediaSourceId());
            request.setStartTimeTicks(startPositionTicks);

            if (!currentUserPolicy.getEnableAudioPlaybackTranscoding()){
                options.setForceDirectStream(true);
            }
            apiClient.GetPlaybackInfoWithPost(request, new GetPlaybackInfoResponse(this, apiClient, serverId, options, response, streamBuilder, false));
            return;
        }

        SendResponse(response, streamBuilder.BuildAudioItem(options));
    }

    public void getVideoStreamInfo(final String serverId, final VideoOptions options, boolean isOffline, ApiClient apiClient, UserPolicy currentUserPolicy, final Response<StreamInfo> response)
    {
        Normalize(options);
        StreamBuilder streamBuilder = new StreamBuilder(logger);

        if (!isOffline)
        {
            PlaybackInfoRequest request = new PlaybackInfoRequest();
            request.setId(options.getItemId());
            request.setUserId(apiClient.getCurrentUserId());
            request.setMaxStreamingBitrate(options.getMaxBitrate());
            request.setMediaSourceId(options.getMediaSourceId());
            request.setAudioStreamIndex(options.getAudioStreamIndex());
            request.setSubtitleStreamIndex(options.getSubtitleStreamIndex());

            if (!currentUserPolicy.getEnableAudioPlaybackTranscoding() &&
                    !currentUserPolicy.getEnableVideoPlaybackTranscoding() &&
                    !currentUserPolicy.getEnablePlaybackRemuxing()){
                options.setForceDirectStream(true);
            }

            apiClient.GetPlaybackInfo(request, new GetPlaybackInfoResponse(this, apiClient, serverId, options, response, streamBuilder, true));
            return;
        }

        SendResponse(response, getVideoStreamInfoInternal(serverId, options));
    }

    public void changeVideoStream(final StreamInfo currentStreamInfo, final String serverId, final VideoOptions options, ApiClient apiClient, UserPolicy currentUserPolicy, final Response<StreamInfo> response)
    {
        Normalize(options);

        String playSessionId = currentStreamInfo.getPlaySessionId();

        if (!currentUserPolicy.getEnableAudioPlaybackTranscoding() &&
                !currentUserPolicy.getEnableVideoPlaybackTranscoding() &&
                !currentUserPolicy.getEnablePlaybackRemuxing()){
            options.setForceDirectStream(true);
        }

        apiClient.StopTranscodingProcesses(device.getDeviceId(), playSessionId, new StopTranscodingResponse(this, serverId, currentStreamInfo, options, logger, response));
    }

    StreamInfo getVideoStreamInfoInternal(String serverId, VideoOptions options)
    {
        StreamBuilder streamBuilder = new StreamBuilder(logger);

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

    public void reportPlaybackProgress(PlaybackProgressInfo info, final StreamInfo streamInfo, boolean isOffline, ApiClient apiClient, EmptyResponse response)
    {
        if (!isOffline)
        {
            apiClient.ReportPlaybackProgressAsync(info, response);
            return;
        }

        MediaSourceInfo mediaSource = streamInfo.getMediaSource();

        if (mediaSource != null){
            info.setLiveStreamId(mediaSource.getLiveStreamId());
        }

        info.setPlaySessionId(streamInfo.getPlaySessionId());

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

        MediaSourceInfo mediaSource = streamInfo.getMediaSource();

        if (mediaSource != null){
            info.setLiveStreamId(mediaSource.getLiveStreamId());
        }

        info.setPlaySessionId(streamInfo.getPlaySessionId());

        apiClient.ReportPlaybackStoppedAsync(info, response);
    }
}
