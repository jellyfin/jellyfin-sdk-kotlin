package MediaBrowser.Model.Dlna.Profiles;

import MediaBrowser.Model.Dlna.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlRoot("Profile")] public class AndroidProfile : DefaultProfile
public class AndroidProfile extends DefaultProfile
{
	public AndroidProfile()
	{
		setName("Android");

		TranscodingProfile tempVar = new TranscodingProfile();
		tempVar.setContainer("mp3");
		tempVar.setAudioCodec("mp3");
		tempVar.setType(DlnaProfileType.Audio);
		TranscodingProfile tempVar2 = new TranscodingProfile();
		tempVar2.setProtocol("hls");
		tempVar2.setContainer("ts");
		tempVar2.setVideoCodec("h264");
		tempVar2.setAudioCodec("aac");
		tempVar2.setType(DlnaProfileType.Video);
		tempVar2.setVideoProfile("Baseline");
		tempVar2.setContext(EncodingContext.Streaming);
		TranscodingProfile tempVar3 = new TranscodingProfile();
		tempVar3.setContainer("mp4");
		tempVar3.setVideoCodec("h264");
		tempVar3.setAudioCodec("aac");
		tempVar3.setType(DlnaProfileType.Video);
		tempVar3.setVideoProfile("Baseline");
		tempVar3.setContext(EncodingContext.Static);
		setTranscodingProfiles(new TranscodingProfile[] {tempVar, tempVar2, tempVar3});

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
		tempVar10.setConditions(new ProfileCondition[]
		{
			new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Width, "1920"),
			new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.Height, "1080"),
			new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.VideoBitDepth, "8"),
			new ProfileCondition(ProfileConditionType.NotEquals, ProfileConditionValue.IsAnamorphic, "true")
		});
		CodecProfile tempVar11 = new CodecProfile();
		tempVar11.setType(CodecType.VideoAudio);
		tempVar11.setCodec("aac");
		tempVar11.setConditions(new ProfileCondition[] {new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2")});
		CodecProfile tempVar12 = new CodecProfile();
		tempVar12.setType(CodecType.Audio);
		tempVar12.setCodec("aac");
		tempVar12.setConditions(new ProfileCondition[] {new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2")});
		CodecProfile tempVar13 = new CodecProfile();
		tempVar13.setType(CodecType.Audio);
		tempVar13.setCodec("mp3");
		tempVar13.setConditions(new ProfileCondition[]
		{
			new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioChannels, "2"),
			new ProfileCondition(ProfileConditionType.LessThanEqual, ProfileConditionValue.AudioBitrate, "320000")
		});
		setCodecProfiles(new CodecProfile[] {tempVar10, tempVar11, tempVar12, tempVar13});

	}
}