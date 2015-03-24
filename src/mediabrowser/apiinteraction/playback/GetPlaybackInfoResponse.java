package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.PlaybackException;
import mediabrowser.model.dlna.StreamInfo;
import mediabrowser.model.dlna.VideoOptions;
import mediabrowser.model.mediainfo.LiveMediaInfoResult;

/**
 * Created by Luke on 3/24/2015.
 */
public class GetPlaybackInfoResponse extends Response<LiveMediaInfoResult> {

    private PlaybackManager playbackManager;
    private String serverId;
    private VideoOptions options;
    private Response<StreamInfo> response;

    public GetPlaybackInfoResponse(PlaybackManager playbackManager, String serverId, VideoOptions options, Response<StreamInfo> response) {
        super(response);
        this.playbackManager = playbackManager;
        this.serverId = serverId;
        this.options = options;
        this.response = response;
    }

    @Override
    public void onResponse(LiveMediaInfoResult playbackInfo) {

        if (playbackInfo.getErrorCode() != null){

            PlaybackException error = new PlaybackException();
            error.setErrorCode(playbackInfo.getErrorCode());
            response.onError(error);
            return;
        }

        options.setMediaSources(playbackInfo.getMediaSources());
        StreamInfo streamInfo = playbackManager.getVideoStreamInfoInternal(serverId, options);
        streamInfo.setPlaybackInfo(playbackInfo);
        response.onResponse(streamInfo);
    }

}
