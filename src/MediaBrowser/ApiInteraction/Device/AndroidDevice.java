package MediaBrowser.ApiInteraction.Device;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.util.Observable;

public class AndroidDevice implements IDevice{

    private final String deviceName;
    private final String deviceId;

    public AndroidDevice(Context context){

        deviceId = getDeviceIdInternal(context);
        deviceName = getDeviceNameInternal();
    }

    @Override
    public String getDeviceName() {

        return deviceName;
    }

    @Override
    public String getDeviceId() {

        return deviceId;
    }

    private String getDeviceIdInternal(Context context) {

        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    private String getDeviceNameInternal() {

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    private Observable resumeFromSleepObservable = new Observable();
    @Override
    public Observable getResumeFromSleepObservable() {
        return resumeFromSleepObservable;
    }

}
