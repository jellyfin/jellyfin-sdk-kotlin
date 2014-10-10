package MediaBrowser.ApiInteraction.Device;

import java.util.Observable;

public interface IDevice {

    String getDeviceName();
    String getDeviceId();

    Observable getResumeFromSleepObservable();
}
