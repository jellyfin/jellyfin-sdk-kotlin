package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.MediaInfo.*;
import MediaBrowser.Model.Session.*;

public class StreamBuilder
{
	private final String[] _serverTextSubtitleOutputs = {"srt", "vtt", "ttml"};

	public final StreamInfo BuildAudioItem(AudioOptions options)
	{
		ValidateAudioInput(options);

		java.util.ArrayList<MediaSourceInfo> mediaSources = options.getMediaSources();

		// If the client wants a specific media source, filter now
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			java.util.ArrayList<MediaSourceInfo> newMediaSources = new java.util.ArrayList<MediaSourceInfo>();
			for (MediaSourceInfo i : mediaSources)
			{
				if (StringHelper.EqualsIgnoreCase(i.getId(), options.getMediaSourceId()))
				{
					newMediaSources.add(i);
				}
			}

			mediaSources = newMediaSources;
		}

		java.util.ArrayList<StreamInfo> streams = new java.util.ArrayList<StreamInfo>();
		for (MediaSourceInfo i : mediaSources)
		{
			streams.add(BuildAudioItem(i, options));
		}

		for (StreamInfo stream : streams)
		{
			stream.setDeviceId(options.getDeviceId());
			stream.setDeviceProfileId(options.getProfile().getId());
		}

