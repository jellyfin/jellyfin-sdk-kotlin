package mediabrowser.apiinteraction.android.profiles;

/**
 * Created by Luke on 4/21/2015.
 */
public class AndroidProfileOptions {

    public int DefaultH264Level = 40;
    public String DefaultH264Profile = "high|main|baseline|constrained baseline";
    public boolean SupportsAc3 = false;
    public boolean SupportsHls = true;
    public boolean SupportsDtsHdMa = false;
    public boolean SupportsDts = false;
    public boolean SupportsTrueHd = false;
    public boolean SupportsMkv = false;

    // Leave 0 for auto-detection
    public int VideoAudioAacChannels = 0;

    public AndroidProfileOptions() {

    }

    public AndroidProfileOptions(String deviceName) {

        deviceName = deviceName.toLowerCase().replace(" ", "");

        if (deviceName.indexOf("aftb") != -1){

            DefaultH264Level = 40;
            SupportsAc3 = true;
            VideoAudioAacChannels = 5;

        }
        else if (deviceName.indexOf("aftm") != -1){

            DefaultH264Level = 40;
            SupportsAc3 = true;
            VideoAudioAacChannels = 5;

        }
        else if (deviceName.indexOf("nexusplayer") != -1){

            DefaultH264Level = 41;

        }
        else if (deviceName.indexOf("adt-1") != -1){

            DefaultH264Level = 41;

        }
        else if (deviceName.indexOf("nvidiashield") != -1){

            SupportsDtsHdMa = true;
            SupportsDts = true;
            SupportsTrueHd = true;
            SupportsAc3 = true;
        }
    }
}
