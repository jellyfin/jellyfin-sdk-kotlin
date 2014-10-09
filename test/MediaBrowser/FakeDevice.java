package MediaBrowser;

import MediaBrowser.ApiInteraction.IDevice;

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

    private Observable resumeFromSleepObservable = new Observable();

    @Override
    public Observable getResumeFromSleepObservable() {
        return resumeFromSleepObservable;
    }
}
