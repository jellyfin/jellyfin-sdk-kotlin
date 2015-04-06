package mediabrowser.apiinteraction.playback;

import android.media.session.MediaController;
import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.*;
import mediabrowser.model.mediainfo.LiveStreamRequest;
import mediabrowser.model.mediainfo.LiveStreamResponse;
import mediabrowser.model.mediainfo.PlaybackInfoResponse;

/**
 * Created by Luke on 3/24/2015.
 */
public class GetPlaybackInfoResponse extends Response<PlaybackInfoResponse> {

    private PlaybackManager playbackManager;
    private ApiClient apiClient;
    private String serverId;
    private AudioOptions options;
    private Response<StreamInfo> response;
    private StreamBuilder streamBuilder;
    private boolean isVideo;

    public GetPlaybackInfoResponse(PlaybackManager playbackManager, ApiClient apiClient, String serverId, AudioOptions options, Response<StreamInfo> response, StreamBuilder streamBuilder, boolean isVideo) {
        super(response);
        this.playbackManager = playbackManager;
        this.apiClient = apiClient;
        this.serverId = serverId;
        this.options = options;
        this.response = response;
        this.streamBuilder = streamBuilder;
        this.isVideo = isVideo;
    }

    @Override
    public void onResponse(PlaybackInfoResponse playbackInfo) {

        if (playbackInfo.getErrorCode() != null){

            PlaybackException error = new PlaybackException();
            error.setErrorCode(playbackInfo.getErrorCode());
            response.onError(error);
            return;
        }

        options.setMediaSources(playbackInfo.getMediaSources());

        final StreamInfo streamInfo;

        if (isVideo){
            streamInfo = playbackManager.getVideoStreamInfoInternal(serverId, (VideoOptions)options);
        }
        else{
            streamInfo = streamBuilder.BuildAudioItem(options);
        }

        streamInfo.setPlaySessionId(playbackInfo.getPlaySessionId());
        streamInfo.setAllMediaSources(playbackInfo.getMediaSources());

        if (streamInfo.getMediaSource().getRequiresOpening()){

            LiveStreamRequest request = new LiveStreamRequest(options);
            request.setUserId(apiClient.getCurrentUserId());
            request.setOpenToken(streamInfo.getMediaSource().getOpenToken());

            apiClient.OpenLiveStream(request, new OpenLiveStreamResponse(streamBuilder, playbackManager, options, !isVideo, response));
        }
        else{

            playbackManager.SendResponse(response, streamInfo);
        }
    }
}
