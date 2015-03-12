package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.users.UserAction;

import java.util.ArrayList;

/**
 * Created by Luke on 3/11/2015.
 */
public class MediaSync {

    private ILocalAssetManager localAssetManager;
    private ILogger logger;

    public MediaSync(ILocalAssetManager localAssetManager, ILogger logger) {
        this.localAssetManager = localAssetManager;
        this.logger = logger;
    }

    public void sync(final ApiClient apiClient,
                     final ServerInfo serverInfo,
                     final Progress<Double> progress,
                     final CancellationToken cancellationToken) {


        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
            return;
        }

        logger.Debug("Beginning media sync process with server Id: {0}", serverInfo.getId());

        // First report actions to the server that occurred while offline
        ReportOfflineActions(apiClient, serverInfo, new EmptyResponse(){

            @Override
            public  void onResponse(){

                if (cancellationToken.isCancellationRequested()){
                    progress.reportCancelled();
                    return;
                }

                progress.report(1.0);

                // Sync data
                SyncData(apiClient, serverInfo, false, cancellationToken, new EmptyResponse() {

                    @Override
                    public void onResponse() {

                        if (cancellationToken.isCancellationRequested()) {
                            progress.reportCancelled();
                            return;
                        }

                        progress.report(3.0);

                        // Get new media
                        GetNewMedia(apiClient, serverInfo, cancellationToken, new EmptyResponse() {

                            @Override
                            public void onResponse() {

                                progress.report(99.0);

                                // Do the data sync twice so the server knows what was removed from the device
                                SyncData(apiClient, serverInfo, true, cancellationToken, new EmptyResponse() {

                                    @Override
                                    public void onResponse() {

                                        progress.reportComplete();
                                    }

                                    @Override
                                    public void onError(Exception ex) {
                                        progress.reportError(ex);
                                    }
                                });
                            }

                            @Override
                            public void onError(Exception ex) {
                                progress.reportError(ex);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception ex) {
                        progress.reportError(ex);
                    }

                });
            }

            @Override
            public void onError(Exception ex){
                progress.reportError(ex);
            }
        });
    }

    private void SyncData(ApiClient apiClient,
                          ServerInfo serverInfo,
                          boolean syncUserItemAccess,
                          CancellationToken cancellationToken,
                          final EmptyResponse response){


        ArrayList<String> localIds = localAssetManager.getServerItemIds(serverInfo.getId());

        response.onResponse();
    }

    private void GetNewMedia(ApiClient apiClient,
                          ServerInfo serverInfo,
                          CancellationToken cancellationToken,
                          final EmptyResponse response){


        response.onResponse();
    }

    private void ReportOfflineActions(ApiClient apiClient,
                                      ServerInfo serverInfo,
                                      final EmptyResponse response){

        final ArrayList<UserAction> actions = localAssetManager.getUserActions(serverInfo.getId());

        logger.Debug("Reporting "+actions.size()+" offline actions to server " + serverInfo.getId());

        EmptyResponse onUserActionsReported = new EmptyResponse(response){

            @Override
            public  void onResponse(){
                for (UserAction action : actions)
                {
                    localAssetManager.delete(action);
                }
                response.onResponse();
            }
        };

        if (actions.size() > 0)
        {
            apiClient.ReportOfflineActions(actions, onUserActionsReported);
        }
        else{
            onUserActionsReported.onResponse();
        }
    }
}
