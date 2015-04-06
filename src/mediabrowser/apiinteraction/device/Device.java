package mediabrowser.apiinteraction.device;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.AutomaticObservable;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.devices.LocalFileInfo;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Luke on 4/5/2015.
 */
public class Device implements IDevice {

    private String deviceId;
    private String deviceName;

    public Device(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public ArrayList<LocalFileInfo> GetLocalPhotos() {
        return new ArrayList<LocalFileInfo>();
    }

    @Override
    public ArrayList<LocalFileInfo> GetLocalVideos() {
        return new ArrayList<LocalFileInfo>();
    }

    private Observable resumeFromSleepObservable = new AutomaticObservable();
    @Override
    public Observable getResumeFromSleepObservable() {
        return resumeFromSleepObservable;
    }

    @Override
    public void UploadFile(LocalFileInfo file, ApiClient apiClient, IProgress<Double> progress, CancellationToken cancellationToken) {
        throw new UnsupportedOperationException();
    }
}
