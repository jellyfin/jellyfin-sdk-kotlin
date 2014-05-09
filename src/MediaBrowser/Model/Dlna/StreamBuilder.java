package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.MediaInfo.*;

public class StreamBuilder
{
	private final CultureInfo _usCulture = new CultureInfo("en-US");

	public final StreamInfo BuildAudioItem(AudioOptions options)
	{
		ValidateAudioInput(options);

		java.util.ArrayList<MediaSourceInfo> mediaSources = options.getMediaSources();

		// If the client wants a specific media soure, filter now
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			// Avoid implicitly captured closure
			String mediaSourceId = options.getMediaSourceId();

			mediaSources = new java.util.ArrayList<MediaSourceInfo>();
			for (MediaSourceInfo i : mediaSources)
			{
				if (String.equals(i.getId(), mediaSourceId, StringComparison.OrdinalIgnoreCase))
				{
					mediaSources.add(i);
				}
			}
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

		// If the client wants a specific media soure, filter now
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			// Avoid implicitly captured closure
			String mediaSourceId = options.getMediaSourceId();

			mediaSources = new java.util.ArrayList<MediaSourceInfo>();
			for (MediaSourceInfo i : mediaSources)
			{
				if (String.equals(i.getId(), mediaSourceId, StringComparison.OrdinalIgnoreCase))
				{
					mediaSources.add(i);
				}
			}
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

		Integer tempVar2 = options.getMaxBitrate();
		Integer maxBitrateSetting = (tempVar2 != null) ? tempVar2 : options.getProfile().getMaxBitrate();

		MediaStream audioStream = item.getMediaStreams().FirstOrDefault(i => i.Type == MediaStreamType.Audio);

		// Honor the max bitrate setting
		if (IsAudioEligibleForDirectPlay(item, maxBitrateSetting))
		{
			DirectPlayProfile directPlay = options.getProfile().getDirectPlayProfiles().FirstOrDefault(i => i.Type == playlistItem.getMediaType() && IsAudioDirectPlaySupported(i, item, audioStream));

			if (directPlay != null)
			{
				String audioCodec = audioStream == null ? null : audioStream.getCodec();

				// Make sure audio codec profiles are satisfied
				if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec))
				{
					ConditionProcessor conditionProcessor = new ConditionProcessor();

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
					Iterable<ProfileCondition> conditions = options.getProfile().getCodecProfiles().Where(i => i.Type == CodecType.Audio && i.ContainsCodec(audioCodec)).SelectMany(i => i.Conditions);

					Integer audioChannels = audioStream.getChannels();
					Integer audioBitrate = audioStream.getBitRate();

					if (conditions.All(c => conditionProcessor.IsAudioConditionSatisfied(c, audioChannels, audioBitrate)))
					{
						playlistItem.setIsDirectStream(true);
						playlistItem.setContainer(item.getContainer());

						return playlistItem;
					}
				}
			}
		}

		TranscodingProfile transcodingProfile = options.getProfile().getTranscodingProfiles().FirstOrDefault(i => i.Type == playlistItem.getMediaType());

		if (transcodingProfile != null)
		{
			playlistItem.setIsDirectStream(false);
			playlistItem.setTranscodeSeekInfo(transcodingProfile.getTranscodeSeekInfo());
			playlistItem.setEstimateContentLength(transcodingProfile.getEstimateContentLength());
			playlistItem.setContainer(transcodingProfile.getContainer());
			playlistItem.setAudioCodec(transcodingProfile.getAudioCodec());
			playlistItem.setProtocol(transcodingProfile.getProtocol());

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
			Iterable<ProfileCondition> audioTranscodingConditions = options.getProfile().getCodecProfiles().Where(i => i.Type == CodecType.Audio && i.ContainsCodec(transcodingProfile.getAudioCodec())).Take(1).SelectMany(i => i.Conditions);

			ApplyTranscodingConditions(playlistItem, audioTranscodingConditions);

			// Honor requested max channels
			if (options.getMaxAudioChannels() != null)
			{
				Integer tempVar3 = playlistItem.getMaxAudioChannels();
				int currentValue = (tempVar3 != null) ? tempVar3 : options.getMaxAudioChannels();

				playlistItem.setMaxAudioChannels(Math.min(options.getMaxAudioChannels(), currentValue));
			}

			// Honor requested max bitrate
			if (maxBitrateSetting != null)
			{
				Integer tempVar4 = playlistItem.getAudioBitrate();
				int currentValue = (tempVar4 != null) ? tempVar4 : maxBitrateSetting;

				playlistItem.setAudioBitrate(Math.min(maxBitrateSetting, currentValue));
			}
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

		MediaStream audioStream = item.getMediaStreams().FirstOrDefault(i => i.Type == MediaStreamType.Audio);
		MediaStream videoStream = item.getMediaStreams().FirstOrDefault(i => i.Type == MediaStreamType.Video);

		Integer tempVar2 = options.getMaxBitrate();
		Integer maxBitrateSetting = (tempVar2 != null) ? tempVar2 : options.getProfile().getMaxBitrate();

		if (IsEligibleForDirectPlay(item, options, maxBitrateSetting))
		{
			// See if it can be direct played
			DirectPlayProfile directPlay = GetVideoDirectPlayProfile(options.getProfile(), item, videoStream, audioStream);

			if (directPlay != null)
			{
				playlistItem.setIsDirectStream(true);
				playlistItem.setContainer(item.getContainer());

				return playlistItem;
			}
		}

		// Can't direct play, find the transcoding profile
		TranscodingProfile transcodingProfile = null;
		for (TranscodingProfile i : options.getProfile().getTranscodingProfiles())
		{
			if (i.getType() == playlistItem.getMediaType())
			{
				transcodingProfile = i;
				break;
			}
		}

		if (transcodingProfile != null)
		{
			playlistItem.setIsDirectStream(false);
			playlistItem.setContainer(transcodingProfile.getContainer());
			playlistItem.setEstimateContentLength(transcodingProfile.getEstimateContentLength());
			playlistItem.setTranscodeSeekInfo(transcodingProfile.getTranscodeSeekInfo());
			playlistItem.setAudioCodec(transcodingProfile.getAudioCodec().split("[,]", -1)[0]);
			playlistItem.setVideoCodec(transcodingProfile.getVideoCodec());
			playlistItem.setProtocol(transcodingProfile.getProtocol());
			playlistItem.setAudioStreamIndex(options.getAudioStreamIndex());
			playlistItem.setSubtitleStreamIndex(options.getSubtitleStreamIndex());

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
			Iterable<ProfileCondition> videoTranscodingConditions = options.getProfile().getCodecProfiles().Where(i => i.Type == CodecType.Video && i.ContainsCodec(transcodingProfile.getVideoCodec())).Take(1).SelectMany(i => i.Conditions);

			ApplyTranscodingConditions(playlistItem, videoTranscodingConditions);

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
			Iterable<ProfileCondition> audioTranscodingConditions = options.getProfile().getCodecProfiles().Where(i => i.Type == CodecType.VideoAudio && i.ContainsCodec(transcodingProfile.getAudioCodec())).Take(1).SelectMany(i => i.Conditions);

			ApplyTranscodingConditions(playlistItem, audioTranscodingConditions);

			// Honor requested max channels
			if (options.getMaxAudioChannels() != null)
			{
				Integer tempVar3 = playlistItem.getMaxAudioChannels();
				int currentValue = (tempVar3 != null) ? tempVar3 : options.getMaxAudioChannels();

				playlistItem.setMaxAudioChannels(Math.min(options.getMaxAudioChannels(), currentValue));
			}

			// Honor requested max bitrate
			if (options.getMaxAudioTranscodingBitrate() != null)
			{
				Integer tempVar4 = playlistItem.getAudioBitrate();
				int currentValue = (tempVar4 != null) ? tempVar4 : options.getMaxAudioTranscodingBitrate();

				playlistItem.setAudioBitrate(Math.min(options.getMaxAudioTranscodingBitrate(), currentValue));
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

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
		Iterable<ProfileCondition> conditions = profile.getContainerProfiles().Where(i => i.Type == DlnaProfileType.Video && i.GetContainers().Contains(container, StringComparer.OrdinalIgnoreCase)).SelectMany(i => i.Conditions);

		ConditionProcessor conditionProcessor = new ConditionProcessor();

		Integer width = videoStream == null ? null : videoStream.getWidth();
		Integer height = videoStream == null ? null : videoStream.getHeight();
		Integer bitDepth = videoStream == null ? null : videoStream.getBitDepth();
		Integer videoBitrate = videoStream == null ? null : videoStream.getBitRate();
		Double videoLevel = videoStream == null ? null : videoStream.getLevel();
		String videoProfile = videoStream == null ? null : videoStream.getProfile();
		Float tempVar = videoStream.getAverageFrameRate();
		Float videoFramerate = videoStream == null ? null : (tempVar != null) ? tempVar : videoStream.getAverageFrameRate();

		Integer audioBitrate = audioStream == null ? null : audioStream.getBitRate();
		Integer audioChannels = audioStream == null ? null : audioStream.getChannels();
		String audioProfile = audioStream == null ? null : audioStream.getProfile();

		TransportStreamTimestamp timestamp = videoStream == null ? TransportStreamTimestamp.None : mediaSource.getTimestamp();
		Integer packetLength = videoStream == null ? null : videoStream.getPacketLength();

		// Check container conditions
		if (!conditions.All(i => conditionProcessor.IsVideoConditionSatisfied(i, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp)))
		{
			return null;
		}

		String videoCodec = videoStream == null ? null : videoStream.getCodec();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(videoCodec))
		{
			return null;
		}

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
		conditions = profile.getCodecProfiles().Where(i => i.Type == CodecType.Video && i.ContainsCodec(videoCodec)).SelectMany(i => i.Conditions);

		if (!conditions.All(i => conditionProcessor.IsVideoConditionSatisfied(i, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp)))
		{
			return null;
		}

		if (audioStream != null)
		{
			String audioCodec = audioStream.getCodec();

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec))
			{
				return null;
			}

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
			conditions = profile.getCodecProfiles().Where(i => i.Type == CodecType.VideoAudio && i.ContainsCodec(audioCodec)).SelectMany(i => i.Conditions);

			if (!conditions.All(i => conditionProcessor.IsVideoAudioConditionSatisfied(i, audioChannels, audioBitrate, audioProfile)))
			{
				return null;
			}
		}

		return directPlay;
	}

	private boolean IsEligibleForDirectPlay(MediaSourceInfo item, VideoOptions options, Integer maxBitrate)
	{
		if (options.getSubtitleStreamIndex() != null)
		{
			return false;
		}

		if (options.getAudioStreamIndex() != null && item.getMediaStreams().size()(i => i.Type == MediaStreamType.Audio) > 1)
		{
			return false;
		}

		return IsAudioEligibleForDirectPlay(item, maxBitrate);
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
//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
		for (ProfileCondition condition : conditions.Where(i => !tangible.DotNetToJavaStringHelper.isNullOrEmpty(i.Value)))
		{
			String value = condition.getValue();

			switch (condition.getProperty())
			{
				case AudioBitrate:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num = new tangible.RefObject<Integer>(num);
						boolean tempVar = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num);
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
						boolean tempVar2 = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num2);
							num = tempRef_num2.argValue;
						if (tempVar2)
						{
							item.setMaxAudioChannels(num);
						}
						break;
				}
				case AudioProfile:
				case Has64BitOffsets:
				case PacketLength:
				case VideoTimestamp:
				case VideoBitDepth:
				{
						// Not supported yet
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
						tangible.RefObject<Integer> tempRef_num3 = new tangible.RefObject<Integer>(num);
						boolean tempVar3 = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num3);
							num = tempRef_num3.argValue;
						if (tempVar3)
						{
							item.setMaxHeight(num);
						}
						break;
				}
				case VideoBitrate:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num4 = new tangible.RefObject<Integer>(num);
						boolean tempVar4 = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num4);
							num = tempRef_num4.argValue;
						if (tempVar4)
						{
							item.setVideoBitrate(num);
						}
						break;
				}
				case VideoFramerate:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num5 = new tangible.RefObject<Integer>(num);
						boolean tempVar5 = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num5);
							num = tempRef_num5.argValue;
						if (tempVar5)
						{
							item.setMaxFramerate(num);
						}
						break;
				}
				case VideoLevel:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num6 = new tangible.RefObject<Integer>(num);
						boolean tempVar6 = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num6);
							num = tempRef_num6.argValue;
						if (tempVar6)
						{
							item.setVideoLevel(num);
						}
						break;
				}
				case Width:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num7 = new tangible.RefObject<Integer>(num);
						boolean tempVar7 = Integer.TryParse(value, NumberStyles.Any, _usCulture, tempRef_num7);
							num = tempRef_num7.argValue;
						if (tempVar7)
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
			if (!profile.GetContainers().Any(i => String.equals(i, mediaContainer, StringComparison.OrdinalIgnoreCase)))
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
			if (!profile.GetContainers().Any(i => String.equals(i, mediaContainer, StringComparison.OrdinalIgnoreCase)))
			{
				return false;
			}
		}

		// Check video codec
		java.util.ArrayList<String> videoCodecs = profile.GetVideoCodecs();
		if (videoCodecs.size() > 0)
		{
			String videoCodec = videoStream == null ? null : videoStream.getCodec();
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(videoCodec) || !videoCodecs.contains(videoCodec, StringComparer.OrdinalIgnoreCase))
			{
				return false;
			}
		}

		java.util.ArrayList<String> audioCodecs = profile.GetAudioCodecs();
		if (audioCodecs.size() > 0)
		{
			// Check audio codecs
			String audioCodec = audioStream == null ? null : audioStream.getCodec();
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || !audioCodecs.contains(audioCodec, StringComparer.OrdinalIgnoreCase))
			{
				return false;
			}
		}

		return true;
	}
}