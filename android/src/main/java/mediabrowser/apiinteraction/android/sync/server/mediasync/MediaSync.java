package mediabrowser.apiinteraction.android.sync.server.mediasync;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.android.mediabrowser.Constants;
import mediabrowser.apiinteraction.android.mediabrowser.IMediaRes;
import mediabrowser.apiinteraction.android.sync.MediaSyncService;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.apiclient.ServerUserInfo;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.ImageOptions;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.entities.ImageType;
import mediabrowser.model.entities.MediaStream;
import mediabrowser.model.entities.MediaStreamType;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.sync.*;
import mediabrowser.model.users.UserAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luke on 3/11/2015.
 */
public class MediaSync {

    private ILocalAssetManager localAssetManager;
    private ILogger logger;
    private MediaSyncService mService;
    private IMediaRes mediaRes;

    public MediaSync(ILocalAssetManager localAssetManager, ILogger logger, Service mService, IMediaRes mediaRes) {
        this.localAssetManager = localAssetManager;
        this.logger = logger;
        this.mService = (MediaSyncService) mService;
        this.mediaRes = mediaRes;
    }

    public void sync(final ApiClient apiClient,
                     final ServerInfo serverInfo,
                     final Progress<Double> progress,
                     final CancellationToken cancellationToken) {


        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
            return;
        }

        logger.Debug("Beginning media sync process with server Id: %s", serverInfo.getId());

        final MediaSync mediaSync = this;

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
                        GetNewMedia(apiClient, serverInfo, cancellationToken, new GetNewMediaProgress(logger, apiClient, serverInfo, progress, mediaSync));
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

    void SyncData(final ApiClient apiClient,
                          final ServerInfo serverInfo,
                          final boolean syncUserItemAccess,
                          final EmptyResponse response){

        logger.Debug("Enter SyncData");

        ArrayList<String> localIds = localAssetManager.getServerItemIds(serverInfo.getId());

        final SyncDataRequest request = new SyncDataRequest();
        request.setTargetId(apiClient.getDeviceId());
        request.setLocalItemIds(localIds);

        ArrayList<String> offlineUserIds = new ArrayList<>();
        for (ServerUserInfo user : serverInfo.getUsers()){
            offlineUserIds.add(user.getId());
        }
        request.setOfflineUserIds(offlineUserIds);

        apiClient.SyncData(request, new SyncDataInnerResponse(response, localAssetManager, serverInfo, syncUserItemAccess, logger));
    }

    private void GetNewMedia(final ApiClient apiClient,
                             final ServerInfo serverInfo,
                             final CancellationToken cancellationToken,
                             final IProgress<Double> progress){

        logger.Debug("Begin GetNewMedia");

        apiClient.getReadySyncItems(apiClient.getDeviceId(), new GetReadySyncItemsResponse(logger, apiClient, serverInfo, cancellationToken, progress, this));
    }

    void GetNextItem(final ArrayList<SyncedItem> jobItems, final int index, final ApiClient apiClient, final ServerInfo serverInfo, final CancellationToken cancellationToken, final IProgress<Double> progress){

        if (index >= jobItems.size()){
            logger.Debug("GetNewMedia complete");
            progress.reportComplete();
            return;
        }

        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
            return;
        }

        SyncedItem jobItem = jobItems.get(index);

