package mediabrowser.model.mediainfo;

import mediabrowser.model.dlna.*;

public class PlaybackInfoRequest
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	private Long MaxStreamingBitrate;
	public final Long getMaxStreamingBitrate()
	{
		return MaxStreamingBitrate;
	}
	public final void setMaxStreamingBitrate(Long value)
	{
		MaxStreamingBitrate = value;
	}

	private Long StartTimeTicks;
	public final Long getStartTimeTicks()
	{
		return StartTimeTicks;
	}
	public final void setStartTimeTicks(Long value)
	{
		StartTimeTicks = value;
	}

	private Integer AudioStreamIndex;
	public final Integer getAudioStreamIndex()
	{
		return AudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		AudioStreamIndex = value;
	}

	private Integer SubtitleStreamIndex;
	public final Integer getSubtitleStreamIndex()
	{
		return SubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		SubtitleStreamIndex = value;
	}

	private Integer MaxAudioChannels;
	public final Integer getMaxAudioChannels()
	{
		return MaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		MaxAudioChannels = value;
	}

	private String MediaSourceId;
	public final String getMediaSourceId()
	{
		return MediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		MediaSourceId = value;
	}

	private String LiveStreamId;
	public final String getLiveStreamId()
	{
		return LiveStreamId;
	}
	public final void setLiveStreamId(String value)
	{
		LiveStreamId = value;
	}

	private DeviceProfile DeviceProfile;
	public final DeviceProfile getDeviceProfile()
	{
		return DeviceProfile;
	}
	public final void setDeviceProfile(DeviceProfile value)
	{
		DeviceProfile = value;
	}

	private boolean EnableDirectPlay;
	public final boolean getEnableDirectPlay()
	{
		return EnableDirectPlay;
	}
	public final void setEnableDirectPlay(boolean value)
	{
		EnableDirectPlay = value;
	}
	private boolean AutoOpenLiveStream;
	public final boolean getAutoOpenLiveStream()
	{
		return AutoOpenLiveStream;
	}
	public final void setAutoOpenLiveStream(boolean value)
	{
		AutoOpenLiveStream = value;
	}
	private boolean EnableDirectStream;
	public final boolean getEnableDirectStream()
	{
		return EnableDirectStream;
	}
	public final void setEnableDirectStream(boolean value)
	{
		EnableDirectStream = value;
	}
	private boolean EnableTranscoding;
	public final boolean getEnableTranscoding()
	{
		return EnableTranscoding;
	}
	public final void setEnableTranscoding(boolean value)
	{
		EnableTranscoding = value;
	}
	private boolean ForceDirectPlayRemoteMediaSource;
	public final boolean getForceDirectPlayRemoteMediaSource()
	{
		return ForceDirectPlayRemoteMediaSource;
	}
	public final void setForceDirectPlayRemoteMediaSource(boolean value)
	{
		ForceDirectPlayRemoteMediaSource = value;
	}
	private boolean AllowVideoSreamCopy;
	public final boolean getAllowVideoStreamCopy()
	{
		return AllowVideoSreamCopy;
	}
	public final void setAllowVideoSreamCopy(boolean value)
	{
		AllowVideoSreamCopy = value;
	}

	private boolean AllowAudioSreamCopy;
	public final boolean getAllowAudioStreamCopy()
	{
		return AllowAudioSreamCopy;
	}
	public final void setAllowAudioSreamCopy(boolean value)
	{
		AllowAudioSreamCopy = value;
	}
	public PlaybackInfoRequest()
	{
		setForceDirectPlayRemoteMediaSource(true);
		setEnableDirectPlay(true);
		setEnableDirectStream(true);
		setEnableTranscoding(true);
	}
}