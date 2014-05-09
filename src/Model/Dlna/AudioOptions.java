package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Dto.*;

/** 
 Class AudioOptions.
*/
public class AudioOptions
{
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}
	private java.util.ArrayList<MediaSourceInfo> privateMediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return privateMediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		privateMediaSources = value;
	}
	private DeviceProfile privateProfile;
	public final DeviceProfile getProfile()
	{
		return privateProfile;
	}
	public final void setProfile(DeviceProfile value)
	{
		privateProfile = value;
	}

	/** 
	 Optional. Only needed if a specific AudioStreamIndex or SubtitleStreamIndex are requested.
	*/
	private String privateMediaSourceId;
	public final String getMediaSourceId()
	{
		return privateMediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		privateMediaSourceId = value;
	}

	private String privateDeviceId;
	public final String getDeviceId()
	{
		return privateDeviceId;
	}
	public final void setDeviceId(String value)
	{
		privateDeviceId = value;
	}

	/** 
	 Allows an override of supported number of audio channels
	 Example: DeviceProfile supports five channel, but user only has stereo speakers
	*/
	private Integer privateMaxAudioChannels = new Integer();
	public final Integer getMaxAudioChannels()
	{
		return privateMaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		privateMaxAudioChannels = value;
	}

	/** 
	 The application's configured quality setting
	*/
	private Integer privateMaxBitrate = new Integer();
	public final Integer getMaxBitrate()
	{
		return privateMaxBitrate;
	}
	public final void setMaxBitrate(Integer value)
	{
		privateMaxBitrate = value;
	}
}