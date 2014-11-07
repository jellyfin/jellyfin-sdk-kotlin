package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.Devices.ContentUploadHistory;
import mediabrowser.model.Devices.DevicesOptions;
import mediabrowser.model.Devices.LocalFileInfo;
import mediabrowser.model.Extensions.ListHelper;
import mediabrowser.model.Extensions.StringHelper;
import mediabrowser.model.Logging.ILogger;

import java.util.ArrayList;

public class ContentUploader {

    private ApiClient apiClient;
    private ILogger logger;

    public ContentUploader(ApiClient apiClient, ILogger logger) {
        this.apiClient = apiClient;
        this.logger = logger;
    }

    public void UploadImages(final IProgress<Double> progress, final CancellationToken cancellationToken) {

        apiClient.GetDevicesOptions(new Response<DevicesOptions>(){

            @Override
            public void onResponse(DevicesOptions options) {

                final String deviceId = apiClient.getDeviceId();

                if (!ListHelper.ContainsIgnoreCase(options.getEnabledCameraUploadDevices(), deviceId))
                {
                    logger.Debug("Camera upload is not enabled for this device.");
                    progress.reportComplete();
                }
                else{
                    UploadImagesInternal(progress, cancellationToken);
                }
            }

            @Override
            public void onError(Exception ex) {

                progress.reportError(null);
            }

        });
    }

    private void UploadImagesInternal(final IProgress<Double> progress, final CancellationToken cancellationToken){

        apiClient.GetContentUploadHistory(new Response<ContentUploadHistory>() {

            @Override
            public void onResponse(ContentUploadHistory history) {

                UploadImagesInternal(history, cancellationToken, progress);
            }

            @Override
            public void onError(Exception ex) {

                progress.reportError(null);
            }
        });
    }

    private void UploadImagesInternal(ContentUploadHistory history,
                                      CancellationToken cancellationToken,
                                      IProgress<Double> progress){

        IDevice device = apiClient.getDevice();

        ArrayList<LocalFileInfo> files = device.GetLocalPhotos();
        files.addAll(device.GetLocalVideos());

        files = GetFilesToUpload(history, files);

        UploadNext(files, 0, device, cancellationToken, progress);
    }

    private void UploadNext(final ArrayList<LocalFileInfo> files,
                            final int index,
                            final IDevice device,
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

        LocalFileInfo file = files.get(index);

        UploadFile(file, device, new Progress<Double>(){

            private void GoNext() {

                double numComplete = index+ 1;
                numComplete /= files.size();
                progress.report(numComplete * 100);

                UploadNext(files, index + 1, device, cancellationToken, progress);
            }

            @Override
            public void onComplete() {

                GoNext();
            }

            @Override
            public void onError(Exception ex) {

                progress.reportError(ex);
            }

            @Override
            public void onCancelled() {

                progress.reportCancelled();
            }

            @Override
            public void onProgress(Double value) {

                // TODO: This is progress for the individual file
            }

        }, cancellationToken);
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

        logger.Info("Uploading file id", file.getId());
        logger.Info("Uploading file name", file.getName());
        logger.Info("Uploading file album", file.getAlbum());
        logger.Info("Uploading file mime type", file.getMimeType());

        device.UploadFile(file, apiClient, progress, cancellationToken);
    }
}
