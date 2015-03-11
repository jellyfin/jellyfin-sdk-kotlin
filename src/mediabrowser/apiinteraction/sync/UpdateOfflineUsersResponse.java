package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;

/**
 * Created by Luke on 3/3/2015.
 */
public class UpdateOfflineUsersResponse extends EmptyResponse {

    private SyncProgress progress;
    private ApiClient apiClient;
    private ServerInfo server;
    CancellationToken cancellationToken;

    public UpdateOfflineUsersResponse(SyncProgress progress, ApiClient apiClient, ServerInfo server, CancellationToken cancellationToken) {

        this.progress = progress;
        this.apiClient = apiClient;
        this.server = server;
        this.cancellationToken = cancellationToken;
    }

    @Override
    public void onError(Exception ex){

        progress.reportError(ex);
    }

    @Override
    public void onResponse(){

        startMediaSync();
    }

    public void startMediaSync(){

        new MediaSync().sync(apiClient, server, new Progress<Double>(){

            @Override
            public void onProgress(Double percent)
            {
                double initialPercent = .25;
                double cumulativeProgress = (initialPercent * 100) + (percent * (1 - initialPercent));
                progress.report(cumulativeProgress);
            }

            @Override
            public void onComplete()
            {
                progress.reportComplete();
            }

            @Override
            public void onCancelled()
            {
                progress.reportCancelled();
            }

            @Override
            public void onError(Exception exception)
            {
                progress.reportError(exception);
            }

        }, cancellationToken);

        progress.reportComplete();
    }
}
