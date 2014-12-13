package mediabrowser.apiinteraction.android;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import mediabrowser.model.dlna.*;
import mediabrowser.model.dlna.profiles.DefaultProfile;
import mediabrowser.model.extensions.StringHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AndroidProfile extends DefaultProfile
{
	public AndroidProfile()
	{
		this(true, true, new String[] {"baseline", "constrained baseline"});
	}

	public AndroidProfile(boolean supportsHls, boolean supportsMpegDash, String[] supportedH264Profiles)
	{
		setName("Android");

		java.util.ArrayList<TranscodingProfile> transcodingProfiles = new java.util.ArrayList<TranscodingProfile>();

		TranscodingProfile tempVar = new TranscodingProfile();
		tempVar.setContainer("mp3");
		tempVar.setAudioCodec("mp3");
		tempVar.setType(DlnaProfileType.Audio);
		transcodingProfiles.add(tempVar);

		if (supportsMpegDash)
		{

		}
		if (supportsHls)
		{
			TranscodingProfile tempVar2 = new TranscodingProfile();
			tempVar2.setProtocol("hls");
			tempVar2.setContainer("ts");
			tempVar2.setVideoCodec("h264");
			tempVar2.setAudioCodec("aac");
			tempVar2.setType(DlnaProfileType.Video);
			tempVar2.setContext(EncodingContext.Streaming);
			transcodingProfiles.add(tempVar2);
		}
		TranscodingProfile tempVar3 = new TranscodingProfile();
		tempVar3.setContainer("mp4");
		tempVar3.setVideoCodec("h264");
		tempVar3.setAudioCodec("aac");
		tempVar3.setType(DlnaProfileType.Video);
		tempVar3.setContext(EncodingContext.Static);
		transcodingProfiles.add(tempVar3);

		setTranscodingProfiles(transcodingProfiles.toArray(new TranscodingProfile[0]));

		DirectPlayProfile tempVar4 = new DirectPlayProfile();
		tempVar4.setContainer("mp4");
		tempVar4.setVideoCodec("h264,mpeg4");
		tempVar4.setAudioCodec("aac");
		tempVar4.setType(DlnaProfileType.Video);
		DirectPlayProfile tempVar5 = new DirectPlayProfile();
		tempVar5.setContainer("mp4,aac");
		tempVar5.setAudioCodec("aac");
		tempVar5.setType(DlnaProfileType.Audio);
		DirectPlayProfile tempVar6 = new DirectPlayProfile();
		tempVar6.setContainer("mp3");
		tempVar6.setAudioCodec("mp3");
		tempVar6.setType(DlnaProfileType.Audio);
		DirectPlayProfile tempVar7 = new DirectPlayProfile();
		tempVar7.setContainer("flac");
		tempVar7.setAudioCodec("flac");
		tempVar7.setType(DlnaProfileType.Audio);
		DirectPlayProfile tempVar8 = new DirectPlayProfile();
		tempVar8.setContainer("ogg");
		tempVar8.setAudioCodec("vorbis");
		tempVar8.setType(DlnaProfileType.Audio);
		DirectPlayProfile tempVar9 = new DirectPlayProfile();
		tempVar9.setContainer("jpeg,png,gif,bmp");
		tempVar9.setType(DlnaProfileType.Photo);
		setDirectPlayProfiles(new DirectPlayProfile[] {tempVar4, tempVar5, tempVar6, tempVar7, tempVar8, tempVar9});

		CodecProfile tempVar10 = new CodecProfile();
		tempVar10.setType(CodecType.Video);
		tempVar10.setCodec("h264");
		tempVar10.setConditions(new ProfileCondition[]
				{
						new ProfileCondition(ProfileConditionType.EqualsAny, ProfileConditionValue.VideoProfile, tangible.DotNetToJavaStringHelper.join("|", supportedH264Profiles)),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Width, "1920"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Height, "1080"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.VideoBitDepth, "8"),
						new ProfileCondition(ProfileConditionType.NotEquals, ProfileConditionValue.IsAnamorphic, "true"),
						new ProfileCondition(ProfileConditionType.Equals, ProfileConditionValue.IsCabac, "true")
				});
		CodecProfile tempVar11 = new CodecProfile();
		tempVar11.setType(CodecType.Video);
		tempVar11.setConditions(new ProfileCondition[]
				{
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Width, "1920"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Height, "1080"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.VideoBitDepth, "8"),
						new ProfileCondition(ProfileConditionType.NotEquals, ProfileConditionValue.IsAnamorphic, "true")
				});
		CodecProfile tempVar12 = new CodecProfile();
		tempVar12.setType(CodecType.VideoAudio);
		tempVar12.setCodec("aac");
		tempVar12.setConditions(new ProfileCondition[] {new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2")});
		CodecProfile tempVar13 = new CodecProfile();
		tempVar13.setType(CodecType.Audio);
		tempVar13.setCodec("aac");
		tempVar13.setConditions(new ProfileCondition[] {new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2")});
		CodecProfile tempVar14 = new CodecProfile();
		tempVar14.setType(CodecType.Audio);
		tempVar14.setCodec("mp3");
		tempVar14.setConditions(new ProfileCondition[]
				{
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioBitrate, "320000")
				});
		setCodecProfiles(new CodecProfile[] {tempVar10, tempVar11, tempVar12, tempVar13, tempVar14});

		buildDynamicProfiles(supportedH264Profiles);
	}

	private void buildDynamicProfiles(String[] supportedH264Profiles){

		if (Build.VERSION.SDK_INT < 16){
			return;
		}

		buildDynamicProfilesInternal();

		for (CodecProfile profile : getCodecProfiles()){

			if (profile.getType() == CodecType.Video){

				if (StringHelper.EqualsIgnoreCase(profile.getCodec(), "h264")) {

					ArrayList<ProfileCondition> conditions = new ArrayList<ProfileCondition>();
					conditions.add(new ProfileCondition(ProfileConditionType.EqualsAny, ProfileConditionValue.VideoProfile, tangible.DotNetToJavaStringHelper.join("|", supportedH264Profiles)));

					for (ProfileCondition existing : profile.getConditions()){
						conditions.add(existing);
					}

					profile.setConditions(conditions.toArray(new ProfileCondition[conditions.size()]));
				}
			}
		}
	}

	private void buildDynamicProfilesInternal(){

		ArrayList<DirectPlayProfile> directPlayProfiles = new ArrayList<DirectPlayProfile>();
		ArrayList<CodecProfile> codecProfiles = new ArrayList<CodecProfile>();

		int numCodecs = MediaCodecList.getCodecCount();
		for (int i = 0; i < numCodecs; i++) {
			MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);

			for (String type : codecInfo.getSupportedTypes()){

				final MediaCodecInfo.CodecCapabilities codecCapabilities = codecInfo.getCapabilitiesForType(type);

				addDirectPlayProfile(directPlayProfiles, type);
				addCodecProfile(codecProfiles, type, codecCapabilities);
			}
		}

		setDirectPlayProfiles(directPlayProfiles.toArray(new DirectPlayProfile[directPlayProfiles.size()]));
		setCodecProfiles(codecProfiles.toArray(new CodecProfile[codecProfiles.size()]));
	}

	private void addDirectPlayProfile(List<DirectPlayProfile> profiles, String type){

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

			if (StringHelper.IndexOfIgnoreCase("mp4", codecType) == 0){
				profile.setContainer("mp4");
				profile.setVideoCodec("h264,mpeg4");
				profile.setAudioCodec("aac");
			}
			else if (StringHelper.IndexOfIgnoreCase("avc", codecType) != -1){
				profile.setContainer("mp4");
				profile.setVideoCodec("h264,mpeg4");
				profile.setAudioCodec("aac");
			}
			else if (StringHelper.IndexOfIgnoreCase("hevc", codecType) != -1){
				profile.setContainer("mp4");
				profile.setVideoCodec("h265");
				profile.setAudioCodec("aac");
			}
			else if (StringHelper.IndexOfIgnoreCase("vp8", codecType) != -1){
				profile.setContainer("webm");
			}
			else if (StringHelper.IndexOfIgnoreCase("vp9", codecType) != -1){
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

			if (StringHelper.IndexOfIgnoreCase("mp4", codecType) == 0){
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

	private void addCodecProfile(List<CodecProfile> profiles, String type, MediaCodecInfo.CodecCapabilities codecCapabilities){

		String[] parts = type.split("/");
		if (parts.length != 2) return;

		CodecProfile profile = new CodecProfile();
		ArrayList<ProfileCondition> conditions = new ArrayList<ProfileCondition>();

		if (StringHelper.EqualsIgnoreCase(parts[0], "audio")) {
			profile.setType(CodecType.Audio);
		}
		else if (StringHelper.EqualsIgnoreCase(parts[0], "video")) {
			profile.setType(CodecType.Video);
		}
		else{
			return;
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
				profile.setCodec("vpx");
			}
			else if (StringHelper.IndexOfIgnoreCase(codecType, "vp9") != -1) {
				profile.setCodec("vpx");
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

		if (!containsCodecProfile(profiles, profile)){
			profiles.add(profile);
		}

		if (profile.getType()==CodecType.Audio && StringHelper.EqualsIgnoreCase("aac", profile.getCodec())) {
			// Create a duplicate under VideoAudioType
			CodecProfile videoAudioProfile = new CodecProfile();
			videoAudioProfile.setType(CodecType.VideoAudio);
			videoAudioProfile.setCodec("aac");
			videoAudioProfile.setConditions(profile.getConditions());

			if (!containsCodecProfile(profiles, videoAudioProfile)){
				profiles.add(videoAudioProfile);
			}
		}
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