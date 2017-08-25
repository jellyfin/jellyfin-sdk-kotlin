package mediabrowser.model.mediainfo;

import mediabrowser.model.dlna.*;

public class LiveStreamRequest
{
	private String OpenToken;
	public final String getOpenToken()
	{
		return OpenToken;
	}
	public final void setOpenToken(String value)
	{
		OpenToken = value;
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
	private String PlaySessionId;
	public final String getPlaySessionId()
	{
		return PlaySessionId;
	}
	public final void setPlaySessionId(String value)
	{
		PlaySessionId = value;
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
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
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