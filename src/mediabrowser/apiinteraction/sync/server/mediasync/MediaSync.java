package mediabrowser.apiinteraction.sync.server.mediasync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.apiclient.ServerUserInfo;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.dto.ImageOptions;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.entities.ImageType;
import mediabrowser.model.entities.MediaStream;
import mediabrowser.model.entities.MediaStreamType;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.sync.SyncDataRequest;
import mediabrowser.model.sync.SyncedItem;
import mediabrowser.model.users.UserAction;

import java.io.InputStream;
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
        ReportOfflineActions(apiClient, serverInfo, new EmptyResponse() {

            @Override
            public void onResponse() {

                if (cancellationToken.isCancellationRequested()) {
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
                                SyncData(apiClient, serverInfo, true, new SecondSyncDataResponse(logger, serverInfo, progress));
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

        apiClient.SyncData(request, new SyncDataInnerResponse(response, localAssetManager, serverInfo, syncUserItemAccess));
    }

    private void GetNewMedia(ApiClient apiClient,
                          ServerInfo serverInfo,
                          CancellationToken cancellationToken,
                          final EmptyResponse response){


        response.onResponse();
    }

    private void DownloadImage(ApiClient apiClient,
                                     final String serverId,
                                     final String itemId,
                                     final String imageTag,
                                     ImageType imageType,
                                     EmptyResponse response)
    {
        boolean hasImage = localAssetManager.hasImage(serverId, itemId, imageTag);

        if (hasImage)
        {
            response.onResponse();
            return;
        }

        ImageOptions options = new ImageOptions();
        options.setImageType(imageType);
        options.setTag(imageTag);

        String url = apiClient.GetImageUrl(itemId, options);

        apiClient.getResponseStream(url, new Response<InputStream>(response) {

            @Override
            public void onResponse(InputStream stream) {

                localAssetManager.saveImage(serverId, itemId, imageTag, stream);
                triggerInnerResponse();
            }
        });
    }

    private void DownloadSubtitle(ApiClient apiClient,
                                  SyncedItem jobItem,
                                  final LocalItem item,
                                  MediaSourceInfo mediaSource,
                                  ItemFileInfo file,
                                  final EmptyResponse response)
    {
        MediaStream subtitleStream = null;

        for(MediaStream stream : mediaSource.getMediaStreams()){
            if (stream.getType() == MediaStreamType.Subtitle && stream.getIndex()== file.getIndex()){
                subtitleStream = stream;
                break;
            }
        }

        if (subtitleStream == null){
            response.onError(new Exception("MediaStream not found."));
            return;
        }

        final MediaStream finalSubtitleStream = subtitleStream;

        apiClient.getSyncJobItemAdditionalFile(jobItem.getSyncJobItemId(), file.getName(), new Response<InputStream>(response){
            @Override
            public void onResponse(InputStream stream) {

                String path = localAssetManager.saveSubtitles(stream, finalSubtitleStream.getCodec(), item, finalSubtitleStream.getLanguage(), finalSubtitleStream.getIsForced());

                finalSubtitleStream.setPath(path);

                localAssetManager.addOrUpdate(item);

                triggerInnerResponse();
            }
        });
    }

    private void ReportOfflineActions(ApiClient apiClient,
                                      ServerInfo serverInfo,
                                      final EmptyResponse response){

        final ArrayList<UserAction> actions = localAssetManager.getUserActions(serverInfo.getId());

        logger.Debug("Reporting "+actions.size()+" offline actions to server " + serverInfo.getId());

        EmptyResponse onUserActionsReported = new UserActionsReportedResponse(response, actions, localAssetManager);

        if (actions.size() > 0)
        {
            apiClient.ReportOfflineActions(actions, onUserActionsReported);
        }
        else{
            onUserActionsReported.onResponse();
        }
    }
}
