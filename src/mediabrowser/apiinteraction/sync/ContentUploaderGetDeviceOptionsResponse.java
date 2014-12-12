package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.devices.DevicesOptions;
import mediabrowser.model.extensions.ListHelper;
import mediabrowser.model.logging.ILogger;

public class ContentUploaderGetDeviceOptionsResponse extends Response<DevicesOptions> {

    private ContentUploader contentUploader;
    private ApiClient apiClient;
    private ILogger logger;
    private SyncProgress progress;
    private CancellationToken cancellationToken;

    public ContentUploaderGetDeviceOptionsResponse(ContentUploader contentUploader, ApiClient apiClient, ILogger logger, SyncProgress progress, CancellationToken cancellationToken) {
        this.contentUploader = contentUploader;
        this.apiClient = apiClient;
        this.logger = logger;
        this.progress = progress;
        this.cancellationToken = cancellationToken;
    }

    @Override
    public void onResponse(DevicesOptions options) {

        final String deviceId = apiClient.getDeviceId();

        if (options.getEnabledCameraUploadDevices() == null || !ListHelper.ContainsIgnoreCase(options.getEnabledCameraUploadDevices(), deviceId))
        {
            logger.Debug("Camera upload is not enabled for this device.");
            progress.reportComplete();
        }
        else{
            contentUploader.UploadImagesInternal(progress, cancellationToken);
        }
    }

    @Override
    public void onError(Exception ex) {

        progress.reportError(ex);
    }
}
