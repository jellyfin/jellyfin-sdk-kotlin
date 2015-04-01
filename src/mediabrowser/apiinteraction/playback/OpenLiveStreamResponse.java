package mediabrowser.apiinteraction.playback;

import android.media.session.MediaController;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.StreamInfo;
import mediabrowser.model.mediainfo.LiveStreamResponse;

/**
 * Created by Luke on 4/1/2015.
 */
public class OpenLiveStreamResponse extends Response<LiveStreamResponse> {

    private StreamInfo streamInfo;
    private PlaybackManager playbackManager;
    private Response<StreamInfo> response;

    public OpenLiveStreamResponse(StreamInfo streamInfo, PlaybackManager playbackManager, Response<StreamInfo> response) {
        this.streamInfo = streamInfo;
        this.playbackManager = playbackManager;
        this.response = response;
    }

    @Override
    public void onResponse(LiveStreamResponse liveStreamResponse){

        streamInfo.setMediaSource(liveStreamResponse.getMediaSource());
        playbackManager.SendResponse(response, streamInfo);
    }

}
