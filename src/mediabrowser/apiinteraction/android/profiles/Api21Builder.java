package mediabrowser.apiinteraction.android.profiles;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.provider.MediaStore;
import mediabrowser.model.dlna.CodecProfile;
import mediabrowser.model.dlna.DeviceProfile;
import mediabrowser.model.dlna.DirectPlayProfile;

import java.util.ArrayList;

public class Api21Builder extends Api16Builder{

    @Override
    protected void ProcessMediaCodecInfoType(MediaCodecInfo codecInfo, String type, MediaCodecInfo.CodecCapabilities codecCapabilities, ArrayList<DirectPlayProfile> directPlayProfiles, ArrayList<CodecProfile> codecProfiles){

        addDirectPlayProfile(directPlayProfiles, type);
        addCodecProfile(codecProfiles, type, codecCapabilities);
    }
}