		return GetOptimalStream(streams);
	}

	public final StreamInfo BuildVideoItem(VideoOptions options)
	{
		ValidateInput(options);

		java.util.ArrayList<MediaSourceInfo> mediaSources = options.getMediaSources();

		// If the client wants a specific media source, filter now
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			java.util.ArrayList<MediaSourceInfo> newMediaSources = new java.util.ArrayList<MediaSourceInfo>();
			for (MediaSourceInfo i : mediaSources)
			{
				if (StringHelper.EqualsIgnoreCase(i.getId(), options.getMediaSourceId()))
				{
					newMediaSources.add(i);
				}
			}

			mediaSources = newMediaSources;
		}

		java.util.ArrayList<StreamInfo> streams = new java.util.ArrayList<StreamInfo>();
		for (MediaSourceInfo i : mediaSources)
		{
			streams.add(BuildVideoItem(i, options));
		}

		for (StreamInfo stream : streams)
		{
			stream.setDeviceId(options.getDeviceId());
			stream.setDeviceProfileId(options.getProfile().getId());
		}

		return GetOptimalStream(streams);
	}

	private StreamInfo GetOptimalStream(java.util.ArrayList<StreamInfo> streams)
	{
		// Grab the first one that can be direct streamed
		// If that doesn't produce anything, just take the first
		for (StreamInfo i : streams)
		{
			if (i.getIsDirectStream())
			{
				return i;
			}
		}

		for (StreamInfo stream : streams)
		{
			return stream;
		}
		return null;
	}

	private StreamInfo BuildAudioItem(MediaSourceInfo item, AudioOptions options)
	{
		StreamInfo tempVar = new StreamInfo();
		tempVar.setItemId(options.getItemId());
		tempVar.setMediaType(DlnaProfileType.Audio);
		tempVar.setMediaSource(item);
		tempVar.setRunTimeTicks(item.getRunTimeTicks());
		StreamInfo playlistItem = tempVar;

		Integer maxBitrateSetting = options.GetMaxBitrate();

		MediaStream audioStream = item.getDefaultAudioStream();

		// Honor the max bitrate setting
		if (IsAudioEligibleForDirectPlay(item, maxBitrateSetting))
		{
			DirectPlayProfile directPlay = null;
			for (DirectPlayProfile i : options.getProfile().getDirectPlayProfiles())
			{
				if (i.getType() == playlistItem.getMediaType() && IsAudioDirectPlaySupported(i, item, audioStream))
				{
					directPlay = i;
					break;
				}
			}

			if (directPlay != null)
			{
				String audioCodec = audioStream == null ? null : audioStream.getCodec();

				// Make sure audio codec profiles are satisfied
				if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec))
				{
					ConditionProcessor conditionProcessor = new ConditionProcessor();

					java.util.ArrayList<ProfileCondition> conditions = new java.util.ArrayList<ProfileCondition>();
					for (CodecProfile i : options.getProfile().getCodecProfiles())
					{
						if (i.getType() == CodecType.Audio && i.ContainsCodec(audioCodec))
						{
							for (ProfileCondition c : i.getConditions())
							{
								conditions.add(c);
							}
						}
					}

					Integer audioChannels = audioStream.getChannels();
					Integer audioBitrate = audioStream.getBitRate();

					boolean all = true;
					for (ProfileCondition c : conditions)
					{
						if (!conditionProcessor.IsAudioConditionSatisfied(c, audioChannels, audioBitrate))
						{
							all = false;
							break;
						}
					}

					if (all)
					{
						playlistItem.setPlayMethod(PlayMethod.DirectStream);
						playlistItem.setContainer(item.getContainer());

						return playlistItem;
					}
				}
			}
		}

		TranscodingProfile transcodingProfile = null;
		for (TranscodingProfile i : options.getProfile().getTranscodingProfiles())
		{
			if (i.getType() == playlistItem.getMediaType() && i.getContext() == options.getContext())
			{
				transcodingProfile = i;
				break;
			}
		}

		if (transcodingProfile != null)
		{
			playlistItem.setPlayMethod(PlayMethod.Transcode);
			playlistItem.setTranscodeSeekInfo(transcodingProfile.getTranscodeSeekInfo());
			playlistItem.setEstimateContentLength(transcodingProfile.getEstimateContentLength());
			playlistItem.setContainer(transcodingProfile.getContainer());
			playlistItem.setAudioCodec(transcodingProfile.getAudioCodec());
			playlistItem.setProtocol(transcodingProfile.getProtocol());

			java.util.ArrayList<CodecProfile> audioCodecProfiles = new java.util.ArrayList<CodecProfile>();
			for (CodecProfile i : options.getProfile().getCodecProfiles())
			{
				if (i.getType() == CodecType.Audio && i.ContainsCodec(transcodingProfile.getAudioCodec()))
				{
					audioCodecProfiles.add(i);
				}

				if (audioCodecProfiles.size() >= 1)
				{
					break;
				}
			}

			java.util.ArrayList<ProfileCondition> audioTranscodingConditions = new java.util.ArrayList<ProfileCondition>();
			for (CodecProfile i : audioCodecProfiles)
			{
				for (ProfileCondition c : i.getConditions())
				{
					audioTranscodingConditions.add(c);
				}
			}

			ApplyTranscodingConditions(playlistItem, audioTranscodingConditions);

			// Honor requested max channels
			if (options.getMaxAudioChannels() != null)
			{
				Integer tempVar2 = playlistItem.getMaxAudioChannels();
				int currentValue = (tempVar2 != null) ? tempVar2 : options.getMaxAudioChannels();

				playlistItem.setMaxAudioChannels(Math.min(options.getMaxAudioChannels(), currentValue));
			}

			Integer tempVar3 = options.getAudioTranscodingBitrate();
			int configuredBitrate = (tempVar3 != null) ? tempVar3 : ((options.getContext() == EncodingContext.Static ? options.getProfile().getMusicSyncBitrate() : options.getProfile().getMusicStreamingTranscodingBitrate()) != null) ? (options.getContext() == EncodingContext.Static ? options.getProfile().getMusicSyncBitrate() : options.getProfile().getMusicStreamingTranscodingBitrate()) : 128000;

			Integer tempVar4 = playlistItem.getAudioBitrate();
			playlistItem.setAudioBitrate(Math.min(configuredBitrate, (tempVar4 != null) ? tempVar4 : configuredBitrate));
		}

		return playlistItem;
	}

	private StreamInfo BuildVideoItem(MediaSourceInfo item, VideoOptions options)
	{
		StreamInfo tempVar = new StreamInfo();
		tempVar.setItemId(options.getItemId());
		tempVar.setMediaType(DlnaProfileType.Video);
		tempVar.setMediaSource(item);
		tempVar.setRunTimeTicks(item.getRunTimeTicks());
		StreamInfo playlistItem = tempVar;

		Integer tempVar2 = options.getAudioStreamIndex();
		Integer audioStreamIndex = (tempVar2 != null) ? tempVar2 : item.getDefaultAudioStreamIndex();
		Integer tempVar3 = options.getSubtitleStreamIndex();
		playlistItem.setSubtitleStreamIndex((tempVar3 != null) ? tempVar3 : item.getDefaultSubtitleStreamIndex());

		MediaStream audioStream = audioStreamIndex != null ? item.GetMediaStream(MediaStreamType.Audio, audioStreamIndex) : null;
		MediaStream subtitleStream = playlistItem.getSubtitleStreamIndex() != null ? item.GetMediaStream(MediaStreamType.Subtitle, playlistItem.getSubtitleStreamIndex()) : null;

		MediaStream videoStream = item.getVideoStream();

		Integer maxBitrateSetting = options.GetMaxBitrate();

		if (IsEligibleForDirectPlay(item, maxBitrateSetting, subtitleStream, options))
		{
			// See if it can be direct played
			DirectPlayProfile directPlay = GetVideoDirectPlayProfile(options.getProfile(), item, videoStream, audioStream);

			if (directPlay != null)
			{
				playlistItem.setPlayMethod(PlayMethod.DirectStream);
				playlistItem.setContainer(item.getContainer());

				if (subtitleStream != null)
				{
					playlistItem.setSubtitleDeliveryMethod(GetSubtitleDeliveryMethod(subtitleStream, options));
				}

				return playlistItem;
			}
		}

		// Can't direct play, find the transcoding profile
		TranscodingProfile transcodingProfile = null;
		for (TranscodingProfile i : options.getProfile().getTranscodingProfiles())
		{
			if (i.getType() == playlistItem.getMediaType() && i.getContext() == options.getContext())
			{
				transcodingProfile = i;
				break;
			}
		}

		if (transcodingProfile != null)
		{
			if (subtitleStream != null)
			{
				playlistItem.setSubtitleDeliveryMethod(GetSubtitleDeliveryMethod(subtitleStream, options));
			}

			playlistItem.setPlayMethod(PlayMethod.Transcode);
			playlistItem.setContainer(transcodingProfile.getContainer());
			playlistItem.setEstimateContentLength(transcodingProfile.getEstimateContentLength());
			playlistItem.setTranscodeSeekInfo(transcodingProfile.getTranscodeSeekInfo());
			playlistItem.setAudioCodec(transcodingProfile.getAudioCodec().split("[,]", -1)[0]);
			playlistItem.setVideoCodec(transcodingProfile.getVideoCodec());
			playlistItem.setProtocol(transcodingProfile.getProtocol());
			playlistItem.setAudioStreamIndex(audioStreamIndex);
			playlistItem.setVideoProfile(transcodingProfile.getVideoProfile());

			java.util.ArrayList<ProfileCondition> videoTranscodingConditions = new java.util.ArrayList<ProfileCondition>();
			for (CodecProfile i : options.getProfile().getCodecProfiles())
			{
				if (i.getType() == CodecType.Video && i.ContainsCodec(transcodingProfile.getVideoCodec()))
				{
					for (ProfileCondition c : i.getConditions())
					{
						videoTranscodingConditions.add(c);
					}
					break;
				}
			}
			ApplyTranscodingConditions(playlistItem, videoTranscodingConditions);

			java.util.ArrayList<ProfileCondition> audioTranscodingConditions = new java.util.ArrayList<ProfileCondition>();
			for (CodecProfile i : options.getProfile().getCodecProfiles())
			{
				if (i.getType() == CodecType.VideoAudio && i.ContainsCodec(transcodingProfile.getAudioCodec()))
				{
					for (ProfileCondition c : i.getConditions())
					{
						audioTranscodingConditions.add(c);
					}
					break;
				}
			}
			ApplyTranscodingConditions(playlistItem, audioTranscodingConditions);

			// Honor requested max channels
			if (options.getMaxAudioChannels() != null)
			{
				Integer tempVar4 = playlistItem.getMaxAudioChannels();
				int currentValue = (tempVar4 != null) ? tempVar4 : options.getMaxAudioChannels();

				playlistItem.setMaxAudioChannels(Math.min(options.getMaxAudioChannels(), currentValue));
			}

			if (playlistItem.getAudioBitrate() == null)
			{
				playlistItem.setAudioBitrate(GetAudioBitrate(playlistItem.getTargetAudioChannels(), playlistItem.getTargetAudioCodec()));
			}

			// Honor max rate
			if (maxBitrateSetting != null)
			{
				int videoBitrate = maxBitrateSetting;

				if (playlistItem.getAudioBitrate() != null)
				{
					videoBitrate -= playlistItem.getAudioBitrate();
				}

				Integer tempVar5 = playlistItem.getVideoBitrate();
				int currentValue = (tempVar5 != null) ? tempVar5 : videoBitrate;

				playlistItem.setVideoBitrate(Math.min(videoBitrate, currentValue));
			}
		}

		return playlistItem;
	}

	private int GetAudioBitrate(Integer channels, String codec)
	{
		if (channels != null)
		{
			if (channels >= 5)
			{
				return 320000;
			}
		}

		return 128000;
	}

	private DirectPlayProfile GetVideoDirectPlayProfile(DeviceProfile profile, MediaSourceInfo mediaSource, MediaStream videoStream, MediaStream audioStream)
	{
		// See if it can be direct played
		DirectPlayProfile directPlay = null;
		for (DirectPlayProfile i : profile.getDirectPlayProfiles())
		{
			if (i.getType() == DlnaProfileType.Video && IsVideoDirectPlaySupported(i, mediaSource, videoStream, audioStream))
			{
				directPlay = i;
				break;
			}
		}

		if (directPlay == null)
		{
			return null;
		}

		String container = mediaSource.getContainer();

		java.util.ArrayList<ProfileCondition> conditions = new java.util.ArrayList<ProfileCondition>();
		for (ContainerProfile i : profile.getContainerProfiles())
		{
			if (i.getType() == DlnaProfileType.Video && ListHelper.ContainsIgnoreCase(i.GetContainers(), container))
			{
				for (ProfileCondition c : i.getConditions())
				{
					conditions.add(c);
				}
			}
		}

		ConditionProcessor conditionProcessor = new ConditionProcessor();

		Integer width = videoStream == null ? null : videoStream.getWidth();
		Integer height = videoStream == null ? null : videoStream.getHeight();
		Integer bitDepth = videoStream == null ? null : videoStream.getBitDepth();
		Integer videoBitrate = videoStream == null ? null : videoStream.getBitRate();
		Double videoLevel = videoStream == null ? null : videoStream.getLevel();
		String videoProfile = videoStream == null ? null : videoStream.getProfile();
		Float tempVar = videoStream.getAverageFrameRate();
		Float videoFramerate = videoStream == null ? null : (tempVar != null) ? tempVar : videoStream.getAverageFrameRate();
		Boolean isAnamorphic = videoStream == null ? null : videoStream.getIsAnamorphic();

		Integer audioBitrate = audioStream == null ? null : audioStream.getBitRate();
		Integer audioChannels = audioStream == null ? null : audioStream.getChannels();
		String audioProfile = audioStream == null ? null : audioStream.getProfile();

		TransportStreamTimestamp timestamp = videoStream == null ? TransportStreamTimestamp.None : mediaSource.getTimestamp();
		Integer packetLength = videoStream == null ? null : videoStream.getPacketLength();
		Integer refFrames = videoStream == null ? null : videoStream.getRefFrames();

		// Check container conditions
		for (ProfileCondition i : conditions)
		{
			if (!conditionProcessor.IsVideoConditionSatisfied(i, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp, isAnamorphic, refFrames))
			{
				return null;
			}
		}

		String videoCodec = videoStream == null ? null : videoStream.getCodec();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(videoCodec))
		{
			return null;
		}

		conditions = new java.util.ArrayList<ProfileCondition>();
		for (CodecProfile i : profile.getCodecProfiles())
		{
			if (i.getType() == CodecType.Video && i.ContainsCodec(videoCodec))
			{
				for (ProfileCondition c : i.getConditions())
				{
					conditions.add(c);
				}
			}
		}

		for (ProfileCondition i : conditions)
		{
			if (!conditionProcessor.IsVideoConditionSatisfied(i, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp, isAnamorphic, refFrames))
			{
				return null;
			}
		}

		if (audioStream != null)
		{
			String audioCodec = audioStream.getCodec();

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec))
			{
				return null;
			}

			conditions = new java.util.ArrayList<ProfileCondition>();
			for (CodecProfile i : profile.getCodecProfiles())
			{
				if (i.getType() == CodecType.VideoAudio && i.ContainsCodec(audioCodec))
				{
					for (ProfileCondition c : i.getConditions())
					{
						conditions.add(c);
					}
				}
			}

			for (ProfileCondition i : conditions)
			{
				if (!conditionProcessor.IsVideoAudioConditionSatisfied(i, audioChannels, audioBitrate, audioProfile))
				{
					return null;
				}
			}
		}

		return directPlay;
	}

	private boolean IsEligibleForDirectPlay(MediaSourceInfo item, Integer maxBitrate, MediaStream subtitleStream, VideoOptions options)
	{
		if (subtitleStream != null)
		{
			if (!subtitleStream.getIsTextSubtitleStream())
			{
				return false;
			}

			SubtitleDeliveryMethod subtitleMethod = GetSubtitleDeliveryMethod(subtitleStream, options);

			if (subtitleMethod != SubtitleDeliveryMethod.External && subtitleMethod != SubtitleDeliveryMethod.Embed)
			{
				return false;
			}
		}

		return IsAudioEligibleForDirectPlay(item, maxBitrate);
	}

	private SubtitleDeliveryMethod GetSubtitleDeliveryMethod(MediaStream subtitleStream, VideoOptions options)
	{
		if (subtitleStream.getIsTextSubtitleStream())
		{
			// See if the device can retrieve the subtitles externally
			boolean supportsSubsExternally = options.getContext() == EncodingContext.Streaming && ContainsSubtitleFormat(options.getProfile().getSubtitleProfiles(), SubtitleDeliveryMethod.External, _serverTextSubtitleOutputs);

			if (supportsSubsExternally)
			{
				return SubtitleDeliveryMethod.External;
			}

			// See if the device can retrieve the subtitles externally
			boolean supportsEmbedded = ContainsSubtitleFormat(options.getProfile().getSubtitleProfiles(), SubtitleDeliveryMethod.Embed, _serverTextSubtitleOutputs);

			if (supportsEmbedded)
			{
				return SubtitleDeliveryMethod.Embed;
			}
		}

		return SubtitleDeliveryMethod.Encode;
	}

	private boolean ContainsSubtitleFormat(SubtitleProfile[] profiles, SubtitleDeliveryMethod method, String[] formats)
	{
		for (SubtitleProfile profile : profiles)
		{
			if (method.getValue() == profile.getMethod().getValue() && ListHelper.ContainsIgnoreCase(formats, profile.getFormat()))
			{
				return true;
			}
		}

		return false;
	}

	private boolean IsAudioEligibleForDirectPlay(MediaSourceInfo item, Integer maxBitrate)
	{
		// Honor the max bitrate setting
		return maxBitrate == null || (item.getBitrate() != null && item.getBitrate() <= maxBitrate);
	}

	private void ValidateInput(VideoOptions options)
	{
		ValidateAudioInput(options);

		if (options.getAudioStreamIndex() != null && tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			throw new IllegalArgumentException("MediaSourceId is required when a specific audio stream is requested");
		}

		if (options.getSubtitleStreamIndex() != null && tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			throw new IllegalArgumentException("MediaSourceId is required when a specific subtitle stream is requested");
		}
	}

	private void ValidateAudioInput(AudioOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getItemId()))
		{
			throw new IllegalArgumentException("ItemId is required");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getDeviceId()))
		{
			throw new IllegalArgumentException("DeviceId is required");
		}
		if (options.getProfile() == null)
		{
			throw new IllegalArgumentException("Profile is required");
		}
		if (options.getMediaSources() == null)
		{
			throw new IllegalArgumentException("MediaSources is required");
		}
	}

	private void ApplyTranscodingConditions(StreamInfo item, Iterable<ProfileCondition> conditions)
	{
		for (ProfileCondition condition : conditions)
		{
			String value = condition.getValue();

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(value))
			{
				continue;
			}

			switch (condition.getProperty())
			{
				case AudioBitrate:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num = new tangible.RefObject<Integer>(num);
						boolean tempVar = IntHelper.TryParseCultureInvariant(value, tempRef_num);
							num = tempRef_num.argValue;
						if (tempVar)
						{
							item.setAudioBitrate(num);
						}
						break;
				}
				case AudioChannels:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num2 = new tangible.RefObject<Integer>(num);
						boolean tempVar2 = IntHelper.TryParseCultureInvariant(value, tempRef_num2);
							num = tempRef_num2.argValue;
						if (tempVar2)
						{
							item.setMaxAudioChannels(num);
						}
						break;
				}
				case AudioProfile:
				case IsAnamorphic:
				case Has64BitOffsets:
				case PacketLength:
				case VideoTimestamp:
				{
						// Not supported yet
						break;
				}
				case RefFrames:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num3 = new tangible.RefObject<Integer>(num);
						boolean tempVar3 = IntHelper.TryParseCultureInvariant(value, tempRef_num3);
							num = tempRef_num3.argValue;
						if (tempVar3)
						{
							item.setMaxRefFrames(num);
						}
						break;
				}
				case VideoBitDepth:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num4 = new tangible.RefObject<Integer>(num);
						boolean tempVar4 = IntHelper.TryParseCultureInvariant(value, tempRef_num4);
							num = tempRef_num4.argValue;
						if (tempVar4)
						{
							item.setMaxVideoBitDepth(num);
						}
						break;
				}
				case VideoProfile:
				{
						item.setVideoProfile(value);
						break;
				}
				case Height:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num5 = new tangible.RefObject<Integer>(num);
						boolean tempVar5 = IntHelper.TryParseCultureInvariant(value, tempRef_num5);
							num = tempRef_num5.argValue;
						if (tempVar5)
						{
							item.setMaxHeight(num);
						}
						break;
				}
				case VideoBitrate:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num6 = new tangible.RefObject<Integer>(num);
						boolean tempVar6 = IntHelper.TryParseCultureInvariant(value, tempRef_num6);
							num = tempRef_num6.argValue;
						if (tempVar6)
						{
							item.setVideoBitrate(num);
						}
						break;
				}
				case VideoFramerate:
				{
						float num = 0F;
						tangible.RefObject<Float> tempRef_num7 = new tangible.RefObject<Float>(num);
						boolean tempVar7 = FloatHelper.TryParseCultureInvariant(value, tempRef_num7);
							num = tempRef_num7.argValue;
						if (tempVar7)
						{
							item.setMaxFramerate(num);
						}
						break;
				}
				case VideoLevel:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num8 = new tangible.RefObject<Integer>(num);
						boolean tempVar8 = IntHelper.TryParseCultureInvariant(value, tempRef_num8);
							num = tempRef_num8.argValue;
						if (tempVar8)
						{
							item.setVideoLevel(num);
						}
						break;
				}
				case Width:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num9 = new tangible.RefObject<Integer>(num);
						boolean tempVar9 = IntHelper.TryParseCultureInvariant(value, tempRef_num9);
							num = tempRef_num9.argValue;
						if (tempVar9)
						{
							item.setMaxWidth(num);
						}
						break;
				}
				default:
					throw new IllegalArgumentException("Unrecognized ProfileConditionValue");
			}
		}
	}

	private boolean IsAudioDirectPlaySupported(DirectPlayProfile profile, MediaSourceInfo item, MediaStream audioStream)
	{
		if (profile.getContainer().length() > 0)
		{
			// Check container type
			String tempVar = item.getContainer();
			String mediaContainer = (tempVar != null) ? tempVar : "";
			boolean any = false;
			for (String i : profile.GetContainers())
			{
				if (StringHelper.EqualsIgnoreCase(i, mediaContainer))
				{
					any = true;
					break;
				}
			}
			if (!any)
			{
				return false;
			}
		}

		return true;
	}

	private boolean IsVideoDirectPlaySupported(DirectPlayProfile profile, MediaSourceInfo item, MediaStream videoStream, MediaStream audioStream)
	{
		// Only plain video files can be direct played
		if (item.getVideoType() != VideoType.VideoFile)
		{
			return false;
		}

		if (profile.getContainer().length() > 0)
		{
			// Check container type
			String tempVar = item.getContainer();
			String mediaContainer = (tempVar != null) ? tempVar : "";
			boolean any = false;
			for (String i : profile.GetContainers())
			{
				if (StringHelper.EqualsIgnoreCase(i, mediaContainer))
				{
					any = true;
					break;
				}
			}
			if (!any)
			{
				return false;
			}
		}

		// Check video codec
		java.util.ArrayList<String> videoCodecs = profile.GetVideoCodecs();
		if (videoCodecs.size() > 0)
		{
			String videoCodec = videoStream == null ? null : videoStream.getCodec();
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(videoCodec) || !ListHelper.ContainsIgnoreCase(videoCodecs, videoCodec))
			{
				return false;
			}
		}

		java.util.ArrayList<String> audioCodecs = profile.GetAudioCodecs();
		if (audioCodecs.size() > 0)
		{
			// Check audio codecs
			String audioCodec = audioStream == null ? null : audioStream.getCodec();
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || !ListHelper.ContainsIgnoreCase(audioCodecs, audioCodec))
			{
				return false;
			}
		}

		return true;
	}
}