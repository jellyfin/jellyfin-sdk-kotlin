package mediabrowser.apiinteraction.android.profiles;

import android.os.Build;
import mediabrowser.model.dlna.*;
import mediabrowser.model.dlna.profiles.DefaultProfile;

public class AndroidProfile extends DefaultProfile
{
	public AndroidProfile()
	{
		this(true, true);
	}

	public AndroidProfile(boolean supportsHls, boolean supportsMpegDash)
	{
		setName("Android");

		setMaxStaticBitrate(20000000);
		setMaxStreamingBitrate(20000000);

		// Adds a lot of weight and not needed in this context
		setProtocolInfo(null);

		java.util.ArrayList<TranscodingProfile> transcodingProfiles = new java.util.ArrayList<TranscodingProfile>();

		TranscodingProfile tempVar = new TranscodingProfile();
		tempVar.setContainer("mp3");
		tempVar.setAudioCodec("mp3");
		tempVar.setType(DlnaProfileType.Audio);
		tempVar.setContext(EncodingContext.Streaming);
		transcodingProfiles.add(tempVar);

		TranscodingProfile tempVar0 = new TranscodingProfile();
		tempVar0.setContainer("mp3");
		tempVar0.setAudioCodec("mp3");
		tempVar0.setType(DlnaProfileType.Audio);
		tempVar0.setContext(EncodingContext.Static);
		transcodingProfiles.add(tempVar0);

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

		TranscodingProfile mkvProfile = new TranscodingProfile();
        	mkvProfile.setContainer("webm");
        	mkvProfile.setVideoCodec("vpx");
        	mkvProfile.setAudioCodec("vorbis");
        	mkvProfile.setType(DlnaProfileType.Video);
        	mkvProfile.setContext(EncodingContext.Streaming);
        	transcodingProfiles.add(mkvProfile);

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
						new ProfileCondition(ProfileConditionType.EqualsAny, ProfileConditionValue.VideoProfile, "baseline|constrained baseline"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Width, "1920"),
						//new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Height, "1080"),
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.VideoBitDepth, "8"),
						new ProfileCondition(ProfileConditionType.NotEquals, ProfileConditionValue.IsAnamorphic, "true")
				});
		CodecProfile tempVar11 = new CodecProfile();
		tempVar11.setType(CodecType.Video);
		tempVar11.setCodec("vpx,mpeg4");
		tempVar11.setConditions(new ProfileCondition[]
				{
						new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Width, "1920"),
						//new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Height, "1080"),
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

		buildDynamicProfiles();
		buildSubtitleProfiles();
	}

	private void buildDynamicProfiles(){

		if (Build.VERSION.SDK_INT >= 21){
			new Api21Builder().buildProfiles(this);
		}
		else if (Build.VERSION.SDK_INT >= 16){
			new Api16Builder().buildProfiles(this);
		}
	}

	private void buildSubtitleProfiles() {

		if (Build.VERSION.SDK_INT >= 16) {
			SubtitleProfile srtSubs = new SubtitleProfile();
			srtSubs.setFormat("srt");
			srtSubs.setMethod(SubtitleDeliveryMethod.External);

			setSubtitleProfiles(new SubtitleProfile[] { srtSubs });
		}
	}
}