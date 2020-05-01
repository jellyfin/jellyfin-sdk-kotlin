package org.jellyfin.apiclient.interaction;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import org.jellyfin.apiclient.interaction.device.IDevice;

public class AndroidDevice implements IDevice {
    private final Context context;
    private final String deviceId;
    private final String deviceName;

    public AndroidDevice(Context context) {
        this.context = context;
        deviceId = getDeviceIdInternal(context);
        deviceName = getDeviceNameInternal();
    }

    public AndroidDevice(Context context, String deviceId, String deviceName) {
        this.context = context;
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

    private String getDeviceIdInternal(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    private String getDeviceNameInternal() {
        String deviceName = Settings.Global.getString(context.getContentResolver(), "device_name");
        if (deviceName != null) {
            return deviceName;
        }

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }
}
