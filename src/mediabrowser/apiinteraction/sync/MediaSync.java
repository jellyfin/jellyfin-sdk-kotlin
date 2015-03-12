package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.apiclient.ServerUserInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.sync.SyncDataRequest;
import mediabrowser.model.sync.SyncDataResponse;
import mediabrowser.model.users.UserAction;

import java.lang.reflect.Array;
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
                SyncData(apiClient, serverInfo, false, new EmptyResponse() {

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
                                SyncData(apiClient, serverInfo, true, new EmptyResponse() {

                                    @Override
                                    public void onResponse() {

                                        logger.Debug("Completed media sync process with server Id: {0}", serverInfo.getId());
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

    private void SyncData(final ApiClient apiClient,
                          final ServerInfo serverInfo,
                          final boolean syncUserItemAccess,
                          final EmptyResponse response){


        ArrayList<String> localIds = localAssetManager.getServerItemIds(serverInfo.getId());

        final SyncDataRequest request = new SyncDataRequest();
        request.setTargetId(apiClient.getDeviceId());
        request.setLocalItemIds(localIds);

        ArrayList<String> offlineUserIds = new ArrayList<>();
        for (ServerUserInfo user : serverInfo.getUsers()){
            offlineUserIds.add(user.getId());
        }
        request.setOfflineUserIds(offlineUserIds);

        apiClient.SyncData(request, new Response<SyncDataResponse>(response){

            @Override
            public void onResponse(SyncDataResponse result) {

                for(String itemIdToRemove : result.getItemIdsToRemove())
                {
                    removeItem(serverInfo.getId(), itemIdToRemove);
                }

                if (syncUserItemAccess)
                {
                    for (String itemId : result.getItemUserAccess().keySet())
                    {
                        LocalItem localItem = localAssetManager.getLocalItem(serverInfo.getId(), itemId);

                        ArrayList<String> userIdsWithAccess = result.getItemUserAccess().get(itemId);

                        if (!userIdsWithAccess.equals(localItem.getUserIdsWithAccess()))
                        {
                            localItem.setUserIdsWithAccess(userIdsWithAccess);
                            localAssetManager.addOrUpdate(localItem);
                        }
                    }
                }

                response.onResponse();
            }
        });
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

    private void removeItem(String serverId, String itemId)
    {
        LocalItem localItem = localAssetManager.getLocalItem(serverId, itemId);

        if (localItem == null)
        {
            return;
        }

        ArrayList<ItemFileInfo> files = localAssetManager.getFiles(localItem);

        for (ItemFileInfo file : files)
        {
            localAssetManager.deleteFile(file.getPath());
        }

        localAssetManager.delete(localItem);
    }
}
