package MediaBrowser.apiinteraction.device;

import MediaBrowser.apiinteraction.ApiClient;
import MediaBrowser.apiinteraction.tasks.CancellationToken;
import MediaBrowser.apiinteraction.tasks.IProgress;
import MediaBrowser.Model.Devices.LocalFileInfo;

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
