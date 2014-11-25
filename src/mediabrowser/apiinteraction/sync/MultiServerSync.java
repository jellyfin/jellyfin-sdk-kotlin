package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.IConnectionManager;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;

import java.util.ArrayList;

public class MultiServerSync {

    private IConnectionManager connectionManager;
    private ILogger logger;

    public MultiServerSync(IConnectionManager connectionManager, ILogger logger) {
        this.connectionManager = connectionManager;
        this.logger = logger;
    }

    public void Sync(final CancellationToken cancellationToken, final SyncProgress progress){

        connectionManager.GetAvailableServers(new Response<ArrayList<ServerInfo>>(){

            @Override
            public void onResponse(ArrayList<ServerInfo> servers) {

                SyncNext(servers, 0, cancellationToken, progress);
            }

            @Override
            public void onError(Exception ex) {

                progress.reportError(ex);
            }
        });
    }

    private void SyncNext(final ArrayList<ServerInfo> servers, final int index, final CancellationToken cancellationToken, final SyncProgress progress){

        if (index >= servers.size()){

            progress.reportComplete();
            return;
        }

        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
        }

        final ServerInfo server = servers.get(index);
        final double numServers = servers.size();
        final double[] numComplete = {0};

        new ServerSync(connectionManager, logger).Sync(server, cancellationToken, new SyncProgress(){

            private void OnServerDone(){

                numComplete[0]++;
                double percent = numComplete[0] / numServers;
                progress.report(percent * 100);

                SyncNext(servers, index + 1, cancellationToken, progress);
            }

            @Override
            public void onProgress(Double serverPercent) {

                double percent = numComplete[0] / numServers;
                percent += (serverPercent / 100);
                progress.report(percent * 100);
            }

            @Override
            public void onComplete() {

                OnServerDone();
            }

            @Override
            public void onFileUploaded(LocalFileInfo file) {

                progress.onFileUploaded(file);
            }

            @Override
            public void onCancelled() {

                OnServerDone();
            }

            @Override
            public void onError(Exception ex) {

                OnServerDone();
            }

            @Override
            public void onFileUploadError(LocalFileInfo file, Exception ex) {

                progress.onFileUploadError(file, ex);
            }
        });
    }
}
