package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.model.dlna.DlnaProfileType;
import mediabrowser.model.dlna.StreamInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.session.PlayMethod;

/**
 * Created by Luke on 3/17/2015.
 */
public class ReportPlaybackStopResponse extends EmptyResponse {

    private ILogger logger;
    private IDevice device;
    private ApiClient apiClient;
    private StreamInfo streamInfo;
    private EmptyResponse response;

    public ReportPlaybackStopResponse(ILogger logger, IDevice device, ApiClient apiClient, StreamInfo streamInfo, EmptyResponse response) {
        this.logger = logger;
        this.device = device;
        this.apiClient = apiClient;
        this.streamInfo = streamInfo;
        this.response = response;
    }

    private void onAny(){

        if (streamInfo.getMediaType() == DlnaProfileType.Video && streamInfo.getPlayMethod() == PlayMethod.Transcode)
        {
            String playSessionId = streamInfo.getPlaybackInfo() == null ?
                    null :
                    streamInfo.getPlaybackInfo().getPlaySessionId();

            apiClient.StopTranscodingProcesses(device.getDeviceId(), playSessionId, response);
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

}