        GetItem(apiClient, serverInfo, jobItem, cancellationToken, new EmptyResponse() {

            private void onAny() {

                int numComplete = index + 1;
                double startingPercent = numComplete;
                startingPercent /= jobItems.size();
                startingPercent *= 100;
                progress.report(startingPercent);

                GetNextItem(jobItems, index + 1, apiClient, serverInfo, cancellationToken, progress);
            }

            @Override
            public void onResponse() {

                onAny();
            }

            @Override
            public void onError(Exception ex) {
                logger.ErrorException("Error getting synced item", ex);
                onAny();
            }
        });
    }

    private PendingIntent createContentIntent() {

        Intent openUI = new Intent(mService, mService.getMainActivityClass());
        openUI.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(mService, 654, openUI, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private Notification createNotification(LocalItem item, int progress) {

        Notification.Builder notificationBuilder = new Notification.Builder(mService);

        notificationBuilder
                .setSmallIcon(mediaRes.getAppIcon())
                .setUsesChronometer(true)
                .setContentIntent(createContentIntent())
                .setWhen(new Date().getTime())
                .setContentTitle("Emby")
                .setContentText("Downloading " + item.getItem().getName())
                .setProgress(100, progress, true)
                //.setContentText(description.getSubtitle())
        ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            notificationBuilder.setShowWhen(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            notificationBuilder.setVisibility(Notification.VISIBILITY_PUBLIC);
        }

        return notificationBuilder.build();
    }

    private void showNotification(LocalItem item, int progress, boolean close){

        NotificationManager notificationManager = (NotificationManager) mService.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 412;

        if (close){
            notificationManager.cancel(notificationId);
            mService.stopForeground(true);
        } else{

            Notification notif = createNotification(item, progress);

            mService.startForeground(notificationId, notif);
            notificationManager.notify(notificationId , notif);
        }
    }

    private void GetItem(final ApiClient apiClient,
                         final ServerInfo server,
                         final SyncedItem jobItem,
                         final CancellationToken cancellationToken,
                         final EmptyResponse response){

        final BaseItemDto libraryItem = jobItem.getItem();

        logger.Debug("Getting new item from sync %s", libraryItem.getName());

        LocalItem newLocalItem;

        try {
            newLocalItem = localAssetManager.createLocalItem(libraryItem, server, jobItem.getOriginalFileName());
        }
        catch (Exception ex) {
            response.onError(ex);
            return;
        }

        final LocalItem localItem = newLocalItem;

        apiClient.GetSyncJobItemFile(jobItem.getSyncJobItemId(), new Response<InputStream>(response){

            @Override
            public void onResponse(InputStream stream) {

                logger.Debug("Got item file response stream");
                showNotification(localItem, 0, false);

                try (InputStream copy = stream){

                    String mediaPath = localAssetManager.saveMedia(copy, localItem, server);

                    localItem.setLocalPath(mediaPath);
                    for (MediaSourceInfo mediaSourceInfo : localItem.getItem().getMediaSources()){
                        mediaSourceInfo.setPath(mediaPath);
                    }

                    showNotification(localItem, 100, true);
                }
                catch (IOException ioException){
                    showNotification(localItem, 0, true);
                    response.onError(ioException);
                    return;
                }

                // Create db record
                localAssetManager.addOrUpdate(localItem);

                GetNextImage(0, apiClient, localItem, cancellationToken, new Progress<Double>() {

                    @Override
                    public void onComplete() {

                        GetSubtitles(apiClient, jobItem, localItem, cancellationToken, new Progress<Double>() {

                            @Override
                            public void onComplete() {

                                apiClient.reportSyncJobItemTransferred(jobItem.getSyncJobItemId(), response);
                            }

                            @Override
                            public void onCancelled() {

                                response.onResponse();
                            }

                            @Override
                            public void onError(Exception ex) {

                                response.onError(ex);
                            }
                        });
                    }

                    @Override
                    public void onCancelled() {

                        response.onResponse();
                    }

                    @Override
                    public void onError(Exception ex) {

                        response.onError(ex);
                    }
                });
            }
        });
    }

    private void GetNextImage(final int index, final ApiClient apiClient, final LocalItem item, final CancellationToken cancellationToken, final IProgress<Double> progress) {

        final int numImages = 4;

        if (index >= numImages){
            progress.reportComplete();
            return;
        }

        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
            return;
        }

        BaseItemDto libraryItem = item.getItem();

        String serverId = libraryItem.getServerId();
        String itemId = null;
        String imageTag = null;
        ImageType imageType = ImageType.Primary;

        switch (index) {

            case 0:
                itemId = libraryItem.getId();
                imageType = ImageType.Primary;
                imageTag = libraryItem.getImageTags() == null ?
                        null :
                        libraryItem.getImageTags().get(ImageType.Primary);
                break;
            case 1:
                itemId = libraryItem.getSeriesId();
                imageType = ImageType.Primary;
                imageTag = libraryItem.getSeriesPrimaryImageTag();
                break;
            case 2:
                itemId = libraryItem.getSeriesId();
                imageType = ImageType.Thumb;
                imageTag = libraryItem.getSeriesPrimaryImageTag();
                break;
            case 3:
                itemId = libraryItem.getAlbumId();
                imageType = ImageType.Primary;
                imageTag = libraryItem.getAlbumPrimaryImageTag();
                break;
            default:
                break;
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            progress.reportComplete();
            return;
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(imageTag))
        {
            int numComplete = index + 1;
            double startingPercent = numComplete;
            startingPercent /= numImages;
            startingPercent *= 100;
            progress.report(startingPercent);

            GetNextImage(index + 1, apiClient, item, cancellationToken, progress);
            return;
        }

        DownloadImage(apiClient, serverId, itemId, imageTag, imageType, new EmptyResponse() {

            @Override
            public void onResponse() {

                int numComplete = index + 1;
                double startingPercent = numComplete;
                startingPercent /= numImages;
                startingPercent *= 100;
                progress.report(startingPercent);

                GetNextImage(index + 1, apiClient, item, cancellationToken, progress);
            }

            @Override
            public void onError(Exception ex) {

                logger.ErrorException("Error downloading image", ex);
                GetNextImage(index + 1, apiClient, item, cancellationToken, progress);
            }

        });
    }

    private void DownloadImage(ApiClient apiClient,
                                     final String serverId,
                                     final String itemId,
                                     final String imageTag,
                                     ImageType imageType,
                                     final EmptyResponse response)
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

                try (InputStream copy = stream){

                    localAssetManager.saveImage(serverId, itemId, imageTag, copy);
                    triggerInnerResponse();
                }
                catch (Exception ex){
                    response.onError(ex);
                    return;
                }

            }
        });
    }

    private void GetSubtitles(ApiClient apiClient,
                              SyncedItem jobItem,
                              final LocalItem item,
                              CancellationToken cancellationToken,
                              IProgress<Double> progress) {

        ArrayList<ItemFileInfo> files = new ArrayList<>();

        for (ItemFileInfo file : jobItem.getAdditionalFiles()){

            if (file.getType() == ItemFileType.Subtitles){
                files.add(file);
            }
        }

        if (jobItem.getItem().getMediaSources().size() == 0){
            logger.Error("Cannot download subtitles because video has no media source info.");
            progress.reportComplete();
            return;
        }

        MediaSourceInfo mediaSource = jobItem.getItem().getMediaSources().get(0);

        GetNextSubtitle(files, 0, apiClient, jobItem, item, mediaSource, cancellationToken, progress);
    }

    void GetNextSubtitle(final ArrayList<ItemFileInfo> files,
                                 final int index,
                                 final ApiClient apiClient,
                                 final SyncedItem jobItem,
                                 final LocalItem item,
                                 final MediaSourceInfo mediaSource,
                                 final CancellationToken cancellationToken,
                                 final IProgress<Double> progress) {

        if (index >= files.size()){
            progress.reportComplete();
            return;
        }

        if (cancellationToken.isCancellationRequested()){
            progress.reportCancelled();
            return;
        }

        ItemFileInfo file = files.get(index);
        MediaStream subtitleStream = null;

        for (MediaStream stream : mediaSource.getMediaStreams()){

            if (stream.getType() == MediaStreamType.Subtitle && stream.getIndex() == file.getIndex()){
                subtitleStream = stream;
                break;
            }
        }

        if (subtitleStream == null){

            // We shouldn't get in here, but let's just be safe anyway
            logger.Error("Cannot download subtitles because matching stream info wasn't found.");
            GetNextSubtitle(files, index + 1, apiClient, jobItem, item, mediaSource, cancellationToken, progress);
            return;
        }

        apiClient.getSyncJobItemAdditionalFile(jobItem.getSyncJobItemId(), file.getName(), new GetSyncJobItemAdditionalFileResponse(logger, apiClient, localAssetManager, jobItem, mediaSource, subtitleStream, cancellationToken, item, files, index, progress, this));
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
