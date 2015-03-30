package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.*;
import mediabrowser.model.mediainfo.PlaybackInfoResponse;

/**
 * Created by Luke on 3/24/2015.
 */
public class GetPlaybackInfoResponse extends Response<PlaybackInfoResponse> {

    private PlaybackManager playbackManager;
    private String serverId;
    private AudioOptions options;
    private Response<StreamInfo> response;
    private StreamBuilder streamBuilder;
    private boolean isVideo;

    public GetPlaybackInfoResponse(PlaybackManager playbackManager, String serverId, AudioOptions options, Response<StreamInfo> response, StreamBuilder streamBuilder, boolean isVideo) {
        super(response);
        this.playbackManager = playbackManager;
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

        if (isVideo){
            StreamInfo streamInfo = playbackManager.getVideoStreamInfoInternal(serverId, (VideoOptions)options);
            streamInfo.setPlaybackInfo(playbackInfo);
            playbackManager.SendResponse(response, streamInfo);
        }
        else{
            StreamInfo streamInfo = streamBuilder.BuildAudioItem(options);
            playbackManager.SendResponse(response, streamInfo);
        }

    }

}
