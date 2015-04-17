package mediabrowser.model.dlna;

import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;
import mediabrowser.model.extensions.*;
import mediabrowser.model.logging.*;
import mediabrowser.model.mediainfo.*;
import mediabrowser.model.session.*;

public class StreamBuilder
{
	private ILocalPlayer _localPlayer;
	private ILogger _logger;

	public StreamBuilder(ILocalPlayer localPlayer, ILogger logger)
	{
		_localPlayer = localPlayer;
		_logger = logger;
	}

	public StreamBuilder(ILogger logger)
	{
		this(new NullLocalPlayer(), logger);
	}

	public final StreamInfo BuildAudioItem(AudioOptions options)
	{
		ValidateAudioInput(options);

		java.util.ArrayList<MediaSourceInfo> mediaSources = new java.util.ArrayList<MediaSourceInfo>();
		for (MediaSourceInfo i : options.getMediaSources())
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()) || StringHelper.EqualsIgnoreCase(i.getId(), options.getMediaSourceId()))
			{
				mediaSources.add(i);
			}
		}

		java.util.ArrayList<StreamInfo> streams = new java.util.ArrayList<StreamInfo>();
		for (MediaSourceInfo i : mediaSources)
		{
			StreamInfo streamInfo = BuildAudioItem(i, options);
			if (streamInfo != null)
			{
				streams.add(streamInfo);
			}
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

		java.util.ArrayList<MediaSourceInfo> mediaSources = new java.util.ArrayList<MediaSourceInfo>();
		for (MediaSourceInfo i : options.getMediaSources())
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()) || StringHelper.EqualsIgnoreCase(i.getId(), options.getMediaSourceId()))
			{
				mediaSources.add(i);
			}
		}

		java.util.ArrayList<StreamInfo> streams = new java.util.ArrayList<StreamInfo>();
		for (MediaSourceInfo i : mediaSources)
		{
			StreamInfo streamInfo = BuildVideoItem(i, options);
			if (streamInfo != null)
			{
				streams.add(streamInfo);
			}
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
		streams = StreamInfoSorter.SortMediaSources(streams);

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
		tempVar.setContext(options.getContext());
		tempVar.setDeviceProfile(options.getProfile());
		StreamInfo playlistItem = tempVar;

		MediaStream audioStream = item.GetDefaultAudioStream(null);

		java.util.ArrayList<PlayMethod> directPlayMethods = GetAudioDirectPlayMethods(item, audioStream, options);

		if (directPlayMethods.size() > 0)
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
					if (item.getProtocol() == MediaProtocol.File && directPlayMethods.contains(PlayMethod.DirectPlay) && _localPlayer.CanAccessFile(item.getPath()))
					{
						playlistItem.setPlayMethod(PlayMethod.DirectPlay);
					}
					else if (item.getProtocol() == MediaProtocol.Http && directPlayMethods.contains(PlayMethod.DirectPlay) && _localPlayer.CanAccessUrl(item.getPath(), item.getRequiredHttpHeaders().size() > 0))
					{
						playlistItem.setPlayMethod(PlayMethod.DirectPlay);
					}
					else if (directPlayMethods.contains(PlayMethod.DirectStream))
					{
						playlistItem.setPlayMethod(PlayMethod.DirectStream);
					}

					playlistItem.setContainer(item.getContainer());

					return playlistItem;
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
			if (!item.getSupportsTranscoding())
			{
				return null;
			}

			playlistItem.setPlayMethod(PlayMethod.Transcode);
			playlistItem.setTranscodeSeekInfo(transcodingProfile.getTranscodeSeekInfo());
			playlistItem.setEstimateContentLength(transcodingProfile.getEstimateContentLength());
			playlistItem.setContainer(transcodingProfile.getContainer());
			playlistItem.setAudioCodec(transcodingProfile.getAudioCodec());
			playlistItem.setSubProtocol(transcodingProfile.getProtocol());

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

	private Integer GetBitrateForDirectPlayCheck(MediaSourceInfo item, AudioOptions options)
	{
		if (item.getProtocol() == MediaProtocol.File)
		{
			return options.getProfile().getMaxStaticBitrate();
		}

		return options.GetMaxBitrate();
	}

	private java.util.ArrayList<PlayMethod> GetAudioDirectPlayMethods(MediaSourceInfo item, MediaStream audioStream, AudioOptions options)
	{
		DirectPlayProfile directPlayProfile = null;
		for (DirectPlayProfile i : options.getProfile().getDirectPlayProfiles())
		{
			if (i.getType() == DlnaProfileType.Audio && IsAudioDirectPlaySupported(i, item, audioStream))
			{
				directPlayProfile = i;
				break;
			}
		}

		java.util.ArrayList<PlayMethod> playMethods = new java.util.ArrayList<PlayMethod>();

		if (directPlayProfile != null)
		{
			// While options takes the network and other factors into account. Only applies to direct stream
			if (item.getSupportsDirectStream() && IsAudioEligibleForDirectPlay(item, options.GetMaxBitrate()))
			{
				playMethods.add(PlayMethod.DirectStream);
			}

			// The profile describes what the device supports
			// If device requirements are satisfied then allow both direct stream and direct play
			if (item.getSupportsDirectPlay() && IsAudioEligibleForDirectPlay(item, GetBitrateForDirectPlayCheck(item, options)))
			{
				playMethods.add(PlayMethod.DirectPlay);
			}
		}

		return playMethods;
	}

	private Integer GetDefaultSubtitleStreamIndex(MediaSourceInfo item, SubtitleProfile[] subtitleProfiles)
	{
		int highestScore = -1;

		for (MediaStream stream : item.getMediaStreams())
		{
			if (stream.getType() == MediaStreamType.Subtitle && stream.getScore() != null)
			{
				if (stream.getScore() > highestScore)
				{
					highestScore = stream.getScore();
				}
			}
		}

		java.util.ArrayList<MediaStream> topStreams = new java.util.ArrayList<MediaStream>();
		for (MediaStream stream : item.getMediaStreams())
		{
			if (stream.getType() == MediaStreamType.Subtitle && stream.getScore() != null && stream.getScore() == highestScore)
			{
				topStreams.add(stream);
			}
		}

		// If multiple streams have an equal score, try to pick the most efficient one
		if (topStreams.size() > 1)
		{
			for (MediaStream stream : topStreams)
			{
				for (SubtitleProfile profile : subtitleProfiles)
				{
					if (profile.getMethod() == SubtitleDeliveryMethod.External && StringHelper.EqualsIgnoreCase(profile.getFormat(), stream.getCodec()))
					{
						return stream.getIndex();
					}
				}
			}
		}

		// If no optimization panned out, just use the original default
		return item.getDefaultSubtitleStreamIndex();
	}

	private StreamInfo BuildVideoItem(MediaSourceInfo item, VideoOptions options)
	{
		StreamInfo tempVar = new StreamInfo();
		tempVar.setItemId(options.getItemId());
		tempVar.setMediaType(DlnaProfileType.Video);
		tempVar.setMediaSource(item);
		tempVar.setRunTimeTicks(item.getRunTimeTicks());
		tempVar.setContext(options.getContext());
		tempVar.setDeviceProfile(options.getProfile());
		StreamInfo playlistItem = tempVar;

		Integer tempVar2 = options.getSubtitleStreamIndex();
		playlistItem.setSubtitleStreamIndex((tempVar2 != null) ? tempVar2 : GetDefaultSubtitleStreamIndex(item, options.getProfile().getSubtitleProfiles()));
		MediaStream subtitleStream = playlistItem.getSubtitleStreamIndex() != null ? item.GetMediaStream(MediaStreamType.Subtitle, playlistItem.getSubtitleStreamIndex()) : null;

		Integer tempVar3 = options.getAudioStreamIndex();
		MediaStream audioStream = item.GetDefaultAudioStream((tempVar3 != null) ? tempVar3 : item.getDefaultAudioStreamIndex());
		Integer audioStreamIndex = null;
		if (audioStream != null)
		{
			audioStreamIndex = audioStream.getIndex();
		}

		MediaStream videoStream = item.getVideoStream();

		// TODO: This doesn't accout for situation of device being able to handle media bitrate, but wifi connection not fast enough
		boolean isEligibleForDirectPlay = IsEligibleForDirectPlay(item, GetBitrateForDirectPlayCheck(item, options), subtitleStream, options);
		boolean isEligibleForDirectStream = IsEligibleForDirectPlay(item, options.GetMaxBitrate(), subtitleStream, options);

		String tempVar4 = options.getProfile().getName();
		String tempVar5 = item.getPath();
		_logger.Debug("Profile: {0}, Path: {1}, isEligibleForDirectPlay: {2}, isEligibleForDirectStream: {3}", (tempVar4 != null) ? tempVar4 : "Unknown Profile", (tempVar5 != null) ? tempVar5 : "Unknown path", isEligibleForDirectPlay, isEligibleForDirectStream);

		if (isEligibleForDirectPlay || isEligibleForDirectStream)
		{
			// See if it can be direct played
			PlayMethod directPlay = GetVideoDirectPlayProfile(options.getProfile(), item, videoStream, audioStream, isEligibleForDirectPlay, isEligibleForDirectStream);

			if (directPlay != null)
			{
				playlistItem.setPlayMethod(directPlay);
				playlistItem.setContainer(item.getContainer());

				if (subtitleStream != null)
				{
					SubtitleProfile subtitleProfile = GetSubtitleProfile(subtitleStream, options.getProfile().getSubtitleProfiles(), options.getContext());

					playlistItem.setSubtitleDeliveryMethod(subtitleProfile.getMethod());
					playlistItem.setSubtitleFormat(subtitleProfile.getFormat());
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
			if (!item.getSupportsTranscoding())
			{
				return null;
			}

			if (subtitleStream != null)
			{
				SubtitleProfile subtitleProfile = GetSubtitleProfile(subtitleStream, options.getProfile().getSubtitleProfiles(), options.getContext());

				playlistItem.setSubtitleDeliveryMethod(subtitleProfile.getMethod());
				playlistItem.setSubtitleFormat(subtitleProfile.getFormat());
			}

			playlistItem.setPlayMethod(PlayMethod.Transcode);
			playlistItem.setContainer(transcodingProfile.getContainer());
			playlistItem.setEstimateContentLength(transcodingProfile.getEstimateContentLength());
			playlistItem.setTranscodeSeekInfo(transcodingProfile.getTranscodeSeekInfo());
			playlistItem.setAudioCodec(transcodingProfile.getAudioCodec().split("[,]", -1)[0]);
			playlistItem.setVideoCodec(transcodingProfile.getVideoCodec());
			playlistItem.setSubProtocol(transcodingProfile.getProtocol());
			playlistItem.setAudioStreamIndex(audioStreamIndex);

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
				Integer tempVar6 = playlistItem.getMaxAudioChannels();
				int currentValue = (tempVar6 != null) ? tempVar6 : options.getMaxAudioChannels();

				playlistItem.setMaxAudioChannels(Math.min(options.getMaxAudioChannels(), currentValue));
			}

			if (playlistItem.getAudioBitrate() == null)
			{
				playlistItem.setAudioBitrate(GetAudioBitrate(playlistItem.getTargetAudioChannels(), playlistItem.getTargetAudioCodec()));
			}

			Integer maxBitrateSetting = options.GetMaxBitrate();
			// Honor max rate
			if (maxBitrateSetting != null)
			{
				int videoBitrate = maxBitrateSetting;

				if (playlistItem.getAudioBitrate() != null)
				{
					videoBitrate -= playlistItem.getAudioBitrate();
				}

				Integer tempVar7 = playlistItem.getVideoBitrate();
				int currentValue = (tempVar7 != null) ? tempVar7 : videoBitrate;

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

	private PlayMethod GetVideoDirectPlayProfile(DeviceProfile profile, MediaSourceInfo mediaSource, MediaStream videoStream, MediaStream audioStream, boolean isEligibleForDirectPlay, boolean isEligibleForDirectStream)
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
			String tempVar = profile.getName();
			String tempVar2 = mediaSource.getPath();
			_logger.Debug("Profile: {0}, No direct play profiles found for Path: {1}", (tempVar != null) ? tempVar : "Unknown Profile", (tempVar2 != null) ? tempVar2 : "Unknown path");

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
		Float tempVar3 = videoStream.getAverageFrameRate();
		Float videoFramerate = videoStream == null ? null : (tempVar3 != null) ? tempVar3 : videoStream.getAverageFrameRate();
		Boolean isAnamorphic = videoStream == null ? null : videoStream.getIsAnamorphic();
		Boolean isCabac = videoStream == null ? null : videoStream.getIsCabac();

		Integer audioBitrate = audioStream == null ? null : audioStream.getBitRate();
		Integer audioChannels = audioStream == null ? null : audioStream.getChannels();
		String audioProfile = audioStream == null ? null : audioStream.getProfile();

		TransportStreamTimestamp timestamp = videoStream == null ? TransportStreamTimestamp.None : mediaSource.getTimestamp();
		Integer packetLength = videoStream == null ? null : videoStream.getPacketLength();
		Integer refFrames = videoStream == null ? null : videoStream.getRefFrames();

		Integer numAudioStreams = mediaSource.GetStreamCount(MediaStreamType.Audio);
		Integer numVideoStreams = mediaSource.GetStreamCount(MediaStreamType.Video);

		// Check container conditions
		for (ProfileCondition i : conditions)
		{
			if (!conditionProcessor.IsVideoConditionSatisfied(i, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp, isAnamorphic, isCabac, refFrames, numVideoStreams, numAudioStreams))
			{
				LogConditionFailure(profile, "VideoContainerProfile", i, mediaSource);

				return null;
			}
		}

		String videoCodec = videoStream == null ? null : videoStream.getCodec();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(videoCodec))
		{
			String tempVar4 = profile.getName();
			String tempVar5 = mediaSource.getPath();
			_logger.Debug("Profile: {0}, DirectPlay=false. Reason=Unknown video codec. Path: {1}", (tempVar4 != null) ? tempVar4 : "Unknown Profile", (tempVar5 != null) ? tempVar5 : "Unknown path");

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
			if (!conditionProcessor.IsVideoConditionSatisfied(i, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp, isAnamorphic, isCabac, refFrames, numVideoStreams, numAudioStreams))
			{
				LogConditionFailure(profile, "VideoCodecProfile", i, mediaSource);

				return null;
			}
		}

		if (audioStream != null)
		{
			String audioCodec = audioStream.getCodec();

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec))
			{
				String tempVar6 = profile.getName();
				String tempVar7 = mediaSource.getPath();
				_logger.Debug("Profile: {0}, DirectPlay=false. Reason=Unknown audio codec. Path: {1}", (tempVar6 != null) ? tempVar6 : "Unknown Profile", (tempVar7 != null) ? tempVar7 : "Unknown path");

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
				Boolean isSecondaryAudio = audioStream == null ? null : mediaSource.IsSecondaryAudio(audioStream);
				if (!conditionProcessor.IsVideoAudioConditionSatisfied(i, audioChannels, audioBitrate, audioProfile, isSecondaryAudio))
				{
					LogConditionFailure(profile, "VideoAudioCodecProfile", i, mediaSource);

					return null;
				}
			}
		}

		if (isEligibleForDirectPlay && mediaSource.getSupportsDirectPlay())
		{
			if (mediaSource.getProtocol() == MediaProtocol.Http)
			{
				if (_localPlayer.CanAccessUrl(mediaSource.getPath(), mediaSource.getRequiredHttpHeaders().size() > 0))
				{
					return PlayMethod.DirectPlay;
				}
			}

			else if (mediaSource.getProtocol() == MediaProtocol.File)
			{
				if (_localPlayer.CanAccessFile(mediaSource.getPath()))
				{
					return PlayMethod.DirectPlay;
				}
			}
		}

		if (isEligibleForDirectStream && mediaSource.getSupportsDirectStream())
		{
			return PlayMethod.DirectStream;
		}

		return null;
	}

	private void LogConditionFailure(DeviceProfile profile, String type, ProfileCondition condition, MediaSourceInfo mediaSource)
	{
		String tempVar = profile.getName();
		String tempVar2 = condition.getValue();
		String tempVar3 = mediaSource.getPath();
		_logger.Debug("Profile: {0}, DirectPlay=false. Reason={1}.{2} Condition: {3}. ConditionValue: {4}. IsRequired: {5}. Path: {6}", type, (tempVar != null) ? tempVar : "Unknown Profile", condition.getProperty(), condition.getCondition(), (tempVar2 != null) ? tempVar2 : "", condition.getIsRequired(), (tempVar3 != null) ? tempVar3 : "Unknown path");
	}

	private boolean IsEligibleForDirectPlay(MediaSourceInfo item, Integer maxBitrate, MediaStream subtitleStream, VideoOptions options)
	{
		if (subtitleStream != null)
		{
			SubtitleProfile subtitleProfile = GetSubtitleProfile(subtitleStream, options.getProfile().getSubtitleProfiles(), options.getContext());

			if (subtitleProfile.getMethod() != SubtitleDeliveryMethod.External && subtitleProfile.getMethod() != SubtitleDeliveryMethod.Embed)
			{
				return false;
			}
		}

		return IsAudioEligibleForDirectPlay(item, maxBitrate);
	}

	public static SubtitleProfile GetSubtitleProfile(MediaStream subtitleStream, SubtitleProfile[] subtitleProfiles, EncodingContext context)
	{
		// Look for an external profile that matches the stream type (text/graphical)
		for (SubtitleProfile profile : subtitleProfiles)
		{
			boolean requiresConversion = !StringHelper.EqualsIgnoreCase(subtitleStream.getCodec(), profile.getFormat());

			if (!profile.SupportsLanguage(subtitleStream.getLanguage()))
			{
				continue;
			}

			if (profile.getMethod() == SubtitleDeliveryMethod.External && subtitleStream.getIsTextSubtitleStream() == MediaStream.IsTextFormat(profile.getFormat()))
			{
				if (!requiresConversion)
				{
					return profile;
				}

				if (subtitleStream.getSupportsExternalStream())
				{
					return profile;
				}

				// For sync we can handle the longer extraction times
				if (context.getValue() == EncodingContext.Static.getValue() && subtitleStream.getIsTextSubtitleStream())
				{
					return profile;
				}
			}
		}

		for (SubtitleProfile profile : subtitleProfiles)
		{
			boolean requiresConversion = !StringHelper.EqualsIgnoreCase(subtitleStream.getCodec(), profile.getFormat());

			if (!profile.SupportsLanguage(subtitleStream.getLanguage()))
			{
				continue;
			}

			if (profile.getMethod() == SubtitleDeliveryMethod.Embed && subtitleStream.getIsTextSubtitleStream() == MediaStream.IsTextFormat(profile.getFormat()))
			{
				if (!requiresConversion)
				{
					return profile;
				}

				return profile;
			}
		}

		SubtitleProfile tempVar = new SubtitleProfile();
		tempVar.setMethod(SubtitleDeliveryMethod.Encode);
		tempVar.setFormat(subtitleStream.getCodec());
		return tempVar;
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

			// No way to express this
			if (condition.getCondition() == ProfileConditionType.GreaterThanEqual)
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
				case IsCabac:
				{
						boolean val = false;
						tangible.RefObject<Boolean> tempRef_val = new tangible.RefObject<Boolean>(val);
						boolean tempVar3 = BoolHelper.TryParseCultureInvariant(value, tempRef_val);
							val = tempRef_val.argValue;
						if (tempVar3)
						{
							if (condition.getCondition() == ProfileConditionType.Equals)
							{
								item.setCabac(val);
							}
							else if (condition.getCondition() == ProfileConditionType.NotEquals)
							{
								item.setCabac(!val);
							}
						}
						break;
				}
				case IsAnamorphic:
				case AudioProfile:
				case Has64BitOffsets:
				case PacketLength:
				case NumAudioStreams:
				case NumVideoStreams:
				case IsSecondaryAudio:
				case VideoTimestamp:
				{
						// Not supported yet
						break;
				}
				case RefFrames:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num3 = new tangible.RefObject<Integer>(num);
						boolean tempVar4 = IntHelper.TryParseCultureInvariant(value, tempRef_num3);
							num = tempRef_num3.argValue;
						if (tempVar4)
						{
							item.setMaxRefFrames(num);
						}
						break;
				}
				case VideoBitDepth:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num4 = new tangible.RefObject<Integer>(num);
						boolean tempVar5 = IntHelper.TryParseCultureInvariant(value, tempRef_num4);
							num = tempRef_num4.argValue;
						if (tempVar5)
						{
							item.setMaxVideoBitDepth(num);
						}
						break;
				}
				case VideoProfile:
				{
						item.setVideoProfile(((value != null) ? value : "").split("[|]", -1)[0]);
						break;
				}
				case Height:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num5 = new tangible.RefObject<Integer>(num);
						boolean tempVar6 = IntHelper.TryParseCultureInvariant(value, tempRef_num5);
							num = tempRef_num5.argValue;
						if (tempVar6)
						{
							item.setMaxHeight(num);
						}
						break;
				}
				case VideoBitrate:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num6 = new tangible.RefObject<Integer>(num);
						boolean tempVar7 = IntHelper.TryParseCultureInvariant(value, tempRef_num6);
							num = tempRef_num6.argValue;
						if (tempVar7)
						{
							item.setVideoBitrate(num);
						}
						break;
				}
				case VideoFramerate:
				{
						float num = 0F;
						tangible.RefObject<Float> tempRef_num7 = new tangible.RefObject<Float>(num);
						boolean tempVar8 = FloatHelper.TryParseCultureInvariant(value, tempRef_num7);
							num = tempRef_num7.argValue;
						if (tempVar8)
						{
							item.setMaxFramerate(num);
						}
						break;
				}
				case VideoLevel:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num8 = new tangible.RefObject<Integer>(num);
						boolean tempVar9 = IntHelper.TryParseCultureInvariant(value, tempRef_num8);
							num = tempRef_num8.argValue;
						if (tempVar9)
						{
							item.setVideoLevel(num);
						}
						break;
				}
				case Width:
				{
						int num = 0;
						tangible.RefObject<Integer> tempRef_num9 = new tangible.RefObject<Integer>(num);
						boolean tempVar10 = IntHelper.TryParseCultureInvariant(value, tempRef_num9);
							num = tempRef_num9.argValue;
						if (tempVar10)
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