package mediabrowser.apiinteraction.sync.server.mediasync;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

/**
 * Created by Luke on 3/17/2015.
 */
public class SecondSyncDataResponse extends EmptyResponse {

    private ILogger logger;
    private ServerInfo serverInfo;
    private Progress<Double> progress;

    public SecondSyncDataResponse(ILogger logger, ServerInfo serverInfo, Progress<Double> progress) {
        this.logger = logger;
        this.serverInfo = serverInfo;
        this.progress = progress;
    }

    @Override
    public void onResponse() {

        logger.Debug("Completed media sync process with server Id: {0}", serverInfo.getId());
        progress.reportComplete();
    }

    @Override
    public void onError(Exception ex) {
        progress.reportError(ex);
    }
}
