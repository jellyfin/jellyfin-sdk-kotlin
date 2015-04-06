package mediabrowser.apiinteraction.playback;

import android.media.session.MediaController;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.AudioOptions;
import mediabrowser.model.dlna.StreamBuilder;
import mediabrowser.model.dlna.StreamInfo;
import mediabrowser.model.dlna.VideoOptions;
import mediabrowser.model.mediainfo.LiveStreamResponse;

/**
 * Created by Luke on 4/1/2015.
 */
public class OpenLiveStreamResponse extends Response<LiveStreamResponse> {

    private StreamBuilder streamBuilder;
    private PlaybackManager playbackManager;
    private AudioOptions options;
    private boolean isAudio;
    private Response<StreamInfo> response;

    public OpenLiveStreamResponse(StreamBuilder streamBuilder, PlaybackManager playbackManager, AudioOptions options, boolean isAudio, Response<StreamInfo> response) {
        this.streamBuilder = streamBuilder;
        this.playbackManager = playbackManager;
        this.options = options;
        this.isAudio = isAudio;
        this.response = response;
    }

    @Override
    public void onResponse(LiveStreamResponse liveStreamResponse){

        options.getMediaSources().clear();
        options.getMediaSources().add(liveStreamResponse.getMediaSource());

        StreamInfo streamInfo;

        if (isAudio){
            streamInfo = streamBuilder.BuildAudioItem(options);
        }
        else{
            streamInfo = streamBuilder.BuildVideoItem((VideoOptions) options);
        }

        playbackManager.SendResponse(response, streamInfo);
    }
}
