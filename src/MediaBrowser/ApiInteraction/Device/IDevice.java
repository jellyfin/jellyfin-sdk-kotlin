package MediaBrowser.ApiInteraction.Device;

import MediaBrowser.ApiInteraction.ApiClient;
import MediaBrowser.Model.Devices.LocalFileInfo;

import java.util.ArrayList;
import java.util.Observable;

public interface IDevice {

    String getDeviceName();
    String getDeviceId();

    ArrayList<LocalFileInfo> GetLocalPhotos();
    ArrayList<LocalFileInfo> GetLocalVideos();

    Observable getResumeFromSleepObservable();

    void UploadFile(LocalFileInfo file, ApiClient apiClient);
}
