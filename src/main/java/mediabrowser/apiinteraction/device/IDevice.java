package mediabrowser.apiinteraction.device;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.devices.LocalFileInfo;

import java.util.ArrayList;
import java.util.Observable;

public interface IDevice {

    String getDeviceName();
    String getDeviceId();

    ArrayList<LocalFileInfo> GetLocalPhotos();
    ArrayList<LocalFileInfo> GetLocalVideos();

    Observable getResumeFromSleepObservable();

    void UploadFile(LocalFileInfo file,
                    ApiClient apiClient,
                    IProgress<Double> progress,
                    CancellationToken cancellationToken);
}
