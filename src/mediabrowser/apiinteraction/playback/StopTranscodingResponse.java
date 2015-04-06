package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.AudioOptions;
import mediabrowser.model.dlna.StreamInfo;
import mediabrowser.model.dlna.VideoOptions;
import mediabrowser.model.logging.ILogger;

/**
 * Created by Luke on 3/24/2015.
 */
public class StopTranscodingResponse extends EmptyResponse {

    private PlaybackManager playbackManager;
    private String serverId;
    private StreamInfo currentStreamInfo;
    private AudioOptions options;
    private ILogger logger;
    private Response<StreamInfo> response;

    public StopTranscodingResponse(PlaybackManager playbackManager, String serverId, StreamInfo currentStreamInfo, AudioOptions options, ILogger logger, Response<StreamInfo> response) {
        this.playbackManager = playbackManager;
        this.serverId = serverId;
        this.currentStreamInfo = currentStreamInfo;
        this.options = options;
        this.logger = logger;
        this.response = response;
    }

    private void onAny(){

        if (currentStreamInfo.getAllMediaSources() != null)
        {
            options.setMediaSources(currentStreamInfo.getAllMediaSources());
        }

        StreamInfo streamInfo = playbackManager.getVideoStreamInfoInternal(serverId, (VideoOptions)options);
        streamInfo.setAllMediaSources(currentStreamInfo.getAllMediaSources());
        streamInfo.setPlaySessionId(currentStreamInfo.getPlaySessionId());

        playbackManager.SendResponse(response, streamInfo);
    }

    @Override
    public void onResponse(){
        onAny();
    }

    @Override
    public void onError(Exception ex) {

        logger.ErrorException("Error in StopTranscodingProcesses", ex);
        onAny();
    }
}
