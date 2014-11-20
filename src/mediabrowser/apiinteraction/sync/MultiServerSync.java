package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.IConnectionManager;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;

import java.util.ArrayList;

public class MultiServerSync {

    private IConnectionManager connectionManager;
    private ILogger logger;

    public MultiServerSync(IConnectionManager connectionManager, ILogger logger) {
        this.connectionManager = connectionManager;
        this.logger = logger;
    }

    public void Sync(final CancellationToken cancellationToken, final IProgress<Double> progress){

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

    private void SyncNext(final ArrayList<ServerInfo> servers, final int index, final CancellationToken cancellationToken, final IProgress<Double> progress){

        if (index >= servers.size()){

            progress.reportComplete();
            return;
        }

        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
        }

        ServerInfo server = servers.get(index);

        new ServerSync(connectionManager, logger).Sync(server, cancellationToken, new Progress<Double>(){

            private void OnServerDone(){
                SyncNext(servers, index+ 1, cancellationToken, progress);
            }

            @Override
            public void onProgress(Double percent) {


            }

            @Override
            public void onComplete() {

                OnServerDone();
            }

            @Override
            public void onCancelled() {
                OnServerDone();
            }

            @Override
            public void onError(Exception ex) {

                OnServerDone();
            }
        });
    }
}
