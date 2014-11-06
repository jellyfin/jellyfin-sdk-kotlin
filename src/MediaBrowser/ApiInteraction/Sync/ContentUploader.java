package MediaBrowser.ApiInteraction.Sync;

import MediaBrowser.ApiInteraction.ApiClient;
import MediaBrowser.ApiInteraction.Device.IDevice;
import MediaBrowser.ApiInteraction.Tasks.CancellationToken;
import MediaBrowser.ApiInteraction.Tasks.IProgress;
import MediaBrowser.ApiInteraction.Response;
import MediaBrowser.Model.Devices.ContentUploadHistory;
import MediaBrowser.Model.Devices.DevicesOptions;
import MediaBrowser.Model.Devices.LocalFileInfo;
import MediaBrowser.Model.Extensions.ListHelper;
import MediaBrowser.Model.Extensions.StringHelper;
import MediaBrowser.Model.Logging.ILogger;

import java.util.ArrayList;
import java.util.concurrent.Future;

public class ContentUploader {

    private ApiClient apiClient;
    private ILogger logger;

    public void UploadImages(final CancellationToken cancellationToken, final IProgress<Double> progress) {

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
                    UploadImagesInternal(cancellationToken, progress);
                }
            }

            @Override
            public void onError(Exception ex) {

                progress.reportError(null);
            }

        });
    }

    private void UploadImagesInternal(final CancellationToken cancellationToken,
                                      final IProgress<Double> progress){

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

        UploadNext(files, 0, cancellationToken, progress);
    }

    private void UploadNext(ArrayList<LocalFileInfo> files,
                            int index,
                            CancellationToken cancellationToken,
                            IProgress<Double> progress) {

        if (index >= files.size()){

            progress.reportComplete();
            return;
        }

        LocalFileInfo file = files.get(index);


    }

    private ArrayList<LocalFileInfo> GetFilesToUpload(ContentUploadHistory history, ArrayList<LocalFileInfo> localFiles){

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
}
