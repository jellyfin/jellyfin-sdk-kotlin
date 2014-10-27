package MediaBrowser;

import MediaBrowser.ApiInteraction.ApiClient;
import MediaBrowser.ApiInteraction.Device.IDevice;
import MediaBrowser.Model.Devices.LocalFileInfo;

import java.util.ArrayList;
import java.util.Observable;

public class FakeDevice implements IDevice {

    @Override
    public String getDeviceName() {
        return "Device Name";
    }

    @Override
    public String getDeviceId() {
        return "Device Id";
    }

    @Override
    public ArrayList<LocalFileInfo> GetLocalPhotos() {
        return null;
    }

    @Override
    public ArrayList<LocalFileInfo> GetLocalVideos() {
        return null;
    }

    private Observable resumeFromSleepObservable = new Observable();

    @Override
    public Observable getResumeFromSleepObservable() {
        return resumeFromSleepObservable;
    }

    @Override
    public void UploadFile(LocalFileInfo file, ApiClient apiClient) {

    }
}
