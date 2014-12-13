package mediabrowser.apiinteraction.android.profiles;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import mediabrowser.model.dlna.*;
import mediabrowser.model.extensions.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class Api16Builder {

    public void buildProfiles(DeviceProfile profile){

        ArrayList<DirectPlayProfile> directPlayProfiles = new ArrayList<DirectPlayProfile>();
        ArrayList<CodecProfile> codecProfiles = new ArrayList<CodecProfile>();

        int numCodecs = MediaCodecList.getCodecCount();
        for (int i = 0; i < numCodecs; i++) {
            MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);

            ProcessMediaCodecInfo(codecInfo, directPlayProfiles, codecProfiles);
        }

        profile.setDirectPlayProfiles(directPlayProfiles.toArray(new DirectPlayProfile[directPlayProfiles.size()]));
        profile.setCodecProfiles(codecProfiles.toArray(new CodecProfile[codecProfiles.size()]));
    }

    protected void ProcessMediaCodecInfo(MediaCodecInfo codecInfo, ArrayList<DirectPlayProfile> directPlayProfiles, ArrayList<CodecProfile> codecProfiles){

        for (String type : codecInfo.getSupportedTypes()){

            final MediaCodecInfo.CodecCapabilities codecCapabilities = codecInfo.getCapabilitiesForType(type);

            ProcessMediaCodecInfoType(codecInfo, type, codecCapabilities, directPlayProfiles, codecProfiles);
        }
    }

    protected void ProcessMediaCodecInfoType(MediaCodecInfo codecInfo, String type, MediaCodecInfo.CodecCapabilities codecCapabilities, ArrayList<DirectPlayProfile> directPlayProfiles, ArrayList<CodecProfile> codecProfiles){

        addDirectPlayProfile(directPlayProfiles, type);

        ArrayList<CodecProfile> newCodecProfiles = getCodecProfiles(type, codecCapabilities);

        for (CodecProfile cp : newCodecProfiles){
            if (!containsCodecProfile(codecProfiles, cp)){
                codecProfiles.add(cp);
                processCodecProfile(codecInfo, type, codecCapabilities, cp);
            }
        }
    }

    protected void processCodecProfile(MediaCodecInfo codecInfo, String type, MediaCodecInfo.CodecCapabilities codecCapabilities, CodecProfile profile){

    }

    protected void addDirectPlayProfile(List<DirectPlayProfile> profiles, String type){

        String[] parts = type.split("/");
        if (parts.length != 2) return;

        DirectPlayProfile profile = new DirectPlayProfile();

        if (StringHelper.EqualsIgnoreCase(parts[0], "audio")) {
            profile.setType(DlnaProfileType.Audio);
        }
        else if (StringHelper.EqualsIgnoreCase(parts[0], "video")) {
            profile.setType(DlnaProfileType.Video);
        }
        else if (StringHelper.EqualsIgnoreCase(parts[0], "image")) {
            profile.setType(DlnaProfileType.Photo);
        }
        else{
            return;
        }

        String codecType = parts[1].toLowerCase();

        // Since we can't get supported codecs per container, we'll have to hardcode them
        if (profile.getType()==DlnaProfileType.Video){

            profile.setContainer(codecType);

            if (StringHelper.IndexOfIgnoreCase(codecType, "mp4") == 0){
                profile.setContainer("mp4");
                profile.setVideoCodec("h264,mpeg4");
                profile.setAudioCodec("aac");
            }
            else if (StringHelper.EqualsIgnoreCase("avc", codecType)){
                profile.setContainer("mp4");
                profile.setVideoCodec("h264,mpeg4");
                profile.setAudioCodec("aac");
            }
            else if (StringHelper.EqualsIgnoreCase("hevc", codecType)){
                profile.setContainer("mp4");
                profile.setVideoCodec("h265");
                profile.setAudioCodec("aac");
            }
            else if (StringHelper.IndexOfIgnoreCase(codecType, "vp8") != -1){
                profile.setContainer("webm");
            }
            else if (StringHelper.IndexOfIgnoreCase(codecType, "vp9") != -1){
                profile.setContainer("webm");
            }
            else if (StringHelper.EqualsIgnoreCase("3gpp", codecType)){
                profile.setContainer("3gp");
            }
            else {

                profile.setContainer(codecType);
            }
        }
        else if (profile.getType()==DlnaProfileType.Audio){

            if (StringHelper.IndexOfIgnoreCase(codecType, "mp4") == 0){
                profile.setContainer("aac");
            }
            else if (StringHelper.EqualsIgnoreCase("mpeg", codecType)){
                profile.setContainer("mp3");
            }
            else if (StringHelper.EqualsIgnoreCase("3gpp", codecType)){
                profile.setContainer("3gp");
            }
            else if (StringHelper.EqualsIgnoreCase("vorbis", codecType)){
                profile.setContainer("webm,webma");
            }
            else if (StringHelper.EqualsIgnoreCase("opus", codecType)){
                profile.setContainer("oga,ogg");
            }
            else {

                // Will cover flac, gsm, and others
                profile.setContainer(codecType);
            }
        }

        if (!containsDirectPlayProfile(profiles, profile)){
            profiles.add(profile);
        }
    }

    protected ArrayList<CodecProfile> getCodecProfiles(String type, MediaCodecInfo.CodecCapabilities codecCapabilities){

        ArrayList<CodecProfile> newlyAddedCodecProfiles = new ArrayList<CodecProfile>();

        String[] parts = type.split("/");
        if (parts.length != 2) return newlyAddedCodecProfiles;

        CodecProfile profile = new CodecProfile();
        ArrayList<ProfileCondition> conditions = new ArrayList<ProfileCondition>();

        if (StringHelper.EqualsIgnoreCase(parts[0], "audio")) {
            profile.setType(CodecType.Audio);
        }
        else if (StringHelper.EqualsIgnoreCase(parts[0], "video")) {
            profile.setType(CodecType.Video);
        }
        else{
            return newlyAddedCodecProfiles;
        }

        String codecType = parts[1].toLowerCase();

        if (profile.getType()==CodecType.Video){

            conditions.add(new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Width, "1920"));
            conditions.add(new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Height, "1080"));
            conditions.add(new ProfileCondition(ProfileConditionType.NotEquals, ProfileConditionValue.IsAnamorphic, "true"));

            if (StringHelper.IndexOfIgnoreCase(codecType, "avc") != -1){
                profile.setCodec("h264");

                conditions.add(new ProfileCondition(ProfileConditionType.Equals, ProfileConditionValue.IsCabac, "true"));
                conditions.add(new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.VideoBitDepth, "8"));
            }
            else if (StringHelper.IndexOfIgnoreCase(codecType, "hevc") != -1){
                profile.setCodec("h265");
            }
            else if (StringHelper.IndexOfIgnoreCase(codecType, "vp8") != -1) {
                profile.setCodec("vp8");
            }
            else if (StringHelper.IndexOfIgnoreCase(codecType, "vp9") != -1) {
                profile.setCodec("vp9");
            }
            else{
                profile.setCodec(codecType);
            }
        }
        else if (profile.getType()==CodecType.Audio){

            if (StringHelper.IndexOfIgnoreCase(codecType, "mp4") == 0){
                profile.setCodec("aac");
            }
            else if (StringHelper.EqualsIgnoreCase("mpeg", codecType)){
                profile.setCodec("mp3");
            }
            else if (StringHelper.EqualsIgnoreCase("opus", codecType)){
                profile.setCodec("vorbis");
            }
            else{
                profile.setCodec(codecType);
            }

            conditions.add(new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2"));
        }

        profile.setConditions(conditions.toArray(new ProfileCondition[conditions.size()]));

        newlyAddedCodecProfiles.add(profile);

        if (profile.getType()==CodecType.Audio && StringHelper.EqualsIgnoreCase("aac", profile.getCodec())) {
            // Create a duplicate under VideoAudioType
            CodecProfile videoAudioProfile = new CodecProfile();
            videoAudioProfile.setType(CodecType.VideoAudio);
            videoAudioProfile.setCodec("aac");

            ArrayList<ProfileCondition> videoAudioProfileConditions = new ArrayList<ProfileCondition>();

            for (ProfileCondition pc : profile.getConditions()){
                videoAudioProfileConditions.add(pc);
            }

            videoAudioProfile.setConditions(videoAudioProfileConditions.toArray(new ProfileCondition[videoAudioProfileConditions.size()]));

            newlyAddedCodecProfiles.add(videoAudioProfile);
        }

        return newlyAddedCodecProfiles;
    }

    private boolean containsDirectPlayProfile(List<DirectPlayProfile> profiles, DirectPlayProfile newProfile){

        for (DirectPlayProfile profile : profiles){
            if (profile.getType() == newProfile.getType()) {
                if (StringHelper.EqualsIgnoreCase(profile.getContainer(), newProfile.getContainer())) {

                    if (profile.getType() == DlnaProfileType.Audio){
                        if (StringHelper.EqualsIgnoreCase(profile.getAudioCodec(), newProfile.getAudioCodec())) {
                            return true;
                        }
                    }
                    else if (profile.getType() == DlnaProfileType.Video){
                        if (StringHelper.EqualsIgnoreCase(profile.getVideoCodec(), newProfile.getVideoCodec())) {
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    private boolean containsCodecProfile(List<CodecProfile> profiles, CodecProfile newProfile){

        for (CodecProfile profile : profiles){
            if (profile.getType() == newProfile.getType()) {
                if (StringHelper.EqualsIgnoreCase(profile.getCodec(), newProfile.getCodec())) {

                    return true;
                }
            }
        }
        return false;
    }
}
