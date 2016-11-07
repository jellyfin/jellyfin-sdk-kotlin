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

	private Integer MaxStreamingBitrate;
	public final Integer getMaxStreamingBitrate()
	{
		return MaxStreamingBitrate;
	}
	public final void setMaxStreamingBitrate(Integer value)
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

	private String MediaSourceId;
	public final String getMediaSourceId()
	{
		return MediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		MediaSourceId = value;
	}

	private Boolean EnableDirectStream;
	public final Boolean getEnableDirectStream()
	{
		return EnableDirectStream;
	}
	public final void setEnableDirectStream(Boolean value)
	{
		EnableDirectStream = value;
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

	private Integer MaxAudioChannels;
	public final Integer getMaxAudioChannels() {
		return MaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value) {
		MaxAudioChannels = value;
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
}