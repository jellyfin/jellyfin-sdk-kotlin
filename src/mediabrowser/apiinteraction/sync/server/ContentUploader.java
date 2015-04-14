package mediabrowser.apiinteraction.sync.server;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.sync.SyncProgress;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.devices.ContentUploadHistory;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;

import java.util.ArrayList;

public class ContentUploader {

    private ApiClient apiClient;
    private ILogger logger;

    public ContentUploader(ApiClient apiClient, ILogger logger) {
        this.apiClient = apiClient;
        this.logger = logger;
    }

    public void UploadImages(final SyncProgress progress, final CancellationToken cancellationToken) {

        apiClient.GetDevicesOptions(new ContentUploaderGetDeviceOptionsResponse(this, apiClient, logger, progress, cancellationToken));
    }

    void UploadImagesInternal(final SyncProgress progress, final CancellationToken cancellationToken){

        apiClient.GetContentUploadHistory(new ContentUploaderHistoryResponse(this, progress, cancellationToken));
    }

    void UploadImagesInternal(ContentUploadHistory history,
                                      CancellationToken cancellationToken,
                                      SyncProgress progress){

        IDevice device = apiClient.getDevice();

        ArrayList<LocalFileInfo> files = device.GetLocalPhotos();
        logger.Debug("Found "+files.size()+" photos on device");

        ArrayList<LocalFileInfo> videos = device.GetLocalVideos();
        logger.Debug("Found "+videos.size()+" videos on device");
        files.addAll(videos);

        files = GetFilesToUpload(history, files);

        logger.Debug("ContentUploader will upload "+files.size()+" files");

        UploadNext(files, 0, device, cancellationToken, progress);
    }

    void UploadNext(final ArrayList<LocalFileInfo> files,
                            final int index,
                            final IDevice device,
                            final CancellationToken cancellationToken,
                            final SyncProgress progress) {

        if (index >= files.size()){

            progress.reportComplete();
            return;
        }

        if (cancellationToken.isCancellationRequested()){

            progress.reportCancelled();
            return;
        }

        final LocalFileInfo file = files.get(index);

        logger.Debug("ContentUploader will upload file %s", file.getName());

        UploadFile(file, device, new FileUploadProgress(this, device, files, index, progress, cancellationToken), cancellationToken);
    }

    private ArrayList<LocalFileInfo> GetFilesToUpload(ContentUploadHistory history,
                                                      ArrayList<LocalFileInfo> localFiles){

        ArrayList<LocalFileInfo> files = new ArrayList<LocalFileInfo>();

        for(LocalFileInfo localFile : localFiles) {

            boolean alreadyUploaded = false;

            for(LocalFileInfo uploadedFile : history.getFilesUploaded()) {

                if (StringHelper.EqualsIgnoreCase(uploadedFile.getId(), localFile.getId())){
                    alreadyUploaded = true;
                    break;
                }
            }

            if (!alreadyUploaded){
                files.add(localFile);
            }
        }

        return  files;
    }

    private void UploadFile(LocalFileInfo file,
                            IDevice device,
                            IProgress<Double> progress,
                            CancellationToken cancellationToken) {

        logger.Info("Uploading file id: "+ file.getId());
        logger.Info("Uploading file name: "+ file.getName());
        logger.Info("Uploading file album: "+ file.getAlbum());
        logger.Info("Uploading file mime type: "+ file.getMimeType());

        device.UploadFile(file, apiClient, progress, cancellationToken);
    }
}
